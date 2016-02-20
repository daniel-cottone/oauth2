package com.brahalla.oauthprovider.configuration;

import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafConfiguration {

  @Bean
  public SpringSecurityDialect securityDialect() {
    return new SpringSecurityDialect();
  }

}
