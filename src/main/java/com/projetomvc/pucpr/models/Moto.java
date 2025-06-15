
package com.projetomvc.pucpr.models;

public class Moto extends Veiculo {
    public Moto(String modelo, double precoPorDia) {
        super(modelo, precoPorDia);
    }

    @Override
    public double calcularTarifa(int dias) {
        // exemplo de comportamento diferente
        return getPrecoPorDia() * dias * 0.9; // 10% de desconto em motos
    }

    @Override
    public String toString() {
        return "Moto: " + getModelo() + " – R$" + getPrecoPorDia() + "/dia (10% desc.)";
    }
}
