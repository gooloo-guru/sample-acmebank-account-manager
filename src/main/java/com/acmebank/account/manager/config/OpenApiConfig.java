package com.acmebank.account.manager.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Value("${system.version}")
  private String systemVersion;

  @Bean
  public OpenAPI accountManagerOpenAPI() {
    return new OpenAPI()
        .info(new Info().title("Account Manager API")
            .description("Sample account manager application")
            .version(systemVersion));
  }
}
