<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <jsp:include page="components/header.jsp" />

    <div class="glass-panel" style="max-width: 600px; margin: 3rem auto;">
        <h2 style="color: var(--primary-dark); margin-bottom: 0.5rem;">Agendar Consulta</h2>
        <p class="subtitle">Preencha os dados para registrar um novo atendimento.</p>

        <form action="${pageContext.request.contextPath}/controller.do?acao=CadastrarConsulta" method="POST"
            style="display: flex; flex-direction: column; gap: 1.5rem;">

            <!-- SeÃ§Ã£o 1: Quem Ã© vocÃª e O que deseja? -->
            <div style="display: flex; gap: 1rem; flex-wrap: wrap;">
                <div style="flex: 1; min-width: 200px;">
                    <label for="idPaciente" style="display: block; font-weight: 500; margin-bottom: 0.5rem;">Sou o
                        Paciente (Falso Login)</label>
                    <select id="idPaciente" name="idPaciente" required
                        style="width: 100%; padding: 0.8rem; border-radius: 8px; border: 1px solid rgba(0,0,0,0.1); background: rgba(255,255,255,0.7); font-family: inherit;">
                        <option value="" disabled selected>Selecione seu nome...</option>
                        <% java.util.List<model.Paciente> pacientes = (java.util.List<model.Paciente>)
                                request.getAttribute("listaPacientes");
                                if (pacientes != null) {
                                for (model.Paciente p : pacientes) {
                                %>
                                <option value="<%= p.getId_pessoa() %>">
                                    <%= p.getNome() %>
                                </option>
                                <% } } %>
                    </select>
                </div>

                <div style="flex: 1; min-width: 200px;">
                    <label for="idServico" style="display: block; font-weight: 500; margin-bottom: 0.5rem;">O que deseja
                        agendar?</label>
                    <select id="idServico" name="idServico" required
                        style="width: 100%; padding: 0.8rem; border-radius: 8px; border: 1px solid rgba(0,0,0,0.1); background: rgba(255,255,255,0.7); font-family: inherit;">
                        <option value="" disabled selected>Escolha o procedimento...</option>
                        <% java.util.List<model.Servico> servicos = (java.util.List<model.Servico>)
                                request.getAttribute("listaServicos");
                                if (servicos != null) {
                                for (model.Servico s : servicos) {
                                %>
                                <option value="<%= s.getId_servico() %>">
                                    <%= s.getNome_servico() %> (R$ <%= s.getValor_servico() %>)
                                </option>
                                <% } } %>
                    </select>
                </div>
            </div>

            <!-- SeÃ§Ã£o 2: PreferÃªncia de Profissional -->
            <div>
                <label for="idProfissional" style="display: block; font-weight: 500; margin-bottom: 0.5rem;">Dentista de
                    PreferÃªncia</label>
                <select id="idProfissional" name="idProfissional" required
                    style="width: 100%; padding: 0.8rem; border-radius: 8px; border: 1px solid rgba(0,0,0,0.1); background: rgba(255,255,255,0.7); font-family: inherit;">
                    <option value="" disabled selected>Com quem vocÃª deseja consultar?</option>
                    <% java.util.List<model.Profissional> profissionais = (java.util.List<model.Profissional>)
                            request.getAttribute("listaProfissionais");
                            if (profissionais != null) {
                            for (model.Profissional prof : profissionais) {
                            %>
                            <option value="<%= prof.getId_pessoa() %>">
                                <%= prof.getNome() %> - <%= prof.getEspecialidade() %>
                            </option>
                            <% } } %>
                </select>
            </div>

            <div style="display: flex; gap: 1rem;">
                <div style="flex: 2;">
                    <label for="data" style="display: block; font-weight: 500; margin-bottom: 0.5rem;">Data da
                        Consulta</label>
                    <input type="date" id="data" name="data" required
                        style="width: 100%; padding: 0.8rem; border-radius: 8px; border: 1px solid rgba(0,0,0,0.1); background: rgba(255,255,255,0.7); font-family: inherit;">
                </div>
                <div style="flex: 1;">
                    <label for="hora" style="display: block; font-weight: 500; margin-bottom: 0.5rem;">HorÃ¡rio</label>
                    <input type="time" id="hora" name="hora" required
                        style="width: 100%; padding: 0.8rem; border-radius: 8px; border: 1px solid rgba(0,0,0,0.1); background: rgba(255,255,255,0.7); font-family: inherit;">
                </div>
            </div>

            <div>
                <label for="observacoes" style="display: block; font-weight: 500; margin-bottom: 0.5rem;">ObservaÃ§Ãµes
                    / Queixa Principal</label>
                <textarea id="observacoes" name="observacoes" rows="4"
                    style="width: 100%; padding: 0.8rem; border-radius: 8px; border: 1px solid rgba(0,0,0,0.1); background: rgba(255,255,255,0.7); font-family: inherit; resize: vertical;"></textarea>
            </div>

            <div style="display: flex; justify-content: flex-end; gap: 1rem; margin-top: 1rem;">
                <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline"
                    style="text-decoration: none;">Cancelar</a>
                <button type="submit" class="btn">Confirmar Agendamento</button>
            </div>
        </form>
    </div>

    <jsp:include page="components/footer.jsp" />