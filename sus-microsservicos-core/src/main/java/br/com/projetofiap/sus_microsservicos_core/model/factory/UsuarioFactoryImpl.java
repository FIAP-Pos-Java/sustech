package br.com.projetofiap.sus_microsservicos_core.model.factory;

import br.com.projetofiap.sus_microsservicos_core.controller.dto.AssistenteSocialDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.CriarUsuarioDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.MedicoDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.PacienteDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.dto.RecepcionistaDTO;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.AssistenteSocialMapper;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.MedicoMapper;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.PacienteMapper;
import br.com.projetofiap.sus_microsservicos_core.controller.mappers.RecepcionistaMapper;
import br.com.projetofiap.sus_microsservicos_core.model.Usuario;
import br.com.projetofiap.sus_microsservicos_core.model.enums.TipoUsuario;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioFactoryImpl implements IUsuarioFactory{

    private final MedicoMapper medicoMapper;
    private final RecepcionistaMapper recepcionistaMapper;
    private final PacienteMapper pacienteMapper;
    private final AssistenteSocialMapper assistenteSocialMapper;
    private static final String TIPO_USUARIO_INVALIDO = "você passou um tipo de usuário que não existe: ";

    @Override
    public Usuario criandoUsuario(CriarUsuarioDTO dto) {
        return switch (dto.tipoUsuario()){
            case TipoUsuario.MEDICO -> this.medicoMapper.toEntity((MedicoDTO) dto);
            case TipoUsuario.RECEPCIONISTA -> this.recepcionistaMapper.toEntity((RecepcionistaDTO) dto);
            case TipoUsuario.PACIENTE -> this.pacienteMapper.toEntity((PacienteDTO) dto);
            case TipoUsuario.ASSISTENTE_SOCIAL -> this.assistenteSocialMapper.toEntity((AssistenteSocialDTO) dto);
            default -> throw new ClassCastException(TIPO_USUARIO_INVALIDO+dto.tipoUsuario());
        };
    }
}
