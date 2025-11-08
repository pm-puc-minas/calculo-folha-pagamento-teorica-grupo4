# Relatório Sprint 3

## Descrição Geral
Nesta sprint, avançamos na implementação da aplicação, ampliando funcionalidades, organizando melhor a arquitetura do código e garantindo boas práticas como tratamento de exceções, DTOs, persistência e serialização.

---

## Funcionalidades e Implementações Realizadas

### ✅ Coleções e Processamento de Dados
- Utilização de **List** como coleção principal.
- Uso de **Streams** para filtragem, mapeamento e cálculos.

### ✅ Endpoints
- Criação dos **endpoints** necessários para o funcionamento do sistema.
- Testes realizados através do **Postman**.

### ✅ Tratamento de Exceções
- Implementação de tratamento customizado de erros.
- Criação de classes que **estendem `RuntimeException`**, evitando uso obrigatório de try/catch nos pontos de chamada.

### ✅ Cálculos Implementados
- Cálculo da **média de salário líquido**.
- Cálculo de **VT** (Vale Transporte) e **VA** (Vale Alimentação).
- Outros métodos relacionados à folha de pagamento.

### ✅ Persistência de Dados
- O grupo decidiu utilizar o **PostgreSQL**.
- Implementação de repositórios utilizando **Spring Data JPA**.

### ✅ Arredondamento
- Conforme definido na Sprint 2, foi aplicado arredondamento utilizando **`Math.round()`** para padronizar valores.

### ✅ DTOs (Data Transfer Objects)
- Implementação de **DTOs** para proteger campos sensíveis e melhorar a transferência de dados entre camadas.

### ✅ Serialização e Desserialização
- Serialização e desserialização para:
  - **Funcionário**
  - **Folha de Pagamento**

### ✅ Logs de Eventos
- Implementação de logs com:
  - **Listener**
  - **Event**
- Objetivo: acompanhar ações importantes e facilitar monitoramento.

### ✅ Correções de Nomenclatura
- Padronização do nome das interfaces.
- Exemplo: `CalculoDescontos` passou a ser `ICalculoDescontos`.


