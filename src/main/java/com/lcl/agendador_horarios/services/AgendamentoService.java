package com.lcl.agendador_horarios.services;

import com.lcl.agendador_horarios.infrastructure.entity.Agendamento;
import com.lcl.agendador_horarios.infrastructure.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AgendamentoService {

    private final AgendamentoRepository agendamentoRepository;

    public Agendamento salvarAgendamento(Agendamento agendamento) {
        LocalDateTime horaAgendamento = agendamento.getDataHoraAgendamento();
        LocalDateTime horaFim = agendamento.getDataHoraAgendamento().plusHours(1);

        Agendamento agendados = agendamentoRepository.findByServicoAndDataHoraAgendamentoBetween(agendamento.getServico(), horaAgendamento, horaFim);

        if (Objects.nonNull(agendados)) {
            throw new RuntimeException("Horário já está preenchido.");
        }
        return agendamentoRepository.save(agendamento);
    }

    public void deletarAgendamento(LocalDateTime dataHoraAgendamento, String cliente) {
            agendamentoRepository.deleteByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);
    }

    public Agendamento buscarAgendamentosDia(LocalDate data){
        LocalDateTime primeiraHoraDia = data.atStartOfDay();
        LocalDateTime ultimaHoraDia = data.atTime(23, 59, 59);
        return agendamentoRepository.findByDataHoraAgendamentoBetween(primeiraHoraDia, ultimaHoraDia);
    }

    public Agendamento alterarAgendamento(Agendamento agendamento, String cliente, LocalDateTime dataHoraAgendamento) {
        Agendamento agenda = agendamentoRepository.findByDataHoraAgendamentoAndCliente(dataHoraAgendamento, cliente);

        if (Objects.isNull(agenda)) {
            throw new RuntimeException("Horário não está preenchido.");
        }

        agendamento.setId(agenda.getId());
        return agendamentoRepository.save(agendamento);

    }
}
