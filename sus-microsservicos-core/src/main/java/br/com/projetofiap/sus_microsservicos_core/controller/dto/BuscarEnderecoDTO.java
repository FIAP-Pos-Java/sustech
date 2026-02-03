package br.com.projetofiap.sus_microsservicos_core.controller.dto;

public record BuscarEnderecoDTO(String rua, String numero, String bairro, String cidade, String estado, String cep) {
}
