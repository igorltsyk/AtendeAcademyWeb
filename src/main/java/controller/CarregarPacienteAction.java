package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CarregarPacienteAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.PacienteDAO dao = new dao.PacienteDAO();
        model.Paciente.PacienteBuilder b = new model.Paciente.PacienteBuilder();
        b.comIdPessoa(id);
        model.Paciente p = b.constroi();
        model.Paciente paciente = dao.consultarById(p);
        
        request.setAttribute("paciente", paciente);
        return "html/editar_paciente.jsp";
    }
}
