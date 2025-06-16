package algoritmos.tarjan_componentes_fortemente_conexos;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import util.pilha.Pilha;
import util.tabelaHash.TabelaHash;

/**
 * Algoritmo de Tarjan para encontrar Componentes Fortemente Conexos (SCCs) em um grafo direcionado.
 *
 * @param <V> Tipo dos vértices do grafo.
 */
public class Tarjan<V> {

    private final Grafo<V> grafo;
    private final TabelaHash<V, Integer> ids;      // Marca o tempo de descoberta de cada vértice.
    private final TabelaHash<V, Integer> low;      // Menor ID alcançável por cada vértice.
    private final Pilha<V> pilha;                  // Pilha de vértices ativos na busca.
    private final HashSet<V> naPilha;              // Indica se um vértice está atualmente na pilha.
    private int idCounter;                         // Contador global de IDs.
    private final ListaEncadeada<HashSet<V>> componentes;  // Lista dos SCCs encontrados.

    public Tarjan(Grafo<V> grafo) {
        // O algoritmo de Tarjan só funciona em grafos direcionados.
        if (!grafo.isDirecionado()) {
            throw new IllegalArgumentException("O algoritmo de Tarjan só funciona em grafos direcionados.");
        }
        this.grafo = grafo;
        this.ids = new TabelaHash<>();
        this.low = new TabelaHash<>();
        this.pilha = new Pilha<>();
        this.naPilha = new HashSet<>();
        this.componentes = new ListaEncadeada<>();
        this.idCounter = 0;
    }

    /**
     * Executa o algoritmo de Tarjan.
     *
     * @return Lista de conjuntos. Cada conjunto representa um componente fortemente conexo.
     */
    public ListaEncadeada<HashSet<V>> encontrarSccs() {
        HashSet<V>.MeuIteradorDeHashSet iteradorVertices = grafo.getVertices().iterator();

        // Garante que todos os vértices sejam analisados (para grafos não totalmente conectados).
        while (iteradorVertices.hasNext()) {
            V vertice = iteradorVertices.next();
            if (ids.get(vertice) == null) {
                dfs(vertice);
            }
        }

        return componentes;
    }

    /**
     * DFS modificada para o algoritmo de Tarjan.
     *
     * @param atual Vértice atualmente sendo explorado.
     */
    private void dfs(V atual) {
        pilha.push(atual);
        naPilha.add(atual);

        // Define o ID de descoberta e o low-link inicial.
        ids.put(atual, idCounter);
        low.put(atual, idCounter);
        idCounter++;

        ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = grafo.getVizinhos(atual).iterador();
        while (iteradorVizinhos.temProximo()) {
            V vizinho = iteradorVizinhos.proximo().no();

            if (ids.get(vizinho) == null) {
                // Vizinho ainda não visitado → Continua a DFS.
                dfs(vizinho);
                // Atualiza o low-link após retornar da recursão.
                low.put(atual, Math.min(low.get(atual), low.get(vizinho)));
            } else if (naPilha.contains(vizinho)) {
                // Vizinho já visitado e ainda na pilha → Atualiza o low-link com o ID do vizinho.
                low.put(atual, Math.min(low.get(atual), ids.get(vizinho)));
            }
        }

        // Se o vértice atual for raiz de um SCC (low-link igual ao ID).
        if (ids.get(atual).equals(low.get(atual))) {
            HashSet<V> novoComponente = new HashSet<>();

            // Remove os vértices da pilha até chegar ao vértice atual.
            while (true) {
                V no = pilha.pop();
                naPilha.remove(no);
                novoComponente.add(no);
                if (no.equals(atual)) {
                    break;
                }
            }

            // Adiciona o novo componente encontrado à lista de SCCs.
            componentes.add(novoComponente);
        }
    }
}
