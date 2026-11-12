package controller.api;

import dao.PacienteDAO;
import dto.PacienteDTO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import model.Paciente;

@WebServlet("/api/pacientes/*")
public class PacienteRestController extends HttpServlet {

    private final PacienteDAO pacienteDAO = new PacienteDAO();

    // GET /api/pacientes?busca=termo  OU  GET /api/pacientes/{id}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        configurarJson(resp);
        try {
            Integer id = extrairIdDaRota(req);
            if (id != null) {
                Paciente ref = criarReferencia(id);
                Paciente encontrado = pacienteDAO.consultarById(ref);
                if (encontrado == null) {
                    enviarErro(resp, HttpServletResponse.SC_NOT_FOUND, "Paciente não encontrado.");
                    return;
                }
                resp.setStatus(HttpServletResponse.SC_OK); // 200 OK
                resp.getWriter().write(PacienteDTO.fromEntity(encontrado).toJson());
                return;
            }

            List<Paciente> lista = pacienteDAO.consultarTodos();
            String busca = req.getParameter("busca");
            if (busca != null && !busca.isBlank()) {
                String termo = busca.toLowerCase();
                lista = lista.stream()
                        .filter(p -> p.getNome().toLowerCase().contains(termo) || p.getCpf().contains(termo))
                        .collect(Collectors.toList());
            }

            String jsonLista = lista.stream()
                    .map(p -> PacienteDTO.fromEntity(p).toJson())
                    .collect(Collectors.joining(",", "[", "]"));

            resp.setStatus(HttpServletResponse.SC_OK); // 200 OK
            resp.getWriter().write(jsonLista);
        } catch (IllegalArgumentException e) {
            enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            enviarErro(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao buscar pacientes.");
        }
    }

    // POST /api/pacientes
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        configurarJson(resp);
        try {
            String nome = req.getParameter("nome");
            String cpf = req.getParameter("cpf");
            if (nome == null || nome.isBlank() || cpf == null || cpf.isBlank()) {
                enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, "Campos 'nome' e 'cpf' são obrigatórios.");
                return;
            }

            Paciente novo = construirPaciente(0, req);
            pacienteDAO.cadastrar(novo);

            resp.setStatus(HttpServletResponse.SC_CREATED); // 201 Created
            resp.getWriter().write(PacienteDTO.fromEntity(novo).toJson());
        } catch (Exception e) {
            enviarErro(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao cadastrar paciente.");
        }
    }

    // PUT /api/pacientes/{id}
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        configurarJson(resp);
        try {
            Integer id = extrairIdDaRota(req);
            if (id == null) {
                enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, "ID do paciente é obrigatório na rota (/api/pacientes/{id}).");
                return;
            }

            Paciente existente = pacienteDAO.consultarById(criarReferencia(id));
            if (existente == null) {
                enviarErro(resp, HttpServletResponse.SC_NOT_FOUND, "Paciente não encontrado.");
                return;
            }

            Paciente atualizado = construirPaciente(id, req);
            pacienteDAO.atualizar(atualizado);

            resp.setStatus(HttpServletResponse.SC_OK); // 200 OK
            resp.getWriter().write(PacienteDTO.fromEntity(atualizado).toJson());
        } catch (IllegalArgumentException e) {
            enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            enviarErro(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao atualizar paciente.");
        }
    }

    // DELETE /api/pacientes/{id}
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        configurarJson(resp);
        try {
            Integer id = extrairIdDaRota(req);
            if (id == null) {
                enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, "ID do paciente é obrigatório na rota (/api/pacientes/{id}).");
                return;
            }

            Paciente referencia = criarReferencia(id);
            if (pacienteDAO.consultarById(referencia) == null) {
                enviarErro(resp, HttpServletResponse.SC_NOT_FOUND, "Paciente não encontrado.");
                return;
            }

            pacienteDAO.deletar(referencia);
            resp.setStatus(HttpServletResponse.SC_NO_CONTENT); // 204 No Content
        } catch (IllegalArgumentException e) {
            enviarErro(resp, HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
        } catch (Exception e) {
            enviarErro(resp, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao remover paciente.");
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

    private Paciente criarReferencia(int id) {
        Paciente.PacienteBuilder b = new Paciente.PacienteBuilder();
        b.comIdPessoa(id);
        return b.constroi();
    }

    private Paciente construirPaciente(int id, HttpServletRequest req) {
        Paciente.PacienteBuilder b = new Paciente.PacienteBuilder();
        b.comIdPessoa(id);
        b.comNome(req.getParameter("nome"));
        b.comCpf(req.getParameter("cpf"));
        b.comEmail(req.getParameter("email"));
        b.comSenha(req.getParameter("senha"));
        b.comTelefone(req.getParameter("telefone"));
        b.comGenero(req.getParameter("genero"));
        b.comEstadoCivil(req.getParameter("estadocivil"));
        String idade = req.getParameter("idade");
        if (idade != null && !idade.isBlank() && Integer.parseInt(idade) >= 18) {
            b.ehMaiorDeIdade();
        }
        return b.constroi();
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

