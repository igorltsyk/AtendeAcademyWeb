package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Ação de exemplo: ListarConsultaAction.
 * Implementa ICommand para processar a listagem de consultas.
 */
public class ListarConsultaAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        try {
            dao.ConsultaDAO dao = new dao.ConsultaDAO();
            java.util.List<model.Consulta> consultas = dao.consultarTodos();
            
            // Injeta a lista no request para que o listar_consultas.jsp possa iterar sobre ela
            request.setAttribute("consultas", consultas);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        
        // Retorna a página JSP para redirecionamento
        return "listar_consultas.jsp";
    }
}
