package controller;

import dao.PacienteDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Paciente;

import java.io.IOException;
import java.util.List;


@WebServlet(name = "PacienteServlet", value = "/pacienteServlet")
public class PacienteServlet extends HttpServlet {

    private PacienteDAO dao;


    @Override
    public void init() throws ServletException {
        this.dao = new PacienteDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

<<<<<<< HEAD

        if (action == null || action.equals("listarTodos")) {
            listarTodos(request, response);
        } else if (action.equals("buscar")) {
            buscarPaciente(request, response);
        } else if (action.equals("carregarParaEditar")) {
            carregarParaEditar(request, response);
        } else {
            listarTodos(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

=======
        // Se 'action' for nulo ou "listarTodos", executa a listagem
        if (action == null || action.equals("listarTodos")) {
            listarTodos(request, response);
        } else if (action.equals("buscar")) {
            buscarPaciente(request, response);
        } else if (action.equals("carregarParaEditar")) {
            carregarParaEditar(request, response);
        } else {
            listarTodos(request, response); // Ação padrão
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
        if (action == null) {
            response.sendRedirect(request.getContextPath() + "/pacienteServlet?action=listarTodos");
            return;
        }

        if (action.equals("excluir")) {
            excluirPaciente(request, response);
        } else if (action.equals("salvarEdicao")) {
            salvarEdicao(request, response);
        }
    }

<<<<<<< HEAD

    private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Método para listar todos os pacientes do banco
         */
        List<Paciente> lista = dao.consultarTodos();
        request.setAttribute("listaDePacientes", lista); //empacota os dados na requisição
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/crud.jsp");
        dispatcher.forward(request, response); // nvia a requisição para o jsp
    }


    private void buscarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String termoBusca = request.getParameter("termoBusca");
        List<Paciente> lista = dao.buscar(termoBusca);
        request.setAttribute("listaDePacientes", lista);
        request.setAttribute("termoBusca", termoBusca);
=======
    // Método para "Consultar Todos" (GET)
    private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paciente> lista = dao.consultarTodos();
        request.setAttribute("listaDePacientes", lista);
>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/crud.jsp");
        dispatcher.forward(request, response);
    }

<<<<<<< HEAD

    private void carregarParaEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = dao.consultarPorId(id);
        request.setAttribute("paciente", paciente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/editar_paciente.jsp");
        dispatcher.forward(request, response);
    }


    private void excluirPaciente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deletar(id);
        response.sendRedirect(request.getContextPath() + "/pacienteServlet?action=listarTodos");
    }


=======
    // Método para "Buscar" (GET)
    private void buscarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String termoBusca = request.getParameter("termoBusca");
        List<Paciente> lista = dao.buscar(termoBusca);
        request.setAttribute("listaDePacientes", lista);
        request.setAttribute("termoBusca", termoBusca); // Devolve o termo para o input
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/crud.jsp");
        dispatcher.forward(request, response);
    }

    // Método para "Carregar Para Editar" (GET)
    private void carregarParaEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = dao.consultarPorId(id);
        request.setAttribute("paciente", paciente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/editar_paciente.jsp");
        dispatcher.forward(request, response);
    }

    // Método para "Excluir" (POST)
    private void excluirPaciente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        dao.deletar(id);
        response.sendRedirect(request.getContextPath() + "/pacienteServlet?action=listarTodos");
    }

    // Método para "Salvar Edição" (POST) - Vem da página editar_paciente.jsp
>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
    private void salvarEdicao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idpaciente"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        int idade = Integer.parseInt(request.getParameter("idade"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");

        Paciente paciente = new Paciente(id, nome, cpf, idade, telefone, email, senha);
        dao.atualizar(paciente);

        response.sendRedirect(request.getContextPath() + "/pacienteServlet?action=listarTodos");
    }
}