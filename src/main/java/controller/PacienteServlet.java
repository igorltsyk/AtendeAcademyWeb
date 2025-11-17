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


    private void listarTodos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Paciente> lista = dao.consultarTodos();
        request.setAttribute("listaDePacientes", lista);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/crud.jsp");
        dispatcher.forward(request, response);
    }


    private void buscarPaciente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String termo = request.getParameter("termoBusca");
        Paciente pacienteFiltro = new Paciente();
        pacienteFiltro.setNomepaciente(termo);
        pacienteFiltro.setCpfpaciente(termo);

        List<Paciente> lista = dao.buscar(pacienteFiltro);
        request.setAttribute("listaDePacientes", lista);
        request.setAttribute("termoBusca", pacienteFiltro); // Devolve o termo para o input
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/crud.jsp");
        dispatcher.forward(request, response);
    }


    private void carregarParaEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = new Paciente();
        paciente.setIdpaciente(id);
        dao.consultarPorId(paciente);
        request.setAttribute("paciente", paciente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/editar_paciente.jsp");
        dispatcher.forward(request, response);
    }


    private void excluirPaciente(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Paciente paciente = new Paciente();
        paciente.setIdpaciente(id);
        dao.deletar(paciente);
        response.sendRedirect(request.getContextPath() + "/pacienteServlet?action=listarTodos");
    }


    private void salvarEdicao(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("idpaciente"));
        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        int idade = Integer.parseInt(request.getParameter("idade"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String estadocivil = request.getParameter("estadocivil");
        String genero = request.getParameter("genero");

        Paciente paciente = new Paciente(id, nome, cpf, idade, telefone, email, senha, estadocivil, genero);
        dao.atualizar(paciente);

        response.sendRedirect(request.getContextPath() + "/pacienteServlet?action=listarTodos");
    }
}