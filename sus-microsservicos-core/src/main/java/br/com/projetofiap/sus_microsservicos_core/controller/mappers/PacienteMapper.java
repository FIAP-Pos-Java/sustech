package br.com.projetofiap.sus_microsservicos_core.controller.mappers;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarPacienteDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.PacienteDTO;
import br.com.projetofiap.sus_microsservicos_core.model.Paciente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LoginMapper.class, EnderecoMapper.class})
public interface PacienteMapper {
    Paciente toEntity(PacienteDTO dto);
    BuscarPacienteDTO toDTO(Paciente paciente);
}
