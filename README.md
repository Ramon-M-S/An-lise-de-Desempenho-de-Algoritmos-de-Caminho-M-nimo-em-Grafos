# Projeto de Algoritmos de Grafos - Estrutura de Dados II (IFMA - Monte Castelo)

---

## 🎯 Sobre o Projeto

Este projeto foi desenvolvido como atividade da disciplina **Estrutura de Dados II**,  
do curso de **Sistemas de Informação - IFMA Campus Monte Castelo**, utilizando a linguagem **Java**.

O foco principal do projeto é a **implementação manual de estruturas de dados básicas** e a aplicação delas em **algoritmos clássicos de grafos**, sem o uso de bibliotecas prontas da linguagem Java (como `ArrayList`, `HashMap`, etc).

---

## ✅ Estruturas de Dados Criadas

Todas as estruturas foram implementadas manualmente dentro do pacote `util`.  
Essas estruturas são utilizadas por todos os algoritmos do projeto.

| Estrutura           | Localização          | Descrição                                          |
|---------------------|----------------------|---------------------------------------------------|
| **Lista Encadeada** | `util.lista`         | Lista simples para armazenamento de elementos de forma encadeada. |
| **Pilha**           | `util.pilha`         | Estrutura de pilha com operações de push/pop.     |
| **HashSet**         | `util.hashSet`       | Conjunto não ordenado para controle de elementos únicos. |
| **TabelaHash**      | `util.tabelaHash`    | Estrutura tipo mapa (chave → valor) para armazenamento eficiente. |
| **Union-Find (DSU)** | `util.union_find`   | Estrutura para gerenciamento de conjuntos disjuntos, usada em algoritmos de MST. |

---

## ✅ Estrutura de Leitura de Arquivos

| Classe                 | Localização          | Função                                       |
|------------------------|----------------------|---------------------------------------------|
| **LeitorDeGrafo**      | `util.leitoDeArquivo` | Faz a leitura de grafos a partir de arquivos `.txt` no formato especificado. |

---

## ✅ Algoritmos de Grafos Implementados

Os seguintes algoritmos clássicos de grafos foram implementados utilizando **exclusivamente as estruturas desenvolvidas no projeto**:

| Algoritmo                         | Pacote                          | Finalidade                                       |
|-----------------------------------|---------------------------------|-------------------------------------------------|
| **Busca em Largura (BFS)**        | `algoritmos.busca.bfs`          | Percorrer grafos por camadas. |
| **Busca em Profundidade (DFS)**   | `algoritmos.busca.dfs`          | Percorrer grafos de forma profunda (recursiva e iterativa). |
| **Verificação de Bipartição**     | `algoritmos.biparticao`         | Verificar se o grafo é bipartido (2-colorível). |
| **Verificação de Conectividade**  | `algoritmos.conectividade`      | Verificar se o grafo é conexo e identificar componentes. |
| **Coloração de Grafos (Greedy)**  | `algoritmos.coloracao_de_grafos` | Atribuir cores diferentes a vértices adjacentes. |
| **Ordenação Topológica - Kahn**   | `algoritmos.ordenacao_topologica.kahn` | Ordenar vértices de um DAG baseado em grau de entrada. |
| **Ordenação Topológica - DFS**    | `algoritmos.ordenacao_topologica.ordenacao_topologicaDFS` | Ordenar vértices de um DAG utilizando DFS. |
| **Tarjan - SCC**                  | `algoritmos.tarjan_componentes_fortemente_conexos` | Encontrar componentes fortemente conexos em grafos direcionados. |
| **Boruvka - MST**                 | `algoritmos.arvore_geradora_minima.boruvka` | Encontrar árvore geradora mínima. |
| **Kruskal - MST**                 | `algoritmos.arvore_geradora_minima.kruskal` | Encontrar árvore geradora mínima. |
| **Prim - MST**                    | `algoritmos.arvore_geradora_minima.prim` | Encontrar árvore geradora mínima. |
| **Hierholzer - Circuito Euleriano** | `algoritmos.hierholzer_circuito_euleriano` | Encontrar um circuito Euleriano. |
| **Backtracking - Caminho/Circuito Hamiltoniano** | `algoritmos.hamiltoniano_backtracking` | Encontrar caminhos e circuitos Hamiltonianos. |

---

## ✅ Conclusão

Este projeto foi uma oportunidade prática de:

- Implementar estruturas de dados fundamentais do zero.
- Aplicar os conceitos teóricos de grafos vistos na disciplina.
- Trabalhar com leitura de grafos via arquivos.
- Integrar algoritmos complexos utilizando apenas **estruturas personalizadas**.

**Disciplina:** Estrutura de Dados II  
**Curso:** Sistemas de Informação - IFMA Monte Castelo  
**Linguagem:** Java

---


