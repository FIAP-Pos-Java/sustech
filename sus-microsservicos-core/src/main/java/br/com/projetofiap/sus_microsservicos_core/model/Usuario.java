package br.com.projetofiap.sus_microsservicos_core.model;

import br.com.projetofiap.sus_microsservicos_core.model.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String nome;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login_id", referencedColumnName = "id")
    private Login login;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", referencedColumnName = "id")
    private Endereco endereco;

    private TipoUsuario tipoUsuario;
}
