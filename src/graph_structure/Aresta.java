package graph_structure;

/**
 * Representa uma aresta em um grafo direcionado, com peso e vértices de início e fim.
 *
 * @param <E> Tipo de dado do vértice (genérico)
 */
public class Aresta<E> {

    // Peso da aresta, que pode ser um valor numérico (Double)
    private Double peso;

    // Vértice de início da aresta
    private Vertice<E> inicio;

    // Vértice de fim da aresta
    private Vertice<E> fim;

    /**
     * Construtor da classe Aresta.
     *
     * @param peso o peso da aresta (valor numérico)
     * @param inicio vértice de origem da aresta
     * @param fim vértice de destino da aresta
     */
    public Aresta(Double peso, Vertice<E> inicio, Vertice<E> fim) {
        this.peso = peso;
        this.inicio = inicio;
        this.fim = fim;
    }

    /**
     * Retorna o peso da aresta.
     *
     * @return peso da aresta
     */
    public Double getPeso() {
        return peso;
    }

    /**
     * Define o peso da aresta.
     *
     * @param peso o peso a ser atribuído à aresta
     */
    public void setPeso(Double peso) {
        this.peso = peso;
    }

    /**
     * Retorna o vértice de início da aresta.
     *
     * @return vértice de início
     */
    public Vertice<E> getInicio() {
        return inicio;
    }

    /**
     * Define o vértice de início da aresta.
     *
     * @param inicio o vértice de início a ser atribuído
     */
    public void setInicio(Vertice<E> inicio) {
        this.inicio = inicio;
    }

    /**
     * Retorna o vértice de fim da aresta.
     *
     * @return vértice de fim
     */
    public Vertice<E> getFim() {
        return fim;
    }

    /**
     * Define o vértice de fim da aresta.
     *
     * @param fim o vértice de fim a ser atribuído
     */
    public void setFim(Vertice<E> fim) {
        this.fim = fim;
    }
}
