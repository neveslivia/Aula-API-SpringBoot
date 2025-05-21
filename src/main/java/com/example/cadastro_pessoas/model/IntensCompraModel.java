package com.example.cadastro_pessoas.model;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "itens_compra")
public class IntensCompraModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private ComprasModel compraId;
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private ProdutosModel produtoId;
    private Integer quantidade;
    private double precoUnitario;


}
