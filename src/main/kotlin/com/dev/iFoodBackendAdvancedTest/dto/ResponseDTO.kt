package com.dev.iFoodBackendAdvancedTest.dto

import com.dev.iFoodBackendAdvancedTest.dto.openweather.response.OpenWeatherResponseDTO
import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.response.SpotifyPlaylistResponseDTO

data class ResponseDTO(
    val openWeather: OpenWeatherResponseDTO,
    val spotify: SpotifyPlaylistResponseDTO
)
