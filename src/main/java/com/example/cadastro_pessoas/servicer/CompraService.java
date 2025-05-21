package com.example.cadastro_pessoas.servicer;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.cadastro_pessoas.model.CompraModel;
import com.example.cadastro_pessoas.repository.CompraRepository;
import com.example.cadastro_pessoas.repository.ProdutoRepository;

@Service
public class CompraService {

     private final CompraRepository compraRepo;
    private final ProdutoRepository produtoRepo;

    public CompraService(CompraRepository compraRepo, ProdutoRepository produtoRepo) {
        this.compraRepo = compraRepo;
        this.produtoRepo = produtoRepo;
    }

    public CompraModel registrarCompra(CompraModel compra) {
        compra.setDataCompra(LocalDateTime.now());

        compra.getItens().forEach(item -> {
            produtoRepo.findById(item.getProdutoId()).ifPresent(produto -> {
                produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
                produtoRepo.save(produto);
                item.setPrecoUnitario(produto.getPreco());
                item.setCompra(compra);
            });
        });

        return compraRepo.save(compra);
    }

    public List<CompraModel> listarCompras() {
        return compraRepo.findAll();
    }

    public CompraModel buscarPorId(Long id) {
        return compraRepo.findById(id).orElse(null);
    }

    public List<CompraModel> listarPorPessoa(Long idPessoa) {
        return compraRepo.findByPessoaId(idPessoa);
    }

    public void cancelarCompra(Long id) {
        compraRepo.deleteById(id);
    }
}
