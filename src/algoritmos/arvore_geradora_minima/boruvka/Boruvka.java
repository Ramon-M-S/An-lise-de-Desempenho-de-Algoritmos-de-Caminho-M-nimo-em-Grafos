package algoritmos.arvore_geradora_minima.boruvka;

import graph_structure.Grafo;
import graph_structure.Grafo.Aresta;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import util.tabelaHash.TabelaHash;
import util.union_find.UnionFind;

/**
 * Implementação do Algoritmo de Borůvka para encontrar a Árvore Geradora Mínima (MST).
 * Funciona de forma iterativa, unindo componentes por meio da menor aresta conectante em cada rodada.
 *
 * @param <V> Tipo dos dados armazenados nos vértices.
 */
public class Boruvka<V> {

    private final Grafo<V> grafo;

    public Boruvka(Grafo<V> grafo) {
        // O algoritmo só funciona para grafos não direcionados.
        if (grafo.isDirecionado()) {
            throw new IllegalArgumentException("O algoritmo de Borůvka só é aplicável a grafos não direcionados.");
        }
        this.grafo = grafo;
    }

    public ListaEncadeada<Aresta<V>> encontrarMST() {
        int numVertices = grafo.getNumeroDeVertices();

        // Caso o grafo não tenha vértices, a MST é uma lista vazia.
        if (numVertices == 0) {
            return new ListaEncadeada<>();
        }

        // Estrutura Union-Find para gerenciar os componentes conectados.
        UnionFind<V> uf = new UnionFind<>(grafo.getVertices());

        // Lista que armazenará as arestas da Árvore Geradora Mínima.
        ListaEncadeada<Aresta<V>> mst = new ListaEncadeada<>();

        // Inicialmente, cada vértice é um componente separado.
        int numComponentes = numVertices;

        // Enquanto houver mais de um componente e a MST ainda não tiver V-1 arestas.
        while (numComponentes > 1 && mst.size() < numVertices - 1) {

            // Armazena a menor aresta que conecta cada componente a outro.
            TabelaHash<V, Aresta<V>> arestaMaisBarata = new TabelaHash<>();

            // Passo 1: Identificar a menor aresta de saída de cada componente.
            ListaEncadeada<Aresta<V>>.MeuIteradorDeLista iteradorArestas = grafo.getArestas().iterador();
            while (iteradorArestas.temProximo()) {
                Aresta<V> aresta = iteradorArestas.proximo();
                V u = aresta.origem();
                V v = aresta.destino();

                // Encontrar os representantes de cada componente.
                V raizU = uf.find(u);
                V raizV = uf.find(v);

                // Apenas considerar arestas entre componentes diferentes.
                if (!raizU.equals(raizV)) {
                    // Atualiza a menor aresta para o componente de u.
                    if (arestaMaisBarata.get(raizU) == null || aresta.peso() < arestaMaisBarata.get(raizU).peso()) {
                        arestaMaisBarata.put(raizU, aresta);
                    }
                    // Atualiza a menor aresta para o componente de v.
                    if (arestaMaisBarata.get(raizV) == null || aresta.peso() < arestaMaisBarata.get(raizV).peso()) {
                        arestaMaisBarata.put(raizV, aresta);
                    }
                }
            }

            boolean algumaArestaAdicionadaNestaRodada = false;

            // Passo 2: Adicionar as arestas selecionadas e unir os componentes.
            ListaEncadeada<V> raizes = arestaMaisBarata.keySet();
            ListaEncadeada<V>.MeuIteradorDeLista iteradorRaizes = raizes.iterador();
            while (iteradorRaizes.temProximo()) {
                V raiz = iteradorRaizes.proximo();
                Aresta<V> aresta = arestaMaisBarata.get(raiz);
                V u = aresta.origem();
                V v = aresta.destino();

                // Verifica novamente se u e v ainda estão em componentes diferentes antes de unir.
                if (!uf.find(u).equals(uf.find(v))) {
                    mst.add(aresta);
                    uf.union(u, v);
                    numComponentes--;
                    algumaArestaAdicionadaNestaRodada = true;
                }
            }

            // Se nenhuma aresta foi adicionada, o grafo é desconectado e o algoritmo termina.
            if (!algumaArestaAdicionadaNestaRodada) {
                break;
            }
        }

        // Retorna a Árvore Geradora Mínima encontrada.
        return mst;
    }
}
