package com.example.cadastro_pessoas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.cadastro_pessoas.model.ComprasModel;

public interface ComprasRepository  extends JpaRepository<ComprasModel, Long>{

}
