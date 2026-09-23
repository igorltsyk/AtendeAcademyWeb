<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="components/header.jsp" />

<div class="glass-panel text-center" style="max-width: 600px; margin: 4rem auto;">
    <div style="background: rgba(29, 233, 182, 0.1); width: 80px; height: 80px; border-radius: 50%; display: flex; align-items: center; justify-content: center; margin: 0 auto 2rem;">
        <svg xmlns="http://www.w3.org/2000/svg" width="40" height="40" viewBox="0 0 24 24" fill="none" stroke="var(--secondary-color)" stroke-width="3" stroke-linecap="round" stroke-linejoin="round">
            <polyline points="20 6 9 17 4 12"></polyline>
        </svg>
    </div>
    
    <h2>Consulta Agendada!</h2>
    <p class="subtitle mt-2" style="margin-bottom: 3rem;">O agendamento foi registrado com sucesso no sistema e a sala foi reservada.</p>
    
    <div style="display: flex; gap: 1rem; justify-content: center;">
        <a href="${pageContext.request.contextPath}/controller.do?acao=ListarConsulta" class="btn">Ver Agenda Completa</a>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline">Voltar ao InÃ­cio</a>
    </div>
</div>

<jsp:include page="components/footer.jsp" />
