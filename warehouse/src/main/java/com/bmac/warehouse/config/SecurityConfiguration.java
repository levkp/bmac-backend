package com.bmac.warehouse.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Reference:
// https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
@Configuration
public class SecurityConfiguration {
    Logger log = LoggerFactory.getLogger(getClass());

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.warn("Disabling CSRF protection");
        return http.csrf().disable().build();
    }
}
