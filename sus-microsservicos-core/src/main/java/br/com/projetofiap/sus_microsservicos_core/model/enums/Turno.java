package br.com.projetofiap.sus_microsservicos_core.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Turno {
    MANHA,
    TARDE,
    NOITE;

    @JsonCreator
    public static Turno fromValue(String value){
        return Turno.valueOf(value.toUpperCase());
    }
}
