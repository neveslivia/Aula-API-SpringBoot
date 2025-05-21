package com.example.cadastro_pessoas.servicer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cadastro_pessoas.exeption.ProdutoException;
import com.example.cadastro_pessoas.model.ComprasModel;
import com.example.cadastro_pessoas.model.IntensCompraModel;
import com.example.cadastro_pessoas.model.ProdutosModel;
import com.example.cadastro_pessoas.repository.ComprasRepository;
import com.example.cadastro_pessoas.repository.IntensCompraRepository;
import com.example.cadastro_pessoas.repository.ProdutosRepository;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository compraRepository;

    @Autowired
    private IntensCompraRepository intensCompraRepository;

    @Autowired
    private ProdutosRepository produtosRepository;

    @Transactional
    public ComprasModel registrarCompra(ComprasModel compra) {
        ComprasModel compraSalva = compraRepository.save(compra);

        for (IntensCompraModel item : compra.getItens()) {
            final ProdutosModel produto = produtosRepository.findById(item.getProdutoId().getId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (produto.getQuantidadeEstoque() < item.getQuantidade()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }

            produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - item.getQuantidade());
            produtosRepository.save(produto);

            item.setCompraId(compraSalva);
            intensCompraRepository.save(item);
        }

        return compraSalva;
    }

    public List<ComprasModel> listarCompras() {
        return compraRepository.findAll();
    }

    public Optional<ComprasModel> buscarPorId(Long id) {
        return compraRepository.findById(id);
    }

    public List<ComprasModel> buscarPorPessoa(Long idPessoa) {
        return compraRepository.findByPessoaId(idPessoa);
    }

    public void cancelarCompra(Long id) {
        if (!compraRepository.existsById(id)) {
            throw new RuntimeException("Compra não encontrada");
        }
        compraRepository.deleteById(id);
    }
}
