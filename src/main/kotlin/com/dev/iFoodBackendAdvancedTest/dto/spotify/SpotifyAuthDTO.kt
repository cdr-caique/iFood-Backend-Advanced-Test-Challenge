package com.dev.iFoodBackendAdvancedTest.dto.spotify

import com.fasterxml.jackson.annotation.JsonProperty

data class SpotifyAuthDTO(
    @JsonProperty("access_token") val accessToken: String,
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("expires_in") val expiresIn: Int
)
