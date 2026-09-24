package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class ListarConsultaAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response) throws Exception {
        
        try {
            dao.ConsultaDAO dao = new dao.ConsultaDAO();
            java.util.List<model.Consulta> consultas = dao.consultarTodos();
            

            request.setAttribute("consultas", consultas);
            
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        

        return "listar_consultas.jsp";
    }
}
