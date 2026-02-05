package br.com.projetofiap.sus_microsservicos_core.model;

import br.com.projetofiap.sus_microsservicos_core.model.enums.Turno;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario_recepcionista")
public final class Recepcionista extends Usuario {

    private String matricula;

    @Enumerated(EnumType.STRING)
    private Turno turno;
}
