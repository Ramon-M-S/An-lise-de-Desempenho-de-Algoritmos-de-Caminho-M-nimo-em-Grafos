package algoritmos.busca.dfs;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import util.pilha.Pilha;

/**
 * Implementação da Busca em Profundidade (DFS) nas versões recursiva e iterativa.
 * Não utiliza bibliotecas da API padrão Java.
 *
 * @param <V> Tipo dos vértices do grafo.
 */
public class DFS<V> {

    private final Grafo<V> grafo;
    private final HashSet<V> visitados; // Controla os vértices visitados na versão recursiva.

    /**
     * Construtor que inicializa a estrutura com o grafo alvo da busca.
     *
     * @param grafo Grafo a ser explorado pela DFS.
     */
    public DFS(Grafo<V> grafo) {
        this.grafo = grafo;
        this.visitados = new HashSet<>();
    }

    /**
     * Executa a DFS na versão recursiva a partir de um vértice inicial.
     *
     * @param inicio Vértice de início da busca.
     */
    public void executarRecursivo(V inicio) {
        // Verifica se o vértice inicial existe no grafo.
        if (!grafo.getVertices().contains(inicio)) {
            System.out.println("Vértice inicial não encontrado no grafo.");
            return;
        }

        // Limpa o conjunto de visitados antes de iniciar a busca.
        visitados.clear();
        System.out.println("--- Iniciando DFS Recursiva ---");
        dfsVisit(inicio);
    }

    /**
     * Método auxiliar recursivo para visitar os vértices.
     *
     * @param vertice Vértice atual sendo visitado.
     */
    private void dfsVisit(V vertice) {
        visitados.add(vertice);
        System.out.println("Visitando: " + vertice);

        ListaEncadeada<Grafo.Vizinho<V>> vizinhos = grafo.getVizinhos(vertice);
        ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iterador = vizinhos.iterador();

        while (iterador.temProximo()) {
            Grafo.Vizinho<V> vizinho = iterador.proximo();
            V destino = vizinho.no();

            // Visita recursivamente os vizinhos ainda não visitados.
            if (!visitados.contains(destino)) {
                dfsVisit(destino);
            }
        }
    }

    /**
     * Executa a DFS na versão iterativa a partir de um vértice inicial.
     *
     * @param inicio Vértice de início da busca.
     */
    public void executarIterativo(V inicio) {
        // Verifica se o vértice inicial existe no grafo.
        if (!grafo.getVertices().contains(inicio)) {
            System.out.println("Vértice inicial não encontrado no grafo.");
            return;
        }

        HashSet<V> visitadosLocal = new HashSet<>(); // Conjunto local para controle de visitados.
        Pilha<V> pilha = new Pilha<>();

        pilha.push(inicio);

        while (!pilha.isEmpty()) {
            V atual = pilha.pop();

            if (!visitadosLocal.contains(atual)) {
                visitadosLocal.add(atual);
                System.out.println("Visitando: " + atual);

                ListaEncadeada<Grafo.Vizinho<V>> vizinhos = grafo.getVizinhos(atual);

                // Empilha os vizinhos em ordem inversa para manter a ordem de visita igual à versão recursiva.
                ListaEncadeada<V> vizinhosParaEmpilhar = new ListaEncadeada<>();
                ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iterador = vizinhos.iterador();

                while (iterador.temProximo()) {
                    Grafo.Vizinho<V> vizinho = iterador.proximo();
                    if (!visitadosLocal.contains(vizinho.no())) {
                        vizinhosParaEmpilhar.add(vizinho.no());
                    }
                }

                // Empilha os vizinhos de trás para frente.
                for (int i = vizinhosParaEmpilhar.size() - 1; i >= 0; i--) {
                    pilha.push(vizinhosParaEmpilhar.get(i));
                }
            }
        }
    }
}
