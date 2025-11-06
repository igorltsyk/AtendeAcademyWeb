package dao;

import model.Curso;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexao.FabricaConexao;


public class CursoDAO {

    public void inserir(Curso curso) {
        String sql = "INSERT INTO curso (nome_curso, nome_professor) VALUES (?, ?)";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            comando.setString(1, curso.getNomecurso());
            comando.setString(2, curso.getNomeprofessor());
            comando.executeUpdate();

            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                curso.setIdcurso(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir curso: " + e.getMessage());
        }
    }

    public List<Curso> consultarTodos() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT * FROM curso";
        try (Connection conn = FabricaConexao.getConexao();
             Statement comando = conn.createStatement();
             ResultSet rs = comando.executeQuery(sql)) {

            while (rs.next()) {
                Curso c = new Curso();
                c.setIdcurso(rs.getInt("id_curso"));
                c.setNomecurso(rs.getString("nome_curso"));
                c.setNomeprofessor(rs.getString("nome_professor"));
                lista.add(c);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar cursos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Curso curso) {
        String sql = "UPDATE curso SET nome_curso=?, nome_professor=? WHERE id_curso=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setString(1, curso.getNomecurso());
            comando.setString(2, curso.getNomeprofessor());
            comando.setInt(3, curso.getIdcurso());
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar curso: " + e.getMessage());
        }
    }

    public void deletar(int idcurso) {
        String sql = "DELETE FROM curso WHERE id_curso=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idcurso);
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar curso: " + e.getMessage());
        }


    }

    public Curso consultarPorId(Integer idcurso) {
        String sql = "SELECT * FROM curso WHERE id_curso = ?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idcurso);
            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                Curso curso = new Curso();
                curso.setIdcurso(rs.getInt("id_curso"));
                curso.setNomecurso(rs.getString("nome_curso"));
                curso.setNomeprofessor(rs.getString("nome_professor"));
                return curso;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar curso: " + e.getMessage());
        }
        return null;
    }
}
