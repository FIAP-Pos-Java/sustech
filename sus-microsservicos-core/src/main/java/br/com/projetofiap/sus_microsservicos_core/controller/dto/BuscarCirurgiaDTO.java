package br.com.projetofiap.sus_microsservicos_core.controller.dto;

import br.com.projetofiap.sus_microsservicos_core.model.enums.StatusCirurgia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public record BuscarCirurgiaDTO(
        UUID id,
        UUID pacienteId,
        UUID medicoId,
        LocalDate dataCirurgia,
        LocalTime horaCirurgia,
        String local,
        String descricao,
        StatusCirurgia status,
        LocalDateTime dataAgendamento
) {
}
