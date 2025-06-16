package algoritmos.ordenacao_topologica.kahn;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import util.tabelaHash.TabelaHash;

/**
 * Implementação do algoritmo de Kahn para Ordenação Topológica.
 * Também detecta a presença de ciclos no grafo.
 *
 * @param <V> Tipo dos vértices do grafo.
 */
public class Kahn<V> {

    private final Grafo<V> grafo;

    public Kahn(Grafo<V> grafo) {
        // O algoritmo de Kahn só funciona em grafos direcionados.
        if (!grafo.isDirecionado()) {
            throw new IllegalArgumentException("Ordenação topológica só é válida para grafos direcionados.");
        }
        this.grafo = grafo;
    }

    /**
     * Executa a ordenação topológica usando o algoritmo de Kahn.
     *
     * @return Lista com os vértices em ordem topológica.
     * Retorna lista vazia se o grafo contiver ciclos.
     */
    public ListaEncadeada<V> ordenar() {
        // Passo 1: Calcula o grau de entrada (in-degree) de todos os vértices.
        TabelaHash<V, Integer> inDegrees = new TabelaHash<>();
        HashSet<V>.MeuIteradorDeHashSet iteradorVertices = grafo.getVertices().iterator();

        while (iteradorVertices.hasNext()) {
            inDegrees.put(iteradorVertices.next(), 0);
        }

        ListaEncadeada<Grafo.Aresta<V>>.MeuIteradorDeLista iteradorArestas = grafo.getArestas().iterador();
        while (iteradorArestas.temProximo()) {
            Grafo.Aresta<V> aresta = iteradorArestas.proximo();
            V destino = aresta.destino();
            inDegrees.put(destino, inDegrees.get(destino) + 1);
        }

        // Passo 2: Enfileira vértices com grau de entrada 0.
        ListaEncadeada<V> fila = new ListaEncadeada<>();
        iteradorVertices = grafo.getVertices().iterator();
        while (iteradorVertices.hasNext()) {
            V vertice = iteradorVertices.next();
            if (inDegrees.get(vertice) == 0) {
                fila.add(vertice);
            }
        }

        // Passo 3: Processa os vértices na fila.
        ListaEncadeada<V> resultado = new ListaEncadeada<>();
        int verticesProcessados = 0;

        while (!fila.isEmpty()) {
            V u = fila.remove(0);
            resultado.add(u);
            verticesProcessados++;

            // Atualiza o grau de entrada dos vizinhos de u.
            ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = grafo.getVizinhos(u).iterador();
            while (iteradorVizinhos.temProximo()) {
                V v = iteradorVizinhos.proximo().no();
                inDegrees.put(v, inDegrees.get(v) - 1);

                if (inDegrees.get(v) == 0) {
                    fila.add(v);
                }
            }
        }

        // Passo 4: Verifica se o grafo contém ciclo.
        if (verticesProcessados != grafo.getNumeroDeVertices()) {
            System.out.println("Erro: O grafo contém um ciclo! Ordenação topológica não é possível.");
            return new ListaEncadeada<>();
        }

        return resultado;
    }
}
