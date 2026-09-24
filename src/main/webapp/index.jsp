<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Início - AtendeAcademy</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
</head>
<body>

    <div class="auth-card" style="max-width: 500px;">
        <h1 class="auth-title">Bem-vindo ao<br>AtendeAcademy</h1>
        <p style="text-align: center; color: #6b7280; margin-bottom: 2rem;"></p>

        <div style="display: flex; flex-direction: column; gap: 1rem;">
            
            <a href="${pageContext.request.contextPath}/html/crud.jsp" class="btn btn-green" style="background-color: #4A4AFF; border-color: #4A4AFF;">
                Gerenciar Pacientes
            </a>

            <a href="${pageContext.request.contextPath}/html/crud_profissional.jsp" class="btn btn-green" style="background-color: #4A4AFF; border-color: #4A4AFF;">
                Gerenciar Profissionais
            </a>

            <a href="${pageContext.request.contextPath}/controller.do?acao=ListarConsulta" class="btn btn-green">
                Acessar Agenda de Consultas
            </a>
            
        </div>
    </div>

</body>
</html>