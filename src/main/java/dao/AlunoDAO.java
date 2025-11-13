package dao;

import model.Aluno;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexao.FabricaConexao;


public class AlunoDAO {

    public void inserir(Aluno aluno) {
        String sql = "INSERT INTO aluno (nome_aluno, cpf_aluno, telefone, email, senha) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            comando.setString(1, aluno.getNomealuno());
            comando.setString(2, aluno.getCpfaluno());
            comando.setString(3, aluno.getTelefone());
            comando.setString(4, aluno.getEmail());
            comando.setString(5, aluno.getSenha());
            comando.executeUpdate();

            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                aluno.setIdaluno(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir aluno: " + e.getMessage());
        }
    }

    public List<Aluno> consultarTodos() {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";
        try (Connection conn = FabricaConexao.getConexao();
             Statement comando = conn.createStatement();
             ResultSet rs = comando.executeQuery(sql)) {

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setIdaluno(rs.getInt("id_aluno"));
                a.setNomealuno(rs.getString("nome_aluno"));
                a.setCpfaluno(rs.getString("cpf_aluno"));
                a.setTelefone(rs.getString("telefone"));
                a.setEmail(rs.getString("email"));
                a.setSenha(rs.getString("senha"));
                a.setIdservico((Integer) rs.getObject("id_servico"));
                lista.add(a);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar alunos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Aluno aluno) {
        String sql = "UPDATE aluno SET nome_aluno=?, cpf_aluno=?, telefone=?, email=?, senha=?, id_servico=? WHERE id_aluno=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setString(1, aluno.getNomealuno());
            comando.setString(2, aluno.getCpfaluno());
            comando.setString(3, aluno.getTelefone());
            comando.setString(4, aluno.getEmail());
            comando.setString(5, aluno.getSenha());
            if (aluno.getIdservico() != null) comando.setInt(6, aluno.getIdservico());
            else comando.setNull(6, Types.INTEGER);
            comando.setInt(7, aluno.getIdaluno());

            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar aluno: " + e.getMessage());
        }
    }

    public void deletar(int idaluno) {
        String sql = "DELETE FROM aluno WHERE id_aluno=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idaluno);
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar aluno: " + e.getMessage());
        }
    }

    public Aluno consultarPorId(Integer idaluno) {
        String sql = "SELECT * FROM aluno WHERE id_aluno = ?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idaluno);
            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdaluno(rs.getInt("id_aluno"));
                aluno.setNomealuno(rs.getString("nome_aluno"));
                aluno.setCpfaluno(rs.getString("cpf_aluno"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                aluno.setSenha(rs.getString("senha"));
                return aluno;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar aluno: " + e.getMessage());
        }
        return null;
    }

    public Aluno validarLogin(String email, String senha) {
        // A "receita" (SQL) para encontrar um usuário com este email E esta senha
        String sql = "SELECT * FROM aluno WHERE email = ? AND senha = ?";

        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            // Preenche os '?' da query com os dados recebidos
            comando.setString(1, email);
            comando.setString(2, senha);


            ResultSet rs = comando.executeQuery();


            if (rs.next()) {
                // Encontrou! Criamos o objeto Aluno com os dados do banco
                Aluno aluno = new Aluno();
                aluno.setIdaluno(rs.getInt("id_aluno"));
                aluno.setNomealuno(rs.getString("nome_aluno"));
                aluno.setCpfaluno(rs.getString("cpf_aluno"));
                aluno.setTelefone(rs.getString("telefone"));
                aluno.setEmail(rs.getString("email"));
                // Não precisamos setar a senha no objeto, por segurança

                return aluno; // Retorna o objeto Aluno (LOGADO COM SUCESSO)
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao validar login: " + e.getMessage());
            // Você pode tratar o erro aqui
        }


        return null; // Retorna null falha o login
    }
}
