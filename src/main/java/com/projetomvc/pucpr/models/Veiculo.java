
package com.projetomvc.pucpr.models;

public abstract class Veiculo {
    private String modelo;
    private double precoPorDia;

    public Veiculo(String modelo, double precoPorDia) {
        this.modelo = modelo;
        this.precoPorDia = precoPorDia;
    }

    public String getModelo() { return modelo; }
    public double getPrecoPorDia() { return precoPorDia; }

    // ► método abstrato
    public abstract double calcularTarifa(int dias);
}
