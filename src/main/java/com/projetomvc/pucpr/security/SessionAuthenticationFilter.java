package com.projetomvc.pucpr.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class SessionAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String path = request.getRequestURI();

        // -> Somente essas rotas podem rodar sem id_token:
        if (path.equals("/")                           
         || path.startsWith("/login")                  
         || path.startsWith("/authorize")              
         || path.startsWith("/css/")                   
         || path.startsWith("/js/")
         || path.startsWith("/imagens/")) {
            filterChain.doFilter(request, response);
            return;
        }

        // tudo o mais exige sessão válida
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("id_token") == null) {
            response.sendRedirect("/");
            return;
        }

        filterChain.doFilter(request, response);
    }
}
