<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - AtendeAcademy</title>
    

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <style>

        body {
            font-family: 'Inter', sans-serif;
            background-color: #f3f4f6; /*  cinza claro */
            margin: 0;
            color: #374151;
        }


        .navbar {
            background-color: white;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.05);
            padding: 1rem 2rem; /* 16px vertical, 32px horizontal */
            display: flex;
            justify-content: space-between; /* alinha itens nas pontas */
            align-items: center;
        }

        .logo {
            color: #4A4AFF; /* Azul
            font-size: 1.5rem; /* 24px */
            font-weight: 700;
            text-decoration: none;
        }

        .user-menu {
            display: flex;
            align-items: center;
        }

        .user-menu span {
            margin-right: 1rem; /* 16px */
            font-weight: 600;
            color: #4b5563; /* Cinza */
        }

        .btn-logout {
            background-color: transparent;
            color: #625BFF; /* Azul do link */
            border: 2px solid #625BFF;
            padding: 0.5rem 1rem; /* 8px 16px */
            border-radius: 8px;
            font-weight: 600;
            font-size: 0.9rem;
            cursor: pointer;
            transition: all 0.2s ease;
        }

        .btn-logout:hover {
            background-color: #f4f3ff; /* auzkl */
        }

        /* --- Conteúdo Principal --- */
        .container {
            max-width: 1100px; /* argura máxima do conteúdo */
            margin: 0 auto; /* centraliza o container */
            padding: 2.5rem 2rem; /* 40px vertical, 32px horizontal */
        }

        .welcome-title {
            font-size: 2rem; /* 32px */
            font-weight: 700;
            color: #111827; /* preto suave */
            margin-top: 0;
            margin-bottom: 2rem; /* 32px */
        }

        /* --- Grade de Ações --- */
        .dashboard-grid {
            display: grid;

            grid-template-columns: repeat(auto-fit, minmax(280px, 1fr));
            gap: 1.5rem; /* 24px de espaço entre os cards */
        }


        .action-card {
            background-color: white;
            border-radius: 12px;
            box-shadow: 0 4px 12px rgba(0, 0, 0, 0.05);
            padding: 2rem; /* 32px */
            transition: all 0.2s ease-in-out;
            text-decoration: none;
            color: inherit; /* Herda a cor do texto padrão */
        }

        .action-card:hover {
            transform: translateY(-5px); /* Efeito de levantar */
            box-shadow: 0 6px 16px rgba(0, 0, 0, 0.08);
        }

        .card-icon {
            width: 48px;
            height: 48px;
            border-radius: 8px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 1.5rem; /* 24px */
        }
        
        /* icone SVG */
        .card-icon svg {
            width: 24px;
            height: 24px;
            stroke-width: 2;
        }


        .icon-blue {
            background-color: #eef2ff; /* Fundo azul claro */
            color: #4A4AFF; /* Ícone azul */
        }
        
        .icon-green {
            background-color: #f0fdf4; /* Fundo verde claro */
            color: #28a745; /* Ícone verde */
        }
        
        .icon-purple {
            background-color: #f5f3ff;
            color: #7c3aed;
        }

        .action-card h3 {
            font-size: 1.25rem; /* 20px */
            font-weight: 600;
            margin-top: 0;
            margin-bottom: 0.5rem; /* 8px */
        }

        .action-card p {
            font-size: 0.95rem;
            color: #6b7280;
            line-height: 1.6;
            margin: 0;
        }
        
    </style>
</head>
<body>


    <header class="navbar">
        <a href="#" class="logo">AtendeAcademy</a>
        <div class="user-menu">
            <span>Olá, ${sessionScope.alunoLogado.nomealuno}!</span>
            <button class="btn-logout">Sair</button>
        </div>
    </header>


    <main>
        <div class="container">
            <h1 class="welcome-title">Bem-vindo(a) de volta!</h1>


            <div class="dashboard-grid">


                <a href="agenda.jsp" class="action-card">
                    <div class="card-icon icon-blue">
                        <!-- incone calendario -->
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round" stroke-linejoin="round" d="M6.75 3v2.25m10.5-2.25v2.25m-10.5 0L3 16.5A2.25 2.25 0 0 0 5.25 18.75h13.5A2.25 2.25 0 0 0 21 16.5l-3.75-11.25zM10.5 8.25h3M9 11.25h6m-6 3h6" />
                        </svg>
                    </div>
                    <h3>Agendar Consulta</h3>
                    <p>Encontre horários disponíveis e marque um novo atendimento.</p>
                </a>


                <a href="#" class="action-card">
                    <div class="card-icon icon-green">
                        <!-- icone de lista  -->
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                           <path stroke-linecap="round" stroke-linejoin="round" d="M8.25 6.75h7.5M8.25 12h7.5m-7.5 5.25h7.5M3.75 6.75h.007v.008H3.75V6.75zm.375 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0zM3.75 12h.007v.008H3.75V12zm.375 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0zm-.375 5.25h.007v.008H3.75v-.008zm.375 0a.375.375 0 1 1-.75 0 .375.375 0 0 1 .75 0z" />
                        </svg>
                    </div>
                    <h3>Meus Agendamentos</h3>
                    <p>Visualize ou cancele suas consultas e exames futuros.</p>
                </a>

                

            </div>
        </div>
    </main>

</body>
</html>