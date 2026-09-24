package controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public interface ICommand {

    String executar(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
