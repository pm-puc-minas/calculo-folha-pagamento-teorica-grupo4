
# README

## Projeto: Cálculo de Folha de Pagamento (Grupo 4)

**Descrição**

Sistema backend para cadastro de funcionários e geração de folhas de pagamento. Implementado em Spring Boot, com persistência em banco relacional (ex.: PostgreSQL, H2). Fornece endpoints para gestão de funcionários, geração e listagem de folhas, autenticação de administradores e funcionalidades de serialização/desserialização em arquivos JSON.

---

## Principais endpoints

### Endpoints dos funcionários

#### 1. Cadastrar funcionário
**POST** `http://localhost:8080/funcionarios`

Esse endpoint cadastra um funcionário.

**Exemplo de corpo (JSON):**

```json
{
  "nome": "Davi",
  "cpf": "791.800.968-70",
  "cargo": "Dev",
  "salarioBase": 1500.0,
  "cargaHorariaDiaria": 7.5,
  "horasTrabalhadas": 150,
  "diasTrabalhadosSemana": 5,
  "diasTrabalhadosMes": 21,
  "dataAdmissao": "2023-03-20",
  "receberValeTransporte": true,
  "receberValeAlimentacao": false,
  "custoValeTransporte": 180.0,
  "custoDiarioAlimentacao": 0.0,
  "periculo": "NAO",
  "insalu": "SIM",
  "insalubridade": "ALTO",
  "dependentes": 0
}
````

**Resposta esperada:** `201 CREATED`

```json
{
  "nome": "Pedro",
  "cpf": "791.800.968-00",
  "idFuncionario": 16,
  "cargo": "Dev",
  "salarioBase": 1500.0,
  "cargaHorariaDiaria": 7.5,
  "horasTrabalhadas": 150,
  "diasTrabalhadosSemana": 5,
  "diasTrabalhadosMes": 21,
  "dataAdmissao": "2023-03-20",
  "receberValeTransporte": true,
  "receberValeAlimentacao": false,
  "custoValeTransporte": 180.0,
  "custoDiarioAlimentacao": 0.0,
  "periculo": "NAO",
  "insalu": "SIM",
  "insalubridade": "ALTO",
  "dependentes": 0
}
```

---

#### 2. Listar funcionários

**GET** `http://localhost:8080/funcionarios`

Esse endpoint lista todos os funcionários cadastrados.

**Resposta esperada:** `200 OK` (array JSON com funcionários)

---

## Endpoints da folha de pagamento

#### 1. Gerar folha de pagamento (individual)

**POST** `http://localhost:8080/folha-pagamento/gerar/{idFuncionario}`

Exemplo de requisição para gerar a folha do funcionário com `id = 16`:

**Resposta (exemplo):**

```json
{
  "idFolhaPagamento": 19,
  "funcionario": {
    "nome": "Pedro",
    "cpf": "791.800.968-00",
    "idFuncionario": 16,
    "cargo": "Dev",
    "salarioBase": 1500.0,
    "cargaHorariaDiaria": 7.5,
    "horasTrabalhadas": 150,
    "diasTrabalhadosSemana": 5,
    "diasTrabalhadosMes": 21,
    "dataAdmissao": "2023-03-20",
    "receberValeTransporte": true,
    "receberValeAlimentacao": false,
    "custoValeTransporte": 180.0,
    "custoDiarioAlimentacao": 0.0,
    "periculo": "NAO",
    "insalu": "SIM",
    "insalubridade": "ALTO",
    "dependentes": 0
  },
  "salarioBase": 1500.0,
  "salarioBruto": 2100.0,
  "salarioLiquido": 1804.53,
  "insalubridade": 600.0,
  "periculosidade": 0.0,
  "fgts": 168.0,
  "valorHora": 12.44,
  "inss": 169.47,
  "irrf": 0.0,
  "geracaoData": "2025-11-08",
  "vt": 126.0,
  "va": 0.0
}
```

**Resposta esperada:** `200 OK`

---

#### 2. Listar todas as folhas geradas

**GET** `http://localhost:8080/folha-pagamento/listar`

Retorna todas as folhas de pagamento geradas.

**Resposta esperada:** `200 OK` (array JSON com folhas)

---

## Endpoints de administrador (gestor do RH)

> Observação: o time optou por permitir login apenas para administradores (gestores do RH). A visualização das folhas pelos funcionários não foi implementada via login individual.

#### 1. Registrar administrador

**POST** `http://localhost:8080/admin/registrar`

**Exemplo de corpo (JSON):**

```json
{
  "nome": "Paulo",
  "cpf": "791.800.000-00",
  "email": "Paulo@gmail.com",
  "senha": "Paulo123"
}
```

**Resposta esperada:** `200 OK` — "Administrador criado com sucesso!"

---

#### 2. Logar no sistema

**POST** `http://localhost:8080/admin/logar`

**Exemplo de corpo (JSON):**

```json
{
  "email": "Paulo@gmail.com",
  "senha": "Paulo123"
}
```

**Resposta esperada:** `200 OK` — "Login realizado com sucesso!!"

**Exemplo de falha (senha incorreta):**

```json
{
  "email": "Paulo@gmail.com",
  "senha": "Paulo12"
}
```

**Resposta esperada:** `401 Unauthorized` — "Email ou senha incorretos!"

---

## Endpoints de serialização e desserialização

### Folha de pagamento

* **Serializar**: **GET** `http://localhost:8080/folhaSerial/salvar/{idFolha}`

  * Retorno: `200 OK` — "Folha de pagamento salva em JSON com sucesso!"

* **Desserializar (carregar)**: **GET** `http://localhost:8080/folhaSerial/carregar`

**Exemplo de objeto (após desserialização):**

```json
{
  "idFolhaPagamento": 19,
  "dataGeracao": "2025-11-08",
  "salarioLiquido": 1804.53
}
```

**Resposta esperada:** `200 OK`

---

### Funcionários

* **Serializar**: **GET** `http://localhost:8080/SerialeDesserial/salvar/{idFuncionario}`

  * Retorno: `200 OK` — "Funcionário salvo no arquivo JSON com sucesso!"

* **Desserializar (carregar)**: **GET** `http://localhost:8080/SerialeDesserial/carregar`

**Exemplo de objeto (após desserialização):**

```json
{
  "idFuncionario": 16,
  "nome": "Pedro",
  "cargo": "Dev",
  "dataAdmissao": "2023-03-20"
}
```

**Resposta esperada:** `200 OK`

---

## Builds / Execução local

1. **Pré-requisitos**

   * Java (versão compatível com o projeto) instalado na máquina.
   * Banco de dados (opcional: PostgreSQL, MySQL, H2, etc.).
   * Apps para testar endpoints: Postman, Insomnia ou extensão REST no VSCode.

2. **Configurar `application.properties`**

   * Local do arquivo: `\calculo-folha-pagamento-teorica-grupo4\BackEnd\backend\src\main\resources\application.properties`
   * Se usar PostgreSQL, atualize `spring.datasource.url`, `spring.datasource.username` e `spring.datasource.password` com os valores do seu servidor.
   * Se preferir usar outro banco (MySQL, H2), ajuste as propriedades correspondentes.

3. **Executar a aplicação**

   * Abra a classe principal e rode `BackendApplication.java` (local: `\calculo-folha-pagamento-teorica-grupo4\BackEnd\backend\src\main\java\com\trabalho\backend\BackendApplication.java`).

4. **Testar endpoints**

   * Use Postman ou similar para fazer requisições aos endpoints descritos acima.

---

## Observações e decisões de arquitetura

* A geração da folha foi pensada para ser acionada individualmente (cada linha no front terá um botão "Gerar folha" para o funcionário correspondente).
* O login foi restringido a administradores/gestores de RH por decisão do time.
* Os exemplos de JSON e respostas são ilustrativos — ajuste conforme o modelo de domínio implementado (nomes de campos e formatos de data).

---



