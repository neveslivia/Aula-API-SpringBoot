package com.example.cadastro_pessoas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro_pessoas.model.ComprasModel;
import com.example.cadastro_pessoas.servicer.ComprasService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/compras")
public class ComprasController {

    
    @Autowired
    private ComprasService comprasService;

    @PostMapping
    public ResponseEntity<ComprasModel> registrar(@RequestBody ComprasModel compra) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(comprasService.registrarCompra(compra));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping
    public List<ComprasModel> listarTodas() {
        return comprasService.listarCompras();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprasModel> buscarPorId(@PathVariable Long id) {
        return comprasService.buscarPorId(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{idPessoa}")
    public List<ComprasModel> listarPorPessoa(@PathVariable Long idPessoa) {
        return comprasService.buscarPorPessoa(idPessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {
        try {
            comprasService.cancelarCompra(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
