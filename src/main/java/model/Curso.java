package model;

public class Curso {
    private int idcurso;
    private String nomecurso;
    private String nomeprofessor;

    public Curso() {}

    public Curso(int idcurso, String nomecurso, String nomeprofessor) {
        this.idcurso = idcurso;
        this.nomecurso = nomecurso;
        this.nomeprofessor = nomeprofessor;
    }

    public int getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(int idcurso) {
        this.idcurso = idcurso;
    }

    public String getNomecurso() {
        return nomecurso;
    }

    public void setNomecurso(String nomecurso) {
        this.nomecurso = nomecurso;
    }

    public String getNomeprofessor() {
        return nomeprofessor;
    }

    public void setNomeprofessor(String nomeprofessor) {
        this.nomeprofessor = nomeprofessor;
    }
}


