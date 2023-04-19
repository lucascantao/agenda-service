package pdev.com.agenda.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import pdev.com.agenda.api.mapper.PacienteMapper;
import pdev.com.agenda.api.request.PacienteRequest;
import pdev.com.agenda.api.response.PacienteResponse;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.domain.service.PacienteService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paciente")
public class PacienteController {
    

    private final PacienteService service;
    private final PacienteMapper mapper;

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest request){
        Paciente paciente = mapper.toPaciente(request);
        Paciente pacienteSalvo = service.salvar(paciente);
        PacienteResponse response = mapper.toPacienteResponse(pacienteSalvo);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    
    @GetMapping
    public ResponseEntity<List<PacienteResponse>> listarTodos() {
        List<Paciente> pacientes = service.listarTodos();
        List<PacienteResponse> responses = mapper.toPacienteResponseList(pacientes);
        return ResponseEntity.status((HttpStatus.OK)).body(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteResponse> listarPorId(@PathVariable Long id) {
        Optional<Paciente> optPaciente = service.buscarPorId(id);

        if(optPaciente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        PacienteResponse response = mapper.toPacienteResponse(optPaciente.get());
        return ResponseEntity.status((HttpStatus.OK)).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteResponse> alterar(@PathVariable Long id, @RequestBody PacienteRequest request){
        Paciente paciente = mapper.toPaciente(request);
        PacienteResponse response = mapper.toPacienteResponse(service.alterar(id, paciente));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
