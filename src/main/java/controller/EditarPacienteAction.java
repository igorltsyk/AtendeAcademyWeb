package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Paciente;
import dao.PacienteDAO;

public class EditarPacienteAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            PacienteDAO dao = new PacienteDAO();
            Paciente.PacienteBuilder tempBuilder = new Paciente.PacienteBuilder();
            tempBuilder.comIdPessoa(id);
            Paciente pacienteExistente = dao.consultarById(tempBuilder.constroi());
            
            Paciente.PacienteBuilder b = new Paciente.PacienteBuilder();
            b.comIdPessoa(id);
            b.comNome(request.getParameter("nome"));
            b.comCpf(pacienteExistente.getCpf());
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
            dao.atualizar(p);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return "controller.do?acao=ListarPaciente";
    }
}
