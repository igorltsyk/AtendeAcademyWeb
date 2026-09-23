package model;

public class Paciente extends Pessoa {
    private String senha;
    private boolean maiorDeIdade;

    private Paciente(Builder builder) {
        super(builder);
        this.senha = builder.senha;
        this.maiorDeIdade = builder.maiorDeIdade;
    }

    public String getSenha() { return senha; }
    public boolean isMaiorDeIdade() { return maiorDeIdade; }

    public static class Builder extends Pessoa.Builder {
        private String senha;
        private boolean maiorDeIdade;

        public void comSenha(String senha) {
            this.senha = senha;
        }

        public void ehMaiorDeIdade() {
            this.maiorDeIdade = true;
        }

        @Override
        public Paciente constroi() {
            return new Paciente(this);
        }
    }
}
