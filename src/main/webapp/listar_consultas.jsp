<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Consulta" %>
<jsp:include page="components/header.jsp" />

<div class="glass-panel">
    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 2rem;">
        <div>
            <h2>Agenda de Consultas</h2>
            <p class="subtitle" style="margin-bottom: 0;">PrÃ³ximos atendimentos e histÃ³rico.</p>
        </div>
        <a href="${pageContext.request.contextPath}/controller.do?acao=CadastrarConsulta" class="btn">Agendar Nova</a>
    </div>

    <div class="table-container">
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Data/Hora</th>
                    <th>Status</th>
                    <th>ObservaÃ§Ãµes</th>
                    <th>AÃ§Ãµes</th>
                </tr>
            </thead>
            <tbody>
                <% 
                   // A Action envia um atributo "consultas" no request
                   List<Consulta> lista = (List<Consulta>) request.getAttribute("consultas");
                   
                   if (lista != null && !lista.isEmpty()) {
                       for (Consulta c : lista) { 
                %>
                <tr>
                    <td>#<%= c.getId_consulta() %></td>
                    <td style="font-weight: 500;"><%= c.getData_hora() != null ? c.getData_hora().toString() : "A Definir" %></td>
                    <td>
                        <span class="status-badge status-agendada">
                            <%= c.getStatus() != null ? c.getStatus() : "Agendada" %>
                        </span>
                    </td>
                    <td style="color: var(--text-muted);"><%= c.getObservacoes() != null ? c.getObservacoes() : "-" %></td>
                    <td>
                        <a href="#" style="color: var(--primary-color); text-decoration: none; font-weight: 500;">Detalhes</a>
                    </td>
                </tr>
                <% 
                       }
                   } else { 
                %>
                <tr>
                    <td colspan="5" class="text-center" style="padding: 3rem; color: var(--text-muted);">
                        Nenhuma consulta agendada no momento.
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="components/footer.jsp" />
