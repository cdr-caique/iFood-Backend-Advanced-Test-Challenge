package com.dev.iFoodBackendAdvancedTest.client.openweather

import com.dev.iFoodBackendAdvancedTest.dto.openweather.request.OpenWeatherRequestDTO
import feign.Headers
import feign.Param
import feign.RequestLine

@Headers(
    "Accept: application/json",
    "Content-Type: application/json"
)
interface OpenWeatherClient {

    @RequestLine("GET /data/2.5/weather?q={q}&appid={appid}")
    fun getCurrentWeatherByCityName(
        @Param("q") name: String,
        @Param("appid") appId: String
    ): OpenWeatherRequestDTO

    @RequestLine("GET /data/2.5/weather?lat={lat}&lon={lon}&appid={appid}")
    fun getCurrentWeatherByCoordinates(
        @Param("lat") lat: Double,
        @Param("lon") lon: Double,
        @Param("appid") appId: String
    ): OpenWeatherRequestDTO

}
