<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<jsp:include page="components/header.jsp" />

<div class="glass-panel text-center mt-2">
    <h1>Bem-vindo ao AtendeAcademy</h1>
    <p class="subtitle">O sistema de gestÃ£o odontolÃ³gica moderno da sua faculdade.</p>
    
    <div style="display: flex; gap: 2rem; justify-content: center; margin-top: 2rem;">
        <div class="glass-panel" style="flex: 1; padding: 2rem; text-align: left;">
            <h3 style="color: var(--primary-dark); margin-bottom: 1rem;">Pacientes</h3>
            <p style="color: var(--text-muted); margin-bottom: 1.5rem; font-size: 0.95rem;">Gerencie o cadastro de pacientes, prontuÃ¡rios e histÃ³ricos mÃ©dicos.</p>
            <a href="#" class="btn btn-outline" style="width: 100%; text-align: center;">Ver Pacientes</a>
        </div>
        
        <div class="glass-panel" style="flex: 1; padding: 2rem; text-align: left; background: rgba(0, 188, 212, 0.05); border-color: rgba(0, 188, 212, 0.2);">
            <h3 style="color: var(--primary-dark); margin-bottom: 1rem;">Agendamentos</h3>
            <p style="color: var(--text-muted); margin-bottom: 1.5rem; font-size: 0.95rem;">Controle a agenda dos profissionais e marque novas consultas rapidamente.</p>
            <a href="${pageContext.request.contextPath}/controller.do?acao=ListarConsulta" class="btn" style="width: 100%; text-align: center;">Acessar Agenda</a>
        </div>

        <div class="glass-panel" style="flex: 1; padding: 2rem; text-align: left;">
            <h3 style="color: var(--primary-dark); margin-bottom: 1rem;">Profissionais</h3>
            <p style="color: var(--text-muted); margin-bottom: 1.5rem; font-size: 0.95rem;">Cadastre dentistas, controle a disponibilidade e especialidades (CRMs).</p>
            <a href="#" class="btn btn-outline" style="width: 100%; text-align: center;">Ver Profissionais</a>
        </div>
    </div>
</div>

<jsp:include page="components/footer.jsp" />
