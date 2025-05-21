package com.example.cadastro_pessoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastro_pessoas.model.CompraModel;

public interface CompraRepository  extends JpaRepository<CompraModel, Long>{
       List<CompraModel> findByPessoaId(Long pessoaId);

}
