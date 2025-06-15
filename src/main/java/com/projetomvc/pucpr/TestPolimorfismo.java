
package com.projetomvc.pucpr;

import java.util.List;

import com.projetomvc.pucpr.models.Carro;
import com.projetomvc.pucpr.models.Moto;
import com.projetomvc.pucpr.models.Veiculo;

public class TestPolimorfismo {
    public static void main(String[] args) {
        List<Veiculo> frota = List.of(
            new Carro("Ferrari F430", 2200),
            new Moto("Honda CB500", 200)
        );
        // ► chamada polimórfica: calcula tarifa sem saber o tipo concreto
        for (Veiculo v : frota) {
            System.out.printf("%s em 8 dias: R$ %.2f%n",
                v.getModelo(), v.calcularTarifa(8));
        }
    }
}
