<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/crud.css">
    <title>Gestão de Pacientes - AtendeAcademy</title>
</head>
<body>

<div class="dashboard-card">
    <h1 class="dashboard-title">Gerenciamento de Pacientes</h1>

    <div class="toolbar">

        <form class="search-group" action="${pageContext.request.contextPath}/pacienteServlet" method="GET">
            <input type="hidden" name="action" value="buscar">
            <input type="text"
                   class="form-input"
                   name="termoBusca"
                   placeholder="Buscar paciente por nome ou CPF..."
                   value="${param.termoBusca}">
            <button type="submit" class="btn btn-blue">Buscar</button>
        </form>

        <a href="${pageContext.request.contextPath}/pacienteServlet?action=listarTodos"
           class="btn btn-blue"
           style="background-color: #6c757d; border-color: #6c757d; text-decoration: none;">
            Listar Todos
        </a>

        <a href="${pageContext.request.contextPath}/cadastro" class="btn btn-green">Cadastrar Novo Paciente</a>
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
                    <td>${paciente.idpaciente}</td>
                    <td>${paciente.nomepaciente}</td>
                    <td>${paciente.cpfpaciente}</td>
                    <td>${paciente.telefone}</td>
                    <td>${paciente.email}</td>

                    <td class="action-buttons">
                        <form action="${pageContext.request.contextPath}/pacienteServlet" method="POST" style="display:inline;">
                            <input type="hidden" name="action" value="excluir">
                            <input type="hidden" name="id" value="${paciente.idpaciente}">
                            <button type="submit" class="btn-action btn-delete">Excluir</button>
                        </form>

                        <form action="${pageContext.request.contextPath}/pacienteServlet" method="GET" style="display:inline;">
                            <input type="hidden" name="action" value="carregarParaEditar">
                            <input type="hidden" name="id" value="${paciente.idpaciente}">
                            <button type="submit" class="btn-action btn-edit" style="background-color: #007bff; color: white; border: 1px solid #007bff;">Editar</button>
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