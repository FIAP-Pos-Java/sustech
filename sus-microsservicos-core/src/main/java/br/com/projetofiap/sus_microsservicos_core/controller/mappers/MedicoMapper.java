package br.com.projetofiap.sus_microsservicos_core.controller.mappers;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarLoginDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarMedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.LoginDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.MedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.model.Login;
import br.com.projetofiap.sus_microsservicos_core.model.Medico;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LoginMapper.class, EnderecoMapper.class})
public interface MedicoMapper {
    Medico toEntity(MedicoDTO dto);
    BuscarMedicoDTO toDTO(Medico medico);
}
