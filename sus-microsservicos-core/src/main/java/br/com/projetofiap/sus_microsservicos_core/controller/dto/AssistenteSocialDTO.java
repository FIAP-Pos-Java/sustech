package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.model.Login;
import br.com.projetofiap.sus_microsservicos_core.model.enums.TipoUsuario;
import br.com.projetofiap.sus_microsservicos_core.model.enums.Turno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AssistenteSocialDTO(
        @NotBlank
        String nome,

        String email,

        @NotBlank
        String matricula,

        @NotBlank
        String telefoneContato,

        @NotNull
        Turno turno,

        @NotNull
        Login login,

        @NotNull
        Endereco endereco,

        @NotNull
        TipoUsuario tipousuario
) implements CriarUsuarioDTO {
    @Override
    public TipoUsuario tipoUsuario() {
        return this.tipousuario;
    }
}
