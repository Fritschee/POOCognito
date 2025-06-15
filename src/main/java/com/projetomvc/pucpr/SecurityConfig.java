package com.projetomvc.pucpr;

import com.projetomvc.pucpr.security.SessionAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
          .csrf(csrf -> csrf.disable())

          // registra nosso filtro de sessão antes do processamento padrão
          .addFilterBefore(
              new SessionAuthenticationFilter(),
              UsernamePasswordAuthenticationFilter.class
          )

          .authorizeHttpRequests(auth -> auth
            // apenas login/Cognito e assets são públicos
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

            // QUALQUER OUTRA ROTA agora exige sessão
            .anyRequest().authenticated()
          )

          .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
