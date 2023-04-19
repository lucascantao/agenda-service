package pdev.com.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pdev.com.agenda.api.mapper.AgendaMapper;
import pdev.com.agenda.api.request.AgendaRequest;
import pdev.com.agenda.api.response.AgendaResponse;
import pdev.com.agenda.domain.entity.Agenda;
import pdev.com.agenda.domain.service.AgendaService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/agenda")
public class AgendaController {
    
    private final AgendaService service;
    private final AgendaMapper mapper;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> listarTodos(){
        List<Agenda> agendas = service.listarTodos();
        List<AgendaResponse> responses = mapper.toAgendaResponseList(agendas);

        return ResponseEntity.status(HttpStatus.OK).body(responses);
        
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendaResponse> buscarPorId(@PathVariable Long id) {
        Optional<Agenda> optAgenda = service.buscaPorId(id);
        
        if(optAgenda.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toAgendaResponse(optAgenda.get()));

    }

    @PostMapping
    public ResponseEntity<AgendaResponse> buscarPorId(@Valid @RequestBody AgendaRequest request) {
        Agenda agenda = mapper.toAgenda(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toAgendaResponse(service.salvar(agenda)));

    }

}
