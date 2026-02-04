package br.com.projetofiap.sus_microsservicos_core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_login")
@Entity
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String userName;
    private String password;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
}
