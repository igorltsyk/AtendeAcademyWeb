package model;

public class Paciente {
    private int idpaciente;
    private String nomepaciente;
    private String cpfpaciente;
    private int idade;
    private String telefone;
    private String email;
    private String senha;
    private String genero;
    private String estadocivil;

    public Paciente() {}

    public Paciente(int idpaciente, String nomepaciente, String cpfpaciente, int idade, String telefone, String email, String senha, String estadocivil, String genero) {
        this.idpaciente = idpaciente;
        this.nomepaciente = nomepaciente;
        this.cpfpaciente = cpfpaciente;
        this.idade = idade;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.estadocivil = estadocivil;
        this.genero = genero;
    }

    public String getGenero() {return genero;   }

    public String getEstadocivil() {return estadocivil;    }

    public void setGenero(String genero) {this.genero = genero; }

    public void setEstadocivil(String estadocivil) {this.estadocivil = estadocivil;}

    public int getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(int idpaciente) { this.idpaciente = idpaciente;}

    public String getNomepaciente() {
        return nomepaciente;
    }

    public void setNomepaciente(String nomepaciente) {
        this.nomepaciente = nomepaciente;
    }

    public String getCpfpaciente() {
        return cpfpaciente;
    }

    public void setCpfpaciente(String cpfpaciente) {
        this.cpfpaciente = cpfpaciente;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
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
}
