package br.com.projetofiap.sus_microsservicos_core.service;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.AssistenteSocialDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarAssistenteSocialDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.AssistenteSocialMapper;
import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioInexistenteException;
import br.com.projetofiap.sus_microsservicos_core.model.AssistenteSocial;
import br.com.projetofiap.sus_microsservicos_core.model.factory.UsuarioFactoryImpl;
import br.com.projetofiap.sus_microsservicos_core.repository.AssistenteSocialRepository;
import br.com.projetofiap.sus_microsservicos_core.validator.AssistenteSocialValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AssistenteSocialService {

    private final AssistenteSocialRepository assistenteSocialRepository;
    private final UsuarioFactoryImpl usuarioFactory;
    private final AssistenteSocialMapper assistenteSocialMapper;
    private final AssistenteSocialValidator assistenteSocialValidator;

    private static final String USUARIO_INEXISTENTE = "usuário não encontrado";

    public Page<BuscarAssistenteSocialDTO> buscarTodosAssistentesSociais(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AssistenteSocial> assistentesSociais = this.assistenteSocialRepository.findAll(pageable);
        Page<BuscarAssistenteSocialDTO> assistentesSociaisDTO = assistentesSociais.map(assistenteSocialMapper::toDTO);
        return assistentesSociaisDTO;
    }

    public List<BuscarAssistenteSocialDTO> buscarAssistenteSocialPorId(String id) {
        UUID uuid = UUID.fromString(id);
        AssistenteSocial buscandoAssistenteSocial = this.assistenteSocialRepository.findById(uuid)
                .orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));
        return List.of(this.assistenteSocialMapper.toDTO(buscandoAssistenteSocial));
    }

    public void cadastrarAssistenteSocial(AssistenteSocialDTO dto) {
        AssistenteSocial novoAssistenteSocial = (AssistenteSocial) this.usuarioFactory.criandoUsuario(dto);
        this.assistenteSocialValidator.validandoMatricula(novoAssistenteSocial);
        this.assistenteSocialRepository.save(novoAssistenteSocial);
    }

    public void atualizarAssistenteSocial(String id, AssistenteSocialDTO dto) {
        UUID uuid = UUID.fromString(id);
        AssistenteSocial buscandoAssistenteSocial = this.assistenteSocialRepository.findById(uuid)
                .orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));

        AssistenteSocial atualizandoAssistenteSocial = (AssistenteSocial) this.usuarioFactory.criandoUsuario(dto);
        atualizandoAssistenteSocial.setId(buscandoAssistenteSocial.getId());
        this.assistenteSocialRepository.save(atualizandoAssistenteSocial);
    }

    public void removerAssistenteSocial(String id) {
        UUID uuid = UUID.fromString(id);
        AssistenteSocial buscandoAssistenteSocial = this.assistenteSocialRepository.findById(uuid)
                .orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));
        this.assistenteSocialRepository.deleteById(uuid);
    }
}
