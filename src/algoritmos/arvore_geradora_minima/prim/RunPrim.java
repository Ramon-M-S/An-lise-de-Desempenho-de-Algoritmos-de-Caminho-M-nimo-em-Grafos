package algoritmos.arvore_geradora_minima.prim;

import graph_structure.Grafo;
import util.leitoDeArquivo.LeitorDeGrafo; // 1. Importar a classe de leitura
import util.lista.ListaEncadeada;

public class RunPrim {
    public static void main(String[] args) {

        // --- ETAPA 1: Carregar o grafo a partir de um arquivo ---
        String caminhoArquivo = "src/algoritmos/arvore_geradora_minima/dadosDeEntrada.txt";
        System.out.println("--- Carregando Grafo do Arquivo: " + caminhoArquivo + " ---");

        // 2. Utilizar o LeitorDeGrafo para carregar o grafo
        Grafo<String> grafo = LeitorDeGrafo.carregarGrafoComDeteccao(caminhoArquivo);

        // Verifica se o grafo foi carregado com sucesso
        if (grafo == null) {
            System.out.println("Falha ao carregar o grafo. Verifique o caminho do arquivo e seu conteúdo.");
            return; // Encerra o programa se o grafo não for carregado
        }
        System.out.println("------------------------------------------\n");


        // --- ETAPA 2: Executar o algoritmo de Prim ---
        System.out.println("--- Executando Algoritmo de Prim ---");
        Prim<String> prim = new Prim<>(grafo);
        ListaEncadeada<Grafo.Aresta<String>> mst = prim.encontrarMST();


        // --- ETAPA 3: Exibir os resultados da MST ---
        double custoTotal = 0;
        System.out.println("\nArestas da Árvore Geradora Mínima:");
        for (int i = 0; i < mst.size(); i++) {
            Grafo.Aresta<String> aresta = mst.get(i);
            System.out.println(aresta.origem() + " - " + aresta.destino() + " (Peso: " + aresta.peso() + ")");
            custoTotal += aresta.peso();
        }
        System.out.println("\nCusto total da MST: " + custoTotal);
    }
}