package com.dev.iFoodBackendAdvancedTest.service

import com.dev.iFoodBackendAdvancedTest.client.openweather.OpenWeatherClient
import com.dev.iFoodBackendAdvancedTest.dto.openweather.request.OpenWeatherRequestDTO
import com.dev.iFoodBackendAdvancedTest.dto.openweather.response.OpenWeatherResponseDTO
import com.dev.iFoodBackendAdvancedTest.dto.openweather.response.WeatherResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OpenWeatherService(
    private val openWeatherClient: OpenWeatherClient,
    @Value("\${open-weather.app-id}") private val openWeatherAppId: String
) {

    fun getCurrentWeatherByCityName(name: String): OpenWeatherResponseDTO {
        val weatherDto = openWeatherClient.getCurrentWeatherByCityName(name, openWeatherAppId)
        return mapWeatherResponse(weatherDto, "city")
    }

    fun getCurrentWeatherByCoordinates(lat: Double, lon: Double): OpenWeatherResponseDTO {
        val weatherDto = openWeatherClient.getCurrentWeatherByCoordinates(lat, lon, openWeatherAppId)
        return mapWeatherResponse(weatherDto, "coord")
    }

    private fun mapWeatherResponse(body: OpenWeatherRequestDTO, method: String): OpenWeatherResponseDTO {
        val weatherResponse = mutableListOf<WeatherResponse>()

        for (item in body.weather) {
            val weather = WeatherResponse(
                description = item.main + " / " + item.description
            )
            weatherResponse.add(weather)
        }

        if (method == "city") {
            return OpenWeatherResponseDTO(
                city = body.name,
                country = body.sys.country,
                lon = body.coord.lon,
                lat = body.coord.lat,
                temp = body.main.temp.toString() + " K / " + convertKelvinToCelsius(body.main.temp).toString() + " °C",
                weather = weatherResponse
            )
        } else if (method == "coord") {
            return OpenWeatherResponseDTO(
                city = body.name,
                country = body.sys.country,
                lon = body.coord.lon,
                lat = body.coord.lat,
                temp = body.main.temp.toString() + " K / " + convertKelvinToCelsius(body.main.temp).toString() + " °C",
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
