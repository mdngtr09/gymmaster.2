# gymmaster.2
# 💪 GymMaster

O **GymMaster** é um sistema web completo de gestão para academias, desenvolvido para auxiliar na administração de alunos, planos de treino, exercícios e divisões musculares.  
O projeto foi criado com foco em **organização, facilidade de uso e escalabilidade**.

---

## 🧱 Funcionalidades

- 👨‍🎓 **Cadastro de Alunos**
  - Nome, idade, contato e plano de treino associado.
  
- 🏋️ **Gestão de Exercícios**
  - Criação e listagem de exercícios com grupo muscular e descrição.

- 📋 **Divisão Muscular**
  - Associação de exercícios a cada aluno.
  - Cadastro de divisões de treino personalizadas (ex: Treino A, Treino B...).

- 💰 **Planos**
  - Cadastro e edição de planos de assinatura (mensal, trimestral, anual, etc).

- 🌗 **Interface Moderna**
  - Visual unificado e responsivo, com tema roxo inspirado no padrão de apps fitness.
  - Estilos reaproveitados via `estilo-base.css` para consistência entre módulos.

---

## 🧰 Tecnologias Utilizadas

### Backend
- **Java 23**
- **Spring Boot 3**
- **JPA / Hibernate**
- **MySQL**
- **Maven**

### Frontend
- **HTML5**
- **CSS3 (estilo-base.css unificado)**
- **JavaScript (consumindo API REST com fetch)**

---

## ⚙️ Estrutura de Pastas

GymMaster/
├── src/
│ ├── main/
│ │ ├── java/com/academia/gymmaster/
│ │ │ ├── controller/
│ │ │ ├── model/
│ │ │ ├── repository/
│ │ │ └── service/
│ │ └── resources/
│ │ ├── application.properties
│ │ └── static/
│ │ ├── css/
│ │ │ ├── estilo-base.css
│ │ │ ├── alunos.css
│ │ │ ├── planos.css
│ │ │ ├── exercicios.css
│ │ │ └── divisao.css
│ │ ├── js/
│ │ │ ├── alunos.js
│ │ │ ├── planos.js
│ │ │ ├── exercicios.js
│ │ │ └── divisao.js
│ │ └── templates/
│ │ ├── alunos.html
│ │ ├── planos.html
│ │ ├── exercicios.html
│ │ └── divisao.html
└── pom.xml



## 🚀 Como Executar o Projeto

### 🔧 Pré-requisitos
- Java 23+
- Maven
- MySQL (em execução)
- Navegador Web (para o frontend)

### ⚙️ Configuração do Banco de Dados

No arquivo `src/main/resources/application.properties`:

spring.datasource.url=jdbc:mysql://localhost:3306/gymmaster
spring.datasource.username=root
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
server.port=8081


A aplicação ficará disponível em:
http://localhost:8081


🌐 Rotas Principais da API
Método	Endpoint	Descrição
GET	/api/alunos	Lista todos os alunos
POST	/api/alunos	Cadastra novo aluno
GET	/api/exercicios	Lista os exercícios
POST	/api/exercicios	Cria um novo exercício
GET	/api/planos	Lista todos os planos
POST	/api/planos	Cadastra novo plano
GET	/api/treinos	Lista divisões de treino
POST	/api/treinos	Cadastra nova divisão muscular

🧩 Páginas Disponíveis
Página	Caminho	Função
🏠 Início	/	Página principal
👨‍🎓 Alunos	/alunos	Cadastro e listagem de alunos
💰 Planos	/planos	Gestão dos planos de academia
🏋️ Exercícios	/exercicios	Cadastro e edição de exercícios
🧠 Divisão	/divisao	Montagem de treinos personalizados

🧑‍💻 Autores e Créditos
Desenvolvido por:
Murillo Dias Nunes
💼 Projeto Integrador — Sistema de Academia
📚 Curso Técnico em Informática / Desenvolvimento de Sistemas

📄 Licença
Este projeto é de uso livre para fins educacionais e não possui fins comerciais.

