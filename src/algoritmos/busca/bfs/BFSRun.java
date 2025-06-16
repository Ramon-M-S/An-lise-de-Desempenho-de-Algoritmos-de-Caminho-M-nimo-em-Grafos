package algoritmos.busca.bfs;

import graph_structure.Grafo;
import util.leitoDeArquivo.LeitorDeGrafo;
import util.lista.ListaEncadeada;

public class BFSRun {
    public static void main(String[] args) {

        // --- ETAPA 1: Carregar o grafo a partir de um arquivo ---
        String caminhoArquivo = "src/algoritmos/busca/dadosDeEntrada.txt";
        System.out.println("--- Carregando Grafo do Arquivo: " + caminhoArquivo + " ---");

        Grafo<String> grafo = LeitorDeGrafo.carregarGrafoComDeteccao(caminhoArquivo);

        if (grafo == null) {
            System.out.println("Falha ao carregar o grafo.");
            return;
        }
        System.out.println("Grafo carregado com sucesso.\n");

        // --- ETAPA 2: Executar a Busca e Obter a Lista da Travessia ---
        String verticeInicial = "A";
        System.out.println("--- Executando BFS a partir de '" + verticeInicial + "' para obter a lista da travessia ---");

        BFS<String> bfs = new BFS<>(grafo);

        // 1. Chame o novo método getTravessia() e guarde a lista retornada
        ListaEncadeada<String> ordemDaTravessia = bfs.getTravessia(verticeInicial);

        // 2. Imprima a lista resultante
        System.out.println("\nOrdem de travessia (BFS):");
        System.out.println(ordemDaTravessia);

    }
}