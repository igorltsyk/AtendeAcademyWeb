package controller;

import java.io.IOException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Controller Central (Front Controller).
 * Mapeada para /controller.do, intercepta as requisições e utiliza
 * o padrão Factory Method com Reflection para instanciar as ações.
 */
@WebServlet("/controller.do")
public class ServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // recupera a ação do usuário
            String paramAction = request.getParameter("acao");
            // monta o nome completo e qualificado da classe
            String nomeDaClasse = "controller." + paramAction + "Action";
            // cria uma classe de representação (meta-programação)
            Class classeAction = Class.forName(nomeDaClasse);
            // instancia a classe utilizando a Factory do objeto Class
            @SuppressWarnings("deprecation")
            ICommand commandAction = (ICommand) classeAction.newInstance();
            // executa a Action
            String pageDispatcher = commandAction.executar(request, response);
            RequestDispatcher rd = request.getRequestDispatcher(pageDispatcher);
            rd.forward(request, response);
        } catch (Exception e) {
            RequestDispatcher rd = request.getRequestDispatcher("erro.jsp");
            request.setAttribute("erro", e);
            rd.forward(request, response);
        }
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
}
