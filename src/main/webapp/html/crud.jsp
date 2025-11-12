<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestão de Pacientes - AtendeAcademy</title>

    <%-- Importa a fonte Inter -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <%-- Caminho CORRETO para o CSS usando EL --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/crud.css">
</head>
<body>

<div class="dashboard-card">
    <h1 class="dashboard-title">Gerenciamento de Pacientes</h1>

    <div class="toolbar">
        <form class="search-group" action="pacienteServlet" method="GET">
            <input type="hidden" name="action" value="buscar">

            <input type="text"
                   class="form-input"
                   name="termoBusca"
                   placeholder="Buscar paciente por nome ou CPF..."
                   value="${param.termoBusca}">

            <button type="submit" class="btn btn-blue">Buscar</button>
        </form>

        <a href="cadastro.jsp" class="btn btn-green">Cadastrar Novo Paciente</a>
    </div>

    <div class="table-container">
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>CPF</th>
                <th>Telefone</th>
                <th>Email</th>
                <th style="width: 180px;">Ações</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="paciente" items="${listaDePacientes}">
                <tr>
                    <td>${paciente.id}</td>
                    <td>${paciente.nome}</td>
                    <td>${paciente.cpf}</td>
                    <td>${paciente.telefone}</td>
                    <td>${paciente.email}</td>
                    <td class="action-buttons">
                        <form action="pacienteServlet" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="excluir">
                            <input type="hidden" name="id" value="${paciente.id}">
                            <button type="submit" class="btn-action btn-delete">Excluir</button>
                        </form>

                        <form action="pacienteServlet" method="GET" style="display:inline;">
                            <input type="hidden" name="action" value="carregarParaEditar">
                            <input type="hidden" name="id" value="${paciente.id}">
                            <button type="submit" class="btn-action btn-edit">Editar</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>