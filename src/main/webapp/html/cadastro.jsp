<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro - AtendeAcademy</title>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">

</head>
<body>

<div class="auth-card">
    <h1 class="auth-title">Registrar-se no<br>AtendeAcademy</h1>

    <form action="${pageContext.request.contextPath}/cadastro" method="post">
        <div class="form-group">
            <input type="text" class="form-input" placeholder="Nome Completo" name="nome" required>
        </div>

        <div class="form-group">
            <input type="text" class="form-input" placeholder="Digite seu CPF" name="cpf" required>
        </div>

        <div class="form-group">
            <input type="number" class="form-input" placeholder="Sua Idade" name="idade" required>
        </div>

        <div class="form-group">
            <input type="email" class="form-input" placeholder="Seu E-mail" name="email" required>
        </div>

        <div class="form-group">
            <input type="password" class="form-input" placeholder="Crie uma Senha (mín. 6 caracteres)" name="senha" required>
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



        <button type="submit" class="btn btn-green">
            Registrar-se como Paciente
        </button>


    </form>

</div>

</body>
</html>