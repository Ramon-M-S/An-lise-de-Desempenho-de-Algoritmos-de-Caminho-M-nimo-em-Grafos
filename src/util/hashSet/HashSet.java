package util.hashSet;

import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

/**
 * Estrutura de dados tipo conjunto (Set), baseada em tabela de hash com encadeamento separado.
 * Não permite elementos duplicados e não preserva ordem de inserção.
 *
 * @param <E> o tipo dos elementos armazenados no conjunto
 */
public class HashSet<E> {

    /** Nó da lista ligada que representa os elementos de um balde. */
    private static class No<E> {
        final int hash;
        final E chave;
        No<E> proximo;

        No(int hash, E chave, No<E> proximo) {
            this.hash = hash;
            this.chave = chave;
            this.proximo = proximo;
        }
    }

    private static final int CAPACIDADE_INICIAL = 16;
    private static final float FATOR_DE_CARGA_PADRAO = 0.75f;

    private transient No<E>[] tabela;
    private int tamanho;
    private int limiar;
    private final float fatorDeCarga;

    protected transient int modCount = 0;

    /** Construtor padrão: capacidade inicial 16, fator de carga 0.75. */
    public HashSet() {
        this(CAPACIDADE_INICIAL, FATOR_DE_CARGA_PADRAO);
    }

    /**
     * Construtor com capacidade e fator de carga personalizados.
     * Garante que a capacidade seja uma potência de dois.
     *
     * @param capacidadeInicial capacidade inicial solicitada
     * @param fatorDeCarga fator de carga
     */
    @SuppressWarnings("unchecked")
    public HashSet(int capacidadeInicial, float fatorDeCarga) {
        if (capacidadeInicial <= 0 || fatorDeCarga <= 0 || Float.isNaN(fatorDeCarga))
            throw new IllegalArgumentException("Capacidade e fator de carga devem ser positivos.");

        this.fatorDeCarga = fatorDeCarga;
        int capacidade = 1;
        while (capacidade < capacidadeInicial) {
            capacidade <<= 1; // próxima potência de 2
        }

        this.tabela = (No<E>[]) new No[capacidade];
        this.limiar = (int) (capacidade * fatorDeCarga);
    }

    public boolean add(E elemento) {
        int hash = hash(elemento);
        int indice = index(hash, tabela.length);
        for (No<E> atual = tabela[indice]; atual != null; atual = atual.proximo) {
            if (atual.hash == hash && equals(elemento, atual.chave)) {
                return false;
            }
        }
        tabela[indice] = new No<>(hash, elemento, tabela[indice]);
        tamanho++;
        modCount++;
        if (tamanho > limiar) {
            resize();
        }
        return true;
    }

    public boolean contains(Object elemento) {
        int hash = hash(elemento);
        int indice = index(hash, tabela.length);
        for (No<E> atual = tabela[indice]; atual != null; atual = atual.proximo) {
            if (atual.hash == hash && equals(elemento, atual.chave)) {
                return true;
            }
        }
        return false;
    }

    public boolean remove(Object elemento) {
        int hash = hash(elemento);
        int indice = index(hash, tabela.length);
        No<E> anterior = null;
        for (No<E> atual = tabela[indice]; atual != null; anterior = atual, atual = atual.proximo) {
            if (atual.hash == hash && equals(elemento, atual.chave)) {
                if (anterior == null) {
                    tabela[indice] = atual.proximo;
                } else {
                    anterior.proximo = atual.proximo;
                }
                tamanho--;
                modCount++;
                return true;
            }
        }
        return false;
    }

    public int size() {
        return tamanho;
    }

    public boolean isEmpty() {
        return tamanho == 0;
    }

    @SuppressWarnings("unchecked")
    public void clear() {
        tabela = (No<E>[]) new No[CAPACIDADE_INICIAL];
        tamanho = 0;
        limiar = (int) (CAPACIDADE_INICIAL * fatorDeCarga);
        modCount++;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        No<E>[] antigaTabela = tabela;
        int novaCapacidade = antigaTabela.length * 2;
        No<E>[] novaTabela = (No<E>[]) new No[novaCapacidade];

        for (No<E> no : antigaTabela) {
            while (no != null) {
                No<E> proximo = no.proximo;
                int novoIndice = index(no.hash, novaCapacidade);
                no.proximo = novaTabela[novoIndice];
                novaTabela[novoIndice] = no;
                no = proximo;
            }
        }

        tabela = novaTabela;
        limiar = (int) (novaCapacidade * fatorDeCarga);
    }

    private int index(int hash, int capacidade) {
        return (capacidade - 1) & hash;
    }

    private int hash(Object chave) {
        if (chave == null) return 0;
        int h = chave.hashCode();
        return h ^ (h >>> 16);
    }

    private boolean equals(Object a, Object b) {
        return a == b || (a != null && a.equals(b));
    }

    /**
     * Retorna um iterador customizado para este conjunto.
     *
     * @return instância de MeuIteradorDeHashSet
     */
    public MeuIteradorDeHashSet iterator() {
        return new MeuIteradorDeHashSet();
    }

    /**
     * Iterador customizado e fail-fast para MeuHashSet.
     */
    public class MeuIteradorDeHashSet {
        private int indiceBaldeAtual = 0;
        private No<E> proximoNo;
        private final int esperadoModCount = modCount;

        public MeuIteradorDeHashSet() {
            avançarParaProximo();
        }

        private void avançarParaProximo() {
            while (indiceBaldeAtual < tabela.length && (proximoNo = tabela[indiceBaldeAtual++]) == null) {
                // apenas avança
            }
        }

        public boolean hasNext() {
            return proximoNo != null;
        }

        public E next() {
            if (modCount != esperadoModCount) {
                throw new ConcurrentModificationException("Conjunto modificado durante iteração.");
            }
            if (proximoNo == null) {
                throw new NoSuchElementException("Sem mais elementos.");
            }

            E resultado = proximoNo.chave;

            // Avança dentro do balde
            proximoNo = proximoNo.proximo;
            if (proximoNo == null) {
                avançarParaProximo();
            }

            return resultado;
        }
    }
}
