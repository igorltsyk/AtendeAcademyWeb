<%@ page import="java.util.List" %>
<%@ page import="model.Paciente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Lista de Pacientes - AtendeAcademy</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="css/lista_style.css">
</head>
<body>

<div class="container">
    <h1>Todos os Pacientes</h1>

    <table>
        <thead>
        <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>CPF</th>
            <th>Idade</th>
            <th>Email</th>
            <th>Telefone</th>
            <th>Editar</th>
            <th>Deletar</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Paciente> listaPacientes = (List<Paciente>) request.getAttribute("listaPacientes");
            if (listaPacientes != null && !listaPacientes.isEmpty()) {
                for (Paciente p : listaPacientes) {
        %>
        <tr>
            <td><% out.print(p.getIdpaciente()); %></td>
            <td><% out.print(p.getNomepaciente()); %></td>
            <td><% out.print(p.getCpfpaciente()); %></td>
            <td><% out.print(p.getIdade()); %></td>
            <td><% out.print(p.getEmail()); %></td>
            <td><% out.print(p.getTelefone()); %></td>
            <td align="center">
                <a href="controlePaciente?op=ATUALIZAR&idpaciente=<% out.print(p.getIdpaciente()); %>" class="btn-edit">
                    Editar
                </a>
            </td>
            <td align="center">
                <a href="controlePaciente?op=DELETAR&idpaciente=<% out.print(p.getIdpaciente()); %>" class="btn-delete" onclick="return confirm('Tem certeza que deseja deletar este paciente?');">
                    Deletar
                </a>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="8" style="text-align: center;">Nenhum paciente encontrado.</td>
        </tr>
        <%
            }
        %>
        </tbody>
    </table>

    <div style="margin-top: 2rem; text-align: center;">
        <a href="gerenciar_pacientes.jsp" class="btn-back">Voltar ao Gerenciamento</a>
    </div>
</div>

</body>
</html>