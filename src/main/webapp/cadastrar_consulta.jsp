<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Agendar Consulta - AtendeAcademy</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">
    </head>

    <body>

        <div class="auth-card" style="max-width: 600px;">
            <h1 class="auth-title" style="margin-bottom: 0.5rem;">Agendar Consulta</h1>
            <p style="text-align: center; color: #6b7280; margin-bottom: 2rem;">Preencha os dados para registrar um novo
                atendimento.</p>

            <% String mensagemErro=(String) request.getAttribute("mensagemErro"); if (mensagemErro !=null) { %>
                <div
                    style="background-color: #fee2e2; color: #dc2626; padding: 1rem; border-radius: 0.5rem; margin-bottom: 1rem; text-align: center; border: 1px solid #fca5a5;">
                    <%= mensagemErro %>
                </div>
                <% } %>

                    <form action="${pageContext.request.contextPath}/controller.do?acao=CadastrarConsulta" method="POST"
                        style="display: flex; flex-direction: column; gap: 1rem;">

                        <div style="display: flex; gap: 1rem; flex-wrap: wrap;">
                            <div class="form-group" style="flex: 1; min-width: 200px; margin-bottom: 0;">
                                <label for="idPaciente"
                                    style="display: block; font-weight: 500; margin-bottom: 0.5rem; font-size: 0.9rem;">Paciente</label>
                                <select id="idPaciente" name="idPaciente" required class="form-input">
                                    <option value="" disabled selected>Selecione seu nome...</option>
                                    <% java.util.List<model.Paciente> pacientes = (java.util.List<model.Paciente>)
                                            request.getAttribute("listaPacientes");
                                            if (pacientes != null) {
                                            for (model.Paciente p : pacientes) { %>
                                            <option value="<%= p.getId_pessoa() %>">
                                                <%= p.getNome() %>
                                            </option>
                                            <% } } %>
                                </select>
                            </div>

                            <div class="form-group" style="flex: 1; min-width: 200px; margin-bottom: 0;">
                                <label for="idServico"
                                    style="display: block; font-weight: 500; margin-bottom: 0.5rem; font-size: 0.9rem;">O
                                    que deseja agendar?</label>
                                <select id="idServico" name="idServico" required class="form-input">
                                    <option value="" disabled selected>Escolha o procedimento...</option>
                                    <% java.util.List<model.Servico> servicos = (java.util.List<model.Servico>)
                                            request.getAttribute("listaServicos");
                                            if (servicos != null) {
                                            for (model.Servico s : servicos) { %>
                                            <option value="<%= s.getId_servico() %>">
                                                <%= s.getNome_servico() %> (R$ <%= s.getValor_servico() %>)
                                            </option>
                                            <% } } %>
                                </select>
                            </div>
                        </div>

                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="idProfissional"
                                style="display: block; font-weight: 500; margin-bottom: 0.5rem; font-size: 0.9rem;">Dentista
                                de Preferência</label>
                            <select id="idProfissional" name="idProfissional" required class="form-input">
                                <option value="" disabled selected>Com quem você deseja consultar?</option>
                                <% java.util.List<model.Profissional> profissionais = (java.util.List
                                    <model.Profissional>) request.getAttribute("listaProfissionais");
                                        if (profissionais != null) {
                                        for (model.Profissional prof : profissionais) { %>
                                        <option value="<%= prof.getId_pessoa() %>">
                                            <%= prof.getNome() %> - <%= prof.getEspecialidade() %>
                                        </option>
                                        <% } } %>
                            </select>
                        </div>

                        <div style="display: flex; gap: 1rem;">
                            <div class="form-group" style="flex: 2; margin-bottom: 0;">
                                <label for="data"
                                    style="display: block; font-weight: 500; margin-bottom: 0.5rem; font-size: 0.9rem;">Data
                                    da Consulta</label>
                                <input type="date" id="data" name="data" required class="form-input">
                            </div>
                            <div class="form-group" style="flex: 1; margin-bottom: 0;">
                                <label for="hora"
                                    style="display: block; font-weight: 500; margin-bottom: 0.5rem; font-size: 0.9rem;">Horário</label>
                                <input type="time" id="hora" name="hora" required class="form-input">
                            </div>
                        </div>

                        <div class="form-group" style="margin-bottom: 0;">
                            <label for="observacoes"
                                style="display: block; font-weight: 500; margin-bottom: 0.5rem; font-size: 0.9rem;">Observações</label>
                            <textarea id="observacoes" name="observacoes" rows="3" class="form-input"
                                style="resize: vertical;"></textarea>
                        </div>

                        <button type="submit" class="btn btn-green">Confirmar Agendamento</button>
                    </form>

                    <div class="bottom-link">
                        <a href="${pageContext.request.contextPath}/index.jsp">Voltar para o Início</a>
                    </div>
        </div>

    </body>

    </html>