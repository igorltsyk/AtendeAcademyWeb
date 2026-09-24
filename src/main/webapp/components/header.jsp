<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>AtendeAcademy - Clínica Odontológica</title>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<header>
    <a href="${pageContext.request.contextPath}/index.jsp" class="logo">
        <svg xmlns="http://www.w3.org/2000/svg" width="28" height="28" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <path d="M12 2v20M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"/>
        </svg>
        Atende<span>Academy</span>
    </a>
    <nav>
        <ul>
            <li><a href="${pageContext.request.contextPath}/index.jsp">Início</a></li>
            <li><a href="${pageContext.request.contextPath}/controller.do?acao=ListarConsulta">Consultas</a></li>
            <li><a href="${pageContext.request.contextPath}/html/crud.jsp">Pacientes</a></li>
            <li><a href="${pageContext.request.contextPath}/html/crud_profissional.jsp">Profissionais</a></li>
        </ul>
    </nav>
    <a href="${pageContext.request.contextPath}/controller.do?acao=CadastrarConsulta" class="btn">Nova Consulta</a>
</header>


<main>
