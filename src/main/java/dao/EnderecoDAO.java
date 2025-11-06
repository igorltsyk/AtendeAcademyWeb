package dao;

import model.Endereco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import conexao.FabricaConexao;


public class EnderecoDAO {



        public void inserir(Endereco endereco) {
            String sql = "INSERT INTO endereco (logradouro, numero, bairro, municipio, uf, cep) VALUES (?, ?, ?, ?, ?, ?)";
            try (Connection conn = FabricaConexao.getConexao();
                 PreparedStatement comando = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                comando.setString(1, endereco.getLogradouro());
                comando.setString(2, endereco.getNumero());
                comando.setString(3, endereco.getBairro());
                comando.setString(4, endereco.getMunicipio());
                comando.setString(5, endereco.getUf());
                comando.setString(6, endereco.getCep());
                comando.executeUpdate();

                ResultSet rs = comando.getGeneratedKeys();
                if (rs.next()) {
                    endereco.setIdendereco(rs.getInt(1));
                }

            } catch (SQLException | ClassNotFoundException e) {
                System.out.println("Erro ao inserir endereco: " + e.getMessage());
            }
        }

        public List<Endereco> consultarTodos() {
        List<Endereco> lista = new ArrayList<>();
        String sql = "SELECT * FROM endereco";
        try (Connection conn = FabricaConexao.getConexao();
             Statement comando = conn.createStatement();
             ResultSet rs = comando.executeQuery(sql)) {

            while (rs.next()) {
                Endereco e = new Endereco();
                e.setIdendereco(rs.getInt("id_endereco"));
                e.setLogradouro(rs.getString("logradouro"));
                e.setNumero(rs.getString("numero"));
                e.setBairro(rs.getString("bairro"));
                e.setMunicipio(rs.getString("municipio"));
                e.setUf(rs.getString("uf"));
                e.setCep(rs.getString("cep"));
                lista.add(e);
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Erro ao consultar enderecos: " + ex.getMessage());
        }
        return lista;
    }

    public void atualizar(Endereco endereco) {
        String sql = "UPDATE endereco SET logradouro=?, numero=?, bairro=?, municipio=?, uf=?, cep=? WHERE id_endereco=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setString(1, endereco.getLogradouro());
            comando.setString(2, endereco.getNumero());
            comando.setString(3, endereco.getBairro());
            comando.setString(4, endereco.getMunicipio());
            comando.setString(5, endereco.getUf());
            comando.setString(6, endereco.getCep());
            comando.setInt(7, endereco.getIdendereco());
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao atualizar endereco: " + e.getMessage());
        }
    }

    public void deletar(int idendereco) {
        String sql = "DELETE FROM endereco WHERE id_endereco=?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idendereco);
            comando.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao deletar endereco: " + e.getMessage());
        }
    }

    public Endereco consultarPorId(Integer idendereco) {
        String sql = "SELECT * FROM endereco WHERE id_endereco = ?";
        try (Connection conn = FabricaConexao.getConexao();
             PreparedStatement comando = conn.prepareStatement(sql)) {

            comando.setInt(1, idendereco);
            ResultSet rs = comando.executeQuery();
            if (rs.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdendereco(rs.getInt("id_endereco"));
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setMunicipio(rs.getString("municipio"));
                endereco.setUf(rs.getString("uf"));
                endereco.setCep(rs.getString("cep"));
                return endereco;
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro ao consultar endereco: " + e.getMessage());
        }
        return null;
    }
}
