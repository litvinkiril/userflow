package com.hse.userflow.gateway.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Components components = new Components();
        hideSchema(components, "HttpMethod");
        hideSchema(components, "HttpRange");
        hideSchema(components, "HttpStatusCode");
        hideSchema(components, "MediaType");
        hideSchema(components, "ProblemDetail");

        return new OpenAPI()
                .info(new Info()
                        .title("userflow")
                        .version("1.0.0")
                        .description("""
                                REST API для системы хранения и анализа студенческих работ.
                                Позволяет загружать файлы, управлять пользователями и получать отчеты анализа плагиата.
                                """)
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Локальный сервер")
                ))
                .components(components)
                .externalDocs(new ExternalDocumentation()
                        .description("Архитектура системы")
                        .url("/api-docs/architecture"))
                .externalDocs(new ExternalDocumentation()
                        .description("Пользовательские сценарии")
                        .url("/api-docs/user-scenarios"))
                .externalDocs(new ExternalDocumentation()
                        .description("Пользовательские сценарии")
                        .url("/api-docs/plagiarism-check"));
    }

    private void hideSchema(Components components, String schemaName) {
        Schema<?> schema = new Schema<>().type("object");
        schema.addExtension("x-hidden", true);
        components.addSchemas(schemaName, schema);
    }
}
