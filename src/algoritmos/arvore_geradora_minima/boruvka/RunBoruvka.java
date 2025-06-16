package algoritmos.arvore_geradora_minima.boruvka;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.leitoDeArquivo.LeitorDeGrafo; // 1. Importar a classe de leitura
import util.lista.ListaEncadeada;

public class RunBoruvka {
    public static void main(String[] args) {

        // --- ETAPA 1: Carregar o grafo a partir de um arquivo ---
        // Especifique o caminho para o seu arquivo de grafo.
        // O arquivo deve estar em um local acessível pelo programa.
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


        // --- ETAPA 2: Executar o algoritmo de Borůvka ---
        System.out.println("--- Executando Algoritmo de Borůvka ---");
        Boruvka<String> boruvka = new Boruvka<>(grafo);
        ListaEncadeada<Grafo.Aresta<String>> mst = boruvka.encontrarMST();


        // --- ETAPA 3: Exibir os resultados da MST ---
        System.out.println("\nArestas da Árvore Geradora Mínima (MST):");

        // HashSet para garantir que as arestas sejam impressas/somadas apenas uma vez
        HashSet<String> arestasUnicas = new HashSet<>();
        double custoTotal = 0;

        for (int i = 0; i < mst.size(); i++) {
            Grafo.Aresta<String> aresta = mst.get(i);
            String u = aresta.origem();
            String v = aresta.destino();

            // Cria uma chave canônica para a aresta (ex: "A-B" é o mesmo que "B-A")
            // para evitar a impressão de arestas duplicadas em grafos não direcionados.
            String chave = u.compareTo(v) < 0 ? u + "-" + v : v + "-" + u;

            if (!arestasUnicas.contains(chave)) {
                System.out.println(u + " - " + v + " (Peso: " + aresta.peso() + ")");
                custoTotal += aresta.peso();
                arestasUnicas.add(chave);
            }
        }

        System.out.println("\nCusto total da MST: " + custoTotal);
    }
}