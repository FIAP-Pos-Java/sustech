package br.com.projetofiap.sus_microsservicos_core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario_assistentesocial")
public final class AssistenteSocial extends Usuario{

    private String registro;
}
