package algoritmos.hamiltoniano_backtracking;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;

/**
 * Algoritmo de Backtracking para encontrar Caminhos e Circuitos Hamiltonianos em um grafo.
 * Observação: Problema NP-Completo. Viável apenas para grafos pequenos.
 *
 * @param <V> Tipo dos vértices do grafo.
 */
public class Backtracking<V> {

    private final Grafo<V> grafo;
    private final int numVertices;
    private static final int LIMITE_VERTICES = 20; // Limite para evitar execução em grafos grandes.

    public Backtracking(Grafo<V> grafo) {
        this.grafo = grafo;
        this.numVertices = grafo.getNumeroDeVertices();
    }

    /**
     * Tenta encontrar um Caminho Hamiltoniano no grafo.
     * Um Caminho Hamiltoniano passa por todos os vértices sem repetir, mas não precisa retornar ao início.
     *
     * @return Lista com a sequência de vértices do caminho encontrado, ou lista vazia se não houver.
     */
    public ListaEncadeada<V> encontrarCaminho() {
        if (numVertices > LIMITE_VERTICES) {
            System.out.println("Erro: Grafo muito grande (" + numVertices + " vértices) para backtracking.");
            return new ListaEncadeada<>();
        }

        // Testa iniciar o caminho a partir de cada vértice.
        HashSet<V>.MeuIteradorDeHashSet iterador = grafo.getVertices().iterator();
        while (iterador.hasNext()) {
            V inicio = iterador.next();
            ListaEncadeada<V> caminho = new ListaEncadeada<>();
            HashSet<V> visitados = new HashSet<>();

            caminho.add(inicio);
            visitados.add(inicio);

            if (backtrack(inicio, caminho, visitados)) {
                return caminho; // Retorna o primeiro caminho encontrado.
            }
        }

        return new ListaEncadeada<>(); // Nenhum caminho encontrado.
    }

    /**
     * Tenta encontrar um Circuito Hamiltoniano no grafo.
     * Um Circuito Hamiltoniano passa por todos os vértices e termina no vértice inicial.
     *
     * @return Lista com a sequência de vértices do circuito, ou lista vazia se não houver.
     */
    public ListaEncadeada<V> encontrarCircuito() {
        if (numVertices > LIMITE_VERTICES) {
            System.out.println("Erro: Grafo muito grande (" + numVertices + " vértices) para backtracking.");
            return new ListaEncadeada<>();
        }

        HashSet<V>.MeuIteradorDeHashSet iterador = grafo.getVertices().iterator();
        while (iterador.hasNext()) {
            V inicio = iterador.next();
            ListaEncadeada<V> caminho = new ListaEncadeada<>();
            HashSet<V> visitados = new HashSet<>();

            caminho.add(inicio);
            visitados.add(inicio);

            if (backtrack(inicio, caminho, visitados)) {
                // Verifica se o caminho forma um ciclo voltando ao início.
                V ultimoVertice = caminho.get(caminho.size() - 1);
                boolean cicloFechado = false;
                ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = grafo.getVizinhos(ultimoVertice).iterador();

                while (iteradorVizinhos.temProximo()) {
                    if (iteradorVizinhos.proximo().no().equals(inicio)) {
                        cicloFechado = true;
                        break;
                    }
                }

                if (cicloFechado) {
                    caminho.add(inicio); // Fecha o circuito adicionando o vértice inicial no final.
                    return caminho;
                }
            }
        }

        return new ListaEncadeada<>(); // Nenhum circuito encontrado.
    }

    /**
     * Método recursivo de backtracking.
     * Tenta estender o caminho atual até cobrir todos os vértices.
     *
     * @param atual    Vértice atual no caminho.
     * @param caminho  Caminho construído até o momento.
     * @param visitados Conjunto de vértices já visitados.
     * @return true se um caminho válido foi encontrado a partir do vértice atual.
     */
    private boolean backtrack(V atual, ListaEncadeada<V> caminho, HashSet<V> visitados) {
        // Condição de parada: caminho contém todos os vértices.
        if (caminho.size() == numVertices) {
            return true;
        }

        // Explora os vizinhos não visitados.
        ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = grafo.getVizinhos(atual).iterador();
        while (iteradorVizinhos.temProximo()) {
            V vizinho = iteradorVizinhos.proximo().no();

            if (!visitados.contains(vizinho)) {
                caminho.add(vizinho);
                visitados.add(vizinho);

                // Chamada recursiva para o próximo vértice.
                if (backtrack(vizinho, caminho, visitados)) {
                    return true;
                }

                // Backtracking: desfaz a escolha atual e tenta outro vizinho.
                caminho.remove(caminho.size() - 1);
                visitados.remove(vizinho);
            }
        }

        return false; // Nenhum caminho válido a partir deste vértice.
    }
}
