package algoritmos.busca.bfs;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;

/**
 * Implementação da Busca em Largura (BFS).
 * Fornece métodos para:
 * - Obter o conjunto de vértices visitados.
 * - Imprimir a ordem de visitação.
 * - Retornar a sequência de visita como lista.
 *
 * @param <V> Tipo dos vértices do grafo.
 */
public class BFS<V> {

    private final Grafo<V> grafo;

    public BFS(Grafo<V> grafo) {
        this.grafo = grafo;
    }

    /**
     * Executa a BFS a partir de um vértice inicial.
     * Retorna o conjunto de todos os vértices alcançados.
     *
     * @param inicio Vértice de partida.
     * @return Conjunto de vértices visitados.
     */
    public HashSet<V> executar(V inicio) {
        HashSet<V> visitados = new HashSet<>();

        // Valida se o vértice inicial existe no grafo.
        if (grafo.getVertices() == null || !grafo.getVertices().contains(inicio)) {
            return visitados;
        }

        ListaEncadeada<V> fila = new ListaEncadeada<>();

        // Inicia a busca com o vértice de partida.
        fila.add(inicio);
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            V atual = fila.remove(0);

            // Visita os vizinhos do vértice atual.
            ListaEncadeada<Grafo.Vizinho<V>> vizinhos = grafo.getVizinhos(atual);
            ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iterador = vizinhos.iterador();

            while (iterador.temProximo()) {
                Grafo.Vizinho<V> vizinho = iterador.proximo();
                V destino = vizinho.no();

                // Enfileira vértices não visitados.
                if (!visitados.contains(destino)) {
                    fila.add(destino);
                    visitados.add(destino);
                }
            }
        }

        return visitados;
    }

    /**
     * Executa a BFS e imprime no console a ordem de visitação dos vértices.
     * Útil para visualização e depuração.
     *
     * @param inicio Vértice de partida.
     */
    public void imprimirBusca(V inicio) {
        // Valida se o vértice inicial existe.
        if (grafo.getVertices() == null || !grafo.getVertices().contains(inicio)) {
            System.out.println("Vértice inicial não encontrado no grafo.");
            return;
        }

        HashSet<V> visitados = new HashSet<>();
        ListaEncadeada<V> fila = new ListaEncadeada<>();

        fila.add(inicio);
        visitados.add(inicio);

        System.out.println("--- Iniciando BFS a partir de " + inicio + " ---");

        while (!fila.isEmpty()) {
            V atual = fila.remove(0);
            System.out.println("Visitando: " + atual);

            ListaEncadeada<Grafo.Vizinho<V>> vizinhos = grafo.getVizinhos(atual);
            ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iterador = vizinhos.iterador();

            while (iterador.temProximo()) {
                Grafo.Vizinho<V> vizinho = iterador.proximo();
                V destino = vizinho.no();

                // Enfileira e marca o vizinho não visitado.
                if (!visitados.contains(destino)) {
                    System.out.println("  -> Enfileirando: " + destino);
                    fila.add(destino);
                    visitados.add(destino);
                }
            }
        }

        System.out.println("--- Fim da BFS ---");
    }

    /**
     * Executa a BFS e retorna a lista com a sequência de visitação dos vértices.
     * Útil quando é necessário obter a ordem exata dos vértices visitados.
     *
     * @param inicio Vértice de partida.
     * @return Lista com os vértices na ordem de visita.
     */
    public ListaEncadeada<V> getTravessia(V inicio) {
        ListaEncadeada<V> ordemDeVisita = new ListaEncadeada<>();
        HashSet<V> visitados = new HashSet<>();

        // Valida se o vértice inicial existe.
        if (grafo.getVertices() == null || !grafo.getVertices().contains(inicio)) {
            return ordemDeVisita; // Retorna lista vazia se o vértice for inválido.
        }

        ListaEncadeada<V> fila = new ListaEncadeada<>();

        fila.add(inicio);
        visitados.add(inicio);

        while (!fila.isEmpty()) {
            V atual = fila.remove(0);
            ordemDeVisita.add(atual); // Registra a ordem de visitação.

            ListaEncadeada<Grafo.Vizinho<V>> vizinhos = grafo.getVizinhos(atual);
            ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iterador = vizinhos.iterador();

            while (iterador.temProximo()) {
                Grafo.Vizinho<V> vizinho = iterador.proximo();
                V destino = vizinho.no();

                // Enfileira apenas os vizinhos não visitados.
                if (!visitados.contains(destino)) {
                    fila.add(destino);
                    visitados.add(destino);
                }
            }
        }

        return ordemDeVisita;
    }
}
