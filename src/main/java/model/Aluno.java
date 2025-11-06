package model;

public class Aluno {
    private int idaluno;
    private String nomealuno;
    private String cpfaluno;
    private String telefone;
    private String email;
    private String senha;
    private Integer idservico;

    public Aluno() {
    }

    public Aluno(int idaluno, String nomealuno, String cpfaluno, String telefone, String email, String senha, Integer idservico) {
        this.idaluno = idaluno;
        this.nomealuno = nomealuno;
        this.cpfaluno = cpfaluno;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.idservico = idservico;
    }

    public int getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(int idaluno) {
        this.idaluno = idaluno;
    }

    public String getNomealuno() {
        return nomealuno;
    }

    public void setNomealuno(String nomealuno) {
        this.nomealuno = nomealuno;
    }

    public String getCpfaluno() {
        return cpfaluno;
    }

    public void setCpfaluno(String cpfaluno) {
        this.cpfaluno = cpfaluno;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }
}
