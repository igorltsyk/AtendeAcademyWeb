package model;


public class Profissional extends Pessoa {
    private String especialidade;
    private String crm;
    private String senha;
    private boolean statusDisponibilidade;

    public Profissional(int id_pessoa, String nome, String cpf, String telefone, String email, java.time.LocalDateTime data_nascimento, String genero, String estado_civil, String especialidade, String crm, String senha, boolean statusDisponibilidade) {
        super(id_pessoa, nome, cpf, telefone, email, data_nascimento, genero, estado_civil);
        this.especialidade = especialidade;
        this.crm = crm;
        this.senha = senha;
        this.statusDisponibilidade = statusDisponibilidade;
    }

    public String getEspecialidade() { return especialidade; }
    public String getCrm() { return crm; }
    public String getSenha() { return senha; }
    public boolean isStatusDisponibilidade() { return statusDisponibilidade; }
    

    public void setStatusDisponibilidade(boolean statusDisponibilidade) {
        this.statusDisponibilidade = statusDisponibilidade;
    }

    public static class ProfissionalBuilder extends Pessoa.PessoaBuilder {
        private String especialidade;
        private String crm;
        private String senha;
        private boolean statusDisponibilidade;

        public void comEspecialidade(String especialidade) {
            this.especialidade = especialidade;
        }

        public void comCrm(String crm) {
            this.crm = crm;
        }

        public void comSenha(String senha) {
            this.senha = senha;
        }

        public void estaDisponivel(boolean statusDisponibilidade) {
            this.statusDisponibilidade = statusDisponibilidade;
        }

        @Override
        public Profissional constroi() {
            return new Profissional(id_pessoa, nome, cpf, telefone, email, data_nascimento, genero, estado_civil, especialidade, crm, senha, statusDisponibilidade);
        }
    }
}
