package com.example.cadastro_pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro_pessoas.model.ProdutosModel;
import com.example.cadastro_pessoas.servicer.ProdutosService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/produtos")
public class ProdutosController {

    @Autowired 

    ProdutosService produtosService;

    @GetMapping
    public List<ProdutosModel> listarProdutos() {
        return produtosService.listarProdutos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutosModel> buscarProdutos(@PathVariable Long id) {
        return produtosService.buscarProduto(id)
         .map(ResponseEntity :: ok)
         .orElse(ResponseEntity.notFound().build());
    }
    

    @PostMapping
    public ProdutosModel salvarProduto(@RequestBody ProdutosModel produtosModel) {   
        return produtosService.salvarProdutos(produtosModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        if(!produtosService.buscarProduto(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        produtosService.deletarProduto(id);
        return ResponseEntity.noContent().build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<ProdutosModel> atualizarProduto(@PathVariable Long id, @RequestBody ProdutosModel produtosModel) {
        if (!produtosService.buscarProduto(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        produtosModel.setId(id);
        return ResponseEntity.ok(produtosService.salvarProdutos(produtosModel));
        
    }





}
