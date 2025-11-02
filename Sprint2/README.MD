# 🧾 Relatório – Sprint 2

## 🧩 Explicação sobre o uso de Interface, Polimorfismo, Herança e Classes Abstratas

Durante esta sprint, utilizamos a classe **Usuário** como uma **classe abstrata**, e as classes **Administrador** e **Funcionário** herdam de **Usuário**, aproveitando atributos como `nome` e `cpf`.

Também criamos duas **interfaces**:  
- `Descontos`  
- `AdicionaisEOpcionais`  

A partir delas, implementamos classes como `CalculoIRRF`, que **sobrescrevem** os métodos definidos nas interfaces utilizando a anotação `@Override`.

---

## ⚙️ Dificuldades

Tivemos dificuldades em trabalhar com o tipo **BigDecimal**.  
Por enquanto, estamos utilizando o tipo **double**, mas planejamos migrar para BigDecimal nas próximas sprints para melhorar a precisão nos cálculos.

---

## ✅ Observações – Conclusões da Sprint 2

Implementamos todos os itens solicitados no documento do trabalho.  
Segue o checklist:

- [x] Implementação do código com base na UML  
- [x] Aplicação de Polimorfismo e Herança  
- [x] Aplicação de Interfaces e Classes Abstratas  
- [x] Implementação de Testes Unitários  
- [x] Apresentação em vídeo (até 5 minutos)  
- [x] Implementação de um `Main` com interface MS-DOS retrô  

Na **Sprint 3**, implementaremos a lógica para **gerenciar vários Funcionários**, usando essa classe como fator principal para os **Endpoints**.

---

## 🚀 Planejamento – Sprint 3

- Implementar o Banco de Dados  
- Utilizar **Coleções** e **Streams**  
- Implementar os **Endpoints**  
- Usar **PostgreSQL** como banco de dados  
- Implementar **Eventos**  
- Criar **novos testes unitários** para as novas funcionalidades (caso haja)

---
