# Algoritmo de Hierholzer - Circuito Euleriano

---

## 🎯 Objetivo do Algoritmo

Encontrar um **Circuito Euleriano** em um grafo não direcionado.

### O que é um Circuito Euleriano?

Um **Circuito Euleriano** é um caminho fechado que passa por todas as arestas do grafo **exatamente uma vez** e termina no vértice de início.

---

## ✅ Condições para Existência de um Circuito Euleriano

Antes de tentar encontrar o circuito, o algoritmo verifica duas condições obrigatórias:

1. **Todos os vértices devem ter grau par.**
2. **O grafo deve ser conexo (considerando apenas vértices com grau maior que zero).**

Se qualquer uma dessas condições falhar, **não existe circuito Euleriano**.

---

## 🧠 Funcionamento do Algoritmo de Hierholzer

### Passo 1: Verificação das Condições

- **Grau par:**  
  Percorre todos os vértices e verifica se o número de vizinhos (grau) de cada um é par.

- **Conectividade:**  
  Utiliza o `VerificadorConectividade` para garantir que o grafo seja conexo, ignorando vértices isolados.

---

### Passo 2: Preparação das Estruturas

- Cria uma cópia da lista de adjacência original, permitindo **remoção de arestas** sem alterar o grafo original.

- Escolhe um **vértice inicial** com grau maior que zero.

- Inicializa uma **pilha (`caminhoAtual`)** e uma **lista (`circuito`)** que armazenará o resultado final.

---

### Passo 3: Construção do Circuito (Hierholzer)

Enquanto a pilha não estiver vazia:

1. Verifica o vértice no topo da pilha:
    - **Se ainda tiver arestas não visitadas:**
        - Escolhe um vizinho qualquer.
        - Remove a aresta entre o vértice atual e o vizinho **nas duas direções (ida e volta)**.
        - Empilha o vizinho.

    - **Se não tiver mais arestas:**
        - Remove o vértice do topo da pilha.
        - Adiciona-o ao **início da lista do circuito final**.

Esse processo garante que o circuito seja fechado e percorra todas as arestas sem repetição.

---

### Passo 4: Retorno do Resultado

Ao final:

- Se todas as arestas foram percorridas corretamente, a lista `circuito` conterá a sequência completa do **Circuito Euleriano**.

- Se o grafo não atender as condições iniciais, o método retorna uma **lista vazia**.

---

## ✅ Exemplo Rápido de Funcionamento

Suponha o seguinte grafo:

- Vértices: `{A, B, C, D}`
- Arestas: `{(A-B), (B-C), (C-D), (D-A), (B-D), (C-A)}`

Esse grafo tem **todos os vértices com grau par** e é **conexo**.

Resultado da execução:  
O algoritmo encontrará uma sequência como:

`A → B → D → C → A → D → B → C → A`

(O resultado pode variar dependendo da ordem dos vizinhos na lista de adjacência.)

---

## ✅ Estruturas de Dados Usadas

| Estrutura              | Função                                           |
|----------------------- | ----------------------------------------------- |
| `TabelaHash`           | Guardar a cópia das listas de vizinhos         |
| `Pilha`                | Controle do caminho atual durante a execução   |
| `ListaEncadeada`       | Armazenar o circuito Euleriano final           |
| `HashSet`              | Verificar grau e conectividade                 |

---

## ✅ Conclusão

O **Algoritmo de Hierholzer** é uma solução eficiente para encontrar circuitos Eulerianos, desde que o grafo satisfaça as condições necessárias.

Sua implementação com **remoção dinâmica de arestas** garante que cada aresta seja usada **exatamente uma vez**, seguindo o princípio central de um circuito Euleriano.

---
