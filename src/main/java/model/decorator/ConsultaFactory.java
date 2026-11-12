package model.decorator;

import model.Servico;

/**
 * Monta a cadeia de decorators a partir de objetos Servico (ja carregados do banco).
 */
public class ConsultaFactory {

    public Consulta clinicaGeral(Servico servico) {
        return new ConsultaClinicaGeral(servico);
    }

    public Consulta ortodontica(Servico servico) {
        return new ConsultaOrtodontica(servico);
    }

    public Consulta anestesia(Consulta consulta, Servico servico) {
        return new Anestesia(consulta, servico);
    }

    public Consulta radiografia(Consulta consulta, Servico servico) {
        return new Radiografia(consulta, servico);
    }

    public Consulta limpeza(Consulta consulta, Servico servico) {
        return new Limpeza(consulta, servico);
    }

    /**
     * Monta a consulta base e encadeia os procedimentos adicionais.
     * O tipo de cada Servico e identificado pelo nome. Adicionais nao reconhecidos sao ignorados.
     */
    public Consulta criar(Servico base, java.util.List<Servico> adicionais) {
        Consulta consulta = normalizar(base).contains("ortod") ? ortodontica(base) : clinicaGeral(base);

        if (adicionais != null) {
            for (Servico s : adicionais) {
                String nome = normalizar(s);
                if (nome.contains("anestesia")) {
                    consulta = anestesia(consulta, s);
                } else if (nome.contains("radiografia")) {
                    consulta = radiografia(consulta, s);
                } else if (nome.contains("limpeza")) {
                    consulta = limpeza(consulta, s);
                }
            }
        }
        return consulta;
    }

    private String normalizar(Servico s) {
        String nome = s.getNome_servico() == null ? "" : s.getNome_servico();
        return java.text.Normalizer.normalize(nome, java.text.Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "").toLowerCase();
    }
}
