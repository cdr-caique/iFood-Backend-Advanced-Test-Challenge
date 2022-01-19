package com.dev.iFoodBackendAdvancedTest.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.dev.iFoodBackendAdvancedTest.client.openweather.OpenWeatherClient
import com.dev.iFoodBackendAdvancedTest.client.spotify.SpotifyAccountsClient
import com.dev.iFoodBackendAdvancedTest.client.spotify.SpotifyApiClient
import feign.Feign
import feign.form.FormEncoder
import feign.jackson.JacksonDecoder
import feign.jackson.JacksonEncoder
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ClientConfiguration(
    @Value("\${open-weather.url}") private val openWeatherUrl: String,
    @Value("\${spotify.accounts.url}") private val spotifyAccountsUrl: String,
    @Value("\${spotify.api.url}") private val spotifyApiUrl: String
) {

    @Bean
    fun openWeatherClient(
        objectMapper: ObjectMapper
    ): OpenWeatherClient {
        return Feign.builder()
            .encoder(JacksonEncoder(objectMapper))
            .decoder(JacksonDecoder(objectMapper))
            .target(OpenWeatherClient::class.java, openWeatherUrl)
    }

    @Bean
    fun spotifyAccountsClient(
        objectMapper: ObjectMapper
    ): SpotifyAccountsClient {
        return Feign.builder()
            .encoder(FormEncoder(JacksonEncoder(objectMapper)))
            .decoder(JacksonDecoder(objectMapper))
            .target(SpotifyAccountsClient::class.java, spotifyAccountsUrl)
    }

    @Bean
    fun spotifyApiClient(
        objectMapper: ObjectMapper
    ): SpotifyApiClient {
        return Feign.builder()
            .encoder(JacksonEncoder(objectMapper))
            .decoder(JacksonDecoder(objectMapper))
            .target(SpotifyApiClient::class.java, spotifyApiUrl)
    }

}
