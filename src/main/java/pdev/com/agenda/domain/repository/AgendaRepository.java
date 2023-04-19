package pdev.com.agenda.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import pdev.com.agenda.domain.entity.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{

    Optional<Agenda> findByHorario(LocalDateTime horario);

    
}
