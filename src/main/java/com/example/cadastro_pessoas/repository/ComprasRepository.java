package com.example.cadastro_pessoas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastro_pessoas.model.ComprasModel;

public interface ComprasRepository  extends JpaRepository<ComprasModel, Long>{
       List<ComprasModel> findByPessoaId(Long pessoaId);

}
