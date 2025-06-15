package com.projetomvc.pucpr.controllers;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class CognitoController {

    @Value("${cognito.client-id}")
    private String clientId;

    @Value("${cognito.client-secret}")
    private String clientSecret;

    @Value("${cognito.user-pool-domain}")
    private String domain;

    @Value("${cognito.redirect-uri}")
    private String redirectUri;

    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/login")
    public void login(HttpServletResponse response) throws IOException {
        String authUrl = String.format(
            "%s/oauth2/authorize?response_type=code&client_id=%s&redirect_uri=%s&scope=openid+email",
            domain,
            clientId,
            URLEncoder.encode(redirectUri, "UTF-8")
        );
        response.sendRedirect(authUrl);
    }

    @GetMapping("/authorize")
    public String authorize(@RequestParam("code") String code, HttpSession session) {
        // Monta requisição de troca de código por tokens
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("grant_type", "authorization_code");
        form.add("client_id", clientId);
        form.add("client_secret", clientSecret);
        form.add("redirect_uri", redirectUri);
        form.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        ResponseEntity<Map> resp = restTemplate.postForEntity(domain + "/oauth2/token", request, Map.class);
        Map<String, Object> tokens = resp.getBody();

        // Validação de null para evitar NullPointerException
        if (tokens == null || tokens.get("id_token") == null) {
            // TODO: trate o erro adequadamente ou redirecione para página de erro
            return "error";
        }

        // Recupera e armazena o ID token
        String idToken = (String) tokens.get("id_token");
        session.setAttribute("id_token", idToken);

        // Redireciona para área interna
        return "redirect:/index.html";
    }

    @GetMapping("/index")
    public String interno(HttpSession session) {
        if (session.getAttribute("id_token") == null) {
            return "redirect:/";
        }
        return "index.html";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}
