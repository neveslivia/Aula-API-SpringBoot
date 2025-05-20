// Pacote onde a classe está localizada
package com.example.cadastro_pessoas.model;

// Importações necessárias para o mapeamento JPA e Lombok
import jakarta.persistence.Column;           // Define colunas no banco de dados
import jakarta.persistence.Entity;           // Define esta classe como uma entidade JPA
import jakarta.persistence.GeneratedValue;   // Define geração automática de IDs
import jakarta.persistence.GenerationType;   // Define a estratégia de geração de IDs
import jakarta.persistence.Id;               // Define o identificador (Primary Key) da entidade
import jakarta.persistence.PrePersist;       // Executa lógica antes de salvar no banco de dados
import jakarta.persistence.Table;            // Define o nome da tabela no banco de dados
import lombok.Getter;                        // Lombok - Gera os métodos GET automaticamente
import lombok.NoArgsConstructor;             // Lombok - Gera um construtor sem argumentos
import lombok.Setter;                        // Lombok - Gera os métodos SET automaticamente

// Anotação para informar que esta classe representa uma entidade do banco de dados
@Entity

// Anotação para definir o nome da tabela que será criada no banco
@Table(name = "table_name") // Aqui você pode mudar para o nome desejado, por exemplo, "pessoas"

// Lombok - Gera automaticamente os métodos getters e setters
@Getter
@Setter

// Lombok - Gera um construtor sem argumentos
@NoArgsConstructor
public class PessoasModel {

    /**
     * Anotação que define o campo 'id' como a chave primária (Primary Key) da tabela.
     * A estratégia de geração é IDENTITY, que significa que o valor é gerado pelo banco de dados.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Define a coluna 'name' no banco de dados.
     * - `nullable = false`: Essa coluna não pode ser nula.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Define a coluna 'telefone' no banco de dados.
     * - `length = 15`: Limita o tamanho do campo para 15 caracteres.
     * - Não é obrigatório (não possui nullable = false), então pode ser nulo.
     */
    @Column(length = 15)
    private String telefone;

    /**
     * Define a coluna 'endereco' no banco de dados.
     * - `length = 150`: Limita o tamanho do campo para 150 caracteres.
     */
    @Column(length = 150)
    private String endereco;

    /**
     * Define a coluna 'imagemPerfil' no banco de dados.
     * - `length = 2083`: Limita o tamanho do campo para 2083 caracteres, ideal para URLs longas.
     */
    @Column(length = 2083)
    private String imagemPerfil;

    /**
     * Define a coluna 'createTime' no banco de dados.
     * - `nullable = false`: Não pode ser nula.
     * - `updatable = false`: O valor não pode ser atualizado após a criação.
     * - Representa a data e hora de criação do registro.
     */
    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime createTime;

    /**
     * Método anotado com @PrePersist, que é executado antes de inserir o objeto no banco de dados.
     * Aqui, estamos definindo que o campo `createTime` vai receber a data e hora atuais no momento da persistência.
     */
    @PrePersist
    public void prePersist() {
        this.createTime = java.time.LocalDateTime.now();
    }
    
}
