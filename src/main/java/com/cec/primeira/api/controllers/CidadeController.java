package com.cec.primeira.api.controllers;

import com.cec.primeira.api.cidades.Cidade;
import com.cec.primeira.api.cidades.CidadeRepositorio;
import com.cec.primeira.api.cidades.DadosAlteracaoCidade;
import com.cec.primeira.api.cidades.DadosCadastroCidade;
import com.cec.primeira.api.cidades.DadosListagemCidade;
import com.cec.primeira.api.paises.Pais;
import com.cec.primeira.api.paises.PaisRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @Autowired
    private PaisRepositorio paisRepositorio;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody DadosCadastroCidade dados) {
        if (!paisRepositorio.existsById(dados.paisId())) {
            return ResponseEntity.notFound().build();
        }
        Pais p = paisRepositorio.getReferenceById(dados.paisId());
        Cidade c = cidadeRepositorio.save(new Cidade(dados, p));
        Long id = c.getId();
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/{id}").buildAndExpand(id).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        var lista = cidadeRepositorio.findAll().stream().map(DadosListagemCidade::new).toList();
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> alterar(@RequestBody DadosAlteracaoCidade dados) {
        if (!paisRepositorio.existsById(dados.paisId())) {
            return ResponseEntity.notFound().build();
        }
        if (!cidadeRepositorio.existsById(dados.id())) {
            return ResponseEntity.notFound().build();
        }
        Cidade c = cidadeRepositorio.getReferenceById(dados.id());
        Pais p = paisRepositorio.getReferenceById(dados.paisId());
        c.atualizaInformacoes(dados, p);
        return ResponseEntity.ok(dados);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> excluir(@PathVariable Long id) {
        if (!cidadeRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cidadeRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
