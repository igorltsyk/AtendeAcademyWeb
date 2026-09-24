package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Paciente;
import util.FabricaConexao;

public class PacienteDAO {
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        return FabricaConexao.getConexaoMySQL();
    }
    
    public void cadastrar(Paciente paciente) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "insert into pacientes (nome, cpf, telefone, email, data_nascimento, genero, estado_civil, senha, maior_de_idade) values (?,?,?,?,?,?,?,?,?)"
        );
        comando.setString(1, paciente.getNome());
        comando.setString(2, paciente.getCpf());
        comando.setString(3, paciente.getTelefone());
        comando.setString(4, paciente.getEmail());
        comando.setTimestamp(5, paciente.getData_nascimento() != null ? Timestamp.valueOf(paciente.getData_nascimento()) : null);
        comando.setString(6, paciente.getGenero());
        comando.setString(7, paciente.getEstado_civil());
        comando.setString(8, paciente.getSenha());
        comando.setBoolean(9, paciente.isMaiorDeIdade());
        
        comando.execute();
        con.close();
    }
    
    public void deletar(Paciente paciente) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("delete from pacientes where id_pessoa = ?");
        comando.setInt(1, paciente.getId_pessoa());
        comando.execute();
        con.close();
    }
    
    public void atualizar(Paciente paciente) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "update pacientes set nome = ?, cpf = ?, telefone = ?, email = ?, data_nascimento = ?, genero = ?, estado_civil = ?, senha = ?, maior_de_idade = ? where id_pessoa = ?"
        );
        comando.setString(1, paciente.getNome());
        comando.setString(2, paciente.getCpf());
        comando.setString(3, paciente.getTelefone());
        comando.setString(4, paciente.getEmail());
        comando.setTimestamp(5, paciente.getData_nascimento() != null ? Timestamp.valueOf(paciente.getData_nascimento()) : null);
        comando.setString(6, paciente.getGenero());
        comando.setString(7, paciente.getEstado_civil());
        comando.setString(8, paciente.getSenha());
        comando.setBoolean(9, paciente.isMaiorDeIdade());
        comando.setInt(10, paciente.getId_pessoa());
        
        comando.execute();
        con.close();
    }    
    
    public Paciente consultarById(Paciente paciente) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("select * from pacientes where id_pessoa = ?");
        comando.setInt(1, paciente.getId_pessoa());
        ResultSet rs = comando.executeQuery();
        
        Paciente p = null;
        if (rs.next()) {
            Paciente.PacienteBuilder builder = new Paciente.PacienteBuilder();
            builder.comIdPessoa(rs.getInt("id_pessoa"));
            builder.comNome(rs.getString("nome"));
            builder.comCpf(rs.getString("cpf"));
            builder.comTelefone(rs.getString("telefone"));
            builder.comEmail(rs.getString("email"));
            
            Timestamp ts = rs.getTimestamp("data_nascimento");
            if (ts != null) builder.comDataNascimento(ts.toLocalDateTime());
            
            builder.comGenero(rs.getString("genero"));
            builder.comEstadoCivil(rs.getString("estado_civil"));
            builder.comSenha(rs.getString("senha"));
            if (rs.getBoolean("maior_de_idade")) {
                builder.ehMaiorDeIdade();
            }
            p = builder.constroi();
        }       
        con.close();
        return p;
    }
    
    public List<Paciente> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("select * from pacientes");        
        ResultSet rs = comando.executeQuery();        
        List<Paciente> lpacientes = new ArrayList<Paciente>();
        int cont = 0;
        
        while(rs.next()){
            Paciente.PacienteBuilder builder = new Paciente.PacienteBuilder();
            builder.comIdPessoa(rs.getInt("id_pessoa"));
            builder.comNome(rs.getString("nome"));
            builder.comCpf(rs.getString("cpf"));
            builder.comTelefone(rs.getString("telefone"));
            builder.comEmail(rs.getString("email"));
            
            Timestamp ts = rs.getTimestamp("data_nascimento");
            if (ts != null) builder.comDataNascimento(ts.toLocalDateTime());
            
            builder.comGenero(rs.getString("genero"));
            builder.comEstadoCivil(rs.getString("estado_civil"));
            builder.comSenha(rs.getString("senha"));
            if (rs.getBoolean("maior_de_idade")) {
                builder.ehMaiorDeIdade();
            }
            
            lpacientes.add(builder.constroi());
            cont++;
        }
        System.out.println("Cont..: " + cont);
        con.close();
        return lpacientes;
    }    
}
