package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import java.util.List;

public record ErroRespostaDTO(
        int status,
        String mensagem,
        List<ErroCampoDTO> erros
) {
}
