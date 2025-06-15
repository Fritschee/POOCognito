
package com.projetomvc.pucpr.models;

public class Carro extends Veiculo {
    public Carro(String modelo, double precoPorDia) {
        super(modelo, precoPorDia);
    }

    @Override
    public double calcularTarifa(int dias) {
        return getPrecoPorDia() * dias;
    }

    @Override
    public String toString() {
        return "Carro: " + getModelo() + " – R$" + getPrecoPorDia() + "/dia";
    }
}
