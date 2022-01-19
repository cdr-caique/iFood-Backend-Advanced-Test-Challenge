package com.dev.iFoodBackendAdvancedTest.controller

import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.response.SpotifyPlaylistResponseDTO
import com.dev.iFoodBackendAdvancedTest.service.SpotifyService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/spotify")
class SpotifyController(
    private val spotifyService: SpotifyService
) {

    @GetMapping("/v1/search")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Search Spotify Playlist")
    fun getPlaylist(
        @RequestParam("q") genre: String,
        @RequestParam("type") type: String
    ): ResponseEntity<SpotifyPlaylistResponseDTO> = ResponseEntity.ok(
        spotifyService.getPlaylist(genre, type)
    )

    @GetMapping("/test")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Test Spotify Controller")
    fun test(): String {
        return "ok"
    }

}
