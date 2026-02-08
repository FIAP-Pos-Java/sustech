package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.model.Login;

import java.time.LocalDate;
import java.util.UUID;

public record BuscarPacienteDTO(
        UUID id,
        String nome,
        String email,
        String cpf,
        String telefone,
        LocalDate dataNascimento,
        Login login,
        Endereco endereco
) {
}
