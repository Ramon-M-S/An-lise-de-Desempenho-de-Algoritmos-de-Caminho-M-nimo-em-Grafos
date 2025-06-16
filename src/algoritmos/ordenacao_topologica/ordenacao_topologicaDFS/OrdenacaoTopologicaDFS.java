package algoritmos.ordenacao_topologica.ordenacao_topologicaDFS;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;

/**
 * Ordenação Topológica usando Busca em Profundidade (DFS).
 * Também realiza a detecção de ciclos no grafo.
 *
 * @param <V> Tipo dos vértices do grafo.
 */
public class OrdenacaoTopologicaDFS<V> {

    private final Grafo<V> grafo;
    private final ListaEncadeada<V> ordem;         // Armazena a ordem topológica final.
    private final HashSet<V> visitados;            // Vértices já visitados.
    private final HashSet<V> naPilhaRecursao;      // Vértices na pilha de chamadas (usado para detectar ciclos).
    private boolean temCiclo;

    public OrdenacaoTopologicaDFS(Grafo<V> grafo) {
        // A ordenação topológica só é válida para grafos direcionados.
        if (!grafo.isDirecionado()) {
            throw new IllegalArgumentException("Ordenação topológica só é válida para grafos direcionados.");
        }
        this.grafo = grafo;
        this.ordem = new ListaEncadeada<>();
        this.visitados = new HashSet<>();
        this.naPilhaRecursao = new HashSet<>();
        this.temCiclo = false;
    }

    /**
     * Executa a ordenação topológica.
     *
     * @return Lista com os vértices em ordem topológica.
     * Retorna lista vazia se houver ciclo no grafo.
     */
    public ListaEncadeada<V> ordenar() {
        // Garante que todos os componentes sejam analisados.
        HashSet<V>.MeuIteradorDeHashSet iterador = grafo.getVertices().iterator();
        while (iterador.hasNext()) {
            V vertice = iterador.next();
            if (!visitados.contains(vertice)) {
                dfs(vertice);
            }
        }

        // Se um ciclo foi detectado, retorna lista vazia.
        if (temCiclo) {
            System.out.println("Erro: O grafo contém um ciclo! Ordenação topológica não é possível.");
            return new ListaEncadeada<>();
        }

        return ordem;
    }

    /**
     * Método recursivo da DFS.
     * Visita os vértices e monta a ordem topológica.
     *
     * @param atual Vértice atualmente sendo processado.
     */
    private void dfs(V atual) {
        // Se já detectamos um ciclo, interrompe a execução.
        if (temCiclo) {
            return;
        }

        visitados.add(atual);
        naPilhaRecursao.add(atual);

        ListaEncadeada<Grafo.Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = grafo.getVizinhos(atual).iterador();
        while (iteradorVizinhos.temProximo()) {
            V vizinho = iteradorVizinhos.proximo().no();

            // Detecta ciclo: o vizinho está na pilha de recursão.
            if (naPilhaRecursao.contains(vizinho)) {
                temCiclo = true;
                return;
            }

            // Continua a busca em vizinhos ainda não visitados.
            if (!visitados.contains(vizinho)) {
                dfs(vizinho);
            }
        }

        // Remove o vértice da pilha de recursão após visitar todos os vizinhos.
        naPilhaRecursao.remove(atual);

        // Insere o vértice no início da ordem (garantindo o topo correto da ordenação).
        ordem.addFirst(atual);
    }
}
