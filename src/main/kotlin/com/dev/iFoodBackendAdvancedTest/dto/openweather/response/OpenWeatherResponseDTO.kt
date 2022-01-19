package com.dev.iFoodBackendAdvancedTest.dto.openweather.response

data class OpenWeatherResponseDTO(
    val city: String,
    val country: String,
    val lon: Double,
    val lat: Double,
    // val temp: Double,
    val temp: String,
    val weather: MutableList<WeatherResponse>
)
