package com.projetomvc.pucpr.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projetomvc.pucpr.models.Reserva;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
