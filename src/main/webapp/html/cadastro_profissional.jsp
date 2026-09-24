<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <!DOCTYPE html>
    <html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Cadastro Profissional - AtendeAcademy</title>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">

    </head>

    <body>

        <div class="auth-card">
            <h1 class="auth-title">Registrar Dentista<br>AtendeAcademy</h1>

            <form action="${pageContext.request.contextPath}/controller.do" method="post">
                <input type="hidden" name="acao" value="CadastrarProfissional">
                <div class="form-group">
                    <input type="text" class="form-input" placeholder="Nome Completo" name="nome" required>
                </div>

                <div class="form-group">
                    <input type="text" class="form-input" placeholder="Digite seu CPF" name="cpf" required>
                </div>

                <div class="form-group">
                    <input type="text" class="form-input" placeholder="Registro CRM (Ex: 12345-SP)" name="crm" required>
                </div>

                <div class="form-group">
                    <input type="text" class="form-input" placeholder="Especialidade (Ex: Ortodontia)"
                        name="especialidade" required>
                </div>

                <div class="form-group">
                    <input type="number" class="form-input" placeholder="Sua Idade" name="idade" required>
                </div>

                <div class="form-group">
                    <input type="email" class="form-input" placeholder="Seu E-mail" name="email" required>
                </div>

                <div class="form-group">
                    <input type="password" class="form-input" placeholder="Crie uma Senha (mín. 6 caracteres)"
                        name="senha" required>
                </div>

                <div class="form-group">
                    <input type="tel" class="form-input" placeholder="Digite seu telefone" name="telefone" required>
                </div>

                <div class="form-group">
                    <label class="form-label">Gênero</label>
                    <div class="radio-group">
                        <input type="radio" id="genMasc" name="genero" value="Masculino">
                        <label for="genMasc">Masculino</label>

                        <input type="radio" id="genFem" name="genero" value="Feminino">
                        <label for="genFem">Feminino</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label">Estado Civil</label>
                    <div class="radio-group">
                        <input type="radio" id="estSol" name="estadocivil" value="Solteiro">
                        <label for="estSol">Solteiro(a)</label>

                        <input type="radio" id="estCas" name="estadocivil" value="Casado">
                        <label for="estCas">Casado(a)</label>
                    </div>
                </div>

                <div class="form-group">
                    <label class="form-label">Disponibilidade Imediata?</label>
                    <div class="radio-group">
                        <input type="radio" id="dispSim" name="disponivel" value="true" checked>
                        <label for="dispSim">Sim</label>

                        <input type="radio" id="dispNao" name="disponivel" value="false">
                        <label for="dispNao">Não</label>
                    </div>
                </div>

                <button type="submit" class="btn btn-green">
                    Registrar-se como Profissional
                </button>

            </form>
            
            <div class="bottom-link">
                <a href="${pageContext.request.contextPath}/index.jsp">Voltar para o Início</a>
            </div>

        </div>

    </body>

    </html>