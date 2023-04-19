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

import lombok.RequiredArgsConstructor;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.domain.service.PacienteService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paciente")
public class PacienteController {
    

    private final PacienteService service;

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente){

        Paciente pacienteSalvo = service.salvar(paciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteSalvo);

    }
    
    @GetMapping
    public ResponseEntity<List<Paciente>> listarTodos() {
        List<Paciente> pacientes = service.listarTodos();
        return ResponseEntity.status((HttpStatus.OK)).body(pacientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> listarPorId(@PathVariable Long id) {
        Optional<Paciente> optPacientes = service.buscarPorId(id);

        if(optPacientes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status((HttpStatus.OK)).body(optPacientes.get());
    }

    @PutMapping
    public ResponseEntity<Paciente> alterar(@RequestBody Paciente paciente){
        Paciente p = service.salvar(paciente);
        return ResponseEntity.status(HttpStatus.OK).body(p);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
