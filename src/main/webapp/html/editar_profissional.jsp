<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Editar Profissional - AtendeAcademy</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">

    </head>

    <body>

        <div class="auth-card">
            <h1 class="auth-title">Editar Dentista</h1>

            <form action="${pageContext.request.contextPath}/controller.do" method="post">
                <input type="hidden" name="acao" value="EditarProfissional">
                <input type="hidden" name="id" value="${profissional.id_pessoa}">
                <div class="form-group">
                    <input type="text" class="form-input" placeholder="Nome Completo" name="nome" value="${profissional.nome}" required>
                </div>

                <div class="form-group">
                    <input type="text" class="form-input" placeholder="Digite seu CPF" name="cpf" value="${profissional.cpf}" required readonly style="background-color: #f3f4f6; color: #6b7280; cursor: not-allowed;">
                </div>

                <div class="form-group">
                    <input type="text" class="form-input" placeholder="Registro CRM (Ex: 12345-SP)" name="crm" value="${profissional.crm}" required readonly style="background-color: #f3f4f6; color: #6b7280; cursor: not-allowed;">
                </div>

                <div class="form-group">
                    <input type="text" class="form-input" placeholder="Especialidade (Ex: Ortodontia)"
                        name="especialidade" value="${profissional.especialidade}" required>
                </div>

                <div class="form-group">
                    <input type="number" class="form-input" placeholder="Sua Idade" name="idade" value="30" required>
                </div>

                <div class="form-group">
                    <input type="email" class="form-input" placeholder="Seu E-mail" name="email" value="${profissional.email}" required>
                </div>

                <div class="form-group">
                    <input type="password" class="form-input" placeholder="Sua Senha (ou nova senha)"
                        name="senha" value="${profissional.senha}" required>
                </div>

                <div class="form-group">
                    <input type="tel" class="form-input" placeholder="Digite seu telefone" name="telefone" value="${profissional.telefone}" required>
                </div>

                <div class="form-group">
                    <label class="form-label">Gênero</label>
                    <div class="radio-group">
                        <input type="radio" id="genMasc" name="genero" value="Masculino" ${profissional.genero == 'Masculino' ? 'checked' : ''}>
                        <label for="genMasc">Masculino</label>

                        <input type="radio" id="genFem" name="genero" value="Feminino" ${profissional.genero == 'Feminino' ? 'checked' : ''}>
                        <label for="genFem">Feminino</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label">Estado Civil</label>
                    <div class="radio-group">
                        <input type="radio" id="estSol" name="estadocivil" value="Solteiro" ${profissional.estado_civil == 'Solteiro' ? 'checked' : ''}>
                        <label for="estSol">Solteiro(a)</label>

                        <input type="radio" id="estCas" name="estadocivil" value="Casado" ${profissional.estado_civil == 'Casado' ? 'checked' : ''}>
                        <label for="estCas">Casado(a)</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label">Disponibilidade Imediata?</label>
                    <div class="radio-group">
                        <input type="radio" id="dispSim" name="disponivel" value="true" ${profissional.statusDisponibilidade ? 'checked' : ''}>
                        <label for="dispSim">Sim</label>

                        <input type="radio" id="dispNao" name="disponivel" value="false" ${!profissional.statusDisponibilidade ? 'checked' : ''}>
                        <label for="dispNao">Não</label>
                    </div>
                </div>

                <button type="submit" class="btn btn-green">
                    Salvar Alterações
                </button>

            </form>
            
            <div class="bottom-link">
                <a href="${pageContext.request.contextPath}/controller.do?acao=ListarProfissional">Voltar para a Lista</a>
            </div>

        </div>

    </body>

    </html>
