package com.example.cadastro_pessoas.util;

import java.util.List;

import com.example.cadastro_pessoas.model.CompraModel;
import com.example.cadastro_pessoas.model.ItemCompraModel;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class CompraRequest {

    private CompraModel compra;
    private List<ItemCompraModel> itens;
}
