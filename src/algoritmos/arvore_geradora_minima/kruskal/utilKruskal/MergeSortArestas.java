package algoritmos.arvore_geradora_minima.kruskal.utilKruskal;

import graph_structure.Grafo.Aresta;
import util.lista.ListaEncadeada;

public class MergeSortArestas<V> {

    public void sort(ListaEncadeada<Aresta<V>> lista) {
        if (lista == null || lista.size() <= 1) {
            return; // Já está ordenada
        }
        mergeSort(lista, 0, lista.size() - 1);
    }

    private void mergeSort(ListaEncadeada<Aresta<V>> lista, int esquerda, int direita) {
        if (esquerda < direita) {
            int meio = esquerda + (direita - esquerda) / 2;
            mergeSort(lista, esquerda, meio);
            mergeSort(lista, meio + 1, direita);
            merge(lista, esquerda, meio, direita);
        }
    }

    @SuppressWarnings("unchecked")
    private void merge(ListaEncadeada<Aresta<V>> lista, int esquerda, int meio, int direita) {
        int n1 = meio - esquerda + 1;
        int n2 = direita - meio;

        // Cria arrays temporários
        Aresta<V>[] L = (Aresta<V>[]) new Aresta[n1];
        Aresta<V>[] R = (Aresta<V>[]) new Aresta[n2];

        // Copia os dados para os arrays temporários
        for (int i = 0; i < n1; ++i) L[i] = lista.get(esquerda + i);
        for (int j = 0; j < n2; ++j) R[j] = lista.get(meio + 1 + j);

        int i = 0, j = 0, k = esquerda;
        while (i < n1 && j < n2) {
            if (L[i].peso() <= R[j].peso()) {
                lista.set(k, L[i]);
                i++;
            } else {
                lista.set(k, R[j]);
                j++;
            }
            k++;
        }

        // Copia os elementos restantes de L[] e R[] se houver
        while (i < n1) lista.set(k++, L[i++]);
        while (j < n2) lista.set(k++, R[j++]);
    }
}