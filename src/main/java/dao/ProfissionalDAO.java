package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Profissional;
import util.FabricaConexao;

public class ProfissionalDAO {
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        return FabricaConexao.getConexaoMySQL();
    }
    
    public void cadastrar(Profissional profissional) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "insert into profissionais (nome, cpf, telefone, email, data_nascimento, genero, estado_civil, especialidade, crm, senha, status_disponibilidade) values (?,?,?,?,?,?,?,?,?,?,?)"
        );
        comando.setString(1, profissional.getNome());
        comando.setString(2, profissional.getCpf());
        comando.setString(3, profissional.getTelefone());
        comando.setString(4, profissional.getEmail());
        comando.setTimestamp(5, profissional.getData_nascimento() != null ? Timestamp.valueOf(profissional.getData_nascimento()) : null);
        comando.setString(6, profissional.getGenero());
        comando.setString(7, profissional.getEstado_civil());
        comando.setString(8, profissional.getEspecialidade());
        comando.setString(9, profissional.getCrm());
        comando.setString(10, profissional.getSenha());
        comando.setBoolean(11, profissional.isStatusDisponibilidade());
        
        comando.execute();
        con.close();
    }
    
    public void deletar(Profissional profissional) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("delete from profissionais where id_pessoa = ?");
        comando.setInt(1, profissional.getId_pessoa());
        comando.execute();
        con.close();
    }
    
    public void atualizar(Profissional profissional) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "update profissionais set nome = ?, cpf = ?, telefone = ?, email = ?, data_nascimento = ?, genero = ?, estado_civil = ?, especialidade = ?, crm = ?, senha = ?, status_disponibilidade = ? where id_pessoa = ?"
        );
        comando.setString(1, profissional.getNome());
        comando.setString(2, profissional.getCpf());
        comando.setString(3, profissional.getTelefone());
        comando.setString(4, profissional.getEmail());
        comando.setTimestamp(5, profissional.getData_nascimento() != null ? Timestamp.valueOf(profissional.getData_nascimento()) : null);
        comando.setString(6, profissional.getGenero());
        comando.setString(7, profissional.getEstado_civil());
        comando.setString(8, profissional.getEspecialidade());
        comando.setString(9, profissional.getCrm());
        comando.setString(10, profissional.getSenha());
        comando.setBoolean(11, profissional.isStatusDisponibilidade());
        comando.setInt(12, profissional.getId_pessoa());
        
        comando.execute();
        con.close();
    }    
    
    public Profissional consultarById(Profissional profissional) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("select * from profissionais where id_pessoa = ?");
        comando.setInt(1, profissional.getId_pessoa());
        ResultSet rs = comando.executeQuery();
        
        Profissional p = null;
        if (rs.next()) {
            Profissional.ProfissionalBuilder builder = new Profissional.ProfissionalBuilder();
            builder.comIdPessoa(rs.getInt("id_pessoa"));
            builder.comNome(rs.getString("nome"));
            builder.comCpf(rs.getString("cpf"));
            builder.comTelefone(rs.getString("telefone"));
            builder.comEmail(rs.getString("email"));
            
            Timestamp ts = rs.getTimestamp("data_nascimento");
            if (ts != null) builder.comDataNascimento(ts.toLocalDateTime());
            
            builder.comGenero(rs.getString("genero"));
            builder.comEstadoCivil(rs.getString("estado_civil"));
            builder.comEspecialidade(rs.getString("especialidade"));
            builder.comCrm(rs.getString("crm"));
            builder.comSenha(rs.getString("senha"));
            builder.estaDisponivel(rs.getBoolean("status_disponibilidade"));
            
            p = builder.constroi();
        }       
        con.close();
        return p;
    }
    
    public List<Profissional> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("select * from profissionais");        
        ResultSet rs = comando.executeQuery();        
        List<Profissional> lprofissionais = new ArrayList<Profissional>();
        int cont = 0;
        
        while(rs.next()){
            Profissional.ProfissionalBuilder builder = new Profissional.ProfissionalBuilder();
            builder.comIdPessoa(rs.getInt("id_pessoa"));
            builder.comNome(rs.getString("nome"));
            builder.comCpf(rs.getString("cpf"));
            builder.comTelefone(rs.getString("telefone"));
            builder.comEmail(rs.getString("email"));
            
            Timestamp ts = rs.getTimestamp("data_nascimento");
            if (ts != null) builder.comDataNascimento(ts.toLocalDateTime());
            
            builder.comGenero(rs.getString("genero"));
            builder.comEstadoCivil(rs.getString("estado_civil"));
            builder.comEspecialidade(rs.getString("especialidade"));
            builder.comCrm(rs.getString("crm"));
            builder.comSenha(rs.getString("senha"));
            builder.estaDisponivel(rs.getBoolean("status_disponibilidade"));
            
            lprofissionais.add(builder.constroi());
            cont++;
        }
        System.out.println("Cont..: " + cont);
        con.close();
        return lprofissionais;
    }    
}
