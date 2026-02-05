package br.com.projetofiap.sus_microsservicos_core.validator;

import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioCadastradoException;
import br.com.projetofiap.sus_microsservicos_core.model.Medico;
import br.com.projetofiap.sus_microsservicos_core.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MedicoValidator {

    private final MedicoRepository medicoRepository;
    private static final String USUARIO_EXISTENTE = "não podemos cadastrar usuário com um crm que já existe.";

    public void validandoCRM(Medico medico) {
        Medico buscandoMedico = this.medicoRepository.findByCrm(medico.getCrm());

        if (buscandoMedico != null) {
            throw new UsuarioCadastradoException(USUARIO_EXISTENTE);
        }
    }
}
