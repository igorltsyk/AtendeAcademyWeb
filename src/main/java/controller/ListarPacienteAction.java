package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.PacienteDAO;

public class ListarPacienteAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PacienteDAO dao = new PacienteDAO();
        request.setAttribute("listaDePacientes", dao.consultarTodos());
        return "html/crud.jsp";
    }
}
