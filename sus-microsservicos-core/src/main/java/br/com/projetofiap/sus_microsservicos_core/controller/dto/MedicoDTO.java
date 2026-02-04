package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.model.Especialidade;
import br.com.projetofiap.sus_microsservicos_core.model.Login;

public record MedicoDTO(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        Login login,
        Endereco endereco
) {
}
