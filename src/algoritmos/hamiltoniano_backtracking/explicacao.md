# Algoritmo de Backtracking - Caminho e Circuito Hamiltoniano

---

## 🎯 Objetivo do Algoritmo

Resolver o **Problema Hamiltoniano**, que envolve duas variações:

- **Caminho Hamiltoniano:**  
  Sequência de vértices que percorre **todos os vértices do grafo uma única vez**, sem a necessidade de voltar ao início.

- **Circuito Hamiltoniano (ou ciclo):**  
  É um **caminho Hamiltoniano que termina no mesmo vértice onde começou**, formando um ciclo fechado.

---

## 🚩 Observação Importante

O problema Hamiltoniano é **NP-Completo**, ou seja:  
O tempo de execução cresce de forma exponencial com o número de vértices.

Por isso, nesta implementação, o algoritmo **não permite grafos com mais de 20 vértices**, evitando tempos de execução inviáveis.

---

## 🧠 Estratégia do Algoritmo

**Técnica:** Backtracking (Busca com Retração Recursiva)

**Ideia principal:**  
Explorar todos os caminhos possíveis de forma recursiva, **testando e voltando atrás** quando um caminho atual não leva a uma solução.

---

## 🪜 Passo a Passo - Encontrando um Caminho Hamiltoniano (`encontrarCaminho`)

1. **Validação de tamanho:**  
   Se o grafo tiver mais de 20 vértices, o algoritmo para.

2. **Tentativa de início a partir de cada vértice:**  
   Para cada vértice do grafo:
- Inicializa um caminho com ele.
- Marca o vértice como visitado.

3. **Processo Recursivo:**  
   Chama o método `backtrack`:
- Se o caminho atingir o tamanho total de vértices, encontrou-se um caminho Hamiltoniano.

4. **Se não houver solução:**  
   Após testar todos os vértices como início, se nenhum caminho for válido, o método retorna uma **lista vazia**.

---

## 🪜 Passo a Passo - Encontrando um Circuito Hamiltoniano (`encontrarCircuito`)

1. **Validação de tamanho:**  
   Mesma regra do caminho: máximo de 20 vértices.

2. **Tentativa de início a partir de cada vértice:**
- Inicia o processo como se fosse um caminho.

3. **Após encontrar um caminho:**  
   Verifica se o último vértice do caminho possui uma **aresta de volta ao vértice inicial**, formando o ciclo.

4. **Se houver ciclo:**  
   Adiciona o vértice inicial ao final da lista para fechar o circuito.

5. **Se não houver ciclo:**  
   Continua buscando.

6. **Se nenhum circuito for encontrado:**  
   Retorna uma **lista vazia**.

---

## ✅ Funcionamento Interno do Backtracking (`backtrack`)

### Condição de Parada:
Se o tamanho do caminho for igual ao número total de vértices, **o caminho é Hamiltoniano**.

### Passos Recursivos:

Para cada vizinho do vértice atual:

- **Se ainda não foi visitado:**
    - Adiciona ao caminho.
    - Marca como visitado.
    - Chama recursivamente para continuar expandindo.

- **Se não houver solução a partir do vizinho:**
    - Remove o vértice do caminho.
    - Desmarca como visitado (backtracking).

---

## ✅ Estruturas de Dados Usadas:

- **ListaEncadeada:**  
  Para armazenar a sequência de vértices do caminho.

- **HashSet:**  
  Para controle dos vértices já visitados durante a busca.

- **Iteradores personalizados:**  
  Para percorrer os vértices e vizinhos.

---

## ✅ Limitações:

| Fator               | Detalhe                          |
|-------------------- |----------------------------------|
| Escalabilidade      | Não recomendado para grafos grandes |
| Determinismo        | Retorna apenas o **primeiro caminho ou circuito encontrado** |
| Complexidade        | Exponencial em relação ao número de vértices |

---

## ✅ Conclusão

Este algoritmo de **Backtracking Hamiltoniano** é útil para:

- **Exercícios educacionais**
- **Testes com grafos pequenos**
- **Simulações de problemas NP-Completo**

Para aplicações reais com grafos grandes, seria necessário recorrer a **algoritmos aproximados** ou **heurísticas**.

---
