package br.com.projetofiap.sus_microsservicos_core.controller.mappers;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarEnderecoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.EnderecoDTO;
import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EnderecoMapper {

    Endereco toEntity(EnderecoDTO dto);
    BuscarEnderecoDTO toDTO(Endereco endereco);
}
