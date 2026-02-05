package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.Endereco;
import br.com.projetofiap.sus_microsservicos_core.model.Login;
import br.com.projetofiap.sus_microsservicos_core.model.enums.Turno;

import java.util.UUID;

public record BuscarRecepcionistaDTO(
        UUID id,
        String nome,
        String email,
        String matricula,
        Turno turno,
        Login login,
        Endereco endereco
) {
}
