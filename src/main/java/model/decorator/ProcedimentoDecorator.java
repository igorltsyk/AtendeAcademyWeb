package model.decorator;

public abstract class ProcedimentoDecorator implements Consulta {
    protected Consulta consulta;

    public ProcedimentoDecorator(Consulta consulta) {
        this.consulta = consulta;
    }

    @Override
    public String getDescricao() {
        return consulta.getDescricao();
    }

    @Override
    public double getPreco() {
        return consulta.getPreco();
    }
}

