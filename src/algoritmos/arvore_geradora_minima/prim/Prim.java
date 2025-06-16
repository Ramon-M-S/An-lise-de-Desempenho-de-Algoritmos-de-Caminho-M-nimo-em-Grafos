package algoritmos.arvore_geradora_minima.prim;

import algoritmos.arvore_geradora_minima.prim.utilPrim.Comparador;
import algoritmos.arvore_geradora_minima.prim.utilPrim.FilaDePrioridade;
import graph_structure.Grafo;
import graph_structure.Grafo.Aresta;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import java.util.Comparator;

/**
 * Implementação do Algoritmo de Prim para encontrar a Árvore Geradora Mínima (MST).
 * O algoritmo constrói a MST expandindo a partir de um vértice inicial, escolhendo sempre a aresta de menor peso que conecta um novo vértice.
 *
 * @param <V> Tipo dos dados armazenados nos vértices.
 */
public class Prim<V> {

    private final Grafo<V> grafo;

    public Prim(Grafo<V> grafo) {
        // O algoritmo de Prim só pode ser aplicado a grafos não direcionados.
        if (grafo.isDirecionado()) {
            throw new IllegalArgumentException("O algoritmo de Prim só funciona em grafos não direcionados.");
        }
        this.grafo = grafo;
    }

    public ListaEncadeada<Aresta<V>> encontrarMST() {
        // Caso o grafo não tenha vértices, retorna uma MST vazia.
        if (grafo.getVertices().isEmpty()) {
            return new ListaEncadeada<>();
        }

        // Seleciona um vértice inicial arbitrário.
        V inicio = grafo.getVertices().iterator().next();

        // Lista que armazenará as arestas da MST.
        ListaEncadeada<Aresta<V>> mst = new ListaEncadeada<>();

        // Conjunto para controlar os vértices já incluídos na MST.
        HashSet<V> naMST = new HashSet<>();

        // Fila de prioridade para escolher a próxima aresta de menor peso.
        FilaDePrioridade<Aresta<V>> pq = new FilaDePrioridade<>(new Comparador<Aresta<V>>() {
            public int comparar(Aresta<V> a1, Aresta<V> a2) {
                // Comparação segura entre valores de peso (double).
                return Double.compare(a1.peso(), a2.peso());
            }
        });

        // Adiciona as arestas incidentes ao vértice inicial na fila.
        adicionarArestas(inicio, naMST, pq);
        naMST.add(inicio);

        // Laço principal: enquanto houver arestas na fila e a MST ainda não estiver completa.
        while (!pq.isEmpty() && mst.size() < grafo.getNumeroDeVertices() - 1) {
            Aresta<V> arestaMenorPeso = pq.remover();
            V u = arestaMenorPeso.origem();
            V v = arestaMenorPeso.destino();

            // Se o vértice de destino ainda não pertence à MST, inclui a aresta.
            if (!naMST.contains(v)) {
                mst.add(arestaMenorPeso);
                naMST.add(v);
                // Adiciona as novas arestas disponíveis a partir do vértice v.
                adicionarArestas(v, naMST, pq);
            }
        }

        // Retorna a Árvore Geradora Mínima construída.
        return mst;
    }

    /**
     * Adiciona à fila de prioridade todas as arestas do vértice informado,
     * considerando apenas aquelas cujo destino ainda não está na MST.
     *
     * @param vertice O vértice de origem.
     * @param naMST Conjunto de vértices já incluídos na MST.
     * @param pq Fila de prioridade de arestas.
     */
    private void adicionarArestas(V vertice, HashSet<V> naMST, FilaDePrioridade<Aresta<V>> pq) {
        ListaEncadeada<Grafo.Vizinho<V>> vizinhos = grafo.getVizinhos(vertice);
        ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iterador = vizinhos.iterador();

        while (iterador.temProximo()) {
            Grafo.Vizinho<V> vizinho = iterador.proximo();
            if (!naMST.contains(vizinho.no())) {
                pq.adicionar(new Aresta<>(vertice, vizinho.no(), vizinho.peso()));
            }
        }
    }
}
