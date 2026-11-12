package model.decorator;

import model.Servico;

/** Demonstracao sem banco: os Servico sao montados em memoria (simulando o resultado do DAO). */
public class TesteDecorator {

    private static Servico servico(String nome, double valor) {
        Servico.ServicoBuilder b = new Servico.ServicoBuilder();
        b.comNomeServico(nome);
        b.comValorServico(valor);
        return b.constroi();
    }

    public static void main(String[] args) {
        Servico clinica = servico("Consulta Cl\u00ednica Geral", 150.0);
        Servico orto = servico("Consulta Ortod\u00f4ntica", 200.0);
        Servico anest = servico("Anestesia", 80.0);
        Servico radio = servico("Radiografia", 60.0);
        Servico limpeza = servico("Limpeza", 100.0);

        exibir(new Limpeza(new Anestesia(new ConsultaClinicaGeral(clinica), anest), limpeza));
        exibir(new Radiografia(new Limpeza(new Anestesia(new ConsultaOrtodontica(orto), anest), limpeza), radio));
        exibir(new ConsultaClinicaGeral(clinica));
    }

    private static void exibir(Consulta c) {
        System.out.printf("%s = R$ %.2f%n", c.getDescricao(), c.getPreco());
    }
}