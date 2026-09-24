<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/crud.css">
    <title>Gestão de Profissionais - AtendeAcademy</title>
</head>

<body>

    <div class="dashboard-card">
        <h1 class="dashboard-title">Gerenciamento de Profissionais</h1>

        <div class="toolbar">

            <form class="search-group" action="${pageContext.request.contextPath}/controller.do"
                method="GET">
                <input type="hidden" name="acao" value="BuscarProfissional">
                <input type="text" class="form-input" name="termoBusca"
                    placeholder="Buscar profissional por nome, CPF ou CRM..." value="${param.termoBusca}">
                <button type="submit" class="btn btn-blue">Buscar</button>
            </form>

            <a href="${pageContext.request.contextPath}/controller.do?acao=ListarProfissional"
                class="btn btn-blue"
                style="background-color: #6c757d; border-color: #6c757d; text-decoration: none;">
                Listar Todos
            </a>

            <a href="${pageContext.request.contextPath}/html/cadastro_profissional.jsp" class="btn btn-green">Cadastrar Novo
                Profissional</a>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Especialidade</th>
                        <th>CRM</th>
                        <th>Disponível</th>
                        <th style="width: 180px;">Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="profissional" items="${listaDeProfissionais}">
                        <tr>
                            <td>${profissional.id_pessoa}</td>
                            <td>${profissional.nome}</td>
                            <td>${profissional.cpf}</td>
                            <td>${profissional.especialidade}</td>
                            <td>${profissional.crm}</td>
                            <td>
                                <span class="badge ${profissional.statusDisponibilidade ? 'badge-success' : 'badge-danger'}">
                                    ${profissional.statusDisponibilidade ? 'Sim' : 'Não'}
                                </span>
                            </td>

                            <td class="action-buttons">
                                <form action="${pageContext.request.contextPath}/controller.do"
                                    method="POST" style="display:inline;"
                                    onsubmit="return confirm('Tem certeza que deseja excluir este profissional?');">
                                    <input type="hidden" name="acao" value="ExcluirProfissional">
                                    <input type="hidden" name="id" value="${profissional.id_pessoa}">
                                    <button type="submit" class="btn-action btn-delete">Excluir</button>
                                </form>

                                <form action="${pageContext.request.contextPath}/controller.do"
                                    method="GET" style="display:inline;">
                                    <input type="hidden" name="acao" value="CarregarProfissional">
                                    <input type="hidden" name="id" value="${profissional.id_pessoa}">
                                    <button type="submit" class="btn-action btn-edit"
                                        style="background-color: #007bff; color: white; border: 1px solid #007bff;">Editar</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div style="text-align: center; margin-top: 1.5rem; font-size: 0.95rem;">
            <a href="${pageContext.request.contextPath}/index.jsp" style="color: #4A4AFF; font-weight: 600; text-decoration: none;">Voltar para o Início</a>
        </div>
    </div>
</body>

</html>
