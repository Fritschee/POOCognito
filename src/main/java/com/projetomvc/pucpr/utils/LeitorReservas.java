package com.projetomvc.pucpr.utils;

import com.projetomvc.pucpr.models.Reserva;
import com.projetomvc.pucpr.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;        // <-- veja que, em Spring Boot 3+, vem de jakarta
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class LeitorReservas {

    @Autowired
    private ReservaRepository reservaRepo;

    /**
     * No startup, tenta ler reservas.csv na raiz do projeto.
     */
    @PostConstruct
    public void importarCsv() {
        String path = "reservas.csv";
        List<Reserva> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                // Formato: modelo,dataInicio(yyyy-MM-dd),dataFim,precoPorDia
                String[] f = linha.split(",");
                Reserva r = new Reserva();
                r.setModelo(f[0]);
                r.setInicio(LocalDate.parse(f[1]));
                r.setFim(LocalDate.parse(f[2]));
                r.setTotal(Double.parseDouble(f[3]) * 
                           (LocalDate.parse(f[2]).toEpochDay() - LocalDate.parse(f[1]).toEpochDay()));
                lista.add(r);
            }
            reservaRepo.saveAll(lista);
            System.out.println("Importação de CSV concluída: " + lista.size() + " reservas.");
        } catch (Exception e) {
            System.err.println("Falha ao importar CSV: " + e.getMessage());
        }
    }
}
