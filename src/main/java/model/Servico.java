package model;

public class Servico {
    private int idservico;
    private String nomeservico;
    private Integer idcurso;
    private Integer idaluno;
    private Integer idpaciente;

    public Servico() {}

    public Servico(int idservico, String nomeservico, Integer idcurso, Integer idaluno, Integer idpaciente) {
        this.idservico = idservico;
        this.nomeservico = nomeservico;
        this.idcurso = idcurso;
        this.idaluno = idaluno;
        this.idpaciente = idpaciente;
    }

    public int getIdservico() {
        return idservico;
    }

    public void setIdservico(int idservico) {
        this.idservico = idservico;
    }

    public String getNomeservico() {
        return nomeservico;
    }

    public void setNomeservico(String nomeservico) {
        this.nomeservico = nomeservico;
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }

    public Integer getIdaluno() {
        return idaluno;
    }

    public void setIdaluno(Integer idaluno) {
        this.idaluno = idaluno;
    }

    public Integer getIdpaciente() {
        return idpaciente;
    }

    public void setIdpaciente(Integer idpaciente) {
        this.idpaciente = idpaciente;
    }
}
