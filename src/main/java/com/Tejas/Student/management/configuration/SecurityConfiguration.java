package com.Tejas.Student.management.configuration;

import com.Tejas.Student.management.service.CustomUserDetailsServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf->csrf.disable())
                .authorizeHttpRequests(auth->
                        auth.requestMatchers("/api/student/profile").hasRole("STUDENT") // 👈 Pehle likh
                        .requestMatchers("/api/student/**").hasRole("ADMIN")        // 👈 Fir ye likh
                        .requestMatchers("/api/user").permitAll().anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    private CustomUserDetailsServices services;

    public SecurityConfiguration(CustomUserDetailsServices services) {
        this.services = services;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder(12);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(services);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }
}
