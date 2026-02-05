package br.com.projetofiap.sus_microsservicos_core.service;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarMedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarRecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.MedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.RecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.RecepcionistaMapper;
import br.com.projetofiap.sus_microsservicos_core.model.Medico;
import br.com.projetofiap.sus_microsservicos_core.model.Recepcionista;
import br.com.projetofiap.sus_microsservicos_core.repository.RecepcionistaRepository;
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

    public Page<BuscarRecepcionistaDTO> buscarTodosRecepcionistas(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Recepcionista> recepcionistas = this.recepcionistaRepository.findAll(pageable);
        Page<BuscarRecepcionistaDTO> recepcionistasDTO = recepcionistas.map(recepcionistaMapper::toDTO);
        return recepcionistasDTO;
    }

    public List<BuscarRecepcionistaDTO> buscarRecepcionistaPorId(String id){
        UUID uuid = UUID.fromString(id);
        Recepcionista buscandoRecepcionista = this.recepcionistaRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("usuario não existe"));
        return List.of(this.recepcionistaMapper.toDTO(buscandoRecepcionista));
    }

    public void cadastrarRecepcionista(RecepcionistaDTO dto){
        // todo implementar erro se tiver mesmo crm nao pode cadastrar
        Recepcionista novoRecepcionista = new Recepcionista();
        novoRecepcionista = this.recepcionistaMapper.toEntity(dto);
        this.recepcionistaRepository.save(novoRecepcionista);
    }

    public void atualizarRecepcionista(String id, RecepcionistaDTO dto){
        UUID uuid = UUID.fromString(id);
        Recepcionista buscandoRecepcionista = this.recepcionistaRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("usuario não existe"));

        Recepcionista atualizandoRecepcionista = this.recepcionistaMapper.toEntity(dto);
        atualizandoRecepcionista.setId(buscandoRecepcionista.getId());
        this.recepcionistaRepository.save(atualizandoRecepcionista);
    }

    public void removerRecepcionista(String id){
        UUID uuid = UUID.fromString(id);
        Recepcionista buscandoRecepcionista = this.recepcionistaRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("usuario não existe"));
        this.recepcionistaRepository.deleteById(uuid);
    }
}
