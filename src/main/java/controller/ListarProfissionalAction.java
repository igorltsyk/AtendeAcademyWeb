package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ProfissionalDAO;

public class ListarProfissionalAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProfissionalDAO dao = new ProfissionalDAO();
        request.setAttribute("listaDeProfissionais", dao.consultarTodos());
        return "html/crud_profissional.jsp";
    }
}
