package br.com.projetofiap.sus_microsservicos_core.event;

import br.com.projetofiap.sus_microsservicos_core.model.enums.StatusCirurgia;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

public record CirurgiaAtualizadaEvent(
        UUID cirurgiaId,
        UUID pacienteId,
        UUID medicoId,
        LocalDate dataCirurgia,
        LocalTime horaCirurgia,
        String local,
        String descricao,
        StatusCirurgia status
) implements Serializable {
}
