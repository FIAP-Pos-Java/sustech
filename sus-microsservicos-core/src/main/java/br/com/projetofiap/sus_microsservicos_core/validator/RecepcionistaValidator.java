package br.com.projetofiap.sus_microsservicos_core.validator;

import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioCadastradoException;
import br.com.projetofiap.sus_microsservicos_core.model.Recepcionista;
import br.com.projetofiap.sus_microsservicos_core.repository.RecepcionistaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecepcionistaValidator {

    private final RecepcionistaRepository recepcionistaRepository;
    private static final String USUARIO_EXISTENTE = "não podemos cadastrar usuário com a mesma matricula.";

    public void validandoMatricula(Recepcionista recepcionista){
        Recepcionista buscandoRecepcionista = this.recepcionistaRepository.findByMatricula(recepcionista.getMatricula());

        if(buscandoRecepcionista != null){
            throw new UsuarioCadastradoException(USUARIO_EXISTENTE);
        }
    }
}
