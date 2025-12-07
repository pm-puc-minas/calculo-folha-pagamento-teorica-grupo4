## 💻 README: Guia de Inicialização do Projeto

Este documento fornece as instruções essenciais para configurar e rodar o projeto **calculo-folha-pagamento-teorica-grupo4**, que inclui um Front-End em JavaScript/Framework (React) e um Back-End em Spring Boot (Java).

---

### 🚀 1. Configurando e Rodando o Front-End

O Front-End é a interface do usuário e requer Node.js e npm.

#### **Passos:**

1.  **Navegue para o diretório do Front-End:**
    ```bash
    cd calculo-folha-pagamento-teorica-grupo4/FrontEnd
    ```

2.  **Instale as dependências do projeto:**
    ```bash
    npm install
    ```

3.  **Instale a biblioteca de gráficos `recharts`:**
    ```bash
    npm install recharts
    ```

4.  **Inicie o servidor de desenvolvimento:**
    ```bash
    npm run dev
    ```

5.  Acesse o link fornecido no console (ex: `http://localhost:5173/`).

---

### 💾 2. Configurando e Rodando o Back-End

O Back-End é o servidor da aplicação e exige a configuração de um banco de dados relacional (ex: PostgreSQL).

#### **Passos:**

1.  **Configuração do Banco de Dados:**
    * No diretório do Back-End, localize o arquivo de propriedades (ex: `application.properties`).
    * **Ajuste** as configurações de conexão do banco de dados (DataSource) com suas credenciais:
        * `spring.datasource.username=seu_login`
        * `spring.datasource.password=sua_senha`
    * *Certifique-se* de que o driver e a URL do banco (ex: PostgreSQL) estejam corretamente configurados no mesmo arquivo.

2.  **Execução da Aplicação:**
    * Abra o projeto na IDE (IntelliJ, Eclipse, VS Code, etc.).
    * Localize o arquivo principal da aplicação: `BackEndApplication.java`.
    * **Rode o arquivo `BackEndApplication.java`** como uma aplicação Spring Boot.
