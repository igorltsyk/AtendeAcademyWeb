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
            

            dao.ServicoDAO servicoDao = new dao.ServicoDAO();
            model.Servico servicoBase = buscarServico(servicoDao, criarReferencia(idServico));

            java.util.List<model.Servico> adicionais = new java.util.ArrayList<>();
            String[] idsAdicionais = request.getParameterValues("idsAdicionais");
            if (idsAdicionais != null) {
                for (String idAd : idsAdicionais) {
                    model.Servico adicional = buscarServico(servicoDao, criarReferencia(Integer.parseInt(idAd)));
                    if (adicional.getId_servico() != servicoBase.getId_servico()) {
                        adicionais.add(adicional);
                    }
                }
            }

            // Decorator: calcula descricao e preco acumulados com valores vindos do banco
            model.decorator.Consulta consultaDecorada = new model.decorator.ConsultaFactory().criar(servicoBase, adicionais);

            // Servicos persistidos na consulta: base + adicionais
            java.util.List<model.Servico> servicos = new java.util.ArrayList<>();
            servicos.add(servicoBase);
            servicos.addAll(adicionais);
            

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

            request.setAttribute("descricaoConsulta", consultaDecorada.getDescricao());
            request.setAttribute("valorConsulta", consultaDecorada.getPreco());
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

        return "sucesso_cadastro_consulta.jsp";
    }

    /** Converte o parametro do formulario em um objeto Servico (somente identificacao). */
    private model.Servico criarReferencia(int idServico) {
        model.Servico.ServicoBuilder b = new model.Servico.ServicoBuilder();
        b.comIdServico(idServico);
        return b.constroi();
    }

    /** Recebe um Servico de referencia e devolve o Servico completo carregado do banco. */
    private model.Servico buscarServico(dao.ServicoDAO servicoDao, model.Servico referencia) throws Exception {
        model.Servico s = servicoDao.consultarById(referencia);
        if (s == null) {
            throw new Exception("Serviço não encontrado: " + referencia.getId_servico());
        }
        return s;
    }
}
