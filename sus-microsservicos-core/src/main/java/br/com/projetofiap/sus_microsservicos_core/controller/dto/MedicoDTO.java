package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.model.enums.Especialidade;
import br.com.projetofiap.sus_microsservicos_core.model.Login;
import br.com.projetofiap.sus_microsservicos_core.model.enums.TipoUsuario;

public record MedicoDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Login login,
        Endereco endereco,
        TipoUsuario tipousuario
) implements CriarUsuarioDTO{
    @Override
    public TipoUsuario tipoUsuario() {
        return this.tipousuario;
    }
}
