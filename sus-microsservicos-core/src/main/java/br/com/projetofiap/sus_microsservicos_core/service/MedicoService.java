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

    public void cadastrarMedico(MedicoDTO dto){
        // todo implementar erro se tiver mesmo crm nao pode cadastrar
        Medico novoMedico = new Medico();
        novoMedico = this.medicoMapper.toEntity(dto);
        this.medicoRepository.save(novoMedico);
    }

}
