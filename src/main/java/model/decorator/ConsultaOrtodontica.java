package model.decorator;

import model.Servico;

/**
 * Componente concreto. Descricao e preco vem do registro de Servico (banco de dados).
 */
public class ConsultaOrtodontica implements Consulta {
    private String descricao;
    private double preco;

    public ConsultaOrtodontica(Servico servico) {
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