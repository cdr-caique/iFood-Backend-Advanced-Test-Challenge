package com.dev.iFoodBackendAdvancedTest.client.spotify

import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.request.SpotifyPlaylistRequestDTO
import feign.Headers
import feign.Param
import feign.RequestLine

@Headers(
    "Accept: application/json",
    "Content-Type: application/json",
    "Authorization: Bearer {token}"
)
interface SpotifyApiClient {

    @RequestLine("GET /v1/search?q={q}&type={type}")
    fun getPlaylistByGenreAndType(
        @Param("token") bearerToken: String,
        @Param("q") genre: String,
        @Param("type") type: String
    ): SpotifyPlaylistRequestDTO

}
