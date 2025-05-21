package com.example.cadastro_pessoas.servicer;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.cadastro_pessoas.model.ProdutoModel;
import com.example.cadastro_pessoas.repository.ProdutoRepository;

@Service
public class ProdutoService {
  private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    public ProdutoModel adicionar(ProdutoModel produto) {
        return repository.save(produto);
    }

    public List<ProdutoModel> listarTodos() {
        return repository.findAll();
    }

    public Optional<ProdutoModel> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public ProdutoModel atualizar(Long id, ProdutoModel novoProduto) {
        return repository.findById(id).map(produto -> {
            produto.setNome(novoProduto.getNome());
            produto.setPreco(novoProduto.getPreco());
            produto.setQuantidadeEstoque(novoProduto.getQuantidadeEstoque());
            produto.setDescricao(novoProduto.getDescricao());
            return repository.save(produto);
        }).orElse(null);
    }

    public void remover(Long id) {
        repository.deleteById(id);
    }
}
