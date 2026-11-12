package model.decorator;

import model.Servico;

/**
 * Componente concreto. Descricao e preco vem do registro de Servico (banco de dados).
 */
public class ConsultaClinicaGeral implements Consulta {
    private String descricao;
    private double preco;

    public ConsultaClinicaGeral(Servico servico) {
        this.descricao = servico.getNome_servico();
        this.preco = servico.getValor_servico();
    }

    @Override
    public String getDescricao() {
        return descricao;
    }

    @Override
    public double getPreco() {
        return preco;
    }
}