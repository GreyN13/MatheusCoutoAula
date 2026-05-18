package com.cec.primeira.api.paises;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "pais")
@Entity(name = "paises")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pais {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String ddi;

    public Pais(DadosCadastroPais dados) {
        this.nome = dados.nome();
        this.ddi = dados.ddi();
    }

    public void atualizaInformacoes(DadosAlteracaoPais dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.ddi() != null) {
            this.ddi = dados.ddi();
        }
    }
}
