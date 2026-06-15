package com.lcl.agendador_horarios.controller;

import com.lcl.agendador_horarios.infrastructure.entity.Agendamento;
import com.lcl.agendador_horarios.services.AgendamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> saveAgendamento(@RequestBody Agendamento agendamento){
        return ResponseEntity.ok().body(agendamentoService.salvarAgendamento(agendamento));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAgendamento(@RequestParam String cliente, @RequestParam LocalDateTime dataHoraAgendamento){
        agendamentoService.deletarAgendamento(dataHoraAgendamento, cliente);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Agendamento> buscarAgendamentosDia(@RequestParam LocalDate data){
        return ResponseEntity.ok().body(agendamentoService.buscarAgendamentosDia(data));
    }

    @PutMapping
    public ResponseEntity<Agendamento> alterarAgendamento(@RequestBody Agendamento agendamento, @RequestParam String cliente, @RequestParam LocalDateTime dataHoraAgendamento){
        return ResponseEntity.ok().body(agendamentoService.alterarAgendamento(agendamento, cliente, dataHoraAgendamento));
    }

}
