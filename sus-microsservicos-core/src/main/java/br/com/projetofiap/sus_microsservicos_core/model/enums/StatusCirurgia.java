package br.com.projetofiap.sus_microsservicos_core.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum StatusCirurgia {
    AGENDADA,
    REALIZADA,
    CANCELADA;

    @JsonCreator
    public static StatusCirurgia fromValue(String value) {
        return StatusCirurgia.valueOf(value.toUpperCase());
    }
}
