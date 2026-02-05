package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.model.enums.Especialidade;
import br.com.projetofiap.sus_microsservicos_core.model.Login;

import java.util.UUID;

public record BuscarMedicoDTO(
        UUID id,
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Login login,
        Endereco endereco
) {
}
