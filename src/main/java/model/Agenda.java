package model;

import java.sql.Date;

public class Agenda {
    private int idagenda;
    private Date dtaagenda;
    private String descricao;
    private String prediosala;
    private Integer idservico;
    private Integer idendereco;
    private Integer idcurso;

    public Agenda() {}

    public Agenda(int idagenda, Date dtaagenda, String descricao, String prediosala, Integer idservico, Integer idendereco, Integer idcurso) {
        this.idagenda = idagenda;
        this.dtaagenda = dtaagenda;
        this.descricao = descricao;
        this.prediosala = prediosala;
        this.idservico = idservico;
        this.idendereco = idendereco;
        this.idcurso = idcurso;
    }

    public int getIdagenda() {
        return idagenda;
    }

    public void setIdagenda(int idagenda) {
        this.idagenda = idagenda;
    }

    public Date getDtaagenda() {
        return dtaagenda;
    }

    public void setDtaagenda(Date dtaagenda) {
        this.dtaagenda = dtaagenda;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrediosala() {
        return prediosala;
    }

    public void setPrediosala(String prediosala) {
        this.prediosala = prediosala;
    }

    public Integer getIdservico() {
        return idservico;
    }

    public void setIdservico(Integer idservico) {
        this.idservico = idservico;
    }

    public Integer getIdendereco() {
        return idendereco;
    }

    public void setIdendereco(Integer idendereco) {
        this.idendereco = idendereco;
    }

    public Integer getIdcurso() {
        return idcurso;
    }

    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }
}
