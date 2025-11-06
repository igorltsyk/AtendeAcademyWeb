package dao;

import model.Agenda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexao.FabricaConexao;

public class AgendaDAO {

    public void inserir(Agenda agenda) {
        String sql = "INSERT INTO agenda (dta_agenda, descricao, predio_sala, id_servico, id_endereco, id_curso) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            comando.setDate(1, agenda.getDtaagenda());
            comando.setString(2, agenda.getDescricao());
            comando.setString(3, agenda.getPrediosala());
            if (agenda.getIdservico() != null) comando.setInt(4, agenda.getIdservico());
            else comando.setNull(4, Types.INTEGER);
            if (agenda.getIdendereco() != null) comando.setInt(5, agenda.getIdendereco());
            else comando.setNull(5, Types.INTEGER);
            if (agenda.getIdcurso() != null) comando.setInt(6, agenda.getIdcurso());
            else comando.setNull(6, Types.INTEGER);

            comando.executeUpdate();

            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                agenda.setIdagenda(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir agenda: " + e.getMessage());
        }
    }


    public List<Agenda> consultarTodos() {
        List<Agenda> lista = new ArrayList<>();
        String sql = "SELECT * FROM agenda";
        try (Connection conn = FabricaConexao.getConexao();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Agenda a = new Agenda();
                a.setIdagenda(rs.getInt("id_agenda"));
                a.setDtaagenda(rs.getDate("dta_agenda"));
                a.setDescricao(rs.getString("descricao"));
                a.setPrediosala(rs.getString("predio_sala"));
                a.setIdservico((Integer) rs.getObject("id_servico"));
                a.setIdendereco((Integer) rs.getObject("id_endereco"));
                a.setIdcurso((Integer) rs.getObject("id_curso"));
                lista.add(a);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar agendas: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Agenda agenda) {
        String sql = "UPDATE agenda SET dta_agenda=?, descricao=?, predio_sala=?, id_servico=?, id_endereco=?, id_curso=? WHERE id_agenda=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setDate(1, agenda.getDtaagenda());
            comando.setString(2, agenda.getDescricao());
            comando.setString(3, agenda.getPrediosala());
            if (agenda.getIdservico() != null) comando.setInt(4, agenda.getIdservico());
            else comando.setNull(4, Types.INTEGER);
            if (agenda.getIdendereco() != null) comando.setInt(5, agenda.getIdendereco());
            else comando.setNull(5, Types.INTEGER);
            if (agenda.getIdcurso() != null) comando.setInt(6, agenda.getIdcurso());
            else comando.setNull(6, Types.INTEGER);
            comando.setInt(7, agenda.getIdagenda());

            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar agenda: " + e.getMessage());
        }
    }

    public void deletar(int idagenda) {
        String sql = "DELETE FROM agenda WHERE id_agenda=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idagenda);
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar agenda: " + e.getMessage());
        }
    }

    public Agenda consultarPorId(Integer idagenda) {
        String sql = "SELECT * FROM agenda WHERE id_agenda = ?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idagenda);
            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                Agenda agenda = new Agenda();
                agenda.setIdagenda(rs.getInt("id_agenda"));
                agenda.setDtaagenda(rs.getDate("dta_agenda"));
                agenda.setDescricao(rs.getString("descricao"));
                agenda.setPrediosala(rs.getString("predio_sala"));
                agenda.setIdservico(rs.getInt("id_servico"));
                agenda.setIdendereco(rs.getInt("id_endereco"));
                agenda.setIdcurso(rs.getInt("id_curso"));
                return agenda;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar agenda: " + e.getMessage());
        }
        return null;
    }
}
