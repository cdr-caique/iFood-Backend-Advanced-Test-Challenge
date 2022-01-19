package com.dev.iFoodBackendAdvancedTest.service

import com.dev.iFoodBackendAdvancedTest.client.spotify.SpotifyAccountsClient
import com.dev.iFoodBackendAdvancedTest.client.spotify.SpotifyApiClient
import com.dev.iFoodBackendAdvancedTest.dto.spotify.SpotifyAuthDTO
import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.request.SpotifyPlaylistRequestDTO
import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.response.PlaylistsResponse
import com.dev.iFoodBackendAdvancedTest.dto.spotify.search.response.SpotifyPlaylistResponseDTO
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.util.Base64Utils

@Service
class SpotifyService(
    private val spotifyAccountsClient: SpotifyAccountsClient,
    private val spotifyApiClient: SpotifyApiClient,
    @Value("\${spotify.clientId}") private val spotifyClientId: String,
    @Value("\${spotify.clientSecret}") private val spotifyClientSecret: String
) {

    fun getAccessToken(): SpotifyAuthDTO {
        val encodedBytes = Base64Utils.encode("$spotifyClientId:$spotifyClientSecret".toByteArray())
        val basicToken = String(encodedBytes)

        return spotifyAccountsClient.getSpotifyToken(
            basicToken,
            "client_credentials"
        )
    }

    fun getPlaylist(genre: String, type: String): SpotifyPlaylistResponseDTO {
        val bearerToken = getAccessToken().accessToken
        val spotifyClientResponse = spotifyApiClient.getPlaylistByGenreAndType(
            bearerToken,
            genre,
            type
        )

        return mapSpotifyResponse(spotifyClientResponse)
    }

    fun mapSpotifyResponse(body: SpotifyPlaylistRequestDTO): SpotifyPlaylistResponseDTO {
        val response = SpotifyPlaylistResponseDTO(
            mutableListOf<PlaylistsResponse>()
        )

        for (item in body.playlists.items) {
            val playlist = PlaylistsResponse(
                item.name,
                item.description,
                item.externalUrls.spotify
            )
            response.playlists.add(playlist)
        }

        return response
    }

}
