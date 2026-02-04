package br.com.projetofiap.sus_microsservicos_core.model;

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
@Table(name = "tb_usuario_medico")
public final class Medico extends Usuario{

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

}
