package com.example.cadastro_pessoas.servicer;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cadastro_pessoas.exeption.ProdutoException;
import com.example.cadastro_pessoas.model.ComprasModel;
import com.example.cadastro_pessoas.repository.ComprasRepository;

@Service
public class ComprasService {

     @Autowired
     ComprasRepository comprasRepository;


       public ComprasModel salvarCompras(ComprasModel comprasModel){
        if(comprasModel.getDataCompra() == null){
            throw new ProdutoException(" A compra não pode ser cadastrada sem uma data!");
        }
        if (comprasModel.getPessoaId() == null) {
            throw new ProdutoException("A compra não pode ser cadastrada sem um comprador!");
        }
        return comprasRepository.save(comprasModel);

    }

    public List<ComprasModel> listarCompras(){
        return comprasRepository.findAll();
    }
    public Optional<ComprasModel> buscarCompra(Long id){
        return comprasRepository.findById(id);

    
    }

    public void deletarCompra(Long id){
        comprasRepository.deleteById(id);
    }

}
