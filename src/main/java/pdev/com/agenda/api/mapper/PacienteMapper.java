package pdev.com.agenda.api.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import pdev.com.agenda.api.request.PacienteRequest;
import pdev.com.agenda.api.response.PacienteResponse;
import pdev.com.agenda.domain.entity.Paciente;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest request){
        return mapper.map(request, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente){
        return mapper.map(paciente, PacienteResponse.class);
    }

    public List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes){
        return pacientes.stream()
        .map(this::toPacienteResponse)
        .collect(Collectors.toList());
    }

    // public static List<PacienteResponse> toPacienteResponseList(List<Paciente> pacientes){
    //     List<PacienteResponse> responses = new ArrayList<>();

    //     for (Paciente paciente : pacientes){
    //         responses.add(toPacienteResponse(paciente));
    //     }

    //     return responses;
    // }

}
