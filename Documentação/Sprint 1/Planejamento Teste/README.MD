# 📌 Casos de Testes – Folha de Pagamento

## 1. Introdução
Planejamento dos casos de testes, especificando o que será feito.  
Foram adicionados testes iniciais contendo apenas a **assinatura dos métodos** (não é um método oficial).  
Também foi adicionada uma dependência no `pom.xml` para suporte aos testes unitários.

---

## 2. Planejamento

### 🔹 RF1 – Calcular Salário Hora
- Verificar se o cálculo do salário por hora está correto quando a jornada é **200h/mês**.  
- Testar com jornadas diferentes (**220h/mês, 180h/mês**) e validar o resultado.  
- Garantir que, se o salário bruto for **0**, o resultado também seja **0**.  

### 🔹 RF2 – Calcular Periculosidade
- Verificar cálculo de **30% do salário bruto**.  
- Testar valores **negativos**.  
- Se não tiver periculosidade, deve retornar 0.

### 🔹 RF3 – Calcular Insalubridade
- Testar os **três níveis de risco** do salário mínimo.  
- Verificar cálculo correto quando **não há insalubridade** → retorno deve ser **0**.  
- Validar com **salário mínimo atualizado**. 
- Se não tiver insalubridade, deve retornar 0.

### 🔹 RF4 – Calcular Vale Transporte
- Quando o valor recebido é **menor que 6% do salário bruto** → descontar **o valor recebido**.  
- Quando o valor recebido é **igual ou maior que 6%** → descontar exatamente **6% do bruto**.  
- Se não tiver vale transporte, deve retornar 0.

### 🔹 RF5 – Calcular Vale Alimentação
- Validar cálculo de **dias úteis × valor diário**.  
- Garantir que o benefício seja **gratuito** (sem desconto).
- Se não tiver vale alimentação, deve retornar 0.

### 🔹 RF6 – Calcular Desconto de INSS
- Verificar cálculo progressivo para cada **faixa salarial da tabela**.  
- Testar salário no **teto** (deve parar no limite).  
- Testar salário **acima do teto** (deve considerar só até o teto).

### 🔹 RF7 – Calcular FGTS
- Verificar cálculo de **8% sobre o salário bruto**.  
- Testar salário bruto **0** → deve retornar **0**.  

### 🔹 RF8 – Calcular Desconto de IRRF
- Testar funcionário **sem dependentes** e com **dependentes**.  
- Testar salário **abaixo do limite de isenção** → retorno deve ser **0**.
- Se o salário for 0.0, deve retornar 0.

### 🔹 RF9 – Calcular Salário Líquido
- Validar se o salário líquido é calculado como:  
  **Salário Bruto – (Descontos) + (Benefícios/Adicionais)**.  
- Testar cenários:  
  - Funcionário só com salário bruto.  
  - Funcionário com adicionais e descontos.  
  - Funcionário com todos os benefícios.  

