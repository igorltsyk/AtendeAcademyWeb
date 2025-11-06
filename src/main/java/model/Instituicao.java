package model;

public class Instituicao {
    private int idinstituicao;
    private String nomeinstituicao;
    private String telefone;
    private String email;
    private Integer idendereco;

    public Instituicao() {}

    public Instituicao(int idinstituicao, String nomeinstituicao, String telefone, String email, Integer idendereco) {
        this.idinstituicao = idinstituicao;
        this.nomeinstituicao = nomeinstituicao;
        this.telefone = telefone;
        this.email = email;
        this.idendereco = idendereco;
    }

    public int getIdinstituicao() {
        return idinstituicao;
    }

    public void setIdinstituicao(int idinstituicao) {
        this.idinstituicao = idinstituicao;
    }

    public String getNomeinstituicao() {
        return nomeinstituicao;
    }

    public void setNomeinstituicao(String nomeinstituicao) {
        this.nomeinstituicao = nomeinstituicao;
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

    public Integer getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(Integer idendereco) {
        this.idendereco = idendereco;
    }
}
