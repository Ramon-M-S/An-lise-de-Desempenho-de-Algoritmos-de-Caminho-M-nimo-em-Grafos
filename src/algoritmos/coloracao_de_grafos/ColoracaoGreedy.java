package algoritmos.coloracao_de_grafos;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import util.tabelaHash.TabelaHash;

/**
 * Implementação de um algoritmo guloso (greedy) para coloração de grafos.
 * Atribui cores inteiras aos vértices garantindo que vértices adjacentes tenham cores diferentes.
 *
 * @param <V> Tipo dos dados armazenados nos vértices.
 */
public class ColoracaoGreedy<V> {

    private final Grafo<V> grafo;

    public ColoracaoGreedy(Grafo<V> grafo) {
        this.grafo = grafo;
    }

    /**
     * Executa o algoritmo de coloração gulosa.
     * A cada vértice, é atribuída a menor cor inteira disponível que não conflite com seus vizinhos.
     *
     * @return TabelaHash mapeando cada vértice para sua cor (inteiro).
     */
    public TabelaHash<V, Integer> colorir() {
        TabelaHash<V, Integer> resultadoCores = new TabelaHash<>();

        // Percorre todos os vértices do grafo.
        HashSet<V>.MeuIteradorDeHashSet iteradorVertices = grafo.getVertices().iterator();
        while (iteradorVertices.hasNext()) {
            V verticeAtual = iteradorVertices.next();

            // Conjunto para armazenar as cores atualmente usadas pelos vizinhos do vértice.
            HashSet<Integer> coresVizinhas = new HashSet<>();

            // Verifica as cores dos vizinhos já coloridos.
            ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = grafo.getVizinhos(verticeAtual).iterador();
            while (iteradorVizinhos.temProximo()) {
                V vizinho = iteradorVizinhos.proximo().no();
                Integer corDoVizinho = resultadoCores.get(vizinho);

                if (corDoVizinho != null) {
                    coresVizinhas.add(corDoVizinho);
                }
            }

            // Escolhe a menor cor inteira disponível que não esteja entre as cores dos vizinhos.
            int proximaCor = 1;
            while (coresVizinhas.contains(proximaCor)) {
                proximaCor++;
            }

            // Atribui a cor escolhida ao vértice atual.
            resultadoCores.put(verticeAtual, proximaCor);
        }

        // Retorna o mapa de cores de cada vértice.
        return resultadoCores;
    }
}
