# Algoritmos de Busca em Grafos: BFS e DFS

---

## 📌 Introdução Geral aos Algoritmos de Busca em Grafos

Os algoritmos de busca em grafos são usados para percorrer os vértices e arestas de um grafo, visando explorar a estrutura de conectividade entre os nós.

Esses algoritmos são fundamentais para:

- Encontrar caminhos entre vértices.
- Verificar se o grafo é conexo.
- Detectar ciclos.
- Realizar ordenações topológicas.
- Encontrar componentes conexos.

---

## 🔎 Diferenças Entre BFS e DFS

| Característica          | BFS (Busca em Largura)                | DFS (Busca em Profundidade)          |
|------------------------ | ------------------------------------- | ----------------------------------- |
| Estrutura base          | **Fila (Queue)**                     | **Pilha (Stack)** ou **Recursão**   |
| Ordem de visita         | Por níveis (vizinhos mais próximos)  | Explora um caminho até o final antes de voltar |
| Uso típico              | Encontrar caminhos mínimos           | Detectar ciclos, fazer travessia completa |
| Complexidade de tempo   | O(V + E)                             | O(V + E)                            |

---

## ✅ Classe BFS (Busca em Largura)

**Nome da Classe:** `BFS`

**Estratégia:** Explora os vértices por camadas, expandindo primeiro os vizinhos mais próximos antes de avançar para os mais distantes.

### Métodos da Classe:

- **`executar(V inicio)`**
    - Retorna um `HashSet` com todos os vértices visitados.
    - Útil para uso em outros algoritmos que dependam apenas dos vértices alcançáveis.

- **`imprimirBusca(V inicio)`**
    - Exibe no console a ordem de visitação dos vértices.
    - Indicado para testes e depuração.

- **`getTravessia(V inicio)`**
    - Retorna uma `ListaEncadeada` com a exata sequência de visitação dos vértices.
    - Útil para análises que precisam da ordem de visita.

### Fluxo da BFS:

1. **Validação:** Verifica se o vértice inicial existe.
2. **Inicialização:** Adiciona o vértice de início na fila e no conjunto de visitados.
3. **Laço Principal:** Enquanto houver vértices na fila:
    - Remove o primeiro da fila.
    - Adiciona à fila os vizinhos ainda não visitados.

---

## ✅ Classe DFS (Busca em Profundidade)

**Nome da Classe:** `DFS`

**Estratégia:** Explora cada caminho o máximo possível antes de retroceder. Implementada em duas formas: **recursiva** e **iterativa**.

### Métodos da Classe:

- **`executarRecursivo(V inicio)`**
    - Faz a busca de forma recursiva.
    - Utiliza um `HashSet` de instância (`visitados`) para controlar os vértices já percorridos.

- **`executarIterativo(V inicio)`**
    - Implementa a busca usando uma `Pilha` personalizada.
    - Utiliza um `HashSet` local para os visitados.

### Fluxo da DFS Recursiva:

1. **Validação:** Verifica se o vértice inicial existe.
2. **Limpa os visitados.**
3. **Chama `dfsVisit`:**
    - Marca o vértice como visitado.
    - Para cada vizinho não visitado, chama recursivamente.

### Fluxo da DFS Iterativa:

1. **Validação:** Verifica se o vértice inicial existe.
2. **Empilha o vértice de início.**
3. **Laço Principal:** Enquanto a pilha não estiver vazia:
    - Remove o topo da pilha.
    - Marca como visitado.
    - Empilha os vizinhos ainda não visitados **em ordem inversa**, para manter a ordem de visita similar à recursiva.

---

## ✅ Comparativo Final

| Critério                        | BFS                           | DFS Recursiva                   | DFS Iterativa                |
|-------------------------------- | ----------------------------- | ------------------------------- | --------------------------- |
| Estrutura usada                 | Fila                          | Pilha de chamadas recursivas    | Pilha explícita             |
| Ordem de visitação              | Por camadas                   | Profundidade                    | Profundidade                |
| Quando usar                     | Busca por menor caminho       | Exploração total, detecção de ciclos | Alternativa à recursão |
| Controle de visitados           | HashSet local                 | HashSet de instância            | HashSet local               |
| Ideal para                      | Distâncias mínimas em grafos | Árvores, labirintos, ciclos     | Situações que não permitem recursão |

---

## ✅ Conclusão

Ambas as classes **BFS** e **DFS** são essenciais para resolver diversos problemas de grafos.  
A implementação permite flexibilidade:  
O programador pode escolher a versão e a forma de saída (conjunto, lista ou impressão) dependendo da necessidade do projeto.

---
