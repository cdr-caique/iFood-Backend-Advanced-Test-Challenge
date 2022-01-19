package com.dev.iFoodBackendAdvancedTest.client.spotify

import com.dev.iFoodBackendAdvancedTest.dto.spotify.SpotifyAuthDTO
import feign.Headers
import feign.Param
import feign.RequestLine

@Headers(
    "Accept: application/json",
    "Content-Type: application/x-www-form-urlencoded",
    "Authorization: Basic {token}"
)
interface SpotifyAccountsClient {

    @RequestLine("POST /api/token")
    fun getSpotifyToken(
        @Param("token") basicToken: String,
        @Param("grant_type") grantType: String,
    ): SpotifyAuthDTO

}
