package algoritmos.arvore_geradora_minima.prim.utilPrim;

import util.lista.ListaEncadeada;



public class FilaDePrioridade<E> {
    private final ListaEncadeada<E> heap;
    private final Comparador<E> comparador;

    /**
     * Construtor que recebe um comparador para definir a prioridade dos elementos.
     * @param comparador O comparador para ordenar os elementos.
     */
    public FilaDePrioridade(Comparador<E> comparador) {
        this.heap = new ListaEncadeada<>();
        this.comparador = comparador;
    }

    public void adicionar(E elemento) {
        heap.add(elemento); // Adiciona no final da lista
        subir(heap.size() - 1); // "Sobe" o elemento para sua posição correta
    }

    public E remover() {
        if (isEmpty()) {
            throw new IllegalStateException("A fila de prioridade está vazia.");
        }
        // Eroca o primeiro (menor) com o último
        E menor = heap.get(0);
        E ultimo = heap.remove(heap.size() - 1);

        if (!isEmpty()) {
            heap.set(0, ultimo); // Coloca o último no lugar do primeiro
            descer(0); // "Desce" o elemento para sua posição correta
        }
        return menor;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void subir(int index) {
        int paiIndex = (index - 1) / 2;
        while (index > 0 && comparador.comparar(heap.get(index), heap.get(paiIndex)) < 0) {
            trocar(index, paiIndex);
            index = paiIndex;
            paiIndex = (index - 1) / 2;
        }
    }

    private void descer(int index) {
        int filhoEsquerdaIndex;
        while ((filhoEsquerdaIndex = 2 * index + 1) < heap.size()) {
            int menorFilhoIndex = filhoEsquerdaIndex;
            int filhoDireitaIndex = filhoEsquerdaIndex + 1;

            if (filhoDireitaIndex < heap.size() && comparador.comparar(heap.get(filhoDireitaIndex), heap.get(filhoEsquerdaIndex)) < 0) {
                menorFilhoIndex = filhoDireitaIndex;
            }

            if (comparador.comparar(heap.get(index), heap.get(menorFilhoIndex)) <= 0) {
                break;
            }

            trocar(index, menorFilhoIndex);
            index = menorFilhoIndex;
        }
    }

    private void trocar(int i, int j) {
        E temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }
}
