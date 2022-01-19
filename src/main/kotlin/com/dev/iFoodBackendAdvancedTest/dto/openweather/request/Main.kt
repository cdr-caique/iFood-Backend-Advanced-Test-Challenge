package com.dev.iFoodBackendAdvancedTest.dto.openweather.request

import com.fasterxml.jackson.annotation.JsonProperty

data class Main(
    val temp: Double,
    @JsonProperty("feels_like") val feels_like: Double,
    @JsonProperty("temp_min") val temp_min: Double,
    @JsonProperty("temp_max") val temp_max: Double,
    val pressure: Int,
    val humidity: Int
)
