package br.com.projetofiap.sus_microsservicos_core.service;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarMedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarRecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.MedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.RecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.RecepcionistaMapper;
import br.com.projetofiap.sus_microsservicos_core.exceptions.UsuarioInexistenteException;
import br.com.projetofiap.sus_microsservicos_core.model.Medico;
import br.com.projetofiap.sus_microsservicos_core.model.Recepcionista;
import br.com.projetofiap.sus_microsservicos_core.model.factory.UsuarioFactoryImpl;
import br.com.projetofiap.sus_microsservicos_core.repository.RecepcionistaRepository;
import br.com.projetofiap.sus_microsservicos_core.validator.RecepcionistaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RecepcionistaService {

    private final RecepcionistaRepository recepcionistaRepository;
    private final RecepcionistaMapper recepcionistaMapper;
    private final UsuarioFactoryImpl usuarioFactory;
    private final RecepcionistaValidator recepcionistaValidator;

    private static final String USUARIO_INEXISTENTE = "usuário não encontrado";

    public Page<BuscarRecepcionistaDTO> buscarTodosRecepcionistas(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Recepcionista> recepcionistas = this.recepcionistaRepository.findAll(pageable);
        Page<BuscarRecepcionistaDTO> recepcionistasDTO = recepcionistas.map(recepcionistaMapper::toDTO);
        return recepcionistasDTO;
    }

    public List<BuscarRecepcionistaDTO> buscarRecepcionistaPorId(String id){
        UUID uuid = UUID.fromString(id);
        Recepcionista buscandoRecepcionista = this.recepcionistaRepository.findById(uuid).orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));
        return List.of(this.recepcionistaMapper.toDTO(buscandoRecepcionista));
    }

    public void cadastrarRecepcionista(RecepcionistaDTO dto){
        Recepcionista novoRecepcionista = (Recepcionista) this.usuarioFactory.criandoUsuario(dto);
        this.recepcionistaValidator.validandoMatricula(novoRecepcionista);
        this.recepcionistaRepository.save(novoRecepcionista);
    }

    public void atualizarRecepcionista(String id, RecepcionistaDTO dto){
        UUID uuid = UUID.fromString(id);
        Recepcionista buscandoRecepcionista = this.recepcionistaRepository.findById(uuid).orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));

        Recepcionista atualizandoRecepcionista = (Recepcionista) this.usuarioFactory.criandoUsuario(dto);
        atualizandoRecepcionista.setId(buscandoRecepcionista.getId());
        this.recepcionistaRepository.save(atualizandoRecepcionista);
    }

    public void removerRecepcionista(String id){
        UUID uuid = UUID.fromString(id);
        Recepcionista buscandoRecepcionista = this.recepcionistaRepository.findById(uuid).orElseThrow(() -> new UsuarioInexistenteException(USUARIO_INEXISTENTE));
        this.recepcionistaRepository.deleteById(uuid);
    }
}
