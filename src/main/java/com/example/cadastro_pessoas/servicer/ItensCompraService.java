package com.example.cadastro_pessoas.servicer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro_pessoas.exeption.ProdutoException;
import com.example.cadastro_pessoas.model.IntensCompraModel;
import com.example.cadastro_pessoas.repository.IntensCompraRepository;

@Service
public class ItensCompraService {


    
    @Autowired
    IntensCompraRepository intensCompraRepository;


    public IntensCompraModel salvarItens(IntensCompraModel intensCompraModel){
        if(intensCompraModel.getCompraId() == null){
            throw new ProdutoException("O item não pode ser cadastrado sem ser atrelado a uma compra!");
        }
        if (intensCompraModel.getPrecoUnitario() <=0) {
            throw new ProdutoException("O preço do item deve ser maior que zero!");
        }
        if (intensCompraModel.getProdutoId() == null) {
            throw new ProdutoException("O ite!");
            
        }
        return intensCompraRepository.save(intensCompraModel);

    }

    public List<IntensCompraModel> listarIntensCompraModels(){
        return intensCompraRepository.findAll();
    }
    public Optional<IntensCompraModel> buscarItem(Long id){
        return intensCompraRepository.findById(id);

    
    }

    public void deletarItem(Long id){
        intensCompraRepository.deleteById(id);
    }
}
