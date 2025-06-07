package com.example.demo.SecurityConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfig {

   @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder()
  {
      return  new BCryptPasswordEncoder(12);
  }
}
