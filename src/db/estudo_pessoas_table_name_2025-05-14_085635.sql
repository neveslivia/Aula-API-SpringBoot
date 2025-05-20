-- Banco de Estudo

CREATE DATABASE estudo_pessoas;
    DEFAULT CHARACTER SET = 'utf8mb4';
--
-- Table structure for table `table_name`
--
Use estudo_pessoas;

DROP TABLE IF EXISTS `table_name`;

CREATE TABLE `table_name` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NOT NULL DEFAULT current_timestamp() COMMENT 'Create Time',
  `name` varchar(255) NOT NULL,
  `telefone` varchar(15) DEFAULT NULL COMMENT 'Telefone no formato XXXXXXXXXXX',
  `endereco` varchar(150) DEFAULT NULL COMMENT 'Endereço Completo',
  `imagem_perfil` varchar(2083) DEFAULT NULL COMMENT 'URL da Imagem de Perfil',
  PRIMARY KEY (`id`)
);
CREATE TABLE `compras`(
`id` bigint(20) primary key auto_increment,
`data_compra` date not null,
`pessoa_id` BIGINT(20) not null,
FOREIGN KEY (pessoa_id) REFERENCES `table_name`(id));

CREATE TABLE `itens_compra`(
`id` bigint(20) primary key auto_increment,
`compra_id` bigint(20) not null,
`produto_id` bigint(20) not null,
`quantidade` int NOT NULL,
`preco_unitario` decimal(10,2) NOT NULL,
FOREIGN KEY (compra_id) REFERENCES `compras`(id),
 FOREIGN KEY (produto_id) REFERENCES `produtos`(id));

CREATE TABLE `produtos`(
  `id` bigint(20) not null auto_increment primary key,
  `nome` VARCHAR(200) not null,
  `preco` DECIMAL(10,2),
  `quantidadeEstoque` BIGINT(20) not null,
  `descricao` VARCHAR(200));





-- Inserção de dados de exemplo
INSERT INTO table_name (name, telefone, endereco, imagem_perfil) VALUES
('João Silva', '(31) 91234-5678', 'Rua das Flores, 123', 'https://imagem.com/perfil.png'),
('Maria Oliveira', '(31) 99887-1234', 'Avenida Central, 456', 'https://imagem.com/perfil2.png');

/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-14  8:56:50
