package algoritmos.biparticao;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import util.tabelaHash.TabelaHash;

/**
 * Verifica se um grafo é bipartido usando busca em largura (BFS) com coloração em duas cores.
 *
 * @param <V> Tipo dos dados armazenados nos vértices.
 */
public class VerificadorBiparticao<V> {

    private final Grafo<V> grafo;
    private final TabelaHash<V, Integer> cores; // Armazena a cor de cada vértice (1 ou -1)
    private boolean isBipartido = true;

    public VerificadorBiparticao(Grafo<V> grafo) {
        // O algoritmo assume grafos não direcionados. Apenas emite aviso para direcionados.
        if (grafo.isDirecionado()) {
            System.out.println("Aviso: A verificação de bipartição geralmente é aplicada a grafos não direcionados.");
        }
        this.grafo = grafo;
        this.cores = new TabelaHash<>();
    }

    /**
     * Verifica se o grafo é bipartido.
     *
     * @return true se for bipartido, false caso contrário.
     */
    public boolean verificar() {
        // Percorre todos os vértices para tratar componentes desconectados.
        HashSet<V>.MeuIteradorDeHashSet iteradorVertices = grafo.getVertices().iterator();

        while (iteradorVertices.hasNext()) {
            V vertice = iteradorVertices.next();
            // Se o vértice ainda não tem cor, inicia BFS a partir dele.
            if (cores.get(vertice) == null) {
                // Se encontrar conflito de cores em algum componente, o grafo não é bipartido.
                if (!bfsColoring(vertice)) {
                    isBipartido = false;
                    break;
                }
            }
        }
        return isBipartido;
    }

    /**
     * Executa BFS para colorir os vértices de um componente.
     *
     * @param inicio Vértice inicial da busca.
     * @return false se houver conflito de cores (grafo não bipartido), true caso contrário.
     */
    private boolean bfsColoring(V inicio) {
        ListaEncadeada<V> fila = new ListaEncadeada<>();

        // Inicializa o vértice de partida com a cor 1.
        cores.put(inicio, 1);
        fila.add(inicio);

        while (!fila.isEmpty()) {
            V atual = fila.remove(0);
            int corAtual = cores.get(atual);

            ListaEncadeada<Grafo.Vizinho<V>> vizinhos = grafo.getVizinhos(atual);
            ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = vizinhos.iterador();

            while (iteradorVizinhos.temProximo()) {
                V vizinho = iteradorVizinhos.proximo().no();
                Integer corVizinho = cores.get(vizinho);

                if (corVizinho == null) {
                    // Se o vizinho ainda não tem cor, atribui a cor oposta e adiciona à fila.
                    cores.put(vizinho, -corAtual);
                    fila.add(vizinho);
                } else if (corVizinho == corAtual) {
                    // Conflito: dois vértices adjacentes com a mesma cor. O grafo não é bipartido.
                    System.out.println("Conflito: Aresta entre " + atual + " e " + vizinho + " com a mesma cor.");
                    return false;
                }
                // Se o vizinho já tem a cor oposta, está correto, não faz nada.
            }
        }
        return true; // Nenhum conflito encontrado neste componente.
    }

    /**
     * Se o grafo for bipartido, retorna as duas partições de vértices.
     * @return Uma ListaEncadeada contendo dois HashSet, cada um representando uma partição.
     * Retorna null se o grafo não for bipartido.
     */
    public ListaEncadeada<HashSet<V>> obterParticoes() {
        // Primeiro, executa a verificação completa.
        // Se não for bipartido, não há partições para retornar.
        if (!verificar()) {
            return null;
        }

        // Se chegou aqui, o grafo é bipartido e o mapa 'cores' está preenchido.
        HashSet<V> particaoA = new HashSet<>();
        HashSet<V> particaoB = new HashSet<>();

        // Itera sobre os vértices coloridos para separá-los em dois conjuntos.
        ListaEncadeada<V> verticesColoridos = cores.keySet();
        ListaEncadeada<V>.MeuIteradorDeLista iterador = verticesColoridos.iterador();

        while(iterador.temProximo()) {
            V vertice = iterador.proximo();
            int cor = cores.get(vertice);

            if (cor == 1) {
                particaoA.add(vertice);
            } else { // cor == -1
                particaoB.add(vertice);
            }
        }

        ListaEncadeada<HashSet<V>> particoes = new ListaEncadeada<>();
        particoes.add(particaoA);
        particoes.add(particaoB);

        return particoes;
    }
}
