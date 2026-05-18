package com.cec.primeira.api.cidades;

import com.cec.primeira.api.paises.Pais;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "cidade")
@Entity(name = "cidades")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String ddd;

    @ManyToOne
    @JoinColumn(name = "pais_id")
    private Pais pais;

    public Cidade(DadosCadastroCidade dados, Pais pais) {
        this.nome = dados.nome();
        this.ddd = dados.ddd();
        this.pais = pais;
    }

    public void atualizaInformacoes(DadosAlteracaoCidade dados, Pais pais) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.ddd() != null) {
            this.ddd = dados.ddd();
        }
        if (pais != null) {
            this.pais = pais;
        }
    }
}
