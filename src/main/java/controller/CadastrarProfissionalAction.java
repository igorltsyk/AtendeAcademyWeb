package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Profissional;
import dao.ProfissionalDAO;

public class CadastrarProfissionalAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return "html/cadastro_profissional.jsp";
        }
        
        try {
            Profissional.ProfissionalBuilder b = new Profissional.ProfissionalBuilder();
            b.comNome(request.getParameter("nome"));
            b.comCpf(request.getParameter("cpf"));
            b.comEmail(request.getParameter("email"));
            b.comSenha(request.getParameter("senha"));
            b.comTelefone(request.getParameter("telefone"));
            b.comGenero(request.getParameter("genero"));
            b.comEstadoCivil(request.getParameter("estadocivil"));
            
            b.comCrm(request.getParameter("crm"));
            b.comEspecialidade(request.getParameter("especialidade"));
            b.estaDisponivel(Boolean.parseBoolean(request.getParameter("disponivel")));
            
            Profissional p = b.constroi();
            new ProfissionalDAO().cadastrar(p);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return "controller.do?acao=ListarProfissional";
    }
}
