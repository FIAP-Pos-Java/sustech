package br.com.projetofiap.sus_microsservicos_core.controller.dto;

public record ErroCampoDTO(
        String campo,
        String erro
) {
}
