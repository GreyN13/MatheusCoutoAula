package com.cec.primeira.api.paises;

public record DadosListagemPais(Long id, String nome, String ddi) {
    public DadosListagemPais(Pais dados) {
        this(dados.getId(), dados.getNome(), dados.getDdi());
    }
}
