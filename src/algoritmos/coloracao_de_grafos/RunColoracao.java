package algoritmos.coloracao_de_grafos;

import graph_structure.Grafo;
import util.leitoDeArquivo.LeitorDeGrafo; // 1. Importar a classe
import util.lista.ListaEncadeada;
import util.tabelaHash.TabelaHash;

public class RunColoracao {
    public static void main(String[] args) {
        System.out.println("--- TESTANDO ALGORITMO DE COLORAÇÃO GULOSA (com arquivo) ---");

        // --- ETAPA 1: Carregar o grafo a partir de um arquivo ---
        String caminhoArquivo = "src/algoritmos/coloracao_de_grafos/dadosDeEntrada.txt";
        System.out.println("Carregando grafo de: " + caminhoArquivo);

        // 2. Utilizar o LeitorDeGrafo para carregar o grafo
        Grafo<String> grafo = LeitorDeGrafo.carregarGrafoComDeteccao(caminhoArquivo);

        // Verifica se o grafo foi carregado com sucesso
        if (grafo == null) {
            System.out.println("Falha ao carregar o grafo. Encerrando.");
            return;
        }
        System.out.println("Grafo carregado com sucesso.\n");


        // --- ETAPA 2: Executar o algoritmo de coloração (o resto do código não muda) ---
        ColoracaoGreedy<String> coloracao = new ColoracaoGreedy<>(grafo);
        TabelaHash<String, Integer> cores = coloracao.colorir();

        System.out.println("Cores atribuídas aos vértices:");

        // Imprimir o resultado
        ListaEncadeada<String> vertices = cores.keySet();
        ListaEncadeada<String>.MeuIteradorDeLista iterador = vertices.iterador();
        int maxCor = 0;

        while (iterador.temProximo()) {
            String vertice = iterador.proximo();
            int cor = cores.get(vertice);
            System.out.println("Vértice " + vertice + ": Cor " + cor);
            if (cor > maxCor) {
                maxCor = cor;
            }
        }

        // O número de cores é o valor da cor máxima (assumindo que as cores são 1, 2, 3...)
        System.out.println("\nNúmero total de cores usadas (resultado guloso): " + maxCor);
    }
}