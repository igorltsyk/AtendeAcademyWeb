package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Paciente;
import dao.PacienteDAO;

public class CadastrarPacienteAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (request.getMethod().equalsIgnoreCase("GET")) {
            return "html/cadastro.jsp";
        }
        
        try {
            Paciente.PacienteBuilder b = new Paciente.PacienteBuilder();
            b.comNome(request.getParameter("nome"));
            b.comCpf(request.getParameter("cpf"));
            b.comEmail(request.getParameter("email"));
            b.comSenha(request.getParameter("senha"));
            b.comTelefone(request.getParameter("telefone"));
            b.comGenero(request.getParameter("genero"));
            b.comEstadoCivil(request.getParameter("estadocivil"));
            
            String idade = request.getParameter("idade");
            if (idade != null && !idade.isEmpty() && Integer.parseInt(idade) >= 18) {
                b.ehMaiorDeIdade();
            }
            
            Paciente p = b.constroi();
            new PacienteDAO().cadastrar(p);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return "controller.do?acao=ListarPaciente";
    }
}
