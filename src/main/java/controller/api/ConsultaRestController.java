package controller.api;

import dao.ConsultaDAO;
import dao.ServicoDAO;
import dto.ConsultaDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import model.Consulta;
import model.Paciente;
import model.Profissional;
import model.Servico;
import model.decorator.ConsultaFactory;

@WebServlet("/api/consultas/*")
public class ConsultaRestController extends HttpServlet {

    private final ConsultaDAO consultaDAO = new ConsultaDAO();
    private final ServicoDAO servicoDAO = new ServicoDAO();
    private final ConsultaFactory consultaFactory = new ConsultaFactory();

    // GET /api/consultas  OU  GET /api/consultas/{id}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        configurarJson(resp);
        try {
            Integer id = extrairIdDaRota(req);
            if (id != null) {
                Consulta encontrada = consultaDAO.consultarById(criarReferenciaConsulta(id));
                if (encontrada == null) {
                    enviarErro(resp, HttpServletResponse.SC_NOT_FOUND, "Consulta não encontrada.");
                    return;
                }
                resp.setStatus(HttpServletResponse.SC_OK); // 200 OK
                resp.getWriter().write(ConsultaDTO.fromEntity(encontrada, "", 0.0).toJson());
                return;
            }

            List<Consulta> consultas = consultaDAO.consultarTodos();
            String jsonLista = consultas.stream()
                    .map(c -> ConsultaDTO.fromEntity(c, "", 0.0).toJson())
                    .collect(Collectors.joining(",", "[", "]"));

            resp.setStatus(HttpServletResponse.SC_OK); // 200 OK
            resp.getWriter().write(jsonLista);
        } catch (IllegalArgumentException e) {
            enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            enviarErro(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar consultas.");
        }
    }

    // POST /api/consultas
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        configurarJson(resp);
        try {
            String idPacStr = req.getParameter("idPaciente");
            String idProfStr = req.getParameter("idProfissional");
            String idServStr = req.getParameter("idServico");
            String dataStr = req.getParameter("data");
            String horaStr = req.getParameter("hora");

            if (idPacStr == null || idProfStr == null || idServStr == null || dataStr == null || horaStr == null) {
                enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST,
                        "Parâmetros obrigatórios: idPaciente, idProfissional, idServico, data, hora.");
                return;
            }

            int idPaciente = Integer.parseInt(idPacStr);
            int idProfissional = Integer.parseInt(idProfStr);
            int idServico = Integer.parseInt(idServStr);
            LocalDateTime dataHora = LocalDateTime.parse(dataStr + "T" + horaStr);

            Servico servicoBase = buscarServico(criarReferenciaServico(idServico));
            if (servicoBase == null) {
                enviarErro(resp, HttpServletResponse.SC_NOT_FOUND, "Serviço base não encontrado.");
                return;
            }

            List<Servico> adicionais = new ArrayList<>();
            String[] idsAdicionais = req.getParameterValues("idsAdicionais");
            if (idsAdicionais != null) {
                for (String idAd : idsAdicionais) {
                    Servico adicional = buscarServico(criarReferenciaServico(Integer.parseInt(idAd)));
                    if (adicional != null && adicional.getId_servico() != servicoBase.getId_servico()) {
                        adicionais.add(adicional);
                    }
                }
            }

            model.decorator.Consulta consultaDecorada = consultaFactory.criar(servicoBase, adicionais);

            List<Servico> todosServicos = new ArrayList<>();
            todosServicos.add(servicoBase);
            todosServicos.addAll(adicionais);

            Paciente.PacienteBuilder pBuilder = new Paciente.PacienteBuilder();
            pBuilder.comIdPessoa(idPaciente);

            Profissional.ProfissionalBuilder profBuilder = new Profissional.ProfissionalBuilder();
            profBuilder.comIdPessoa(idProfissional);

            Consulta.ConsultaBuilder cBuilder = new Consulta.ConsultaBuilder();
            cBuilder.comPaciente(pBuilder.constroi());
            cBuilder.comProfissional(profBuilder.constroi());
            cBuilder.comServicos(todosServicos);
            cBuilder.comDataHora(dataHora);
            cBuilder.comStatus("Agendada");
            cBuilder.comObservacoes(req.getParameter("observacoes"));
            Consulta novaConsulta = cBuilder.constroi();

            if (consultaDAO.existeConsultaNesseHorario(novaConsulta)) {
                enviarErro(resp, HttpServletResponse.SC_CONFLICT, // 409 Conflict
                        "Horário já reservado para o profissional selecionado.");
                return;
            }

            consultaDAO.cadastrar(novaConsulta);

            resp.setStatus(HttpServletResponse.SC_CREATED); // 201 Created
            resp.getWriter().write(
                    ConsultaDTO.fromEntity(novaConsulta, consultaDecorada.getDescricao(), consultaDecorada.getPreco()).toJson()
            );
        } catch (IllegalArgumentException e) {
            enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            enviarErro(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao agendar consulta.");
        }
    }

    // DELETE /api/consultas/{id}
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        configurarJson(resp);
        try {
            Integer id = extrairIdDaRota(req);
            if (id == null) {
                enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, "ID da consulta é obrigatório na rota (/api/consultas/{id}).");
                return;
            }

            Consulta referencia = criarReferenciaConsulta(id);
            if (consultaDAO.consultarById(referencia) == null) {
                enviarErro(resp, HttpServletResponse.SC_NOT_FOUND, "Consulta não encontrada.");
                return;
            }

            consultaDAO.deletar(referencia);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
        } catch (IllegalArgumentException e) {
            enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            enviarErro(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao excluir consulta.");
        }
    }

    private Integer extrairIdDaRota(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            return null;
        }
        try {
            return Integer.valueOf(pathInfo.substring(1));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("ID inválido na URL.");
        }
    }

    private Consulta criarReferenciaConsulta(int id) {
        Consulta.ConsultaBuilder b = new Consulta.ConsultaBuilder();
        b.comIdConsulta(id);
        return b.constroi();
    }

    private Servico criarReferenciaServico(int id) {
        Servico.ServicoBuilder b = new Servico.ServicoBuilder();
        b.comIdServico(id);
        return b.constroi();
    }

    private Servico buscarServico(Servico referencia) throws Exception {
        return servicoDAO.consultarById(referencia);
    }

    private void configurarJson(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }

    private void enviarErro(HttpServletResponse resp, int status, String mensagem) throws IOException {
        resp.setStatus(status);
        resp.getWriter().write(String.format("{\"erro\":\"%s\"}", mensagem));
    }
}

