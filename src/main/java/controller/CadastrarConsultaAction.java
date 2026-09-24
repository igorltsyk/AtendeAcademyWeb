package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class CadastrarConsultaAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        

        if (request.getMethod().equalsIgnoreCase("GET")) {
            try {
                dao.PacienteDAO pDao = new dao.PacienteDAO();
                dao.ProfissionalDAO profDao = new dao.ProfissionalDAO();
                dao.ServicoDAO sDao = new dao.ServicoDAO();
                
                request.setAttribute("listaPacientes", pDao.consultarTodos());
                request.setAttribute("listaProfissionais", profDao.consultarTodos());
                request.setAttribute("listaServicos", sDao.consultarTodos());
                
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Erro ao carregar dados para o formulário.", e);
            }
            return "cadastrar_consulta.jsp";
        }
        

        try {
            int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
            int idProfissional = Integer.parseInt(request.getParameter("idProfissional"));
            int idServico = Integer.parseInt(request.getParameter("idServico"));
            String dataStr = request.getParameter("data");
            String horaStr = request.getParameter("hora");
            String observacoes = request.getParameter("observacoes");
            

            java.time.LocalDateTime dataHora = java.time.LocalDateTime.parse(dataStr + "T" + horaStr);
            

            model.Paciente.PacienteBuilder pBuilder = new model.Paciente.PacienteBuilder();
            pBuilder.comIdPessoa(idPaciente);
            model.Paciente paciente = pBuilder.constroi();
            

            model.Profissional.ProfissionalBuilder profBuilder = new model.Profissional.ProfissionalBuilder();
            profBuilder.comIdPessoa(idProfissional);
            model.Profissional profissional = profBuilder.constroi();
            

            model.Servico.ServicoBuilder sBuilder = new model.Servico.ServicoBuilder();
            sBuilder.comIdServico(idServico);
            java.util.List<model.Servico> servicos = new java.util.ArrayList<>();
            servicos.add(sBuilder.constroi());
            

            model.Consulta.ConsultaBuilder cBuilder = new model.Consulta.ConsultaBuilder();
            cBuilder.comPaciente(paciente);
            cBuilder.comProfissional(profissional);
            cBuilder.comServicos(servicos);
            cBuilder.comDataHora(dataHora);
            cBuilder.comStatus("Agendada");
            cBuilder.comObservacoes(observacoes);
            model.Consulta consulta = cBuilder.constroi();
            

            dao.ConsultaDAO dao = new dao.ConsultaDAO();
            
            if (dao.existeConsultaNesseHorario(consulta)) {
                dao.PacienteDAO pDao = new dao.PacienteDAO();
                dao.ProfissionalDAO profDao = new dao.ProfissionalDAO();
                dao.ServicoDAO sDao = new dao.ServicoDAO();
                
                request.setAttribute("listaPacientes", pDao.consultarTodos());
                request.setAttribute("listaProfissionais", profDao.consultarTodos());
                request.setAttribute("listaServicos", sDao.consultarTodos());
                request.setAttribute("mensagemErro", "Este horário já está reservado para o profissional selecionado. Por favor, escolha outro.");
                return "cadastrar_consulta.jsp";
            }
            
            dao.cadastrar(consulta);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return "sucesso_cadastro_consulta.jsp";
    }
}
