<%@ page import="model.Paciente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Paciente - AtendeAcademy</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
</head>
<body>

<div class="auth-card">
    <h1 class="auth-title">Editar Paciente</h1>

    <%
        Paciente p = (Paciente) request.getAttribute("paciente");
        if (p != null) {
    %>


    <form action="${pageContext.request.contextPath}/pacienteServlet" method="POST">

        <input type="hidden" name="idpaciente" value="<% out.print(p.getIdpaciente()); %>">

        <div class="form-group">
            <label class="form-label">Nome Completo</label>
            <input type="text" class="form-input" name="nome" value="<% out.print(p.getNomepaciente()); %>" required>
        </div>

        <div class="form-group">
            <label class="form-label">CPF</label>
            <input type="text" class="form-input" name="cpf" value="<% out.print(p.getCpfpaciente()); %>" required>
        </div>

        <div class="form-group">
            <label class="form-label">Idade</label>
            <input type="number" class="form-input" name="idade" value="<% out.print(p.getIdade()); %>" required>
        </div>

        <div class="form-group">
            <label class="form-label">E-mail</label>
            <input type="email" class="form-input" name="email" value="<% out.print(p.getEmail()); %>" required>
        </div>

        <div class="form-group">
            <label class="form-label">Senha</label>
            <input type="password" class="form-input" name="senha" value="<% out.print(p.getSenha()); %>" required>
        </div>

        <div class="form-group">
            <label class="form-label">Telefone</label>
            <input type="tel" class="form-input" name="telefone" value="<% out.print(p.getTelefone()); %>" required>
        </div>

        <div class="form-group">
            <label class="form-label">Gênero</label>
            <div class="radio-group">
                <input type="radio" id="genMasc" name="genero" value="Masculino"
                <%-- REMOVIDO o .name() e ajustada a comparação --%>
                    <% if (p.getGenero() != null && p.getGenero().equals("Masculino")) out.print("checked"); %>>
                <label for="genMasc">Masculino</label>

                <input type="radio" id="genFem" name="genero" value="Feminino"
                    <% if (p.getGenero() != null && p.getGenero().equals("Feminino")) out.print("checked"); %>>
                <label for="genFem">Feminino</label>
            </div>
        </div>

        <div class="form-group">
            <label class="form-label">Estado Civil</label>
            <div class="radio-group">
                <%-- Faça o mesmo para Estado Civil --%>
                <input type="radio" id="estSol" name="estadoCivil" value="Solteiro"
                    <% if (p.getEstadocivil() != null && p.getEstadocivil().equals("Solteiro")) out.print("checked"); %>>
                <label for="estSol">Solteiro(a)</label>

                <input type="radio" id="estCas" name="estadoCivil" value="Casado"
                    <% if (p.getEstadocivil() != null && p.getEstadocivil().equals("Casado")) out.print("checked"); %>>
                <label for="estCas">Casado(a)</label>
            </div>
        </div>




        <input type="hidden" name="action" value="salvarEdicao">

        <button type="submit" class="btn btn-green">
            Salvar Alterações
        </button>
    </form>

    <%
    } else {
    %>
    <h2 style="text-align: center; color: #dc3545;">Paciente não encontrado.</h2>
    <%
        }
    %>


    <div class="bottom-link">
        <a href="${pageContext.request.contextPath}/pacienteServlet?action=listarTodos">Voltar para a Lista</a>
    </div>
</div>

</body>
</html>