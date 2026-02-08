package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.model.Login;
import br.com.projetofiap.sus_microsservicos_core.model.enums.Turno;

import java.util.UUID;

public record BuscarAssistenteSocialDTO(
        UUID id,
        String nome,
        String email,
        String matricula,
        String telefoneContato,
        Turno turno,
        Login login,
        Endereco endereco
) {
}
