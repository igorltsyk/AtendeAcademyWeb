# AtendeAcademyWeb 🏥

Sistema de Gerenciamento de Pacientes (CRUD) desenvolvido como projeto final da disciplina de **Programação Orientada a Objetos (3º Semestre)**.

O projeto consiste em uma aplicação web baseada na arquitetura **MVC (Model-View-Controller)**, permitindo o cadastro, listagem, atualização, exclusão e busca de pacientes.

## Funcionalidades

* **Cadastro de Pacientes:** Registro completo com dados pessoais (Nome, CPF, Idade, Email, Telefone, Gênero e Estado Civil).
* **Listagem (Read):** Visualização de todos os pacientes cadastrados em uma tabela responsiva.
* **Busca:** Pesquisa dinâmica de pacientes por **Nome** ou **CPF**.
* **Edição (Update):** Interface para atualizar os dados de um paciente existente.
* **Exclusão (Delete):** Remoção de registros com confirmação de segurança.
* **Interface Web:** Design limpo utilizando CSS puro e JSTL para renderização dinâmica.

## Tecnologias Utilizadas

* **Java (JDK 24)** - Linguagem principal.
* **Jakarta EE 6** (Servlet & JSP) - Desenvolvimento web.
* **Apache Maven** - Gerenciamento de dependências e build.
* **MySQL** - Banco de dados relacional.
* **JSTL (JSP Standard Tag Library)** - Para lógica de apresentação nas páginas JSP.
* **HTML5 & CSS3** - Front-end (Estilização com Flexbox).
* **IntelliJ IDEA** - IDE utilizada.

## Estrutura do Projeto (MVC)

O código foi organizado seguindo boas práticas de Orientação a Objetos:

* **`model`**: Classes que representam as entidades do banco (ex: `Paciente.java`).
* **`dao` (Data Access Object)**: Camada de persistência responsável pela comunicação com o banco de dados (SQL puro via JDBC).
* **`controller`**: Servlets que gerenciam as requisições HTTP e a lógica de navegação (`PacienteServlet`, `CadastroServlet`).
* **`view` (`webapp`)**: Páginas `.jsp` e arquivos de estilo `.css`.

## Pré-requisitos

Para rodar este projeto, você precisará de:

* Java JDK instalado.
* Apache Tomcat (versão 10 ou superior, compatível com Jakarta EE).
* MySQL Server instalado e rodando.
* Maven (opcional, pois o projeto inclui o Maven Wrapper).

## Configuração do Banco de Dados

Crie um banco de dados no MySQL e execute o seguinte script para criar a tabela necessária:

```sql
CREATE DATABASE atende_academy;
USE atende_academy;

CREATE TABLE paciente (
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,
    nome_paciente VARCHAR(100) NOT NULL,
    cpf_paciente VARCHAR(14) NOT NULL,
    idade INT,
    telefone VARCHAR(20),
    email VARCHAR(100),
    senha VARCHAR(255),
    estado_civil VARCHAR(20),
    genero VARCHAR(20)
);
```

> [!Nota]
> Certifique-se de configurar a classe conexao.FabricaConexao com seu usuário e senha do banco de dados local.


Como Executar

Você pode rodar a aplicação através da sua IDE.


Clone o repositório:

git clone [https://github.com/seu-usuario/AtendeAcademyWeb.git](https://github.com/seu-usuario/AtendeAcademyWeb.git)

Importe o projeto:

Abra o IntelliJ IDEA e selecione File > Open.

Navegue até a pasta do projeto clonado.

Configure o Servidor (Tomcat):

Vá em Run > Edit Configurations.

Clique no + e selecione Tomcat Server > Local.

Na aba Deployment, clique no + e selecione Artifact.

Escolha AtendeAcademyWeb:war exploded.

Execute:

Clique no botão Run (▶️) ou Debug.

Acesse no navegador: http://localhost:8080/AtendeAcademyWeb_war_exploded




