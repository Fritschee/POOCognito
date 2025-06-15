package com.projetomvc.pucpr.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarRentalController {

    /**
     * Mapeia a home da aplicação (/ e /aluguel).
     * Remove qualquer outro handler "/" ou "/aluguel" duplicado.
     */
    @GetMapping({"/", "/aluguel"})
    public String home() {
        return "forward:/index.html";
    }

    /**
     * Mapeia apenas /reservas.
     * Certifique-se de remover todo outro @GetMapping("/reservas") em
     * outras classes (por exemplo, ViewController).
     */
    @GetMapping("/reservas")
    public String reservas() {
        return "forward:/reservas.html";
    }
}
