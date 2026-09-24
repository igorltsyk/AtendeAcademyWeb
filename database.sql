CREATE DATABASE IF NOT EXISTS atendeacademy;
USE atendeacademy;

DROP TABLE IF EXISTS consulta_servico;
DROP TABLE IF EXISTS consultas;
DROP TABLE IF EXISTS servicos;
DROP TABLE IF EXISTS profissionais;
DROP TABLE IF EXISTS pacientes;

-- -----------------------------------------------------
-- Tabela: pacientes
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pacientes (
  id_pessoa INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  cpf VARCHAR(14) NOT NULL UNIQUE,
  telefone VARCHAR(20) DEFAULT NULL,
  email VARCHAR(100) DEFAULT NULL,
  data_nascimento DATETIME DEFAULT NULL,
  genero VARCHAR(20) DEFAULT NULL,
  estado_civil VARCHAR(30) DEFAULT NULL,
  senha VARCHAR(255) NOT NULL,
  maior_de_idade TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id_pessoa)
);

-- -----------------------------------------------------
-- Tabela: profissionais
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS profissionais (
  id_pessoa INT NOT NULL AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  cpf VARCHAR(14) NOT NULL UNIQUE,
  telefone VARCHAR(20) DEFAULT NULL,
  email VARCHAR(100) DEFAULT NULL,
  data_nascimento DATETIME DEFAULT NULL,
  genero VARCHAR(20) DEFAULT NULL,
  estado_civil VARCHAR(30) DEFAULT NULL,
  especialidade VARCHAR(100) NOT NULL,
  crm VARCHAR(20) NOT NULL UNIQUE,
  senha VARCHAR(255) NOT NULL,
  status_disponibilidade TINYINT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (id_pessoa)
);

-- -----------------------------------------------------
-- Tabela: servicos
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS servicos (
  id_servico INT NOT NULL AUTO_INCREMENT,
  nome_servico VARCHAR(100) NOT NULL,
  descricao TEXT DEFAULT NULL,
  valor_servico DECIMAL(10,2) NOT NULL,
  duracao_servico_minutos INT NOT NULL,
  PRIMARY KEY (id_servico)
);

-- -----------------------------------------------------
-- Tabela: consultas
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS consultas (
  id_consulta INT NOT NULL AUTO_INCREMENT,
  id_paciente INT NOT NULL,
  id_profissional INT NOT NULL,
  data_hora DATETIME NOT NULL,
  status VARCHAR(30) NOT NULL,
  observacoes TEXT DEFAULT NULL,
  PRIMARY KEY (id_consulta),
  CONSTRAINT fk_consulta_paciente
    FOREIGN KEY (id_paciente)
    REFERENCES pacientes (id_pessoa)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_consulta_profissional
    FOREIGN KEY (id_profissional)
    REFERENCES profissionais (id_pessoa)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Tabela Associativa: consulta_servico
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS consulta_servico (
  id_consulta INT NOT NULL,
  id_servico INT NOT NULL,
  PRIMARY KEY (id_consulta, id_servico),
  CONSTRAINT fk_cs_consulta
    FOREIGN KEY (id_consulta)
    REFERENCES consultas (id_consulta)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT fk_cs_servico
    FOREIGN KEY (id_servico)
    REFERENCES servicos (id_servico)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);
