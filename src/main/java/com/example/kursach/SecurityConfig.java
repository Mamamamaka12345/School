package com.example.kursach;

import com.example.kursach.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests()
                .requestMatchers("/subjects/addCourse").hasAuthority("ADMIN")
                .requestMatchers("/subjects/delCourse").hasAuthority("ADMIN")
                .requestMatchers("/personalArea").hasAnyAuthority("USER", "TEACHER", "ADMIN")
                .requestMatchers("/**").permitAll()
                .and()
                .formLogin
                        (form -> form.loginPage("/loginM")
                .permitAll()
                        )
                .userDetailsService(userDetailsService());

        return http.build();
    }
}
