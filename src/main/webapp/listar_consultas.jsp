<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Consulta" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda de Consultas - AtendeAcademy</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
    
    <style>
        .table-container {
            width: 100%;
            overflow-x: auto;
            border-radius: 8px;
            margin-top: 1rem;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            background: #fff;
        }
        th, td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #e5e7eb;
        }
        th {
            background: #f9fafb;
            font-weight: 600;
            color: #6b7280;
            text-transform: uppercase;
            font-size: 0.85rem;
        }
        .status-badge {
            padding: 0.25rem 0.75rem;
            border-radius: 9999px;
            font-size: 0.85rem;
            font-weight: 600;
        }
        .status-agendada {
            background: #e0f2fe;
            color: #0284c7;
        }
    </style>
</head>
<body>

    <div class="auth-card" style="max-width: 800px; width: 90%;">
        <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 1rem;">
            <div>
                <h1 class="auth-title" style="margin-bottom: 0.2rem; text-align: left;">Agenda de Consultas</h1>
                <p style="color: #6b7280; margin: 0;">Próximos atendimentos e histórico.</p>
            </div>
            <a href="${pageContext.request.contextPath}/controller.do?acao=CadastrarConsulta"
                class="btn btn-green" style="margin-top: 0; display: inline-block;">Agendar Nova</a>
        </div>

        <div class="table-container">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Data/Hora</th>
                        <th>Status</th>
                        <th>Observações</th>
                        <th>Ações</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        List<Consulta> lista = (List<Consulta>) request.getAttribute("consultas");
                        if (lista != null && !lista.isEmpty()) {
                            for (Consulta c : lista) {
                    %>
                            <tr>
                                <td>#<%= c.getId_consulta() %></td>
                                <td style="font-weight: 500;">
                                    <%= c.getData_hora() !=null ? c.getData_hora().toString() : "A Definir" %>
                                </td>
                                <td>
                                    <span class="status-badge status-agendada">
                                        <%= c.getStatus() !=null ? c.getStatus() : "Agendada" %>
                                    </span>
                                </td>
                                <td style="color: #6b7280;">
                                    <%= c.getObservacoes() !=null ? c.getObservacoes() : "-" %>
                                </td>
                                <td>
                                    <a href="#" style="color: #4A4AFF; text-decoration: none; font-weight: 600;">Detalhes</a>
                                </td>
                            </tr>
                    <%      } 
                        } else { 
                    %>
                            <tr>
                                <td colspan="5" style="text-align: center; padding: 3rem; color: #6b7280;">
                                    Nenhuma consulta agendada no momento.
                                </td>
                            </tr>
                    <%  } %>
                </tbody>
            </table>
        </div>
        
        <div class="bottom-link">
            <a href="${pageContext.request.contextPath}/index.jsp">Voltar para o Início</a>
        </div>
    </div>

</body>
</html>