package controller;

// Imports do Servlet
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import model.Paciente;
import dao.PacienteDAO;

@WebServlet(name = "CadastroServlet", value = "/cadastro")
public class CadastroServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  encaminha a requisição para a página HTML
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");


        Paciente novoPaciente = new Paciente();
        novoPaciente.setNomepaciente(nome);
        novoPaciente.setCpfpaciente(cpf);
        novoPaciente.setEmail(email);
        novoPaciente.setSenha(senha);
        novoPaciente.setTelefone(telefone);



        PacienteDAO dao = new PacienteDAO();
        try {

            dao.inserir(novoPaciente);

        } catch (Exception e) {

            throw new ServletException("Erro ao inserir aluno no banco de dados", e);
        }


        response.sendRedirect("login.html");
    }
}