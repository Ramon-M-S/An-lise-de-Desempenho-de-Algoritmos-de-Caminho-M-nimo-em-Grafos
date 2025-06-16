# Verificador de Conectividade de Grafos - Explicação do Algoritmo

---

## 🎯 Objetivo da Classe

A classe `VerificadorConectividade` tem duas funções principais:

1. **Verificar se o grafo é conectado (isConexo).**
2. **Encontrar todos os componentes conexos do grafo (encontrarComponentesConexos).**

Esses métodos são aplicados apenas a **grafos não direcionados**.

---

## ✅ O que é um Grafo Conectado?

Um grafo é considerado **conectado** se **existe pelo menos um caminho entre qualquer par de vértices**.

Se o grafo tiver regiões isoladas (ou seja, grupos de vértices não ligados entre si), ele é considerado **não conectado** e possui **múltiplos componentes conexos**.

---

## 🧠 Funcionamento do Método `isConexo()`

### Objetivo:

Determinar se **o grafo inteiro é conectado**.

### Passos do método:

1. **Caso especial:**  
   Se o grafo for vazio ou tiver apenas um vértice, é considerado conectado por definição.

2. **Escolha de vértice inicial:**  
   Seleciona um vértice arbitrário do grafo para iniciar a busca.

3. **Execução de BFS:**  
   Utiliza o algoritmo de **Busca em Largura (BFS)** para visitar todos os vértices alcançáveis a partir do vértice inicial.

4. **Verificação de conectividade:**  
   Compara o número de vértices visitados com o número total de vértices do grafo:
- Se for igual: **grafo conectado (true)**.
- Se for menor: **grafo desconectado (false)**.

---

## 🧠 Funcionamento do Método `encontrarComponentesConexos()`

### Objetivo:

Identificar todos os **componentes conexos** do grafo.

### Passos do método:

1. **Inicialização:**
- Cria uma lista (`componentes`) para armazenar cada componente.
- Cria um conjunto (`visitadosGlobalmente`) para controlar os vértices já explorados.

2. **Percorrer todos os vértices:**
- Para cada vértice **não visitado ainda**:
    - Inicia uma nova **BFS** a partir dele.
    - Todos os vértices visitados nessa BFS formam um **novo componente conexo**.
    - Adiciona esse componente à lista de resultados.
    - Marca todos os vértices desse componente como visitados.

3. **Resultado:**
- Ao final, a lista contém um `HashSet` para cada componente.

---

## ✅ Exemplo de Saída (Componentes Conexos)

Suponha o seguinte grafo:

- Vértices: `{A, B, C, D, E, F}`
- Arestas: `{(A-B), (B-C), (D-E)}`

**Resultado da execução:**

- **isConexo()** → `false`
- **encontrarComponentesConexos()** → Lista com:

| Componente | Vértices         |
| ---------- | ---------------- |
| 1          | `{A, B, C}`      |
| 2          | `{D, E}`         |
| 3          | `{F}` (isolado)  |

---

## ✅ Estruturas de Dados Utilizadas:

- **HashSet:**  
  Controle de vértices visitados.

- **ListaEncadeada:**  
  Armazenamento da lista de componentes.

- **BFS:**  
  Utilizado para explorar os vértices de cada componente.

---

## ✅ Conclusão

A classe `VerificadorConectividade` fornece uma solução eficiente para:

- **Verificar se o grafo é totalmente conectado**.
- **Listar todos os seus componentes conexos**, mesmo em grafos com múltiplas regiões isoladas.

Essa abordagem é fundamental em aplicações de análise de redes, conectividade de rotas e problemas de clusterização.

---
