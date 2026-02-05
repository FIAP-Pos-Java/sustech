package br.com.projetofiap.sus_microsservicos_core.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Especialidade {
    GERAL,
    CARDIOVASCULAR,
    NEUROCIRURGIA,
    ORTOPEDIA;

    @JsonCreator
    public static Especialidade fromValue(String value) {
        return Especialidade.valueOf(value.toUpperCase());
    }
}
