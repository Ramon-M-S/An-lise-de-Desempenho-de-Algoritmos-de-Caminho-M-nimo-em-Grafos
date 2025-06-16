package algoritmos.arvore_geradora_minima.prim.utilPrim;

/**
 * Uma interface de comparação customizada para determinar a ordem entre dois objetos.
 *
 * @param <T> O tipo dos objetos a serem comparados.
 */
public interface Comparador<T> {

    /**
     * Compara dois objetos para ordenação.
     *
     * @param o1 o primeiro objeto.
     * @param o2 o segundo objeto.
     * @return um inteiro negativo se o1 < o2,
     * zero se o1 == o2,
     * um inteiro positivo se o1 > o2.
     */
    int comparar(T o1, T o2);
}