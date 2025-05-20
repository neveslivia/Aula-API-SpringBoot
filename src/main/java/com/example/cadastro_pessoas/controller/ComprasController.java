package com.example.cadastro_pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro_pessoas.model.ComprasModel;
import com.example.cadastro_pessoas.servicer.ComprasService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/compras")
public class ComprasController {

     @Autowired 

     ComprasService comprasService;
     ComprasModel comprasModel;
      @GetMapping
     public List<ComprasModel> listarCompras() {
        return comprasService.listarCompras();
     }

     @GetMapping("/{id}")
     public ResponseEntity<ComprasModel> buscarCompras(@PathVariable Long id) {
        return comprasService.buscarCompra(id)
         .map(ResponseEntity :: ok)
         .orElse(ResponseEntity.notFound().build());
     }
    

    @PostMapping
    public ComprasModel salvarCompra(@RequestBody ComprasModel comprasModel) {   
        return comprasService.salvarCompras(comprasModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        if(!comprasService.buscarCompra(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        comprasService.deletarCompra(id);
        return ResponseEntity.noContent().build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<ComprasModel> atualizarCompra(@PathVariable Long id, @RequestBody ComprasModel comprasModel) {
        if (!comprasService.buscarCompra(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        comprasModel.setId(id);
        return ResponseEntity.ok(comprasService.salvarCompras(comprasModel));
        
    }

}
