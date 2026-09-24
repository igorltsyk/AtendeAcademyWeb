package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Profissional;
import dao.ProfissionalDAO;

public class ExcluirProfissionalAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        Profissional.ProfissionalBuilder b = new Profissional.ProfissionalBuilder();
        b.comIdPessoa(id);
        
        new ProfissionalDAO().deletar(b.constroi());
        return "controller.do?acao=ListarProfissional";
    }
}
