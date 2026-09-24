package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Profissional;
import dao.ProfissionalDAO;

public class EditarProfissionalAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            ProfissionalDAO dao = new ProfissionalDAO();
            Profissional.ProfissionalBuilder tempBuilder = new Profissional.ProfissionalBuilder();
            tempBuilder.comIdPessoa(id);
            Profissional profissionalExistente = dao.consultarById(tempBuilder.constroi());
            
            Profissional.ProfissionalBuilder b = new Profissional.ProfissionalBuilder();
            b.comIdPessoa(id);
            b.comNome(request.getParameter("nome"));
            b.comCpf(profissionalExistente.getCpf());
            b.comEmail(request.getParameter("email"));
            b.comSenha(request.getParameter("senha"));
            b.comTelefone(request.getParameter("telefone"));
            b.comGenero(request.getParameter("genero"));
            b.comEstadoCivil(request.getParameter("estadocivil"));
            
            b.comCrm(profissionalExistente.getCrm());
            b.comEspecialidade(request.getParameter("especialidade"));
            b.estaDisponivel(Boolean.parseBoolean(request.getParameter("disponivel")));
            
            Profissional p = b.constroi();
            dao.atualizar(p);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return "controller.do?acao=ListarProfissional";
    }
}
