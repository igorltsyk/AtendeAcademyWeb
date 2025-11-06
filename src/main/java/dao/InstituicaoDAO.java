package dao;

import model.Instituicao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexao.FabricaConexao;


public class InstituicaoDAO {

    public void inserir(Instituicao instituicao) {
        String sql = "INSERT INTO instituicao (nome_instituicao, telefone, email, id_endereco) VALUES (?, ?, ?, ?)";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                                            //Statement.RETURN_GENERATED_KEYS retorna o id que foi gerado c
                                             // com o auto_increment do banco


            comando.setString(1, instituicao.getNomeinstituicao());
            comando.setString(2, instituicao.getTelefone());
            comando.setString(3, instituicao.getEmail());
            if (instituicao.getIdendereco() != null) comando.setInt(4, instituicao.getIdendereco());
            else comando.setNull(4, Types.INTEGER);

            comando.executeUpdate();

            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                instituicao.setIdinstituicao(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir instituicao: " + e.getMessage());
        }
    }

    public List<Instituicao> consultarTodos() {
        List<Instituicao> lista = new ArrayList<>();
        String sql = "SELECT * FROM instituicao";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql);
             ResultSet rs = comando.executeQuery(sql)) {

            while (rs.next()) {
                Instituicao i = new Instituicao();
                i.setIdinstituicao(rs.getInt("id_instituicao"));
                i.setNomeinstituicao(rs.getString("nome_instituicao"));
                i.setTelefone(rs.getString("telefone"));
                i.setEmail(rs.getString("email"));
                i.setIdendereco((Integer) rs.getObject("id_endereco"));
                lista.add(i);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar instituicoes: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Instituicao instituicao) {
        String sql = "UPDATE instituicao SET nome_instituicao=?, telefone=?, email=?, id_endereco=? WHERE id_instituicao=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setString(1, instituicao.getNomeinstituicao());
            comando.setString(2, instituicao.getTelefone());
            comando.setString(3, instituicao.getEmail());
            if (instituicao.getIdendereco() != null) comando.setInt(4, instituicao.getIdendereco());
            else comando.setNull(4, Types.INTEGER);
            comando.setInt(5, instituicao.getIdinstituicao());

            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar instituicao: " + e.getMessage());
        }
    }

    public void deletar(int idinstituicao) {
        String sql = "DELETE FROM instituicao WHERE id_instituicao=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idinstituicao);
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar instituicao: " + e.getMessage());
        }
    }
}
