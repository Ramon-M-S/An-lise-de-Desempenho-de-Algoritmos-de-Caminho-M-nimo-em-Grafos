package util.lista;

import java.io.Serializable;
import java.util.NoSuchElementException;

/**
 * Uma implementação de lista duplamente encadeada que emula LinkedList.
 * @param <E> o tipo dos elementos armazenados na lista
 */
public class ListaEncadeada<E> implements Cloneable, Serializable {

    private static final long serialVersionUID = 1L;

    private static class No<E> {
        E item;
        No<E> proximo;
        No<E> anterior;

        No(No<E> anterior, E item, No<E> proximo) {
            this.item = item;
            this.anterior = anterior;
            this.proximo = proximo;
        }
    }

    private transient No<E> primeiro;
    private transient No<E> ultimo;
    private int tamanho;
    protected transient int modCount = 0;

    /** Construtor padrão. Cria uma lista vazia. */
    public ListaEncadeada() {}

    /** Retorna o tamanho atual da lista. */
    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    public void addFirst(E e) {
        No<E> novo = new No<>(null, e, primeiro);
        if (primeiro == null) {
            ultimo = novo;
        } else {
            primeiro.anterior = novo;
        }
        primeiro = novo;
        tamanho++;
        modCount++;
    }

    public void addLast(E e) {
        No<E> novo = new No<>(ultimo, e, null);
        if (ultimo == null) {
            primeiro = novo;
        } else {
            ultimo.proximo = novo;
        }
        ultimo = novo;
        tamanho++;
        modCount++;
    }

    public E getFirst() {
        if (primeiro == null) throw new NoSuchElementException();
        return primeiro.item;
    }

    public E getLast() {
        if (ultimo == null) throw new NoSuchElementException();
        return ultimo.item;
    }

    public E removeFirst() {
        if (primeiro == null) throw new NoSuchElementException();
        E elemento = primeiro.item;
        primeiro = primeiro.proximo;
        if (primeiro == null) {
            ultimo = null;
        } else {
            primeiro.anterior = null;
        }
        tamanho--;
        modCount++;
        return elemento;
    }

    public E removeLast() {
        if (ultimo == null) throw new NoSuchElementException();
        E elemento = ultimo.item;
        ultimo = ultimo.anterior;
        if (ultimo == null) {
            primeiro = null;
        } else {
            ultimo.proximo = null;
        }
        tamanho--;
        modCount++;
        return elemento;
    }

    public E peekFirst() {
        return primeiro == null ? null : primeiro.item;
    }

    public E peekLast() {
        return ultimo == null ? null : ultimo.item;
    }

    public boolean add(E e) {
        addLast(e);
        return true;
    }

    public void add(int indice, E elemento) {
        checarIndiceParaAdd(indice);
        if (indice == tamanho) {
            addLast(elemento);
        } else {
            No<E> noAtual = no(indice);
            No<E> noAnterior = noAtual.anterior;
            No<E> novo = new No<>(noAnterior, elemento, noAtual);
            noAtual.anterior = novo;
            if (noAnterior == null) {
                primeiro = novo;
            } else {
                noAnterior.proximo = novo;
            }
            tamanho++;
            modCount++;
        }
    }

    public E get(int indice) {
        checarIndice(indice);
        return no(indice).item;
    }

    public E remove(int indice) {
        checarIndice(indice);
        return unlink(no(indice));
    }

    public boolean remove(Object o) {
        for (No<E> atual = primeiro; atual != null; atual = atual.proximo) {
            if (equals(o, atual.item)) {
                unlink(atual);
                return true;
            }
        }
        return false;
    }

    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    public int indexOf(Object o) {
        int index = 0;
        for (No<E> atual = primeiro; atual != null; atual = atual.proximo) {
            if (equals(o, atual.item)) return index;
            index++;
        }
        return -1;
    }

    public void clear() {
        No<E> atual = primeiro;
        while (atual != null) {
            No<E> proximo = atual.proximo;
            atual.item = null;
            atual.anterior = null;
            atual.proximo = null;
            atual = proximo;
        }
        primeiro = ultimo = null;
        tamanho = 0;
        modCount++;
    }

    /**
     * Retorna uma cópia profunda da lista atual.
     *
     * @return uma nova instância de MinhaListaLigada com os mesmos elementos (mas nós independentes)
     */
    @Override
    public Object clone() {
        try {
            @SuppressWarnings("unchecked")
            ListaEncadeada<E> clone = (ListaEncadeada<E>) super.clone();
            clone.primeiro = null;
            clone.ultimo = null;
            clone.tamanho = 0;
            clone.modCount = 0;

            for (No<E> atual = this.primeiro; atual != null; atual = atual.proximo) {
                clone.addLast(atual.item);
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new InternalError(e);
        }
    }


    public MeuIteradorDeLista iterador() {
        return new MeuIteradorDeLista();
    }

    private E unlink(No<E> no) {
        E elemento = no.item;
        No<E> ant = no.anterior;
        No<E> prox = no.proximo;

        if (ant == null) {
            primeiro = prox;
        } else {
            ant.proximo = prox;
            no.anterior = null;
        }

        if (prox == null) {
            ultimo = ant;
        } else {
            prox.anterior = ant;
            no.proximo = null;
        }

        no.item = null;
        tamanho--;
        modCount++;
        return elemento;
    }

    private No<E> no(int indice) {
        checarIndice(indice);
        if (indice < (tamanho / 2)) {
            No<E> atual = primeiro;
            for (int i = 0; i < indice; i++) {
                atual = atual.proximo;
            }
            return atual;
        } else {
            No<E> atual = ultimo;
            for (int i = tamanho - 1; i > indice; i--) {
                atual = atual.anterior;
            }
            return atual;
        }
    }

    private void checarIndice(int indice) {
        if (indice < 0 || indice >= tamanho)
            throw new IndexOutOfBoundsException("Índice inválido: " + indice);
    }

    private void checarIndiceParaAdd(int indice) {
        if (indice < 0 || indice > tamanho)
            throw new IndexOutOfBoundsException("Índice para adicionar inválido: " + indice);
    }

    private boolean equals(Object o1, Object o2) {
        return (o1 == null) ? o2 == null : o1.equals(o2);
    }

    /** Iterador bidirecional customizado com suporte a remoção. */
    public class MeuIteradorDeLista {
        private No<E> proximo = primeiro;
        private No<E> anterior = null;
        private No<E> ultimoRetornado = null;
        private int esperadoModCount = modCount;

        public boolean temProximo() {
            checarComodificacao();
            return proximo != null;
        }

        public E proximo() {
            checarComodificacao();
            if (proximo == null) throw new IllegalStateException("Sem próximo.");
            ultimoRetornado = proximo;
            E valor = proximo.item;
            anterior = proximo;
            proximo = proximo.proximo;
            return valor;
        }

        public boolean temAnterior() {
            checarComodificacao();
            return anterior != null;
        }

        public E anterior() {
            checarComodificacao();
            if (anterior == null) throw new IllegalStateException("Sem anterior.");
            ultimoRetornado = anterior;
            E valor = anterior.item;
            proximo = anterior;
            anterior = anterior.anterior;
            return valor;
        }

        /**
         * Remove o último elemento retornado por proximo() ou anterior().
         */
        public void remove() {
            checarComodificacao();
            if (ultimoRetornado == null) {
                throw new IllegalStateException("remove() chamado indevidamente.");
            }
            ListaEncadeada.this.unlink(ultimoRetornado);
            if (ultimoRetornado == anterior) {
                anterior = anterior.anterior;
            } else {
                proximo = proximo.proximo;
            }
            ultimoRetornado = null;
            esperadoModCount = modCount;
        }

        private void checarComodificacao() {
            if (modCount != esperadoModCount) {
                throw new IllegalStateException("Lista modificada durante iteração.");
            }
        }
    }
}
