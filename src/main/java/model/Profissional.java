package model;


public class Profissional extends Pessoa {
    private String especialidade;
    private String crm;
    private String senha;
    private boolean statusDisponibilidade;

    private Profissional(Builder builder) {
        super(builder);
        this.especialidade = builder.especialidade;
        this.crm = builder.crm;
        this.senha = builder.senha;
        this.statusDisponibilidade = builder.statusDisponibilidade;
    }

    public String getEspecialidade() { return especialidade; }
    public String getCrm() { return crm; }
    public String getSenha() { return senha; }
    public boolean isStatusDisponibilidade() { return statusDisponibilidade; }
    
    // Método para permitir a alteração pela Automação de Processo de Negócio
    public void setStatusDisponibilidade(boolean statusDisponibilidade) {
        this.statusDisponibilidade = statusDisponibilidade;
    }

    public static class Builder extends Pessoa.Builder {
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
            return new Profissional(this);
        }
    }
}
