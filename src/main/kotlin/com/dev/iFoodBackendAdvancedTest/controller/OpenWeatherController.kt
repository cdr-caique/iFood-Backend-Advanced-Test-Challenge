package com.dev.iFoodBackendAdvancedTest.controller

import com.dev.iFoodBackendAdvancedTest.dto.openweather.response.OpenWeatherResponseDTO
import com.dev.iFoodBackendAdvancedTest.service.OpenWeatherService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/weather")
class OpenWeatherController(
    private val openWeatherService: OpenWeatherService
) {

    @GetMapping("/city")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get current weather by city name")
    fun getCurrentWeatherByCityName(
        @RequestParam name: String
    ): ResponseEntity<OpenWeatherResponseDTO> = ResponseEntity.ok(
        openWeatherService.getCurrentWeatherByCityName(name)
    )

    @GetMapping("/coordinates")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get current weather by coordinates")
    fun getCurrentWeatherByCoordinates(
        @RequestParam lat: Double,
        @RequestParam lon: Double
    ): ResponseEntity<OpenWeatherResponseDTO> = ResponseEntity.ok(
        openWeatherService.getCurrentWeatherByCoordinates(lat, lon)
    )

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Test Open Weather Controller")
    fun getTestResponse(): String {
        return "Ok"
    }

}
