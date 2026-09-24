# AtendeAcademyWeb

Sistema de Gerenciamento de Clinicas e Atendimentos (CRUD) desenvolvido como projeto final da disciplina de **Programacao Orientada a Objetos (3 Semestre)**.

O projeto consiste em uma aplicacao web baseada na arquitetura **MVC (Model-View-Controller)** com o padrao **Command**, permitindo o cadastro, listagem, atualizacao, exclusao e agendamento de consultas.

## Funcionalidades e Atualizacoes Recentes

* **Gerenciamento de Pacientes e Profissionais:** Registro completo com dados pessoais.
* **Agendamento de Consultas:** Sistema de agendamento com validacao de conflitos (impede que duas pessoas agendem o mesmo medico, no mesmo dia e horario).
* **Edicao (Update) Segura:** Interface e backend para atualizar os dados de pacientes e profissionais. Dados sensiveis e imutaveis, como CPF e CRM, sao protegidos (read-only na interface e validados no backend) contra edicoes maliciosas.
* **Design Patterns:** Utilizacao intensiva do padrao **Builder** (ex: `PacienteBuilder`, `ProfissionalBuilder`) que instanciam as classes passando todos os parametros diretamente para seus construtores, alem do padrao **Command** (ex: `EditarPacienteAction`, `CadastrarConsultaAction`) para processar as requisicoes no Controller.
* **Listagem e Busca:** Visualizacao de todos os registros e pesquisas dinamicas.
* **Interface Web:** Design limpo utilizando CSS puro e JSTL para renderizacao dinamica.

## Tecnologias Utilizadas

* **Java (JDK 24)** - Linguagem principal.
* **Jakarta EE 6** (Servlet & JSP) - Desenvolvimento web.
* **Apache Maven** - Gerenciamento de dependencias e build.
* **MySQL** - Banco de dados relacional.
* **JSTL (JSP Standard Tag Library)** - Para logica de apresentacao nas paginas JSP.
* **HTML5 & CSS3** - Front-end.
* **IntelliJ IDEA** - IDE utilizada.

## Estrutura do Projeto (MVC)

O codigo foi organizado seguindo boas praticas de Orientacao a Objetos:

* **`model`**: Classes POJO e Builders que representam as entidades do banco (ex: `Paciente`, `Profissional`, `Consulta`).
* **`dao` (Data Access Object)**: Camada de persistencia responsavel pela comunicacao com o banco de dados (SQL puro via JDBC).
* **`controller`**: Utiliza o padrao Command (`ICommand`) para gerenciar as acoes atraves de um Front Controller (Servlet central).
* **`view` (`webapp`)**: Paginas `.jsp` e arquivos de estilo `.css`.

## Pre-requisitos

Para rodar este projeto, voce precisara de:

* Java JDK instalado.
* Apache Tomcat (versao 10 ou superior, compativel com Jakarta EE).
* MySQL Server instalado e rodando.
* Maven (opcional, pois o projeto inclui o Maven Wrapper).

## Configuracao do Banco de Dados

Crie um banco de dados no MySQL e execute o script `database.sql` contido na raiz do projeto para criar a estrutura necessaria de tabelas (pacientes, profissionais, consultas, servicos).

> [!NOTE]
> Certifique-se de configurar a classe FabricaConexao com seu usuario e senha do banco de dados local.

## Como Executar

Voce pode rodar a aplicacao atraves da sua IDE.

1. Clone o repositorio:
   `git clone https://github.com/igorltsyk/AtendeAcademyWeb.git`

2. Importe o projeto:
   Abra o IntelliJ IDEA e selecione File > Open. Navegue ate a pasta do projeto clonado.

3. Configure o Servidor (Tomcat):
   Va em Run > Edit Configurations.
   Clique no + e selecione Tomcat Server > Local.
   Na aba Deployment, clique no + e selecione Artifact.
   Escolha AtendeAcademyWeb:war exploded.

4. Execute:
   Clique no botao Run ou Debug.
   Acesse no navegador: http://localhost:8080/AtendeAcademyWeb_war_exploded
