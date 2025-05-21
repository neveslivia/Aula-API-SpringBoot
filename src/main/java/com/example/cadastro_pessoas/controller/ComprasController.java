package com.example.cadastro_pessoas.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cadastro_pessoas.model.CompraModel;
import com.example.cadastro_pessoas.servicer.CompraService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("api/compras")
public class ComprasController {

    private final CompraService service;

    public ComprasController(CompraService service) {
        this.service = service;
    }

    @PostMapping
    public CompraModel registrarCompra(@RequestBody CompraModel compra) {
        return service.registrarCompra(compra);
    }

    @GetMapping
    public List<CompraModel> listarCompras() {
        return service.listarCompras();
    }

    @GetMapping("/{id}")
    public CompraModel buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @GetMapping("/pessoa/{idPessoa}")
    public List<CompraModel> listarPorPessoa(@PathVariable Long idPessoa) {
        return service.listarPorPessoa(idPessoa);
    }

    @DeleteMapping("/{id}")
    public void cancelarCompra(@PathVariable Long id) {
        service.cancelarCompra(id);
    }
}
