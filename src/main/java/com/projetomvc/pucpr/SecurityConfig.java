package com.projetomvc.pucpr;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          // desabilita CSRF pra chamadas GET/redirect não darem 403
          .csrf(csrf -> csrf.disable())

          // libera todo o fluxo de login/Cognito e assets estáticos
          .authorizeHttpRequests(auth -> auth
            .requestMatchers(
               "/", 
               "/login**",
               "/api/reservas**",
               "/api/reservas/**",
               "/api/**",
               "/detalhe**", 
               "/avaliacoes**", 
               "/authorize**",
               "/index**",
               "/imagens/**",
               "/index.html", 
               "/interno",
               "/fetch**",
               "http://localhost/phpmyadmin/**",
               "/logout", 
               "/css/**", 
               "/reservas**",
               "/fleet**",
               "/contato**",
               "/static/**",
               "/js/**"
            ).permitAll()

            // libera todo o acesso aos endpoints REST /api/**
            .requestMatchers("/api/**").permitAll()
            .anyRequest().authenticated()
          )

          // *desativa* o HTTP Basic
          .httpBasic(basic -> basic.disable())

          // não habilita formLogin nem oauth2Login aqui, pois o login é tratado no seu controller
          ;

        return http.build();
    }
}