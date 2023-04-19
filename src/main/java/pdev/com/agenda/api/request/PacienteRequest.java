package pdev.com.agenda.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PacienteRequest {

    @NotBlank(message = "Nome do Paciente é obrigatório")
    private String nome;

    @NotBlank(message = "Sobrenome do Paciente é obrigatório")
    private String sobrenome;

    private String email;

    @NotBlank(message = "Cpf do Paciente é obrigatório")
    private String cpf;
}
