![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![React](https://img.shields.io/badge/react-%2320232a.svg?style=for-the-badge&logo=react&logoColor=%2361DAFB)


🪙 Wishlist Budget - Simulador de Cofre de Desejos
Este é um projeto Full Stack desenvolvido para consolidar meus estudos em Spring Boot e React. A aplicação permite que o usuário gerencie metas financeiras ("desejos"), acompanhando quanto já economizou e quanto falta para realizar cada objetivo.

🚀 Objetivo do Projeto
Praticar a comunicação entre uma API REST uma interface dinâmica, focando em:

Backend: CRUD completo, persistência de dados e lógica de negócio.

Frontend: Consumo de APIs, gerenciamento de estado e componentes reutilizáveis.

🛠️ Tecnologias Utilizadas
Backend (API)
Java 17 & Spring Boot 3

Spring Data JPA: Abstração da camada de dados.

Mysql:Banco de dados.

Maven: Gerenciador de dependências.

Lombok: Produtividade no código Java.

Frontend (Interface)
React.js: Biblioteca principal para a UI.

CSS Modules / Styled Components: (Ajuste conforme sua escolha de estilização).

Vite / Create React App: Ferramenta de build.

📋 Funcionalidades
[x] Painel de Controle: Visualização geral dos desejos e economias.

[x] Gestão de Desejos: Criar, editar e excluir metas (itens desejados).

[x] Simulador de Depósito: Adicionar valores ao "cofre" de um item específico.

[x] Cálculo Automático: A interface mostra a porcentagem (%) concluída de cada meta.

⚙️ Como Executar o Projeto
1. Backend (Spring Boot)
Navegue até a pasta do backend:

Bash
cd backend
./mvnw spring-boot:run
A API rodará em: http://localhost:8080

2. Frontend (React)
Navegue até a pasta do frontend :

Bash
cd frontend
npm install
npm start
A interface rodará em: http://localhost:5173 (se usar Vite) ou 3000.

📁 Estrutura de Pastas Simplificada
Plaintext
├── backend/            # Código fonte Spring Boot
│   ├── src/main/java   # Controllers, Services, Models e Repositories
│   └── pom.xml         # Dependências Maven
├── frontend/           # Código fonte React
│   ├── src/components  # Componentes da interface
│   ├── src/services    # Configuração do Axios para chamadas à API
│   └── package.json    # Dependências Node
└── README.md


💡 Aprendizados:
Este simulador foi fundamental para evoluir minhas habilidades com o ecossistema Spring. Foquei em melhorar a estrutura das requisições e a segurança na transição de informações. Aprendi a lidar com o tratamento de erros no Spring, garantindo que o backend seja confiável, e conectei tudo a um frontend em React focado em performance e feedback em tempo real para o usuário.


⭐ Projeto desenvolvido por Guilherme dos Anjos Silva como parte da minha jornada de aprendizado em desenvolvimento de software.
