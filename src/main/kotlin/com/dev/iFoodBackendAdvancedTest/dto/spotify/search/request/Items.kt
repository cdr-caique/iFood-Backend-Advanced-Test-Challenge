package com.dev.iFoodBackendAdvancedTest.dto.spotify.search.request

import com.fasterxml.jackson.annotation.JsonProperty

data class Items(
    val name: String,
    val description: String?,
    @JsonProperty("external_urls") val externalUrls: ExternalUrls
)
