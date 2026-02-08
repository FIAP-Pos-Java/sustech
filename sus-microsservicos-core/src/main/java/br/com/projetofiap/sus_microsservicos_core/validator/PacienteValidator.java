package br.com.projetofiap.sus_microsservicos_core.validator;

import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioCadastradoException;
import br.com.projetofiap.sus_microsservicos_core.model.Paciente;
import br.com.projetofiap.sus_microsservicos_core.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PacienteValidator {

    private final PacienteRepository pacienteRepository;
    private static final String USUARIO_EXISTENTE = "não podemos cadastrar paciente com um CPF que já existe.";

    public void validandoCPF(Paciente paciente) {
        Paciente buscandoPaciente = this.pacienteRepository.findByCpf(paciente.getCpf());

        if (buscandoPaciente != null) {
            throw new UsuarioCadastradoException(USUARIO_EXISTENTE);
        }
    }
}
