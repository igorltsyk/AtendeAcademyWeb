package controller;

// Imports do Servlet
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;


import model.Aluno;
import dao.AlunoDAO;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/login.html");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String email = request.getParameter("email");
        String senha = request.getParameter("senha");


        AlunoDAO dao = new AlunoDAO();
        Aluno alunoLogado = null;

        try {

            alunoLogado = dao.validarLogin(email, senha);

        } catch (Exception e) {
            throw new ServletException("Erro ao validar login", e);
        }



        if (alunoLogado != null) {

            HttpSession session = request.getSession();

            session.setAttribute("alunoLogado", alunoLogado);


            response.sendRedirect("home.jsp");

        } else {

            response.sendRedirect("login.html");
        }
    }
}