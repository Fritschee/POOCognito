package com.projetomvc.pucpr.models;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String modelo;
    private LocalDate inicio;
    private LocalDate fim;
    private double total;

    public Long getId() { return id; }
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    public LocalDate getInicio() { return inicio; }
    public void setInicio(LocalDate inicio) { this.inicio = inicio; }
    public LocalDate getFim() { return fim; }
    public void setFim(LocalDate fim) { this.fim = fim; }
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
}
