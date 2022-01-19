package com.dev.iFoodBackendAdvancedTest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class ])
class IFoodBackendAdvancedTestApplication

fun main(args: Array<String>) {
	runApplication<IFoodBackendAdvancedTestApplication>(*args)
}
