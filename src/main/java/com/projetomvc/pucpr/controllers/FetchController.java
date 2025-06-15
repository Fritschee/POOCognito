package com.projetomvc.pucpr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class FetchController {

    @GetMapping("/fetch")
    public String fetchPage(HttpSession session) {
        // garante que só usuários autenticados cheguem aqui
        if (session.getAttribute("id_token") == null) {
            return "redirect:/";
        }
        return "fetch";  // vai resolver o template fetch.html
    }
}
