package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Ação de exemplo: CadastrarConsultaAction.
 * Implementa ICommand para processar o cadastro de uma consulta.
 */
public class CadastrarConsultaAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        // Se a requisição for GET, preparamos os dados para popular os dropdowns (selects)
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
        
        // Se for POST, processamos os dados do formulário
        try {
            int idPaciente = Integer.parseInt(request.getParameter("idPaciente"));
            int idProfissional = Integer.parseInt(request.getParameter("idProfissional"));
            int idServico = Integer.parseInt(request.getParameter("idServico"));
            String dataStr = request.getParameter("data"); // ex: 2026-12-31
            String horaStr = request.getParameter("hora"); // ex: 14:30
            String observacoes = request.getParameter("observacoes");
            
            // Tratamento da Data e Hora
            java.time.LocalDateTime dataHora = java.time.LocalDateTime.parse(dataStr + "T" + horaStr);
            
            // Instancia paciente apenas com ID
            model.Paciente.Builder pBuilder = new model.Paciente.Builder();
            pBuilder.comIdPessoa(idPaciente);
            model.Paciente paciente = pBuilder.constroi();
            
            // Instancia profissional apenas com ID
            model.Profissional.Builder profBuilder = new model.Profissional.Builder();
            profBuilder.comIdPessoa(idProfissional);
            model.Profissional profissional = profBuilder.constroi();
            
            // Instancia serviço apenas com ID
            model.Servico.Builder sBuilder = new model.Servico.Builder();
            sBuilder.comIdServico(idServico);
            java.util.List<model.Servico> servicos = new java.util.ArrayList<>();
            servicos.add(sBuilder.constroi());
            
            // Cria a consulta via Builder
            model.Consulta.Builder cBuilder = new model.Consulta.Builder();
            cBuilder.comPaciente(paciente);
            cBuilder.comProfissional(profissional);
            cBuilder.comServicos(servicos);
            cBuilder.comDataHora(dataHora);
            cBuilder.comStatus("Agendada");
            cBuilder.comObservacoes(observacoes);
            model.Consulta consulta = cBuilder.constroi();
            
            // Salva via DAO (A transação manual salvará a consulta e na tabela associativa consulta_servico)
            dao.ConsultaDAO dao = new dao.ConsultaDAO();
            dao.cadastrar(consulta);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return "sucesso_cadastro_consulta.jsp";
    }
}
