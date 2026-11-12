package dto;

import java.util.Locale;
import model.Consulta;

public class ConsultaDTO {
    private int id;
    private int idPaciente;
    private int idProfissional;
    private String dataHora;
    private String status;
    private String observacoes;
    private String descricaoProcedimentos;
    private double valorTotal;

    public ConsultaDTO() {
    }

    public static ConsultaDTO fromEntity(Consulta c, String descricaoProcedimentos, double valorTotal) {
        ConsultaDTO dto = new ConsultaDTO();
        dto.id = c.getId_consulta();
        dto.idPaciente = c.getPaciente() != null ? c.getPaciente().getId_pessoa() : 0;
        dto.idProfissional = c.getProfissional() != null ? c.getProfissional().getId_pessoa() : 0;
        dto.dataHora = c.getData_hora() != null ? c.getData_hora().toString() : "";
        dto.status = c.getStatus();
        dto.observacoes = c.getObservacoes();
        dto.descricaoProcedimentos = descricaoProcedimentos;
        dto.valorTotal = valorTotal;
        return dto;
    }

    public String toJson() {
        return String.format(
                Locale.US,
                "{\"id\":%d,\"idPaciente\":%d,\"idProfissional\":%d,\"dataHora\":\"%s\",\"status\":\"%s\",\"observacoes\":\"%s\",\"descricaoProcedimentos\":\"%s\",\"valorTotal\":%.2f}",
                id, idPaciente, idProfissional, escape(dataHora), escape(status), escape(observacoes), escape(descricaoProcedimentos), valorTotal
        );
    }

    private String escape(String s) {
        return s == null ? "" : s.replace("\"", "\\\"");
    }
}

