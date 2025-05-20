package com.example.cadastro_pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro_pessoas.model.IntensCompraModel;
import com.example.cadastro_pessoas.servicer.ItensCompraService;

@RestController
@RequestMapping("api/itens-compra")

public class ItensCompraController {
    @Autowired 

      ItensCompraService itensCompraService;
      IntensCompraModel  intensCompraModel;
      @GetMapping
     public List<IntensCompraModel> listarItensCompra() {
        return itensCompraService.listarIntensCompraModels();
     }

     @GetMapping("/{id}")
     public ResponseEntity<IntensCompraModel> buscarItemCompra(@PathVariable Long id) {
        return itensCompraService.buscarItem(id)
         .map(ResponseEntity :: ok)
         .orElse(ResponseEntity.notFound().build());
     }
    

    @PostMapping
    public IntensCompraModel salvarItemCompra(@RequestBody IntensCompraModel intensCompraModel) {   
        return itensCompraService.salvarItens(intensCompraModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>deletar(@PathVariable Long id){
        if(!itensCompraService.buscarItem(id).isPresent()){
            return ResponseEntity.notFound().build();
        }
        itensCompraService.deletarItem(id);
        return ResponseEntity.noContent().build();

    }


    @PutMapping("/{id}")
    public ResponseEntity<IntensCompraModel> atualizarItemCompra(@PathVariable Long id, @RequestBody IntensCompraModel intensCompraModel) {
        if (!itensCompraService.buscarItem(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        intensCompraModel.setId(id);
        return ResponseEntity.ok(itensCompraService.salvarItens(intensCompraModel));
        
    }


}
