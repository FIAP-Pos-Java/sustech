package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.*;
import br.com.projetofiap.sus_microsservicos_core.model.enums.TipoUsuario;
import br.com.projetofiap.sus_microsservicos_core.model.enums.Turno;

public record RecepcionistaDTO(
        String nome,
        String email,
        String matricula,
        Turno turno,
        Login login,
        Endereco endereco,
        TipoUsuario tipousuario
) implements CriarUsuarioDTO {
    @Override
    public TipoUsuario tipoUsuario() {
        return this.tipousuario;
    }
}
