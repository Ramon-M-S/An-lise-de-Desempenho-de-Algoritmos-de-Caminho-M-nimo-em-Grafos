package util.union_find;


import util.hashSet.HashSet;
import util.tabelaHash.TabelaHash;

/**
 * Estrutura de dados Union-Find (Disjoint Set Union) com otimizações
 * de path compression e union by size.
 * @param <V> O tipo do elemento.
 */
public class UnionFind<V> {

    private final TabelaHash<V, V> pai;
    private final TabelaHash<V, Integer> tamanho;

    public UnionFind(HashSet<V> elementos) {
        pai = new TabelaHash<>();
        tamanho = new TabelaHash<>();

        HashSet<V>.MeuIteradorDeHashSet iterador = elementos.iterator();
        while(iterador.hasNext()) {
            V elemento = iterador.next();
            pai.put(elemento, elemento); // Cada elemento é seu próprio pai
            tamanho.put(elemento, 1);     // Tamanho inicial de cada conjunto é 1
        }
    }

    /**
     * Encontra o representante (raiz) do conjunto ao qual o elemento pertence.
     * Aplica a otimização de path compression.
     */
    public V find(V elemento) {
        V raiz = elemento;
        while (!raiz.equals(pai.get(raiz))) {
            raiz = pai.get(raiz);
        }
        // Path compression: aponta todos os nós no caminho diretamente para a raiz.
        V atual = elemento;
        while (!atual.equals(raiz)) {
            V proximo = pai.get(atual);
            pai.put(atual, raiz);
            atual = proximo;
        }
        return raiz;
    }

    /**
     * Une os conjuntos de dois elementos.
     * Aplica a otimização de union by size.
     */
    public void union(V a, V b) {
        V raizA = find(a);
        V raizB = find(b);

        if (!raizA.equals(raizB)) {
            // Une o conjunto menor ao maior.
            if (tamanho.get(raizA) < tamanho.get(raizB)) {
                pai.put(raizA, raizB);
                tamanho.put(raizB, tamanho.get(raizA) + tamanho.get(raizB));
            } else {
                pai.put(raizB, raizA);
                tamanho.put(raizA, tamanho.get(raizA) + tamanho.get(raizB));
            }
        }
    }
}