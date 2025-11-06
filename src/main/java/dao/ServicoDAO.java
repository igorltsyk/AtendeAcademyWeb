package dao;

import model.Servico;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexao.FabricaConexao;


public class ServicoDAO {

    public void inserir(Servico servico) {
        String sql = "INSERT INTO servico (nome_servico, id_curso, id_aluno, id_paciente) VALUES (?, ?, ?, ?)";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            comando.setString(1, servico.getNomeservico());
            if (servico.getIdcurso() != null) comando.setInt(2, servico.getIdcurso());
            else comando.setNull(2, Types.INTEGER);
            if (servico.getIdaluno() != null) comando.setInt(3, servico.getIdaluno());
            else comando.setNull(3, Types.INTEGER);
            if (servico.getIdpaciente() != null) comando.setInt(4, servico.getIdpaciente());
            else comando.setNull(4, Types.INTEGER);

            comando.executeUpdate();

            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                servico.setIdservico(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir servico: " + e.getMessage());
        }
    }

    public List<Servico> consultarTodos() {
        List<Servico> lista = new ArrayList<>();
        String sql = "SELECT * FROM servico";
        try (Connection conn = FabricaConexao.getConexao();
             Statement comando = conn.createStatement();
             ResultSet rs = comando.executeQuery(sql)) {

            while (rs.next()) {
                Servico s = new Servico();
                s.setIdservico(rs.getInt("id_servico"));
                s.setNomeservico(rs.getString("nome_servico"));
                s.setIdcurso((Integer) rs.getObject("id_curso"));
                s.setIdaluno((Integer) rs.getObject("id_aluno"));
                s.setIdpaciente((Integer) rs.getObject("id_paciente"));
                lista.add(s);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar servicos: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Servico servico) {
        String sql = "UPDATE servico SET nome_servico=?, id_curso=?, id_aluno=?, id_paciente=? WHERE id_servico=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setString(1, servico.getNomeservico());
            if (servico.getIdcurso() != null) comando.setInt(2, servico.getIdcurso());
            else comando.setNull(2, Types.INTEGER);
            if (servico.getIdaluno() != null) comando.setInt(3, servico.getIdaluno());
            else comando.setNull(3, Types.INTEGER);
            if (servico.getIdpaciente() != null) comando.setInt(4, servico.getIdpaciente());
            else comando.setNull(4, Types.INTEGER);
            comando.setInt(5, servico.getIdservico());

            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                servico.setIdservico(rs.getInt(1));
            }
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar servico: " + e.getMessage());
        }
    }

    public void deletar(int idservico) {
        String sql = "DELETE FROM servico WHERE id_servico=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idservico);
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar servico: " + e.getMessage());
        }
    }
}
