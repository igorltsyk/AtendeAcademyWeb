package dto;

import model.Paciente;

public class PacienteDTO {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
    private String genero;
    private String estadoCivil;
    private boolean maiorDeIdade;

    public PacienteDTO() {
    }

    public static PacienteDTO fromEntity(Paciente p) {
        PacienteDTO dto = new PacienteDTO();
        dto.id = p.getId_pessoa();
        dto.nome = p.getNome();
        dto.cpf = p.getCpf();
        dto.telefone = p.getTelefone();
        dto.email = p.getEmail();
        dto.genero = p.getGenero();
        dto.estadoCivil = p.getEstado_civil();
        dto.maiorDeIdade = p.isMaiorDeIdade();
        return dto;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEmail() {
        return email;
    }

    public String getGenero() {
        return genero;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public boolean isMaiorDeIdade() {
        return maiorDeIdade;
    }

    public String toJson() {
        return String.format(
                "{\"id\":%d,\"nome\":\"%s\",\"cpf\":\"%s\",\"telefone\":\"%s\",\"email\":\"%s\",\"genero\":\"%s\",\"estadoCivil\":\"%s\",\"maiorDeIdade\":%b}",
                id, escape(nome), escape(cpf), escape(telefone), escape(email), escape(genero), escape(estadoCivil), maiorDeIdade
        );
    }

    private String escape(String s) {
        return s == null ? "" : s.replace("\"", "\\\"");
    }
}

