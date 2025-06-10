package graph_structure;

import util.lista.ListaEncadeada;

/**
 * Representa um vértice de um grafo, contendo dados e listas de arestas de entrada e saída.
 *
 * @param <E> Tipo de dado armazenado no vértice (genérico)
 */
public class Vertice<E> {

    // Dado armazenado no vértice (pode ser qualquer tipo de dado genérico)
    private E dado;

    // Lista de arestas que entram neste vértice (arestas de entrada)
    private final ListaEncadeada<Aresta<E>> arestaEntrada;

    // Lista de arestas que saem deste vértice (arestas de saída)
    private final ListaEncadeada<Aresta<E>> arestaSaida;

    /**
     * Construtor da classe Vertice.
     * Inicializa o dado do vértice e as listas de arestas de entrada e saída.
     *
     * @param dado dado que será armazenado no vértice
     */
    public Vertice(E dado) {
        this.dado = dado;
        this.arestaEntrada = new ListaEncadeada<>(); // Lista de arestas de entrada
        this.arestaSaida = new ListaEncadeada<>(); // Lista de arestas de saída
    }

    /**
     * Retorna o dado armazenado no vértice.
     *
     * @return dado armazenado no vértice
     */
    public E getDado() {
        return dado;
    }

    /**
     * Define o dado do vértice.
     *
     * @param dado o dado a ser armazenado no vértice
     */
    public void setDado(E dado) {
        this.dado = dado;
    }

    /**
     * Adiciona uma aresta de saída ao vértice (aresta que sai do vértice).
     *
     * @param tAresta a aresta a ser adicionada à lista de arestas de saída
     */
    public void adicionaArestaSaida(Aresta<E> tAresta){
        this.arestaSaida.add(tAresta);
    }

    /**
     * Adiciona uma aresta de entrada ao vértice (aresta que entra no vértice).
     *
     * @param tAresta a aresta a ser adicionada à lista de arestas de entrada
     */
    public void adicionaArestaEntrada(Aresta<E> tAresta){
        this.arestaEntrada.add(tAresta);
    }

    /**
     * Retorna a lista de arestas de saída do vértice.
     *
     * @return lista de arestas que saem deste vértice
     */
    public ListaEncadeada<Aresta<E>> getArestaSaida(){
        return arestaSaida;
    }

    /**
     * Retorna a lista de arestas de entrada do vértice.
     *
     * @return lista de arestas que entram neste vértice
     */
    public ListaEncadeada<Aresta<E>> getArestaEntrada(){
        return arestaEntrada;
    }
}
