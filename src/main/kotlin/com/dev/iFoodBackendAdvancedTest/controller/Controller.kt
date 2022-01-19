package com.dev.iFoodBackendAdvancedTest.controller

import com.dev.iFoodBackendAdvancedTest.dto.ResponseDTO
import com.dev.iFoodBackendAdvancedTest.service.Service
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping
class Controller(
    private val service: Service
) {

    @GetMapping("/city")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Suggest Spotify playlist by city current weather")
    fun getSpotifyPlaylistByCityWeather(
        @RequestParam name: String
    ): ResponseEntity<ResponseDTO> = ResponseEntity.ok(
        service.getSpotifyPlaylistByCityWeather(name)
    )

    @GetMapping("/coordinates")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Suggest Spotify playlist by coordinates current weather")
    fun getSpotifyPlaylistByCoordsWeather(
        @RequestParam lat: Double,
        @RequestParam lon: Double
    ): ResponseEntity<ResponseDTO> = ResponseEntity.ok(
        service.getSpotifyPlaylistByCoordsWeather(lat, lon)
    )

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Test Open Weather Controller")
    fun getTestResponse(): String {
        return "Ok"
    }

}
