package com.circe.invoice.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CirceWebMvcConfigurer implements WebMvcConfigurer {

  /** Configure content negociation */
  @Override
  public void configureContentNegotiation(final ContentNegotiationConfigurer configurer) {
    configurer
        .useRegisteredExtensionsOnly(false)
        // DEPRECATED IN SPRING 5.favorPathExtension(false) // disable the fact that Spring
        // determines the type mime on url extension
        .ignoreAcceptHeader(true)
        .defaultContentTypeStrategy(new CirceContentNegotiationStrategy());
  }
}
