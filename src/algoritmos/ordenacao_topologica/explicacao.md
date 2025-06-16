# Ordenação Topológica em Grafos Direcionados - Algoritmo de Kahn e Algoritmo por DFS

---

## 🎯 O que é Ordenação Topológica?

A **ordenação topológica** é uma forma de linearizar os vértices de um grafo direcionado acíclico (DAG), de modo que **para toda aresta (u → v), o vértice u apareça antes de v na ordem resultante**.

Exemplos de aplicações:

- Planejamento de tarefas com dependências.
- Compilação de código fonte com dependências de módulos.
- Ordenação de eventos com restrições temporais.

---

## ✅ Condição Obrigatória: Grafo sem Ciclo (DAG)

A ordenação topológica **só é possível em grafos direcionados acíclicos (DAGs)**.  
Se houver ciclo, **não existe ordenação válida**.

---

## 🔎 Algoritmo 1: Kahn (Baseado em Grau de Entrada)

### 🧠 Ideia Principal:

Processar os vértices com **grau de entrada zero** e, a cada remoção, atualizar os graus dos vizinhos.

---

### Passo a Passo:

1. **Calcular o grau de entrada (in-degree) de todos os vértices.**

2. **Colocar na fila todos os vértices com grau de entrada zero.**

3. **Enquanto a fila não estiver vazia:**
    - Remover o vértice da frente.
    - Adicionar esse vértice à ordem topológica.
    - Para cada vizinho, decrementar o grau de entrada.
    - Se o grau de entrada de algum vizinho se tornar zero, adicioná-lo à fila.

4. **Detectar ciclo:**
    - Se ao final restarem vértices não processados, o grafo contém um ciclo.

---

### ✅ Características do Algoritmo de Kahn:

| Critério                      | Detalhe                  |
|-------------------------------|--------------------------|
| Estrutura principal           | Fila                    |
| Detecção de ciclo             | Sim (comparando contagem de vértices processados) |
| Tempo de execução             | O(V + E)                |
| Vantagem                      | Fácil de entender e implementar |

---

## 🔎 Algoritmo 2: Ordenação Topológica via DFS (Profundidade)

### 🧠 Ideia Principal:

Realizar uma **Busca em Profundidade (DFS)** em todos os vértices e, ao finalizar a visita de cada vértice, **adicioná-lo ao início da lista de ordenação**.

---

### Passo a Passo:

1. **Inicializar:**
    - Conjunto de visitados.
    - Conjunto para controle da pilha de recursão (detecta ciclos).
    - Lista de resultado.

2. **Para cada vértice não visitado:**
    - Executar a DFS recursiva.

3. **Durante a DFS:**
    - Marcar o vértice atual como visitado e adicioná-lo na pilha de recursão.
    - Para cada vizinho:
        - Se o vizinho já estiver na pilha de recursão → **ciclo detectado**.
        - Se ainda não visitado, continuar a DFS.

4. **Ao finalizar a visita de um vértice:**
    - Remover da pilha de recursão.
    - Inserir o vértice no início da lista de ordenação.

---

### ✅ Características do Algoritmo DFS:

| Critério                      | Detalhe                  |
|-------------------------------|--------------------------|
| Estrutura principal           | Pilha de chamadas recursivas |
| Detecção de ciclo             | Sim (via marcação da pilha de recursão) |
| Tempo de execução             | O(V + E)                |
| Vantagem                      | Baixo uso de memória extra |

---

## ✅ Comparação Direta: Kahn vs DFS

| Critério                     | Kahn                     | DFS                           |
|----------------------------- | ------------------------ | ----------------------------- |
| Estrutura Base               | Fila                    | Recursão + Lista              |
| Ordem produzida              | Não necessariamente única | Não necessariamente única    |
| Detecção de ciclo            | Por contagem de vértices processados | Por marcação durante a DFS |
| Facilidade de implementação  | Fácil                   | Moderada                     |
| Controle explícito de graus  | Sim                     | Não                          |

---

## ✅ Conclusão

Ambos os algoritmos são eficientes e têm complexidade **O(V + E)**.

**Quando usar Kahn:**
- Quando você já tem os graus de entrada ou quer um algoritmo iterativo simples.

**Quando usar DFS:**
- Quando for necessário combinar com outros algoritmos baseados em DFS, como detecção de ciclos.

Ambos são válidos, depende do contexto e da preferência de implementação.

---
