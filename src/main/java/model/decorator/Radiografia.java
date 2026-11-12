package model.decorator;

import model.Servico;

/**
 * Decorator concreto. Nome e valor do procedimento vem do registro de Servico (banco de dados).
 */
public class Radiografia extends ProcedimentoDecorator {
    private final Servico servico;

    public Radiografia(Consulta consulta, Servico servico) {
        super(consulta);
        this.servico = servico;
    }

    @Override
    public String getDescricao() {
        return consulta.getDescricao() + " + " + servico.getNome_servico();
    }

    @Override
    public double getPreco() {
        return consulta.getPreco() + servico.getValor_servico();
    }
}