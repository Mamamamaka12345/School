package com.example.kursach;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;


@Configuration
@ComponentScan
@EnableJpaRepositories
@EnableWebSecurity
public class AppConfig {
}
