# Verificador de Bipartição - Explicação do Funcionamento do Algoritmo

---

## 🎯 Objetivo do Algoritmo

Verificar se um grafo é bipartido.

### O que é um Grafo Bipartido?

Um grafo é bipartido quando seus vértices podem ser divididos em **dois conjuntos disjuntos**, de forma que **nenhuma aresta conecte vértices do mesmo conjunto**.

**Exemplo:**

- Conjunto A: `{1, 3, 5}`
- Conjunto B: `{2, 4, 6}`

As arestas só podem conectar de A para B (ou vice-versa).

---

## 🧠 Estratégia Utilizada

O algoritmo usa **Busca em Largura (BFS)** com um sistema de **coloração em duas cores** (exemplo: `1` e `-1`).

**Regra principal:**

> Dois vértices adjacentes **nunca podem ter a mesma cor**.

Se isso acontecer, o grafo **não é bipartido**.

---

## 🪜 Passo a Passo do Algoritmo

### 1) Inicialização

- Cria-se uma tabela (`cores`) para mapear cada vértice com uma cor (`1` ou `-1`).
- Define o grafo inicialmente como bipartido:
  ```java
  boolean isBipartido = true;
