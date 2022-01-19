package com.dev.iFoodBackendAdvancedTest.dto.openweather.request

data class OpenWeatherRequestDTO(
    val name: String,
    val sys: Sys,
    val coord: Coord,
    val main: Main,
    val weather: List<WeatherRequest>
)
