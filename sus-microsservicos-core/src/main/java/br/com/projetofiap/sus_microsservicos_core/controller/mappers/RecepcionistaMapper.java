package br.com.projetofiap.sus_microsservicos_core.controller.mappers;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarMedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarRecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.MedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.RecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.model.Medico;
import br.com.projetofiap.sus_microsservicos_core.model.Recepcionista;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LoginMapper.class, EnderecoMapper.class})
public interface RecepcionistaMapper {
    Recepcionista toEntity(RecepcionistaDTO dto);
    BuscarRecepcionistaDTO toDTO(Recepcionista recepcionista);
}
