package com.cec.primeira.api.cidades;

public record DadosListagemCidade(Long id, String nome, String ddd, Long paisId) {
    public DadosListagemCidade(Cidade dados) {
        this(dados.getId(), dados.getNome(), dados.getDdd(), dados.getPais().getId());
    }
}
