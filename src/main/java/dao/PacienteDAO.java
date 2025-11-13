package dao;

import model.Paciente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexao.FabricaConexao;


public class PacienteDAO {

    public void inserir(Paciente paciente) {
        String sql = "INSERT INTO paciente (nome_paciente, cpf_paciente, idade, telefone, email, senha) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            comando.setString(1, paciente.getNomepaciente());
            comando.setString(2, paciente.getCpfpaciente());
            comando.setInt(3, paciente.getIdade());
            comando.setString(4, paciente.getTelefone());
            comando.setString(5, paciente.getEmail());
            comando.setString(6, paciente.getSenha());

            comando.executeUpdate();

            ResultSet rs = comando.getGeneratedKeys();
            if (rs.next()) {
                paciente.setIdpaciente(rs.getInt(1));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao inserir paciente: " + e.getMessage());

            throw new RuntimeException("Erro de banco de dados ao inserir paciente", e);
        }
    }


    public List<Paciente> consultarTodos() {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente";
        try (Connection conn = FabricaConexao.getConexao();
             Statement comando = conn.createStatement();
             ResultSet rs = comando.executeQuery(sql)) {

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setIdpaciente(rs.getInt("id_paciente"));
                p.setNomepaciente(rs.getString("nome_paciente"));
                p.setCpfpaciente(rs.getString("cpf_paciente"));
                p.setIdade(rs.getInt("idade"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                lista.add(p);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar pacientes: " + e.getMessage());
        }
        return lista;
    }

    public void atualizar(Paciente paciente) {
        String sql = "UPDATE paciente SET nome_paciente=?, cpf_paciente=?, idade=?, telefone=?, email=?, senha=? WHERE id_paciente=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setString(1, paciente.getNomepaciente());
            comando.setString(2, paciente.getCpfpaciente());
            comando.setInt(3, paciente.getIdade());
            comando.setString(4, paciente.getTelefone());
            comando.setString(5, paciente.getEmail());
            comando.setString(6, paciente.getSenha());
            comando.setInt(7, paciente.getIdpaciente());

            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar paciente: " + e.getMessage());
        }
    }

    public void deletar(int idpaciente) {
        String sql = "DELETE FROM paciente WHERE id_paciente=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idpaciente);
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar paciente: " + e.getMessage());
        }
    }
    public List<Paciente> buscar(String termoBusca) {
        List<Paciente> lista = new ArrayList<>();
        String sql = "SELECT * FROM paciente WHERE nome_paciente LIKE ? OR cpf_paciente LIKE ?";

        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            String termoLike = "%" + termoBusca + "%";
            comando.setString(1, termoLike);
            comando.setString(2, termoLike);

            ResultSet rs = comando.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setIdpaciente(rs.getInt("id_paciente"));
                p.setNomepaciente(rs.getString("nome_paciente"));
                p.setCpfpaciente(rs.getString("cpf_paciente"));
                p.setIdade(rs.getInt("idade"));
                p.setTelefone(rs.getString("telefone"));
                p.setEmail(rs.getString("email"));
                p.setSenha(rs.getString("senha"));
                lista.add(p);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao buscar pacientes: " + e.getMessage());
        }
        return lista;
    }

    public Paciente consultarPorId(Integer idpaciente) {
        String sql = "SELECT * FROM paciente WHERE id_paciente = ?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idpaciente);
            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setIdpaciente(rs.getInt("id_paciente"));
                paciente.setNomepaciente(rs.getString("nome_paciente"));
                paciente.setCpfpaciente(rs.getString("cpf_paciente"));
                paciente.setIdade(rs.getInt("idade"));
                paciente.setTelefone(rs.getString("telefone"));
                paciente.setEmail(rs.getString("email"));
                paciente.setSenha(rs.getString("senha"));
                return paciente;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar paciente: " + e.getMessage());
        }
        return null;
    }
}
