package com.dev.iFoodBackendAdvancedTest.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfiguration {

    companion object {
        private const val API_NAME = "iFood Backend Advanced Test"
        private const val BASE_PACKAGE = "com.dev.iFoodBackendAdvancedTest.controller"
    }

    @Bean
    fun api(): Docket? {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(
                ApiInfoBuilder()
                    .title(API_NAME)
                    .build()
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage(BASE_PACKAGE))
            .build()
            .useDefaultResponseMessages(false)
            .enableUrlTemplating(false)
    }
}
