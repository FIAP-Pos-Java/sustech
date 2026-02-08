package br.com.projetofiap.sus_microsservicos_core.event;

import java.io.Serializable;
import java.util.UUID;

public record CirurgiaCanceladaEvent(
        UUID cirurgiaId
) implements Serializable {
}
