package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import model.Profissional;
import dao.ProfissionalDAO;

public class BuscarProfissionalAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String termoBusca = request.getParameter("termoBusca");
        List<Profissional> todos = new ProfissionalDAO().consultarTodos();
        
        if (termoBusca != null && !termoBusca.trim().isEmpty()) {
            String t = termoBusca.toLowerCase();
            todos = todos.stream()
                .filter(p -> p.getNome().toLowerCase().contains(t) || p.getCpf().contains(t) || p.getCrm().toLowerCase().contains(t))
                .collect(Collectors.toList());
        }
        
        request.setAttribute("listaDeProfissionais", todos);
        return "html/crud_profissional.jsp";
    }
}
