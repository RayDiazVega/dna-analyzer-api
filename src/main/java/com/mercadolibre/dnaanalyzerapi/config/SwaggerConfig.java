package com.mercadolibre.dnaanalyzerapi.config;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.media.Content;
import io.swagger.v3.oas.models.media.MediaType;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI(@Value("${info.project.version}") String projectVersion,
      @Value("${info.project.name}") String projectName,
      @Value("${info.project.description}") String projectDescription) {
    return new OpenAPI().info(
        new Info().version(projectVersion).title(projectName).description(projectDescription)
            .contact(new Contact().name("Raymundo Diaz Vega").email("raydiazvega@gmail.com"))
            .license(new License().name("Apache 2.0").url("http://springdoc.org")));
  }

  @Bean
  public OpenApiCustomiser openApiCustomiser() {
    return openApi -> openApi.getPaths().values().parallelStream().forEach(pathItem ->
        pathItem.readOperations().parallelStream().forEach(operation -> operation.getResponses()
            .addApiResponse("400", new ApiResponse().description("Bad Request")
                .content(new Content().addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                    .example("{\n"
                        + "    \"timestamp\": \"2022-03-11T14:36:48.4514789\",\n"
                        + "    \"error\": [\n"
                        + "        \"only values A,T,C and G are allowed\"\n"
                        + "    ],\n"
                        + "    \"message\": \"Client error\"\n"
                        + "}"))))
            .addApiResponse("500", new ApiResponse().description("Internal Server Error")
                .content(new Content().addMediaType(APPLICATION_JSON_VALUE, new MediaType()
                    .example("{\n"
                        + "    \"timestamp\": \"2022-03-11T14:36:48.4514789\",\n"
                        + "    \"message\": \"Server error\"\n"
                        + "}"))))));
  }
}
