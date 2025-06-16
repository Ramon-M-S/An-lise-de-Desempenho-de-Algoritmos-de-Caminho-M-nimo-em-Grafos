package algoritmos.hierholzer_circuito_euleriano;

import algoritmos.conectividade.VerificadorConectividade;
import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import util.pilha.Pilha;
import util.tabelaHash.TabelaHash;

/**
 * Implementação do Algoritmo de Hierholzer para encontrar um Circuito Euleriano.
 *
 * @param <V> Tipo dos vértices do grafo.
 */
public class Hierholzer<V> {

    private final Grafo<V> grafo;

    public Hierholzer(Grafo<V> grafo) {
        // O algoritmo só funciona em grafos não direcionados.
        if (grafo.isDirecionado()) {
            throw new IllegalArgumentException("O Algoritmo de Hierholzer requer um grafo não direcionado.");
        }
        this.grafo = grafo;
    }

    /**
     * Verifica se o grafo atende as condições necessárias para ter um Circuito Euleriano.
     * Condições:
     * 1) Todos os vértices têm grau par.
     * 2) O grafo é conexo (ignorando vértices isolados com grau zero).
     *
     * @return true se o circuito existir, false caso contrário.
     */
    private boolean existeCircuitoEuleriano() {
        // Verifica se todos os vértices têm grau par.
        HashSet<V>.MeuIteradorDeHashSet iteradorVertices = grafo.getVertices().iterator();
        while (iteradorVertices.hasNext()) {
            V vertice = iteradorVertices.next();
            if (grafo.getVizinhos(vertice).size() % 2 != 0) {
                System.out.println("Erro: O vértice " + vertice + " tem grau ímpar.");
                return false;
            }
        }

        // Verifica a conectividade, considerando apenas vértices com grau maior que zero.
        VerificadorConectividade<V> conector = new VerificadorConectividade<>(grafo);
        ListaEncadeada<HashSet<V>> componentes = conector.encontrarComponentesConexos();

        int componentesRelevantes = 0;
        for (int i = 0; i < componentes.size(); i++) {
            if (componentes.get(i).size() > 1) {
                componentesRelevantes++;
            } else if (componentes.get(i).size() == 1) {
                V verticeUnico = componentes.get(i).iterator().next();
                if (grafo.getVizinhos(verticeUnico).size() > 0) {
                    componentesRelevantes++;
                }
            }
        }

        // Só pode haver um único componente relevante.
        if (componentesRelevantes > 1) {
            System.out.println("Erro: O grafo não é conexo.");
            return false;
        }

        return true;
    }

    /**
     * Executa o Algoritmo de Hierholzer para construir o Circuito Euleriano.
     *
     * @return Lista com a sequência de vértices do circuito.
     * Retorna lista vazia se o circuito não existir.
     */
    public ListaEncadeada<V> encontrarCircuito() {
        if (!existeCircuitoEuleriano()) {
            return new ListaEncadeada<>();
        }

        // Copia as listas de adjacência para manipular as arestas sem alterar o grafo original.
        TabelaHash<V, ListaEncadeada<V>> adjacenciasCopia = new TabelaHash<>();
        HashSet<V>.MeuIteradorDeHashSet iteradorVertices = grafo.getVertices().iterator();
        while (iteradorVertices.hasNext()) {
            V vertice = iteradorVertices.next();
            ListaEncadeada<V> vizinhos = new ListaEncadeada<>();
            ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = grafo.getVizinhos(vertice).iterador();
            while (iteradorVizinhos.temProximo()) {
                vizinhos.add(iteradorVizinhos.proximo().no());
            }
            adjacenciasCopia.put(vertice, vizinhos);
        }

        Pilha<V> caminhoAtual = new Pilha<>();
        ListaEncadeada<V> circuito = new ListaEncadeada<>();

        // Escolhe um vértice inicial com pelo menos uma aresta.
        V inicio = null;
        iteradorVertices = grafo.getVertices().iterator();
        while (iteradorVertices.hasNext()) {
            V v = iteradorVertices.next();
            if (grafo.getVizinhos(v).size() > 0) {
                inicio = v;
                break;
            }
        }

        // Caso o grafo não tenha arestas.
        if (inicio == null) {
            return circuito;
        }

        caminhoAtual.push(inicio);

        while (!caminhoAtual.isEmpty()) {
            V verticeCorrente = caminhoAtual.peek();

            // Enquanto houver arestas não visitadas a partir do vértice atual.
            if (!adjacenciasCopia.get(verticeCorrente).isEmpty()) {
                V proximoVizinho = adjacenciasCopia.get(verticeCorrente).remove(0);
                adjacenciasCopia.get(proximoVizinho).remove(verticeCorrente); // Remove aresta na volta.
                caminhoAtual.push(proximoVizinho);
            } else {
                // Quando não há mais arestas, adiciona o vértice ao início do circuito final.
                circuito.addFirst(caminhoAtual.pop());
            }
        }

        return circuito;
    }
}
