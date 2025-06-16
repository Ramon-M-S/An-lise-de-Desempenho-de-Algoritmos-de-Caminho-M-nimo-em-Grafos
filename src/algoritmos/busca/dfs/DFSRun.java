package algoritmos.busca.dfs;

import graph_structure.Grafo;
import util.leitoDeArquivo.LeitorDeGrafo; // 1. Importar a classe

public class DFSRun {
    public static void main(String[] args) {

        // --- ETAPA 1: Carregar o grafo a partir de um arquivo ---
        String caminhoArquivo = "src/algoritmos/busca/dadosDeEntrada.txt";
        System.out.println("--- Carregando Grafo do Arquivo: " + caminhoArquivo + " ---");

        // 2. Utilizar o LeitorDeGrafo para carregar o grafo.
        // Ele detectará que o grafo é direcionado pela ausência de arestas de volta.
        Grafo<String> grafo = LeitorDeGrafo.carregarGrafoComDeteccao(caminhoArquivo);

        // Verifica se o grafo foi carregado com sucesso
        if (grafo == null) {
            System.out.println("Falha ao carregar o grafo. Verifique o caminho e o conteúdo do arquivo.");
            return;
        }
        System.out.println("Grafo carregado com sucesso.\n");


        // --- ETAPA 2: Executar a Busca em Profundidade (DFS) ---
        String verticeInicial = "A";
        System.out.println("--- Executando DFS (Iterativo) a partir do vértice '" + verticeInicial + "' ---");

        // O restante do código permanece o mesmo
        DFS<String> dfs = new DFS<>(grafo);
        dfs.executarIterativo(verticeInicial);

        System.out.println("---------------------------------------------------------");
    }
}