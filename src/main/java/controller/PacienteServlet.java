package controller;

import dao.PacienteDAO;
import model.Paciente;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ControlePacienteServlet", urlPatterns = {"/controlePaciente"})
public class        PacienteServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String op = request.getParameter("op");
        PacienteDAO dao = new PacienteDAO();
        String paginaDestino = "";
        String mensagem = "";

        try {
            if (op.equals("CADASTRAR")) {
                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                String telefone = request.getParameter("telefone");
                int idade = Integer.parseInt(request.getParameter("idade")); // Adicionado

                Paciente p = new Paciente();
                p.setNomepaciente(nome);
                p.setCpfpaciente(cpf);
                p.setEmail(email);
                p.setSenha(senha);
                p.setTelefone(telefone);
                p.setIdade(idade); // Adicionado

                dao.inserir(p);

                mensagem = "Paciente cadastrado com sucesso!";
                paginaDestino = "resultado_paciente.jsp";

            } else if (op.equals("CONSULTAR_TODOS")) {
                List<Paciente> listaPacientes = dao.consultarTodos();
                request.setAttribute("listaPacientes", listaPacientes);
                paginaDestino = "lista_pacientes.jsp";

            } else if (op.equals("DELETAR")) {
                int id = Integer.parseInt(request.getParameter("idpaciente"));
                dao.deletar(id);

                // Recarrega a lista após deletar
                List<Paciente> listaPacientes = dao.consultarTodos();
                request.setAttribute("listaPacientes", listaPacientes);
                paginaDestino = "lista_pacientes.jsp";

            } else if (op.equals("ATUALIZAR")) {
                // Esta operação busca o paciente e encaminha para a página de edição
                int id = Integer.parseInt(request.getParameter("idpaciente"));
                Paciente p = dao.consultarPorId(id);
                request.setAttribute("paciente", p);
                paginaDestino = "editar_paciente.jsp";

            } else if (op.equals("EFETIVAR_ATUALIZACAO")) {
                int id = Integer.parseInt(request.getParameter("idpaciente"));
                String nome = request.getParameter("nome");
                String cpf = request.getParameter("cpf");
                String email = request.getParameter("email");
                String senha = request.getParameter("senha");
                String telefone = request.getParameter("telefone");
                int idade = Integer.parseInt(request.getParameter("idade"));

                Paciente p = new Paciente();
                p.setIdpaciente(id);
                p.setNomepaciente(nome);
                p.setCpfpaciente(cpf);
                p.setEmail(email);
                p.setSenha(senha);
                p.setTelefone(telefone);
                p.setIdade(idade);

                dao.atualizar(p);

                mensagem = "Paciente atualizado com sucesso!";
                paginaDestino = "resultado_paciente.jsp";

            } else if (op.equals("CONSULTAR_POR_ID")) {
                int id = Integer.parseInt(request.getParameter("idpaciente_busca"));
                Paciente p = dao.consultarPorId(id);
                request.setAttribute("paciente", p);
                paginaDestino = "resultado_busca_paciente.jsp";
            }

        } catch (Exception e) {
            System.out.println("Erro no Servlet: " + e.getMessage());
            e.printStackTrace();
            mensagem = "Erro: " + e.getMessage();
            paginaDestino = "erro_paciente.jsp";
        }

        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher(paginaDestino);
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para controlar o CRUD de Pacientes";
    }
}