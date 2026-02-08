package br.com.projetofiap.sus_microsservicos_core.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_usuario_paciente")
public final class Paciente extends Usuario {

    private String cpf;
    private String telefone;
    private LocalDate dataNascimento;
}
