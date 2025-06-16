# ALGORITMOS DE ÁRVORE GERADORA MÍNIMA (MST) - EXPLICAÇÃO GERAL E DETALHADA POR CLASSE

---

## 🔍 VISÃO GERAL SOBRE ÁRVORES GERADORAS MÍNIMAS (MST)

Uma Árvore Geradora Mínima (MST - *Minimum Spanning Tree*) é um subconjunto de arestas de um grafo não direcionado e conectado que conecta todos os vértices com o menor custo total possível, **sem formar ciclos**.

Três dos algoritmos mais conhecidos para resolver o problema de MST são:

1. **Prim**
2. **Kruskal**
3. **Borůvka**

Embora todos tenham o mesmo objetivo (encontrar a MST), cada um adota estratégias diferentes para alcançar o resultado.

---

## 🌳 1) CLASSE PRIM

- **Nome:** `Prim`
- **Estratégia:** Crescimento progressivo a partir de um vértice.

### Funcionamento:
- Escolhe um vértice inicial arbitrário.
- Mantém um conjunto de vértices já incluídos na MST.
- Usa uma **fila de prioridade** para escolher, a cada passo, a aresta de menor peso que conecta um vértice dentro da MST a um vértice fora dela.
- O processo continua até que todos os vértices sejam incluídos na MST.

### Estruturas principais usadas:
- `HashSet`: Para controlar quais vértices já estão na MST.
- `FilaDePrioridade`: Para selecionar a aresta de menor peso.
- `ListaEncadeada`: Para armazenar o resultado final (arestas da MST).

### Vantagens:
- Eficiente em grafos densos.
- Ideal quando o grafo já está na forma de lista de adjacência.

---

## 🔗 2) CLASSE KRUSKAL

- **Nome:** `Kruskal`
- **Estratégia:** Seleção global de arestas, evitando ciclos.

### Funcionamento:
- Ordena todas as arestas do grafo por peso crescente.
- Inicializa cada vértice como um componente separado (*Union-Find*).
- Itera pelas arestas ordenadas:
  - Inclui a aresta na MST somente se ela conectar vértices de componentes diferentes (evitando ciclos).
- O processo termina quando a MST tiver exatamente (V - 1) arestas.

### Estruturas principais usadas:
- `UnionFind`: Para controle de componentes e detecção de ciclos.
- `MergeSortArestas`: Para ordenar as arestas pelo peso.
- `ListaEncadeada`: Para armazenar as arestas da MST.

### Vantagens:
- Muito eficiente em grafos esparsos (com poucas arestas).
- Baseado em ordenação e união de conjuntos.

---

## 🏗️ 3) CLASSE BORUVKA

- **Nome:** `Boruvka`
- **Estratégia:** União simultânea de componentes por meio das menores arestas.

### Funcionamento:
- Inicialmente, cada vértice é tratado como um componente independente.
- Em cada rodada:
  - Para cada componente, encontra a menor aresta de saída (que conecta a outro componente).
  - Adiciona todas essas arestas de uma vez à MST, unindo os componentes conectados.
- Repete o processo até restar apenas um único componente ou até a MST atingir (V - 1) arestas.

### Estruturas principais usadas:
- `UnionFind`: Para rastrear os componentes conectados.
- `TabelaHash`: Para guardar a menor aresta de cada componente em cada rodada.
- `ListaEncadeada`: Para construir a MST.

### Vantagens:
- Fácil de paralelizar (pois busca as menores arestas de cada componente em paralelo).
- Naturalmente trabalha em "fases" ou "rodadas".

---

## 📊 COMPARAÇÃO ENTRE OS ALGORITMOS

| Algoritmo | Estratégia Base                   | Melhor em...        |
| ---------- | --------------------------------- | ------------------- |
| **Prim**   | Crescimento local                 | Grafos densos       |
| **Kruskal**| Ordenação global de arestas       | Grafos esparsos     |
| **Boruvka**| União por componentes simultâneos | Processos paralelos |

---

## ✅ Conclusão

Todos os três algoritmos são eficientes para resolver o problema de MST, mas a escolha entre eles depende das características do grafo (**denso, esparso, número de vértices/arestas**) e das restrições do projeto.

---
