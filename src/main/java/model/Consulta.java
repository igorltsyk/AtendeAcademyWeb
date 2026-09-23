package model;

import java.time.LocalDateTime;
import java.util.List;


public class Consulta {
    private int id_consulta;
    private Paciente paciente;
    private Profissional profissional;
    private List<Servico> servicos;
    private LocalDateTime data_hora;
    private String status;
    private String observacoes;

    private Consulta(Builder builder) {
        this.id_consulta = builder.id_consulta;
        this.paciente = builder.paciente;
        this.profissional = builder.profissional;
        this.servicos = builder.servicos;
        this.data_hora = builder.data_hora;
        this.status = builder.status;
        this.observacoes = builder.observacoes;
    }

    public int getId_consulta() { return id_consulta; }
    public Paciente getPaciente() { return paciente; }
    public Profissional getProfissional() { return profissional; }
    public List<Servico> getServicos() { return servicos; }
    public LocalDateTime getData_hora() { return data_hora; }
    public String getStatus() { return status; }
    public String getObservacoes() { return observacoes; }


    public double agendarConsulta() {
        double valorTotal = calcularValorTotal();
        
        if (this.profissional != null) {
            this.profissional.setStatusDisponibilidade(false);
        }
        
        this.status = "AGENDADA";
        
        return valorTotal;
    }


    private double calcularValorTotal() {
        if (this.servicos == null || this.servicos.isEmpty()) {
            return 0.0;
        }
        
        double total = 0.0;
        for (Servico servico : this.servicos) {
            total += servico.getValor_servico();
        }
        return total;
    }

    public static class Builder {
        private int id_consulta;
        private Paciente paciente;
        private Profissional profissional;
        private List<Servico> servicos;
        private LocalDateTime data_hora;
        private String status;
        private String observacoes;

        public void comIdConsulta(int id_consulta) {
            this.id_consulta = id_consulta;
        }

        public void comPaciente(Paciente paciente) {
            this.paciente = paciente;
        }

        public void comProfissional(Profissional profissional) {
            this.profissional = profissional;
        }

        public void comServicos(List<Servico> servicos) {
            this.servicos = servicos;
        }

        public void comDataHora(LocalDateTime data_hora) {
            this.data_hora = data_hora;
        }

        public void comStatus(String status) {
            this.status = status;
        }

        public void comObservacoes(String observacoes) {
            this.observacoes = observacoes;
        }

        public Consulta constroi() {
            return new Consulta(this);
        }
    }
}
