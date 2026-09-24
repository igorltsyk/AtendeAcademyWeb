package model;

public class Paciente extends Pessoa {
    private String senha;
    private boolean maiorDeIdade;

    public Paciente(int id_pessoa, String nome, String cpf, String telefone, String email, java.time.LocalDateTime data_nascimento, String genero, String estado_civil, String senha, boolean maiorDeIdade) {
        super(id_pessoa, nome, cpf, telefone, email, data_nascimento, genero, estado_civil);
        this.senha = senha;
        this.maiorDeIdade = maiorDeIdade;
    }

    public String getSenha() { return senha; }
    public boolean isMaiorDeIdade() { return maiorDeIdade; }

    public static class PacienteBuilder extends Pessoa.PessoaBuilder {
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
            return new Paciente(id_pessoa, nome, cpf, telefone, email, data_nascimento, genero, estado_civil, senha, maiorDeIdade);
        }
    }
}
