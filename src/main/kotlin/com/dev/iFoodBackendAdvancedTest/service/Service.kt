package com.dev.iFoodBackendAdvancedTest.service

import com.dev.iFoodBackendAdvancedTest.client.openweather.OpenWeatherClient
import com.dev.iFoodBackendAdvancedTest.client.spotify.SpotifyAccountsClient
import com.dev.iFoodBackendAdvancedTest.client.spotify.SpotifyApiClient
import com.dev.iFoodBackendAdvancedTest.dto.ResponseDTO
import com.dev.iFoodBackendAdvancedTest.dto.openweather.request.OpenWeatherRequestDTO
import com.dev.iFoodBackendAdvancedTest.dto.openweather.response.OpenWeatherResponseDTO
import com.dev.iFoodBackendAdvancedTest.dto.openweather.response.WeatherResponse
import com.dev.iFoodBackendAdvancedTest.dto.spotify.SpotifyAuthDTO
import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.request.SpotifyPlaylistRequestDTO
import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.response.PlaylistsResponse
import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.response.SpotifyPlaylistResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils

@Service
class Service(
    private val openWeatherClient: OpenWeatherClient,
    private val spotifyAccountsClient: SpotifyAccountsClient,
    private val spotifyApiClient: SpotifyApiClient,
    @Value("\${open-weather.app-id}") private val openWeatherAppId: String,
    @Value("\${spotify.clientId}") private val spotifyClientId: String,
    @Value("\${spotify.clientSecret}") private val spotifyClientSecret: String
) {

    private var tempCelsius = 0.0

    fun getSpotifyPlaylistByCityWeather(name: String): ResponseDTO {
        val weatherResponse = getCurrentWeatherByCityName(name)
        val genre = getGenreByWeatherResponse()
        val bearerToken = getAccessToken().accessToken
        val spotifyClientResponse = spotifyApiClient.getPlaylistByGenreAndType(
            bearerToken,
            genre,
            "playlist"
        )
        val spotifyResponse = mapSpotifyResponse(spotifyClientResponse)

        return ResponseDTO(weatherResponse, spotifyResponse)
    }

    fun getSpotifyPlaylistByCoordsWeather(lat: Double, lon: Double): ResponseDTO {
        val weatherResponse = getCurrentWeatherByCoordinates(lat, lon)
        val genre = getGenreByWeatherResponse()
        val bearerToken = getAccessToken().accessToken
        val spotifyClientResponse = spotifyApiClient.getPlaylistByGenreAndType(
            bearerToken,
            genre,
            "playlist"
        )
        val spotifyResponse = mapSpotifyResponse(spotifyClientResponse)

        return ResponseDTO(weatherResponse, spotifyResponse)
    }

    private fun getCurrentWeatherByCityName(name: String): OpenWeatherResponseDTO {
        val weatherDto = openWeatherClient.getCurrentWeatherByCityName(name, openWeatherAppId)
        return mapWeatherResponse(weatherDto, "city")
    }

    private fun getCurrentWeatherByCoordinates(lat: Double, lon: Double): OpenWeatherResponseDTO {
        val weatherDto = openWeatherClient.getCurrentWeatherByCoordinates(lat, lon, openWeatherAppId)
        return mapWeatherResponse(weatherDto, "coord")
    }

    private fun getGenreByWeatherResponse(): String {
        if (tempCelsius > 30.0) {
            return "party"
        } else if (tempCelsius > 15.0) {
            return "pop"
        } else if (tempCelsius > 10.0) {
            return "rock"
        } else return "classical"
    }

    private fun getAccessToken(): SpotifyAuthDTO {
        val encodedBytes = Base64Utils.encode("$spotifyClientId:$spotifyClientSecret".toByteArray())
        val basicToken = String(encodedBytes)

        return spotifyAccountsClient.getSpotifyToken(
            basicToken,
            "client_credentials"
        )
    }

    private fun mapSpotifyResponse(body: SpotifyPlaylistRequestDTO): SpotifyPlaylistResponseDTO {
        val response = SpotifyPlaylistResponseDTO(
            mutableListOf<PlaylistsResponse>()
        )

        for (item in body.playlists.items) {
            val playlist = PlaylistsResponse(
                item.name,
                item.description,
                item.externalUrls.spotify
            )
            response.playlists.add(playlist)
        }

        return response
    }

    private fun mapWeatherResponse(body: OpenWeatherRequestDTO, method: String): OpenWeatherResponseDTO {
        val weatherResponse = mutableListOf<WeatherResponse>()

        for (item in body.weather) {
            val weather = WeatherResponse(
                description = item.main + " / " + item.description
            )
            weatherResponse.add(weather)
        }

        tempCelsius = convertKelvinToCelsius(body.main.temp)

        if (method == "city") {
            return OpenWeatherResponseDTO(
                city = body.name,
                country = body.sys.country,
                lon = body.coord.lon,
                lat = body.coord.lat,
                temp = body.main.temp.toString() + " K / " + tempCelsius.toString() + " °C",
                weather = weatherResponse
            )
        } else if (method == "coord") {
            return OpenWeatherResponseDTO(
                city = body.name,
                country = body.sys.country,
                lon = body.coord.lon,
                lat = body.coord.lat,
                temp = body.main.temp.toString() + " K / " + tempCelsius.toString() + " °C",
                weather = weatherResponse
            )
        } else return OpenWeatherResponseDTO(
            city = "",
            country = "",
            lon = 0.0,
            lat = 0.0,
            temp = "No Information",
            weather = mutableListOf()
        )
    }

    private fun convertKelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

}
