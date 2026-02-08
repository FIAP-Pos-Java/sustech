package br.com.projetofiap.sus_microsservicos_core.validator;

import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioCadastradoException;
import br.com.projetofiap.sus_microsservicos_core.model.AssistenteSocial;
import br.com.projetofiap.sus_microsservicos_core.repository.AssistenteSocialRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AssistenteSocialValidator {

    private final AssistenteSocialRepository assistenteSocialRepository;
    private static final String USUARIO_EXISTENTE = "não podemos cadastrar assistente social com uma matrícula que já existe.";

    public void validandoMatricula(AssistenteSocial assistenteSocial) {
        AssistenteSocial buscandoAssistenteSocial = this.assistenteSocialRepository.findByMatricula(assistenteSocial.getMatricula());

        if (buscandoAssistenteSocial != null) {
            throw new UsuarioCadastradoException(USUARIO_EXISTENTE);
        }
    }
}
