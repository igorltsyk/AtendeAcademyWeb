package controller;

// Imports do Servlet
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


import model.Aluno;
import dao.AlunoDAO;

@WebServlet(name = "CadastroServlet", value = "/cadastro")
public class CadastroServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //  encaminha a requisição para a página HTML
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastro.html");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String email = request.getParameter("email");
        String senha = request.getParameter("senha");
        String telefone = request.getParameter("telefone");


        Aluno novoAluno = new Aluno();
        novoAluno.setNomealuno(nome);
        novoAluno.setCpfaluno(cpf);
        novoAluno.setEmail(email);
        novoAluno.setSenha(senha);
        novoAluno.setTelefone(telefone);



        AlunoDAO dao = new AlunoDAO();
        try {

            dao.inserir(novoAluno);

        } catch (Exception e) {

            throw new ServletException("Erro ao inserir aluno no banco de dados", e);
        }


        response.sendRedirect("login.html");
    }
}