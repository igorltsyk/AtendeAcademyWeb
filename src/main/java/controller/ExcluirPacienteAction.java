package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Paciente;
import dao.PacienteDAO;

public class ExcluirPacienteAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente.PacienteBuilder b = new Paciente.PacienteBuilder();
        b.comIdPessoa(id);
        
        new PacienteDAO().deletar(b.constroi());
        return "controller.do?acao=ListarPaciente";
    }
}
