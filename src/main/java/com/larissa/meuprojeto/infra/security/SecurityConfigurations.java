package com.larissa.meuprojeto.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;


@Configuration
@EnableWebSecurity
public class SecurityConfigurations {
    @Autowired
    SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
        .csrf(csrf -> csrf.disable())
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(authorize -> authorize
                         .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                         .requestMatchers(HttpMethod.POST, "/auth/registrar").permitAll()
        
                        .requestMatchers(HttpMethod.POST, "/livros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/livros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/livros/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/livros/**").hasAnyRole("ADMIN", "USER")

                        // Pessoas
                        .requestMatchers(HttpMethod.POST, "/pessoas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/pessoas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/pessoas/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/pessoas/**").hasAnyRole("ADMIN", "USER")

                        // Empréstimos
                        .requestMatchers(HttpMethod.POST, "/emprestimos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.PUT, "/emprestimos/**").hasAnyRole("ADMIN", "USER")
                        .requestMatchers(HttpMethod.GET, "/emprestimos/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated())
                        .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
    }

    @Bean
    public AuthenticationManager authenticationManager (AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder (){
        return new BCryptPasswordEncoder();
    }
}