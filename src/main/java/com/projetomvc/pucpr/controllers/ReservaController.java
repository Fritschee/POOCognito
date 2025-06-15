package com.projetomvc.pucpr.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetomvc.pucpr.dto.ReservaRequest;
import com.projetomvc.pucpr.exceptions.ReservaException;  
import com.projetomvc.pucpr.models.Reserva;
import com.projetomvc.pucpr.repository.ReservaRepository;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin
public class ReservaController {

    private final ReservaRepository reservaRepository;

    public ReservaController(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    @PostMapping
    public ResponseEntity<Reserva> criarReserva(@RequestBody ReservaRequest request) {
        LocalDate inicio = LocalDate.parse(request.getDataInicio());
        LocalDate fim    = LocalDate.parse(request.getDataFim());

        long dias = ChronoUnit.DAYS.between(inicio, fim);
        if (dias <= 0) {
            return ResponseEntity.badRequest().body(null);
        }

        double total = dias * request.getPrecoPorDia();

        Reserva reserva = new Reserva();
        reserva.setModelo(request.getModelo());
        reserva.setInicio(inicio);
        reserva.setFim(fim);
        reserva.setTotal(total);

        Reserva salvo = reservaRepository.save(reserva);
        return ResponseEntity.ok(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> buscarPorId(@PathVariable Long id) throws ReservaException {
        Reserva reserva = reservaRepository.findById(id)
            .orElseThrow(() -> new ReservaException("Reserva não encontrada: " + id));
        return ResponseEntity.ok(reserva);
    }
}
