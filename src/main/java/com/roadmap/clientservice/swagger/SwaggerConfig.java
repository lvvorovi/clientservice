package com.roadmap.clientservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import static com.roadmap.clientservice.swagger.SwagerTagStore.CLIENT_CONTROLLER_TAG;


@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.roadmap.clientservice.web.controller"))
                .paths(PathSelectors.ant("/clients").negate())
                .paths(PathSelectors.ant("/clients/{id}").negate())
                .build()
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo());
        return appendTags(docket);
    }

    private Docket appendTags(Docket docket) {
        return docket.tags(
                new Tag(CLIENT_CONTROLLER_TAG, "Provides CRUD operations")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Client Service API")
                .description("API for Client Management")
                .version("0.0.1")
                .build();
    }
}
