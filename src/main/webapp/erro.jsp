<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.io.PrintWriter" %>
<jsp:include page="components/header.jsp" />

<div class="glass-panel text-center" style="max-width: 700px; margin: 4rem auto; border-color: rgba(255, 82, 82, 0.3);">
    <div style="background: rgba(255, 82, 82, 0.1); width: 80px; height: 80px; border-radius: 50%; display: flex; align-items: center; justify-content: center; margin: 0 auto 2rem;">
        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="#ff5252" stroke-width="2" stroke-linecap="round" stroke-linejoin="round">
            <circle cx="12" cy="12" r="10"></circle>
            <line x1="12" y1="8" x2="12" y2="12"></line>
            <line x1="12" y1="16" x2="12.01" y2="16"></line>
        </svg>
    </div>
    
    <h2 style="color: #d32f2f;">Ops! Ocorreu um problema.</h2>
    <p class="subtitle mt-2" style="margin-bottom: 2rem;">
        Não foi possível concluir sua requisição no momento. Nossa equipe técnica já foi notificada.
    </p>
    
    <% 
        Exception ex = (Exception) request.getAttribute("erro");
        if (ex != null) {
    %>
    <div style="background: rgba(255,255,255,0.5); padding: 1rem; border-radius: 8px; text-align: left; overflow-x: auto; font-family: monospace; font-size: 0.85rem; color: #555; margin-bottom: 2rem;">
        <strong>Detalhes técnicos:</strong><br>
        <%= ex.getMessage() != null ? ex.getMessage() : ex.toString() %>
    </div>
    <% } %>
    
    <a href="${pageContext.request.contextPath}/index.jsp" class="btn" style="background: linear-gradient(135deg, #607d8b, #455a64); box-shadow: none;">Voltar para Segurança</a>
</div>

<jsp:include page="components/footer.jsp" />
