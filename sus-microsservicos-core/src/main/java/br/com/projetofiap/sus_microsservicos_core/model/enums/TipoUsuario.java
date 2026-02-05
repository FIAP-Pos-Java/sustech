package br.com.projetofiap.sus_microsservicos_core.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoUsuario {
    MEDICO,
    RECEPCIONISTA,
    PACIENTE,
    ASSISTENTE_SOCIAL;

    @JsonCreator
    public static TipoUsuario fromValue(String value){
        return TipoUsuario.valueOf(value.toUpperCase());
    }
}
