<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sistema de Agendamento Gratuito (Visualização Estática)</title>

    <!-- Importando a fonte Inter -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;600;700&display=swap" rel="stylesheet">

    <style>

        html {
            box-sizing: border-box;
            line-height: 1.5;
            -webkit-text-size-adjust: 100%;
        }
        *, *::before, *::after {
            box-sizing: inherit;
        }
        body, h1, h2, h3, h4, p, figure, blockquote, dl, dd {
            margin: 0;
        }
        button, input, select, textarea {
            font-family: Inter, sans-serif;
            font-size: 100%;
            margin: 0;
            padding: 0;
            border: none;
            background: transparent;
        }
        button {
            cursor: pointer;
        }
        input[type="text"],
        input[type="datetime-local"],
        select,
        textarea {
            border: 1px solid #ccc; /* Padrão, será sobrescrito */
        }
        select {
            -webkit-appearance: none;
            -moz-appearance: none;
            appearance: none;
        }

        /* --- Estilos Originais do Arquivo --- */
        body {
            font-family: 'Inter', sans-serif;
            background-color: #f3f4f6; /* cinza claro de fundo (bg-gray-100) */
        }
        .card {
            box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -2px rgba(0, 0, 0, 0.06); /* shadow-md */
        }

        /* --- Estilos Traduzidos do Tailwind --- */

        /* Layout Principal */
        #main-container {
            min-height: 100vh;
            padding: 1rem; /* p-4 */
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        @media (min-width: 640px) { /* sm: */
            #main-container {
                padding: 2rem; /* sm:p-8 */
            }
        }

        /* Tela de Autenticação */
        #auth-message-box {
            font-size: 0.875rem; /* text-sm */
            padding: 0.75rem; /* p-3 */
            border-radius: 0.25rem; /* rounded */
            margin-bottom: 1rem; /* mb-4 */
            display: none; /* hidden */
            background-color: #fee2e2; /* bg-red-100 */
            color: #b91c1c; /* text-red-700 */
        }

        /* Conteúdo da Aplicação */
        #app-content {
            width: 100%;
            max-width: 56rem; /* max-w-4xl */
            transition-property: opacity;
            transition-duration: 500ms; /* duration-500 */
        }

        /* Cabeçalho */
        header {
            background-color: #4338ca; /* bg-indigo-700 */
            padding: 1.5rem; /* p-6 */
            border-top-left-radius: 0.75rem; /* rounded-t-xl */
            border-top-right-radius: 0.75rem; /* rounded-t-xl */
            margin-bottom: 2rem; /* mb-8 */
        }
        header > .max-w-4xl {
            max-width: 56rem; /* max-w-4xl */
            margin-left: auto;
            margin-right: auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        header h1 {
            font-size: 1.875rem; /* text-3xl */
            font-weight: 800; /* font-extrabold */
            color: #ffffff; /* text-white */
        }
        header .flex.space-x-4 {
            display: flex;
            align-items: center;
            column-gap: 1rem; /* space-x-4 */
        }
        header .text-right {
            text-align: right;
        }
        header .text-right p:first-child {
            font-size: 0.875rem; /* text-sm */
            color: #c7d2fe; /* text-indigo-200 */
        }
        #user-id-display {
            font-family: Inter, sans-serif; /* font-mono */
            font-size: 0.75rem; /* text-xs */
            color: #ffffff; /* text-white */
            word-break: break-all; /* break-all */
        }
        #logout-button {
            padding: 0.5rem 1rem; /* py-2 px-4 */
            background-color: #ef4444; /* bg-red-500 */
            color: #ffffff; /* text-white */
            font-weight: 500; /* font-medium */
            border-radius: 0.5rem; /* rounded-lg */
            transition-duration: 150ms; /* duration-150 */
            transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1); /* ease-in-out */
        }
        #logout-button:hover {
            background-color: #dc2626; /* hover:bg-red-600 */
        }

        /* Conteúdo Principal (Main) */
        main {
            max-width: 56rem; /* max-w-4xl */
            margin-left: auto;
            margin-right: auto;
            display: flex;
            flex-direction: column;
            row-gap: 2rem; /* space-y-8 */
        }

        /* Seções */
        section {
            background-color: #ffffff; /* bg-white */
            padding: 1.5rem; /* p-6 */
            border-radius: 0.75rem; /* rounded-xl */
        }
        section h2 {
            font-size: 1.5rem; /* text-2xl */
            font-weight: 600; /* font-semibold */
            color: #1f2937; /* text-gray-800 */
            margin-bottom: 1rem; /* mb-4 */
            border-bottom-width: 1px; /* border-b */
            border-bottom-color: #e5e7eb; /* Padrão do Tailwind */
            padding-bottom: 0.5rem; /* pb-2 */
        }

        /* Formulário */
        #scheduling-form {
            display: flex;
            flex-direction: column;
            row-gap: 1rem; /* space-y-4 */
        }
        label {
            display: block; /* block */
            font-size: 0.875rem; /* text-sm */
            font-weight: 500; /* font-medium */
            color: #374151; /* text-gray-700 */
        }

        /* Inputs, Select, Textarea */
        select,
        input[type="datetime-local"],
        input[type="text"],
        textarea {
            margin-top: 0.25rem; /* mt-1 */
            display: block; /* block */
            width: 100%; /* w-full */
            border-radius: 0.375rem; /* rounded-md */
            border: 1px solid #d1d5db; /* border-gray-300 */
            transition-duration: 150ms;
            transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
        }

        /* Estilos específicos dos inputs */
        #service-select {
            padding: 0.5rem 2.5rem 0.5rem 0.75rem; /* py-2 pl-3 pr-10 */
            font-size: 1rem; /* text-base */
            /* Adiciona uma seta padrão de CSS */
            background-image: url("data:image/svg+xml;charset=utf-8,%3Csvg xmlns='http://www.w3.org/2000/svg' fill='none' viewBox='0 0 20 20'%3E%3Cpath stroke='%236b7280' stroke-linecap='round' stroke-linejoin='round' stroke-width='1.5' d='m6 8 4 4 4-4'/%3E%3C/svg%3E");
            background-position: right 0.5rem center;
            background-repeat: no-repeat;
            background-size: 1.5em 1.5em;
        }
        @media (min-width: 640px) { /* sm: */
            #service-select {
                font-size: 0.875rem; /* sm:text-sm */
            }
        }

        input[type="datetime-local"],
        input[type="text"],
        textarea {
            padding: 0.5rem; /* p-2 */
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05); /* shadow-sm */
        }

        /* Foco dos Inputs */
        select:focus,
        input[type="datetime-local"]:focus,
        input[type="text"]:focus,
        textarea:focus {
            outline: none;
            border-color: #6366f1; /* focus:border-indigo-500 */
            /* Anel de foco (ring) */
            box-shadow: 0 0 0 3px rgba(99, 102, 241, 0.5); /* focus:ring-indigo-500 */
        }

        /* Grid do Formulário */
        #scheduling-form .grid {
            display: grid;
            grid-template-columns: repeat(1, minmax(0, 1fr)); /* grid-cols-1 */
            gap: 1rem; /* gap-4 */
        }
        @media (min-width: 640px) { /* sm: */
            #scheduling-form .grid {
                grid-template-columns: repeat(2, minmax(0, 1fr)); /* sm:grid-cols-2 */
            }
        }

        /* Botão de Agendar */
        #schedule-button {
            width: 100%; /* w-full */
            display: flex; /* flex */
            justify-content: center; /* justify-center */
            padding: 0.5rem 1rem; /* py-2 px-4 */
            border: 1px solid transparent; /* border border-transparent */
            border-radius: 0.375rem; /* rounded-md */
            box-shadow: 0 1px 2px 0 rgba(0, 0, 0, 0.05); /* shadow-sm */
            font-size: 0.875rem; /* text-sm */
            font-weight: 500; /* font-medium */
            color: #ffffff; /* text-white */
            background-color: #4f46e5; /* bg-indigo-600 */
            transition-duration: 150ms;
            transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
        }
        #schedule-button:hover {
            background-color: #4338ca; /* hover:bg-indigo-700 */
        }
        #schedule-button:focus {
            outline: 2px solid transparent;
            outline-offset: 2px;
            /* Anel de foco com offset */
            box-shadow: 0 0 0 2px #f3f4f6, 0 0 0 4px #6366f1; /* focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 */
        }

        /* Lista de Agendamentos */
        #appointments-list {
            display: flex;
            flex-direction: column;
            row-gap: 1rem; /* space-y-4 */
        }
        #appointments-list > div {
            background-color: #f9fafb; /* bg-gray-50 */
            padding: 1rem; /* p-4 */
            border-radius: 0.5rem; /* rounded-lg */
            border: 1px solid #e5e7eb; /* border border-gray-200 */
        }
        #appointments-list .flex {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-bottom: 0.5rem; /* mb-2 */
        }
        #appointments-list h3 {
            font-size: 1.125rem; /* text-lg */
            font-weight: 700; /* font-bold */
            color: #4338ca; /* text-indigo-700 */
        }
        #appointments-list .flex span {
            padding: 0.25rem 0.75rem; /* px-3 py-1 */
            font-size: 0.75rem; /* text-xs */
            font-weight: 600; /* font-semibold */
            border-radius: 9999px; /* rounded-full */
            background-color: #dbeafe; /* bg-blue-100 */
            color: #1e40af; /* text-blue-800 */
        }
        #appointments-list p {
            font-size: 0.875rem; /* text-sm */
            color: #374151; /* text-gray-700 */
            margin-bottom: 0.25rem; /* mb-1 */
        }
        #appointments-list p span {
            font-weight: 600; /* font-semibold */
        }
        #appointments-list p.italic {
            font-size: 0.75rem; /* text-xs */
            font-style: italic; /* italic */
            color: #6b7280; /* text-gray-500 */
            margin-top: 0.5rem; /* mt-2 */
        }
        .slots-grid {
            display: grid;
            grid-template-columns: repeat(1, minmax(0, 1fr)); /* 1 coluna por padrão */
            gap: 0.75rem; /* gap-3 */
            margin-top: 0.25rem; /* mt-1 */
            max-height: 250px;
            overflow-y: auto;
            border: 1px solid #d1d5db; /* border-gray-300 */
            border-radius: 0.375rem; /* rounded-md */
            padding: 0.75rem; /* p-3 */
        }

        @media (min-width: 640px) { /* sm: */
            .slots-grid {
                /* 2 colunas em telas maiores */
                grid-template-columns: repeat(2, minmax(0, 1fr));
            }
        }

        .slot-option {
            display: block;
            background-color: #f9fafb; /* bg-gray-50 */
            border: 1px solid #e5e7eb; /* border-gray-200 */
            border-radius: 0.375rem; /* rounded-md */
            padding: 0.75rem 1rem; /* py-3 px-4 */
            cursor: pointer;
            transition-duration: 150ms;
        }

        .slot-option:hover {
            background-color: #f3f4f6; /* hover:bg-gray-100 */
        }

        /* Esconde o radio button real */
        .slot-option input[type="radio"] {
            display: none;
        }

        .slot-info {
            display: flex;
            flex-direction: column;
        }

        .slot-date {
            font-weight: 600; /* font-semibold */
            color: #1f2937; /* text-gray-800 */
        }
        .slot-location {
            font-size: 0.875rem; /* text-sm */
            color: #4b5563; /* text-gray-600 */
        }

        /* Estilo quando o radio está selecionado */
        .slot-option input[type="radio"]:checked + .slot-info {
            /* Você pode adicionar um estilo de "selecionado" se quiser */
            /* Ex: contorno azul */
            outline: 2px solid #4f46e5; /* ring-2 ring-indigo-600 */
            border-radius: 0.25rem; /* Adicionado para o outline ficar bonito */
        }

        /* Ajuste para o outline não ser cortado */
        .slot-option input[type="radio"]:checked + .slot-info {
            box-shadow: 0 0 0 2px #4f46e5;
            border-radius: 0.25rem;
        }
    </style>
</head>
<body>

<div id="main-container" class="min-h-screen p-4 sm:p-8 flex flex-col items-center">

    <div id="auth-screen" class="">
        <div id="auth-message-box" class="text-sm p-3 rounded mb-4 hidden bg-red-100 text-red-700">
            Esta é uma mensagem de erro de exemplo.
        </div>



    </div>

    <div id="app-content" class="w-full max-w-4xl transition-opacity duration-500" style="display: block;">

        <header class="bg-indigo-700 p-6 rounded-t-xl mb-8 card">
            <div class="max-w-4xl mx-auto flex justify-between items-center">
                <h1 class="text-3xl font-extrabold text-white">
                    AtendeAcademy
                </h1>
                <div class="flex items-center space-x-4">
                    <div class="text-right">
                        <p class="text-sm text-indigo-200">Paciente:</p>
                        <span>Olá, ${sessionScope.alunoLogado.nomealuno}!</span>
                    </div>
                    <button id="logout-button" class="py-2 px-4 bg-red-500 hover:bg-red-600 text-white font-medium rounded-lg transition duration-150 ease-in-out">
                        Voltar
                    </button>
                </div>
            </div>
        </header>

        <main class="max-w-4xl mx-auto space-y-8">

            <section class="bg-white p-6 rounded-xl card">
                <h2 class="text-2xl font-semibold text-gray-800 mb-4 border-b pb-2">Agendar Novo Serviço</h2>

                <form id="scheduling-form" class="space-y-4">

                    <div>
                        <label for="service-select" class="block text-sm font-medium text-gray-700">Serviço/Curso</label>
                        <select id="service-select" required class="mt-1 block w-full pl-3 pr-10 py-2 text-base border-gray-300 focus:outline-none focus:ring-indigo-500 focus:border-indigo-500 sm:text-sm rounded-md transition duration-150 ease-in-out">
                            <option value="" disabled selected>Selecione um Serviço</option>
                            <option value="1">Limpeza e Profilaxia (Odontologia)</option>
                            <option value="2">Declaração de Imposto de Renda (Ciências Contábeis)</option>
                            <option value="3">Orientação Psicológica (Psicologia)</option>
                        </select>
                    </div>

                    <div id="available-slots-container" style="display: none;">
                        <label class="block text-sm font-medium text-gray-700">Horários e Locais Disponíveis</label>

                        <div id="slots-grid" class="slots-grid">

                            <label class="slot-option">
                                <input type="radio" name="selectedSlot" value="1" required>
                                <div class="slot-info">
                                    <span class="slot-date">Segunda, 10/11/2025 - 14:00</span>
                                    <span class="slot-location">Prédio 1, Sala 10</span>
                                </div>
                            </label>

                            <label class="slot-option">
                                <input type="radio" name="selectedSlot" value="2">
                                <div class="slot-info">
                                    <span class="slot-date">Segunda, 10/11/2025 - 15:00</span>
                                    <span class="slot-location">Prédio 1, Sala 10</span>
                                </div>
                            </label>

                            <label class="slot-option">
                                <input type="radio" name="selectedSlot" value="3">
                                <div class="slot-info">
                                    <span class="slot-date">Terça, 11/11/2025 - 09:00</span>
                                    <span class="slot-location">Prédio 3, Sala 21</span>
                                </div>
                            </label>
                        </div>

                        <p id="no-slots-message" style="display: none; color: #9ca3af; font-size: 0.875rem; text-align: center; margin-top: 1rem;">
                            Nenhum horário disponível para este serviço.
                        </p>
                    </div>



                    <div>
                        <label for="description-input" class="block text-sm font-medium text-gray-700">Descrição/Motivo do Agendamento (Opcional)</label>
                        <textarea id="description-input" rows="3" placeholder="Detalhe brevemente o que você precisa..." class="mt-1 block w-full p-2 border border-gray-300 rounded-md shadow-sm focus:ring-indigo-500 focus:border-indigo-500"></textarea>
                    </div>

                    <button type="submit" id="schedule-button" class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition duration-150 ease-in-out">
                        Agendar
                    </button>
                </form>
            </section>

            <section class="bg-white p-6 rounded-xl card">
                <h2 class="text-2xl font-semibold text-gray-800 mb-4 border-b pb-2">Meus Agendamentos</h2>

                <div id="appointments-list" class="space-y-4">
                    <div class="bg-gray-50 p-4 rounded-lg border border-gray-200">
                        <div class="flex items-center justify-between mb-2">
                            <h3 class="text-lg font-bold text-indigo-700">Limpeza e Profilaxia</h3>
                            <span class="px-3 py-1 text-xs font-semibold rounded-full bg-blue-100 text-blue-800">Agendado</span>
                        </div>
                        <p class="text-sm text-gray-700 mb-1">
                            <span class="font-semibold">Data:</span> 31 de outubro de 2025 às 14:30
                        </p>
                        <p class="text-sm text-gray-700 mb-1">
                            <span class="font-semibold">Local:</span> Prédio B, Sala 102
                        </p>
                        <p class="text-xs italic text-gray-500 mt-2 italic">"Consulta de rotina."</p>
                    </div>

                </div>
            </section>
        </main>
    </div>
</div>

</body>
</html>