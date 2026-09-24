package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import model.Paciente;
import dao.PacienteDAO;

public class BuscarPacienteAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String termoBusca = request.getParameter("termoBusca");
        List<Paciente> todos = new PacienteDAO().consultarTodos();
        
        if (termoBusca != null && !termoBusca.trim().isEmpty()) {
            String t = termoBusca.toLowerCase();
            todos = todos.stream()
                .filter(p -> p.getNome().toLowerCase().contains(t) || p.getCpf().contains(t))
                .collect(Collectors.toList());
        }
        
        request.setAttribute("listaDePacientes", todos);
        return "html/crud.jsp";
    }
}
