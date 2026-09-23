package model;

import java.time.LocalDateTime;


public abstract class Pessoa {
    private int id_pessoa;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private LocalDateTime data_nascimento;
    private String genero;
    private String estado_civil;

    protected Pessoa(Builder builder) {
        this.id_pessoa = builder.id_pessoa;
        this.nome = builder.nome;
        this.cpf = builder.cpf;
        this.telefone = builder.telefone;
        this.email = builder.email;
        this.data_nascimento = builder.data_nascimento;
        this.genero = builder.genero;
        this.estado_civil = builder.estado_civil;
    }

    public int getId_pessoa() { return id_pessoa; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
    public LocalDateTime getData_nascimento() { return data_nascimento; }
    public String getGenero() { return genero; }
    public String getEstado_civil() { return estado_civil; }

    public static abstract class Builder {
        protected int id_pessoa;
        protected String nome;
        protected String cpf;
        protected String telefone;
        protected String email;
        protected LocalDateTime data_nascimento;
        protected String genero;
        protected String estado_civil;

        public void comIdPessoa(int id_pessoa) {
            this.id_pessoa = id_pessoa;
        }

        public void comNome(String nome) {
            this.nome = nome;
        }

        public void comCpf(String cpf) {
            this.cpf = cpf;
        }

        public void comTelefone(String telefone) {
            this.telefone = telefone;
        }

        public void comEmail(String email) {
            this.email = email;
        }

        public void comDataNascimento(LocalDateTime data_nascimento) {
            this.data_nascimento = data_nascimento;
        }

        public void comGenero(String genero) {
            this.genero = genero;
        }

        public void comEstadoCivil(String estado_civil) {
            this.estado_civil = estado_civil;
        }

        public abstract Pessoa constroi();
    }
}
