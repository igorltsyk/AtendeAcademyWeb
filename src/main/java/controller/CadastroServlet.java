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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/html/cadastro.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        int idade = Integer.parseInt(request.getParameter("idade"));
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");
        String genero = request.getParameter("genero");
        String estadocivil = request.getParameter("estadocivil");

        Paciente novoPaciente = new Paciente();
        novoPaciente.setNomepaciente(nome);
        novoPaciente.setCpfpaciente(cpf);
        novoPaciente.setIdade(idade);
        novoPaciente.setEmail(email);
        novoPaciente.setSenha(senha);
        novoPaciente.setTelefone(telefone);
        novoPaciente.setGenero(genero);
        novoPaciente.setEstadocivil(estadocivil);



        PacienteDAO dao = new PacienteDAO();
        try {

            dao.inserir(novoPaciente);

        } catch (Exception e) {

            throw new ServletException("Erro ao inserir paciente no banco de dados", e);
        }


        response.sendRedirect(request.getContextPath()+"/html/crud.jsp");
    }
}