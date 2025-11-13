<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cadastro - AtendeAcademy</title>

<<<<<<< HEAD

=======
    <!-- Importando a fonte Inter -->
>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <%-- Caminho corrigido para usar o ContextPath --%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">

</head>
<body>

<div class="auth-card">
    <h1 class="auth-title">Registrar-se no<br>AtendeAcademy</h1>

<<<<<<< HEAD

    <form action="${pageContext.request.contextPath}/cadastro" method="post">
        <!-- nome -->
=======
    <%--
      CORREÇÃO AQUI:
      O 'action' agora aponta para o 'pacienteServlet' usando o ContextPath.
    --%>
    <form action="${pageContext.request.contextPath}/cadastro" method="post">
        <!-- Campo Nome Completo -->
>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
        <div class="form-group">
            <input type="text" class="form-input" placeholder="Nome Completo" name="nome" required>
        </div>

<<<<<<< HEAD
        <!-- cfp -->
        <div class="form-group">
            <input type="text" class="form-input" placeholder="Digite seu CPF" name="cpf" required>
        </div>
        <!-- idade -->
=======
        <!-- Campo CPF -->
        <div class="form-group">
            <input type="text" class="form-input" placeholder="Digite seu CPF" name="cpf" required>
        </div>

>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
        <div class="form-group">
            <input type="number" class="form-input" placeholder="Sua Idade" name="idade" required>
        </div>

<<<<<<< HEAD
        <!-- email -->
=======
        <!-- Campo E-mail -->
>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
        <div class="form-group">
            <input type="email" class="form-input" placeholder="Seu E-mail" name="email" required>
        </div>

<<<<<<< HEAD
        <!-- senha -->
=======
        <!-- Campo Senha -->
>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
        <div class="form-group">
            <input type="password" class="form-input" placeholder="Crie uma Senha (mín. 6 caracteres)" name="senha" required>
        </div>

<<<<<<< HEAD
        <!-- telefone -->
=======
        <!-- Campo Telefone -->
>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
        <div class="form-group">
            <input type="tel" class="form-input" placeholder="Digite seu telefone" name="telefone" required>
        </div>

<<<<<<< HEAD
        <!-- registrar -->
=======
        <!-- Botão Registrar -->
>>>>>>> 027322adab79dfc8b2f53dcb16d0f6c8627bc1f4
        <button type="submit" class="btn btn-green">
            Registrar-se como Paciente
        </button>
    </form>


</div>

</body>
</html>