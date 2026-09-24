package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CarregarProfissionalAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.ProfissionalDAO dao = new dao.ProfissionalDAO();
        Profissional.ProfissionalBuilder b = new Profissional.ProfissionalBuilder();
        b.comIdPessoa(id);
        model.Profissional p = b.constroi();
        model.Profissional profissional = dao.consultarById(p);
        
        request.setAttribute("profissional", profissional);
        return "html/editar_profissional.jsp";
    }
}
