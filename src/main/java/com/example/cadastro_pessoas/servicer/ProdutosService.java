package com.example.cadastro_pessoas.servicer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro_pessoas.exeption.ProdutoException;
import com.example.cadastro_pessoas.model.ProdutosModel;
import com.example.cadastro_pessoas.repository.ProdutosRepository;

@Service
public class ProdutosService {

    @Autowired
    ProdutosRepository produtosRepository;


    public ProdutosModel salvarProdutos(ProdutosModel produtosModel){
        if(produtosModel.getNome() == null){
            throw new ProdutoException("O produto não pode ser cadastrado sem um nome!");
        }
        if (produtosModel.getPreco() <=0) {
            throw new ProdutoException("O preço do produto deve ser maior que zero!");
        }
        
        return produtosRepository.save(produtosModel);

    }

    public List<ProdutosModel> listarProdutos(){
        return produtosRepository.findAll();
    }
    public Optional<ProdutosModel> buscarProduto(Long id){
        return produtosRepository.findById(id);

    
    }

    public void deletarProduto(Long id){
        produtosRepository.deleteById(id);
    }


}
