# Algoritmo de Coloração de Grafos - Abordagem Gulosa (Greedy)

---

## 🎯 Objetivo do Algoritmo

Atribuir cores a cada vértice de um grafo de modo que **nenhum par de vértices adjacentes tenha a mesma cor**.

Cada cor é representada por um número inteiro.  
O objetivo é minimizar o número total de cores usadas, porém **este algoritmo não garante o número mínimo absoluto de cores (número cromático)**, pois adota uma abordagem gulosa.

---

## 🧠 Estratégia do Algoritmo

**Tipo de algoritmo:** Guloso (Greedy)

**Ideia principal:**  
Para cada vértice, atribuir a **menor cor inteira disponível** que não esteja sendo usada pelos seus vizinhos imediatos.

**Ordem de processamento:**  
Percorre os vértices em qualquer ordem (a ordem depende da iteração sobre o conjunto de vértices do grafo).

---

## 🪜 Passo a Passo do Funcionamento

### 1. Inicialização

- Cria uma estrutura `TabelaHash` para armazenar a cor de cada vértice.

---

### 2. Iteração sobre os vértices

Para cada vértice do grafo:

#### a) Identificar as cores dos vizinhos
- Percorre todos os vizinhos do vértice atual.
- Verifica quais vizinhos já estão coloridos.
- Armazena todas as cores usadas pelos vizinhos em um `HashSet<Integer>`.

---

#### b) Escolher a menor cor disponível
- Começa testando a cor `1`.
- Incrementa até encontrar a primeira cor que **não esteja sendo usada por nenhum vizinho**.

---

#### c) Atribuir a cor ao vértice
- Salva a cor escolhida na tabela de resultado (`resultadoCores`).

---

### 3. Finalização

- Após colorir todos os vértices, retorna a tabela contendo o mapeamento de **vértice → cor**.

---

## ✅ Exemplo Rápido

Se tivermos o seguinte grafo:

- Vértices: A, B, C
- Arestas: (A-B), (B-C)

**Resultado da coloração greedy possível:**

| Vértice | Cor |
| ------  | --- |
| A       | 1   |
| B       | 2   |
| C       | 1   |

---

## ✅ Características da Implementação

- **Estruturas utilizadas:**
    - `TabelaHash` → Mapeamento vértice-cor.
    - `HashSet` → Controle das cores dos vizinhos.
    - `ListaEncadeada` → Percorrer vizinhos de cada vértice.

- **Limitações:**
    - Não garante o número mínimo de cores (não é um algoritmo de coloração ótima).
    - Depende da ordem de iteração dos vértices.

- **Vantagens:**
    - Simples de implementar.
    - Execução rápida.
    - Boa solução aproximada para muitos casos práticos.

---

## ✅ Conclusão

O **ColoracaoGreedy** é um algoritmo eficiente para coloração rápida de grafos, adequado para cenários onde uma solução aproximada é aceitável.  
É especialmente útil quando o desempenho é mais importante que a otimização cromática.

---
