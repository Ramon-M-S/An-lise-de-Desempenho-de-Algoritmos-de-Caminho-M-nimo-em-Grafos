package util.pilha;

import java.io.Serializable;
import java.util.ConcurrentModificationException;
import java.util.EmptyStackException;

/**
 * Pilha (LIFO) baseada em array dinâmico, com suporte a serialização,
 * conversão para array e iteração customizada.
 *
 * @param <E> o tipo dos elementos armazenados na pilha
 */
public class Pilha<E> implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elementData;
    private int elementCount;

    /** Contador de modificações para suporte a iterador fail-fast. */
    protected transient int modCount = 0;

    /** Construtor padrão: cria uma pilha com capacidade inicial padrão. */
    public Pilha() {
        this.elementData = new Object[DEFAULT_CAPACITY];
        this.elementCount = 0;
    }

    public E push(E item) {
        ensureCapacityHelper();
        elementData[elementCount++] = item;
        modCount++;
        return item;
    }

    @SuppressWarnings("unchecked")
    public E pop() {
        if (isEmpty()) throw new EmptyStackException();
        E item = (E) elementData[--elementCount];
        elementData[elementCount] = null;
        modCount++;
        return item;
    }

    @SuppressWarnings("unchecked")
    public E peek() {
        if (isEmpty()) throw new EmptyStackException();
        return (E) elementData[elementCount - 1];
    }

    public boolean isEmpty() {
        return elementCount == 0;
    }

    public int size() {
        return elementCount;
    }

    private void ensureCapacityHelper() {
        if (elementCount == elementData.length) {
            int newCapacity = elementData.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(elementData, 0, newArray, 0, elementData.length);
            elementData = newArray;
        }
    }

    /**
     * Converte a pilha para um array do tipo Object[], do topo até a base.
     *
     * @return um novo array contendo os elementos da pilha
     */
    public Object[] toArray() {
        Object[] array = new Object[elementCount];
        for (int i = 0; i < elementCount; i++) {
            array[i] = elementData[elementCount - 1 - i]; // LIFO
        }
        return array;
    }

    /**
     * Converte a pilha para um array do tipo T[], do topo até a base.
     *
     * @param <T> tipo do array de saída
     * @param a array de saída (pode ser redimensionado)
     * @return array contendo os elementos da pilha
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < elementCount) {
            a = (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), elementCount);
        }
        for (int i = 0; i < elementCount; i++) {
            a[i] = (T) elementData[elementCount - 1 - i];
        }
        if (a.length > elementCount) {
            a[elementCount] = null;
        }
        return a;
    }

    /**
     * Verifica se a pilha contém um determinado elemento.
     *
     * @param o o elemento a ser verificado (pode ser null)
     * @return true se o elemento estiver presente, false caso contrário
     * @throws ClassCastException se o tipo não for compatível com E
     *
     * Complexidade: O(n)
     */
    public boolean contains(Object o) {
        for (int i = 0; i < elementCount; i++) {
            if (o == null ? elementData[i] == null : o.equals(elementData[i])) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna um iterador para a pilha, que percorre do topo até a base.
     *
     * @return iterador customizado da pilha
     */
    public MeuIterador iterator() {
        return new MeuIterador();
    }

    /** Iterador interno da pilha (do topo para base), com comportamento fail-fast. */
    public class MeuIterador {
        private int cursor = elementCount - 1;
        private final int esperadoModCount = modCount;

        public boolean hasNext() {
            checkForComodification();
            return cursor >= 0;
        }

        @SuppressWarnings("unchecked")
        public E next() {
            checkForComodification();
            if (!hasNext()) throw new IllegalStateException("Sem mais elementos.");
            return (E) elementData[cursor--];
        }

        private void checkForComodification() {
            if (modCount != esperadoModCount) {
                throw new ConcurrentModificationException("A pilha foi modificada durante a iteração.");
            }
        }
    }
}
