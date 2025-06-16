# Algoritmo de Tarjan - Componentes Fortemente Conexos (SCCs)

---

## 🎯 Objetivo do Algoritmo

Encontrar todos os **Componentes Fortemente Conexos (SCCs)** em um grafo direcionado.

### O que é um Componente Fortemente Conexo?

Um **SCC (Strongly Connected Component)** é um subconjunto de vértices onde **existe um caminho de qualquer vértice para qualquer outro dentro do mesmo conjunto**, respeitando a direção das arestas.

---

## ✅ Características Gerais do Algoritmo de Tarjan

- **Tipo de grafo:** Somente grafos direcionados.
- **Complexidade de tempo:** O(V + E) → Linear em relação ao número de vértices e arestas.
- **Estratégia:**  
  Baseado em **DFS modificada** com uso de **low-link values** e **controle de pilha**.

---

## 🧠 Estruturas Utilizadas na Implementação

| Estrutura               | Função                                      |
|-------------------------|-------------------------------------------|
| `TabelaHash<V, Integer> ids` | Marca o tempo de descoberta de cada vértice (ordem de visita) |
| `TabelaHash<V, Integer> low` | Guarda o menor ID alcançável por cada vértice |
| `Pilha<V> pilha`             | Mantém os vértices ativos da DFS atual |
| `HashSet<V> naPilha`         | Permite saber rapidamente se um vértice está na pilha |
| `ListaEncadeada<HashSet<V>> componentes` | Armazena os SCCs finais |

---

## 🪜 Passo a Passo do Algoritmo de Tarjan

### 1. Inicialização:
- Define contadores e estruturas de controle (ids, low, pilha, etc).

---

### 2. DFS Modificada:

Para cada vértice ainda não visitado:

- Marca o **ID de descoberta** e o **low-link** igual ao ID atual.
- Adiciona o vértice na pilha.
- Para cada vizinho:
    - **Se o vizinho não foi visitado:**
        - Faz recursão para o vizinho.
        - Após a recursão, atualiza o `low` do vértice atual.
    - **Se o vizinho já estiver na pilha:**
        - Atualiza o `low` do vértice atual com o ID do vizinho (detecção de back-edge).

---

### 3. Identificação de um SCC:

Após visitar todos os vizinhos de um vértice:

- **Se o ID e o low-link forem iguais:**
    - O vértice é a raiz de um SCC.
    - Remove todos os vértices da pilha até chegar nele.
    - Agrupa os vértices retirados como um **novo SCC**.

---

### 4. Resultado Final:

Ao final da execução:

- Cada SCC será um **HashSet de vértices** armazenado dentro da lista `componentes`.

---

## ✅ Exemplo de Saída:

Suponha o grafo:

- Vértices: `{A, B, C, D, E, F}`
- Arestas: `{(A-B), (B-C), (C-A), (B-D), (D-E), (E-F), (F-D)}`

**SCCs encontrados:**

| SCC Nº | Vértices        |
|------ |---------------- |
| 1     | `{A, B, C}`      |
| 2     | `{D, E, F}`      |

---

## ✅ Vantagens da Implementação de Tarjan:

- Detecta todos os SCCs em **tempo linear O(V + E)**.
- Não precisa fazer transposição de grafo (diferente de Kosaraju).
- Baixo uso de memória extra.

---

## ✅ Conclusão:

O **Algoritmo de Tarjan** é uma solução eficiente e rápida para encontrar **componentes fortemente conexos** em **grafos direcionados**.

Sua estrutura baseada em **DFS com low-link e pilha** o torna uma das soluções mais clássicas e usadas em problemas de análise de grafos.

---
