package com.FitToMe.project.Config;

import com.FitToMe.project.Config.Security.AuthUser;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2Config {

    static {
        SpringDocUtils.getConfig().addAnnotationsToIgnore(AuthUser.class);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/**")
                .build();
    }

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("FitToMe API")
                        .description("FitToMe 프로젝트 API 명세서입니다.")
                        .version("v0.0.1"));
    }
}