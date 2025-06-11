package graph_structure;

import util.lista.ListaEncadeada;

/**
 * Classe que representa um Grafo com vértices e arestas.
 * Suporta grafos direcionados e não direcionados.
 *
 * @param <E> Tipo dos dados armazenados nos vértices.
 */
public class Grafo<E> {

    private final ListaEncadeada<Vertice<E>> vertices; // Lista de vértices do grafo
    private final ListaEncadeada<Aresta<E>> arestas;   // Lista de arestas do grafo

    /**
     * Construtor da classe Grafo, inicializa as listas de vértices e arestas.
     */
    public Grafo() {
        this.vertices = new ListaEncadeada<>();
        this.arestas = new ListaEncadeada<>();
    }

    /**
     * Adiciona um vértice ao grafo.
     *
     * @param dado O dado que será armazenado no vértice.
     */
    public void addVertice(E dado) {
        Vertice<E> novoVertice = new Vertice<>(dado);
        this.vertices.add(novoVertice);
    }

    /**
     * Adiciona uma aresta ao grafo.
     *
     * @param peso        Peso da aresta.
     * @param dadoInicio  Vértice de origem.
     * @param dadoFim     Vértice de destino.
     * @param direcionado Indica se a aresta é direcionada (true) ou não (false).
     */
    public void addAresta(Double peso, E dadoInicio, E dadoFim, boolean direcionado) {
        Vertice<E> inicio = this.getVertice(dadoInicio);
        Vertice<E> fim = this.getVertice(dadoFim);

        // Se algum dos vértices não existir, a aresta não será criada
        if (inicio == null || fim == null) return;

        // Cria a aresta normal (de início para fim)
        Aresta<E> aresta = new Aresta<>(peso, inicio, fim);
        inicio.adicionaArestaSaida(aresta);
        fim.adicionaArestaEntrada(aresta);
        this.arestas.add(aresta);

        // Se o grafo não for direcionado, adiciona também a aresta reversa (de fim para início)
        if (!direcionado) {
            Aresta<E> arestaReversa = new Aresta<>(peso, fim, inicio);
            fim.adicionaArestaSaida(arestaReversa);
            inicio.adicionaArestaEntrada(arestaReversa);
            this.arestas.add(arestaReversa);
        }
    }

    /**
     * Busca um vértice no grafo a partir do dado informado.
     *
     * @param dado O dado do vértice a ser buscado.
     * @return O vértice encontrado ou null se não existir.
     */
    public Vertice<E> getVertice(E dado) {
        for (int i = 0; i < vertices.size(); i++) {
            Vertice<E> vertice = vertices.get(i);
            if (vertice.getDado().equals(dado)) {
                return vertice;
            }
        }
        return null; // Vértice não encontrado
    }

    /**
     * Remove um vértice do grafo, junto com todas as suas arestas.
     *
     * @param dado O dado do vértice a ser removido.
     */
    public void removerVertice(E dado) {
        Vertice<E> verticeRemover = getVertice(dado);
        if (verticeRemover != null) {
            removerArestasDoVertice(verticeRemover);
            vertices.remove(verticeRemover);
            System.out.println("Vértice " + dado + " removido.");
        } else {
            System.out.println("Vértice não encontrado: " + dado);
        }
    }

    /**
     * Remove todas as arestas conectadas a um vértice.
     *
     * @param vertice O vértice cujas arestas serão removidas.
     */
    private void removerArestasDoVertice(Vertice<E> vertice) {
        // Remove as arestas de entrada
        for (int i = 0; i < vertice.getArestaEntrada().size(); i++) {
            Aresta<E> entrada = vertice.getArestaEntrada().get(i);
            entrada.getInicio().getArestaSaida().remove(entrada);
            this.arestas.remove(entrada);
        }

        // Remove as arestas de saída
        for (int i = 0; i < vertice.getArestaSaida().size(); i++) {
            Aresta<E> saida = vertice.getArestaSaida().get(i);
            saida.getFim().getArestaEntrada().remove(saida);
            this.arestas.remove(saida);
        }
    }

    /**
     * Remove uma aresta específica entre dois vértices.
     *
     * @param dadoInicio Vértice de origem.
     * @param dadoFim    Vértice de destino.
     */
    public void removerAresta(E dadoInicio, E dadoFim) {
        Vertice<E> inicio = getVertice(dadoInicio);
        Vertice<E> fim = getVertice(dadoFim);

        if (inicio != null && fim != null) {
            for (int i = 0; i < arestas.size(); i++) {
                Aresta<E> aresta = arestas.get(i);
                if (aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim)) {
                    inicio.getArestaSaida().remove(aresta);
                    fim.getArestaEntrada().remove(aresta);
                    this.arestas.remove(aresta);
                    return;
                }
            }
            System.out.println("Aresta não encontrada entre " + dadoInicio + " e " + dadoFim);
        } else {
            System.out.println("Vértices não encontrados.");
        }
    }

    /**
     * Verifica se um vértice existe no grafo.
     *
     * @param dado O dado do vértice a ser pesquisado.
     * @return true se o vértice existir, false caso contrário.
     */
    public boolean pesquisarVertice(E dado) {
        return getVertice(dado) != null;
    }

    /**
     * Verifica se existe uma aresta entre dois vértices.
     *
     * @param dadoInicio Vértice de origem.
     * @param dadoFim    Vértice de destino.
     * @return true se a aresta existir, false caso contrário.
     */
    public boolean pesquisarAresta(E dadoInicio, E dadoFim) {
        Vertice<E> inicio = getVertice(dadoInicio);
        Vertice<E> fim = getVertice(dadoFim);

        if (inicio != null && fim != null) {
            for (int i = 0; i < arestas.size(); i++) {
                Aresta<E> aresta = arestas.get(i);
                if (aresta.getInicio().equals(inicio) && aresta.getFim().equals(fim)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Imprime todos os vértices do grafo.
     */
    public void imprimirVertices() {
        for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i).getDado());
        }
    }

    /**
     * Imprime os vértices adjacentes de cada vértice no grafo.
     */
    public void imprimirAdjacentes() {
        for (int i = 0; i < vertices.size(); i++) {
            Vertice<E> vertice = vertices.get(i);
            System.out.print("Vértice " + vertice.getDado() + " -> Adjacentes: ");
            ListaEncadeada<Aresta<E>> saidas = vertice.getArestaSaida();

            if (saidas.size() == 0) {
                System.out.print("Nenhum");
            } else {
                for (int j = 0; j < saidas.size(); j++) {
                    System.out.print(saidas.get(j).getFim().getDado() + " ");
                }
            }
            System.out.println();
        }
    }
}
