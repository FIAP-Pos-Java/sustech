package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.model.Login;
import br.com.projetofiap.sus_microsservicos_core.model.enums.TipoUsuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PacienteDTO(
        @NotBlank
        String nome,

        String email,

        @NotBlank
        String cpf,

        String telefone,

        @NotNull
        LocalDate dataNascimento,

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
