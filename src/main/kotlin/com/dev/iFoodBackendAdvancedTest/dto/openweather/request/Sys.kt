package com.dev.iFoodBackendAdvancedTest.dto.openweather.request

import java.math.BigInteger

data class Sys(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: BigInteger,
    val sunset: BigInteger
)
