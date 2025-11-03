package com.abdullah.personalblog.personal_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}admin")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/delete/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/processEdit/**").hasRole("ADMIN")
                        .anyRequest().permitAll()
        );
        http.httpBasic(Customizer.withDefaults());
        return http.build();
    }
}
