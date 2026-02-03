package br.com.projetofiap.sus_microsservicos_core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_endereco")
@Entity
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String rua;
    private String numero;
    private String bairro;
    private String cidade;
    private String estado;
    private String cep;

//    @ManyToOne
//    @JoinColumn(name = "usuario_id")
//    private Usuario usuario;
}
