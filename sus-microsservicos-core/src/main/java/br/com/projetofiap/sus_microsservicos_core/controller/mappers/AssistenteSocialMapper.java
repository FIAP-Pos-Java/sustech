package br.com.projetofiap.sus_microsservicos_core.controller.mappers;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.AssistenteSocialDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.BuscarAssistenteSocialDTO;
import br.com.projetofiap.sus_microsservicos_core.model.AssistenteSocial;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {LoginMapper.class, EnderecoMapper.class})
public interface AssistenteSocialMapper {
    AssistenteSocial toEntity(AssistenteSocialDTO dto);
    BuscarAssistenteSocialDTO toDTO(AssistenteSocial assistenteSocial);
}
