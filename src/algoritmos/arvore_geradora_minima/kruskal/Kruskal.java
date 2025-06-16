package algoritmos.arvore_geradora_minima.kruskal;

import algoritmos.arvore_geradora_minima.kruskal.utilKruskal.MergeSortArestas;
import util.union_find.UnionFind;
import graph_structure.Grafo;
import graph_structure.Grafo.Aresta;
import util.lista.ListaEncadeada;

/**
 * Implementação do Algoritmo de Kruskal para encontrar a Árvore Geradora Mínima (MST).
 * O algoritmo seleciona as arestas de menor peso, evitando ciclos, até conectar todos os vértices.
 *
 * @param <V> Tipo dos dados armazenados nos vértices.
 */
public class Kruskal<V> {

    private final Grafo<V> grafo;

    public Kruskal(Grafo<V> grafo) {
        // O algoritmo de Kruskal só é aplicável a grafos não direcionados.
        if (grafo.isDirecionado()) {
            throw new IllegalArgumentException("O algoritmo de Kruskal só funciona em grafos não direcionados.");
        }
        this.grafo = grafo;
    }

    public ListaEncadeada<Aresta<V>> encontrarMST() {
        // Passo 1: Obter todas as arestas do grafo e ordená-las por peso crescente.
        ListaEncadeada<Aresta<V>> arestas = grafo.getArestas();
        new MergeSortArestas<V>().sort(arestas);

        // Passo 2: Inicializar a estrutura Union-Find para controle dos componentes.
        UnionFind<V> uf = new UnionFind<>(grafo.getVertices());

        // Lista que armazenará as arestas da Árvore Geradora Mínima.
        ListaEncadeada<Aresta<V>> mst = new ListaEncadeada<>();

        // Passo 3: Iterar sobre as arestas ordenadas.
        for (int i = 0; i < arestas.size(); i++) {
            // Condição de parada: MST completa quando contém (V - 1) arestas.
            if (mst.size() == grafo.getNumeroDeVertices() - 1) {
                break;
            }

            Aresta<V> aresta = arestas.get(i);
            V u = aresta.origem();
            V v = aresta.destino();

            // Verifica se os vértices u e v estão em componentes diferentes.
            if (!uf.find(u).equals(uf.find(v))) {
                // Se estiverem, a aresta pode ser adicionada sem formar ciclo.
                mst.add(aresta);
                uf.union(u, v); // Une os componentes de u e v.
            }
        }

        // Retorna a Árvore Geradora Mínima construída.
        return mst;
    }
}
