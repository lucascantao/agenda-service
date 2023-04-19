package pdev.com.agenda.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import pdev.com.agenda.domain.entity.Paciente;
import pdev.com.agenda.domain.repository.PacienteRepository;
import pdev.com.agenda.exception.BusinessException;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    @Autowired
    private final PacienteRepository repository;

    

    public Paciente salvar(Paciente paciente) {
        boolean existeCPf = false;
        
        Optional<Paciente> optPaciente = repository.findByCpf(paciente.getCpf());
        
        if(optPaciente.isPresent()){
            if(optPaciente.get().getId().equals(paciente.getId())){
                existeCPf = true;
            }
        }

        if(existeCPf){
            throw new BusinessException("Cpf já Cadastrado");
        }
        
        return repository.save(paciente);
    }

    public Paciente alterar(Long id, Paciente paciente) {

        if(this.buscarPorId(id).isEmpty()){
            throw new BusinessException("Paciente não existente");
        }
        
        paciente.setId(id);
        
        return repository.save(paciente);
        
    }

    public List<Paciente> listarTodos() {
        return repository.findAll();
        
    }

    public Optional<Paciente> buscarPorId(Long id){
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
    
}
