package com.example.hw_spring_store.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

//  private String[] avaliablePath = {
//    "/",
//    "/profile"
//  };

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    // todo проверить патерн
    registry.addMapping("/**") // Путь к вашему API
      .allowedOrigins("http://localhost:8080", "http://localhost:8081") // Разрешенный домен (адрес вашего Vue.js приложения)
      .allowedMethods("*");
//      .allowCredentials(true) // Разрешение использования credentials (например, куки, аутентификация через HTTP)
//      .maxAge(3600); // Время в секундах, в течение которого preflight запросы будут кэшироваться (3600 секунд или 1 час)
  }
}
