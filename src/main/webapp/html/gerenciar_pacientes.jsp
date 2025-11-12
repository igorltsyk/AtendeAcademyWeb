<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerenciar Pacientes - AtendeAcademy</title>

    <!-- Importando a fonte Inter e o CSS de cadastro -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cadastro.css">

    <style>
        /* Estilos adicionais para esta página */
        .auth-card {
            max-width: 500px; /* Um pouco mais largo para o CRUD */
        }
        .btn-group {
            display: flex;
            flex-wrap: wrap;
            gap: 10px; /* Espaço entre os botões */
            margin-top: 1.5rem;
        }
        .btn {
            flex-grow: 1; /* Faz os botões crescerem */
            min-width: 150px; /* Largura mínima */
        }
        .btn-blue {
            background-color: #007bff;
            color: white;
            border: 2px solid #007bff;
        }
        .btn-blue:hover {
            background-color: #0069d9;
            border-color: #0062cc;
        }
        .btn-gray {
            background-color: #6c757d;
            color: white;
            border: 2px solid #6c757d;
        }
        .btn-gray:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }
        .search-group {
            display: flex;
            gap: 10px;
            margin-top: 1.5rem;
        }
        .search-group input[type="text"] {
            flex-grow: 1;
        }
    </style>
</head>
<body>

<div class="auth-card">
    <h1 class="auth-title">Gerenciar Pacientes</h1>

    <form action="controlePaciente" method="POST">
        <!-- Campos para CADASTRO -->
        <h2 style="font-weight: 600; color: #4A4AFF; margin-bottom: 1rem;">Cadastrar Novo Paciente</h2>

        <div class="form-group">
            <input type="text" class="form-input" placeholder="Nome Completo" name="nome">
        </div>
        <div class="form-group">
            <input type="text" class="form-input" placeholder="CPF" name="cpf">
        </div>
        <div class="form-group">
            <input type="number" class="form-input" placeholder="Idade" name="idade">
        </div>
        <div class="form-group">
            <input type="email" class="form-input" placeholder="E-mail" name="email">
        </div>
        <div class="form-group">
            <input type="password" class="form-input" placeholder="Senha" name="senha">
        </div>
        <div class="form-group">
            <input type="tel" class="form-input" placeholder="Telefone" name="telefone">
        </div>

        <div class="btn-group">
            <button type="submit" name="op" value="CADASTRAR" class="btn btn-green">
                Cadastrar
            </button>
        </div>
    </form>

    <hr style="border: 0; border-top: 1px solid #e5e7eb; margin: 2rem 0;">

    <!-- Seção para CONSULTA POR ID -->
    <h2 style="font-weight: 600; color: #4A4AFF; margin-bottom: 1rem;">Consultar por ID</h2>
    <form action="controlePaciente" method="GET" class="search-group">
        <input type="text" class="form-input" placeholder="Digite o ID do Paciente" name="idpaciente_busca">
        <button type="submit" name="op" value="CONSULTAR_POR_ID" class="btn btn-blue" style="margin-top: 0; min-width: 120px;">
            Buscar
        </button>
    </form>

    <hr style="border: 0; border-top: 1px solid #e5e7eb; margin: 2rem 0;">

    <!-- Botão para CONSULTAR TODOS -->
    <div class="btn-group">
        <a href="controlePaciente?op=CONSULTAR_TODOS" class="btn btn-gray" style="text-decoration: none;">
            Listar Todos os Pacientes
        </a>
    </div>
</div>

</body>
</html>