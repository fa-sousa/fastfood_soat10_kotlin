package com.fastfood.configuration

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.ExternalDocumentation
import io.swagger.v3.oas.models.responses.ApiResponse
import io.swagger.v3.oas.models.responses.ApiResponses
import org.springdoc.core.customizers.OpenApiCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class SwaggerConfig {

    @Bean
    open fun springFastfoodOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("FastFood")
                    .description("FastFood Project, have a crud and module structure.")
                    .version("v0.0.1")
                    .license(
                        License()
                            .name("FastFood")
                            .url("https://fastfood.com.br/")
                    )
                    .contact(
                        Contact()
                            .name("Fátima Sousa")
                            .url("https://github.com/fa-sousa")
                            .email("fsousa292@gmail.com")
                    )
            )
            .externalDocs(
                ExternalDocumentation()
                    .description("Github")
                    .url("https://github.com/fa-sousa/fastfood_soat10_kotlin")
            )
    }

    @Bean
    open fun customerGlobalHeaderOpenApiCustomizer(): OpenApiCustomizer {
        return OpenApiCustomizer { openApi ->
            openApi.paths.values.forEach { pathItem ->
                pathItem.readOperations().forEach { operation ->
                    val apiResponses: ApiResponses = operation.responses

                    apiResponses.addApiResponse("200", createApiResponse("Sucesso!"))
                    apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"))
                    apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"))
                    apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"))
                    apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"))
                    apiResponses.addApiResponse("403", createApiResponse("Acesso Proibido!"))
                    apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"))
                    apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"))
                }
            }
        }
    }

    private fun createApiResponse(message: String): ApiResponse {
        return ApiResponse().description(message)
    }
}
