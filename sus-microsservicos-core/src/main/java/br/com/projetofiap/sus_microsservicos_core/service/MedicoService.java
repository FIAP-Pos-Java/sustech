package br.com.projetofiap.sus_microsservicos_core.service;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarMedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.MedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.MedicoMapper;
import br.com.projetofiap.sus_microsservicos_core.model.Medico;
import br.com.projetofiap.sus_microsservicos_core.repository.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MedicoService {

    private final MedicoRepository medicoRepository;
    private final MedicoMapper medicoMapper;

    public Page<BuscarMedicoDTO> buscarTodosMedicos(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Medico> medicos = this.medicoRepository.findAll(pageable);
        Page<BuscarMedicoDTO> medicosDTO = medicos.map(medicoMapper::toDTO);
        return medicosDTO;
    }

    public List<BuscarMedicoDTO> buscarMedicoPorId(String id){
        UUID uuid = UUID.fromString(id);
        Medico buscandoMedico = this.medicoRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("usuario não existe"));
        return List.of(this.medicoMapper.toDTO(buscandoMedico));
    }

    public void cadastrarMedico(MedicoDTO dto){
        // todo implementar erro se tiver mesmo crm nao pode cadastrar
        Medico novoMedico = new Medico();
        novoMedico = this.medicoMapper.toEntity(dto);
        this.medicoRepository.save(novoMedico);
    }

    public void atualizarMedico(String id, MedicoDTO dto){
        UUID uuid = UUID.fromString(id);
        Medico buscandoMedico = this.medicoRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("usuario não existe"));

        Medico atualizandoMedico = this.medicoMapper.toEntity(dto);
        atualizandoMedico.setId(buscandoMedico.getId());
        this.medicoRepository.save(atualizandoMedico);
    }

    public void removerMedico(String id){
        UUID uuid = UUID.fromString(id);
        Medico buscandoMedico = this.medicoRepository.findById(uuid).orElseThrow(() -> new IllegalArgumentException("usuario não existe"));
        this.medicoRepository.deleteById(uuid);
    }

}
