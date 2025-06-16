package algoritmos.conectividade;

import algoritmos.busca.bfs.BFS;
import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;

/**
 * Algoritmos para verificar a conectividade de um grafo
 * e para identificar seus componentes conexos.
 *
 * @param <V> Tipo dos dados armazenados nos vértices.
 */
public class VerificadorConectividade<V> {

    private final Grafo<V> grafo;

    public VerificadorConectividade(Grafo<V> grafo) {
        this.grafo = grafo;
    }

    /**
     * Verifica se o grafo é conectado.
     * Um grafo é considerado conectado se todos os vértices estiverem acessíveis a partir de qualquer outro vértice.
     *
     * @return true se o grafo for conectado, false caso contrário.
     */
    public boolean isConexo() {
        // Grafos vazios ou com apenas um vértice são considerados conectados.
        if (grafo.getNumeroDeVertices() <= 1) {
            return true;
        }

        // Seleciona um vértice inicial arbitrário.
        HashSet<V> todosOsVertices = grafo.getVertices();
        HashSet<V>.MeuIteradorDeHashSet iterador = todosOsVertices.iterator();
        V inicio = iterador.next();

        // Executa uma BFS a partir do vértice inicial.
        BFS<V> bfs = new BFS<>(grafo);
        HashSet<V> visitados = bfs.executar(inicio);

        // Se todos os vértices foram visitados, o grafo é conectado.
        return visitados.size() == grafo.getNumeroDeVertices();
    }

    /**
     * Encontra todos os componentes conexos do grafo.
     * Cada componente é representado como um conjunto de vértices.
     *
     * @return Lista onde cada elemento é um HashSet representando um componente conexo.
     */
    public ListaEncadeada<HashSet<V>> encontrarComponentesConexos() {
        ListaEncadeada<HashSet<V>> componentes = new ListaEncadeada<>();
        HashSet<V> visitadosGlobalmente = new HashSet<>();
        BFS<V> bfs = new BFS<>(grafo);

        // Percorre todos os vértices do grafo.
        HashSet<V> todosOsVertices = grafo.getVertices();
        HashSet<V>.MeuIteradorDeHashSet iteradorVertices = todosOsVertices.iterator();

        while (iteradorVertices.hasNext()) {
            V vertice = iteradorVertices.next();

            // Se o vértice ainda não foi visitado por buscas anteriores.
            if (!visitadosGlobalmente.contains(vertice)) {
                // Executa uma BFS para encontrar o componente conexo deste vértice.
                HashSet<V> novoComponente = bfs.executar(vertice);
                componentes.add(novoComponente);

                // Marca todos os vértices do novo componente como visitados.
                HashSet<V>.MeuIteradorDeHashSet iteradorComponente = novoComponente.iterator();
                while (iteradorComponente.hasNext()) {
                    visitadosGlobalmente.add(iteradorComponente.next());
                }
            }
        }

        // Retorna a lista contendo todos os componentes conexos encontrados.
        return componentes;
    }
}
