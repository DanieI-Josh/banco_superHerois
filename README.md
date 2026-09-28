# Sistema de Gerenciamento de Super-Heróis

Projeto desenvolvido em Java para praticar desenvolvimento de aplicações integradas a banco de dados.

O sistema permite realizar o cadastro e gerenciamento de super-heróis e cidades, utilizando MySQL como banco de dados e JDBC para realizar a comunicação entre a aplicação Java e o banco.

## Tecnologias utilizadas

* Java
* MySQL
* JDBC
* SQL
* XAMPP

## Funcionalidades

O sistema possui operações de CRUD para heróis e cidades.

### Heróis

* Cadastrar herói
* Listar heróis
* Buscar herói por ID
* Buscar heróis por nome
* Buscar heróis por cidade
* Atualizar informações de um herói
* Excluir herói

### Cidades

* Cadastrar cidade
* Listar cidades
* Buscar cidade
* Atualizar cidade
* Excluir cidade

## Banco de dados

O banco de dados foi desenvolvido utilizando MySQL e executado localmente através do XAMPP.

O projeto possui duas tabelas principais:

* `cidade`
* `herois`

A tabela `herois` possui uma relação com a tabela `cidade` através da chave estrangeira `id_cidade`.

O arquivo SQL utilizado para criar e inserir os dados do banco está disponível na pasta:

```text
sql/superherois.sql
```

## Estrutura do projeto

```text
src/
├── Cidade.java
├── CidadeDAO.java
├── Conexao.java
├── Heroi.java
├── HeroiDAO.java
└── Principal.java

sql/
└── superherois.sql
```

## JDBC e DAO

Para realizar a comunicação com o banco de dados foi utilizado JDBC.

As operações de acesso aos dados foram organizadas utilizando o padrão DAO, separando a lógica de acesso ao banco das demais partes da aplicação.

A classe `Conexao.java` é responsável pela conexão com o banco de dados.

A classe `HeroiDAO.java` contém as operações relacionadas aos heróis.

A classe `CidadeDAO.java` contém as operações relacionadas às cidades.

## SQL

Durante o desenvolvimento foram utilizados conceitos de SQL como:

* `CREATE TABLE`
* `INSERT`
* `SELECT`
* `UPDATE`
* `DELETE`
* `WHERE`
* `LIKE`
* `PRIMARY KEY`
* `FOREIGN KEY`
* `AUTO_INCREMENT`

Também foram utilizados `PreparedStatement` e `ResultSet` através do JDBC.

## Como executar

### 1. Pré-requisitos

É necessário ter instalado:

* Java JDK
* XAMPP
* MySQL/MariaDB
* MySQL Connector/J

### 2. Iniciar o banco de dados

Abra o XAMPP e inicie o serviço MySQL.

Depois, abra o phpMyAdmin e importe o arquivo:

```text
sql/superherois.sql
```

### 3. Configurar a conexão

Verifique as configurações do banco no arquivo:

```text
src/Conexao.java
```

É necessário conferir o endereço, porta, nome do banco, usuário e senha utilizados pelo MySQL.

### 4. Executar o projeto

Execute a classe:

```text
Principal.java
```

## Objetivo

O objetivo deste projeto foi praticar o desenvolvimento de uma aplicação Java integrada a um banco de dados relacional, utilizando orientação a objetos, SQL, JDBC, CRUD e o padrão DAO.

## Autor

Daniel Josh Santos
