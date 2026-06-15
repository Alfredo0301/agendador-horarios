package com.lcl.agendador_horarios.infrastructure.repository;

import com.lcl.agendador_horarios.infrastructure.entity.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
}
