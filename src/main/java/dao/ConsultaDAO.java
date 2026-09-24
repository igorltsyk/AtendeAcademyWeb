package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Consulta;
import model.Paciente;
import model.Profissional;
import model.Servico;
import util.FabricaConexao;

public class ConsultaDAO {
    
    public static Connection getConexao() throws ClassNotFoundException, SQLException {
        return FabricaConexao.getConexaoMySQL();
    }
    
    public void cadastrar(Consulta consulta) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        con.setAutoCommit(false);
        
        try {
            PreparedStatement comando = con.prepareStatement(
                "insert into consultas (id_paciente, id_profissional, data_hora, status, observacoes) values (?,?,?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS
            );
            
            comando.setInt(1, consulta.getPaciente() != null ? consulta.getPaciente().getId_pessoa() : 0);
            comando.setInt(2, consulta.getProfissional() != null ? consulta.getProfissional().getId_pessoa() : 0);
            comando.setTimestamp(3, consulta.getData_hora() != null ? Timestamp.valueOf(consulta.getData_hora()) : null);
            comando.setString(4, consulta.getStatus());
            comando.setString(5, consulta.getObservacoes());
            
            comando.execute();
            

            ResultSet rsId = comando.getGeneratedKeys();
            int idConsulta = 0;
            if (rsId.next()) {
                idConsulta = rsId.getInt(1);
            }
            
            if (consulta.getServicos() != null && !consulta.getServicos().isEmpty()) {
                PreparedStatement comandoServicos = con.prepareStatement(
                    "insert into consulta_servico (id_consulta, id_servico) values (?, ?)"
                );
                for (Servico s : consulta.getServicos()) {
                    comandoServicos.setInt(1, idConsulta);
                    comandoServicos.setInt(2, s.getId_servico());
                    comandoServicos.execute();
                }
            }
            
            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally {
            con.setAutoCommit(true);
            con.close();
        }
    }
    
    public void deletar(Consulta consulta) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("delete from consultas where id_consulta = ?");
        comando.setInt(1, consulta.getId_consulta());
        comando.execute();
        con.close();
    }
    
    public void atualizar(Consulta consulta) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "update consultas set id_paciente = ?, id_profissional = ?, data_hora = ?, status = ?, observacoes = ? where id_consulta = ?"
        );
        comando.setInt(1, consulta.getPaciente() != null ? consulta.getPaciente().getId_pessoa() : 0);
        comando.setInt(2, consulta.getProfissional() != null ? consulta.getProfissional().getId_pessoa() : 0);
        comando.setTimestamp(3, consulta.getData_hora() != null ? Timestamp.valueOf(consulta.getData_hora()) : null);
        comando.setString(4, consulta.getStatus());
        comando.setString(5, consulta.getObservacoes());
        comando.setInt(6, consulta.getId_consulta());
        
        comando.execute();
        con.close();
    }    
    
    public Consulta consultarById(Consulta consulta) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("select * from consultas where id_consulta = ?");
        comando.setInt(1, consulta.getId_consulta());
        ResultSet rs = comando.executeQuery();
        
        Consulta c = null;
        if (rs.next()) {
            Consulta.ConsultaBuilder builder = new Consulta.ConsultaBuilder();
            builder.comIdConsulta(rs.getInt("id_consulta"));
            
            Paciente p = new Paciente.PacienteBuilder().constroi();
            
            Timestamp ts = rs.getTimestamp("data_hora");
            if (ts != null) builder.comDataHora(ts.toLocalDateTime());
            
            builder.comStatus(rs.getString("status"));
            builder.comObservacoes(rs.getString("observacoes"));
            
            c = builder.constroi();
        }       
        con.close();
        return c;
    }
    
    public List<Consulta> consultarTodos() throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement("select * from consultas");        
        ResultSet rs = comando.executeQuery();        
        List<Consulta> lconsultas = new ArrayList<Consulta>();
        int cont = 0;
        
        while(rs.next()){
            Consulta.ConsultaBuilder builder = new Consulta.ConsultaBuilder();
            builder.comIdConsulta(rs.getInt("id_consulta"));
            
            Timestamp ts = rs.getTimestamp("data_hora");
            if (ts != null) builder.comDataHora(ts.toLocalDateTime());
            
            builder.comStatus(rs.getString("status"));
            builder.comObservacoes(rs.getString("observacoes"));
            
            lconsultas.add(builder.constroi());
            cont++;
        }
        System.out.println("Cont..: " + cont);
        con.close();
        return lconsultas;
    }    
    
    public boolean existeConsultaNesseHorario(int idProfissional, java.time.LocalDateTime dataHora) throws ClassNotFoundException, SQLException {
        Connection con = getConexao();
        PreparedStatement comando = con.prepareStatement(
            "select count(*) from consultas where id_profissional = ? and data_hora = ? and status != 'Cancelada'"
        );
        comando.setInt(1, idProfissional);
        comando.setTimestamp(2, java.sql.Timestamp.valueOf(dataHora));
        ResultSet rs = comando.executeQuery();
        
        boolean existe = false;
        if (rs.next()) {
            if (rs.getInt(1) > 0) {
                existe = true;
            }
        }
        con.close();
        return existe;
    }
}
