package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Interface Padrão Command.
 * Define o contrato para as classes de ação que processam requisições.
 */
public interface ICommand {
    /**
     * Método executar que será chamado pelo Front Controller (ServletController).
     * 
     * @param request a requisição HTTP.
     * @param response a resposta HTTP.
     * @return a string da página JSP para a qual o RequestDispatcher fará o redirecionamento.
     * @throws Exception caso ocorra algum erro durante o processamento.
     */
    String executar(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
