package algoritmos.hierholzer_circuito_euleriano;

import graph_structure.Grafo;
import util.leitoDeArquivo.LeitorDeGrafo;
import util.lista.ListaEncadeada;

public class RunHierholzer {
    public static void main(String[] args) {

        // --- Cenário 1: Grafo Euleriano Clássico (lido de arquivo) ---
        System.out.println("--- TESTANDO GRAFO EULERIANO ---");
        String arquivoEuleriano = "src/algoritmos/hierholzer_circuito_euleriano/grafo_euleriano.txt";
        System.out.println("Carregando grafo de: " + arquivoEuleriano);
        // 2. Carregar o grafo do arquivo
        Grafo<String> grafoEuleriano = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoEuleriano);

        if (grafoEuleriano != null) {
            // Note que Hierholzer agora é instanciado com <String>
            Hierholzer<String> h1 = new Hierholzer<>(grafoEuleriano);
            ListaEncadeada<String> circuito1 = h1.encontrarCircuito();
            imprimirCircuito("Circuito encontrado", circuito1);
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoEuleriano);
        }
        System.out.println("-------------------------------------\n");


        // --- Cenário 2: Grafo Não-Euleriano (lido de arquivo) ---
        System.out.println("--- TESTANDO GRAFO NÃO-EULERIANO (GRAU ÍMPAR) ---");
        String arquivoNaoEuleriano = "src/algoritmos/hierholzer_circuito_euleriano/grafo_nao_euleriano.txt";
        System.out.println("Carregando grafo de: " + arquivoNaoEuleriano);
        // 2. Carregar o grafo do arquivo
        Grafo<String> grafoNaoEuleriano = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoNaoEuleriano);

        if (grafoNaoEuleriano != null) {
            Hierholzer<String> h2 = new Hierholzer<>(grafoNaoEuleriano);
            ListaEncadeada<String> circuito2 = h2.encontrarCircuito();

            if (circuito2.isEmpty()) {
                System.out.println("Resultado: O algoritmo corretamente não encontrou um circuito.");
            } else {
                imprimirCircuito("Circuito encontrado (Inesperado)", circuito2);
            }
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoNaoEuleriano);
        }
        System.out.println("-------------------------------------\n");
    }

    private static <V> void imprimirCircuito(String mensagem, ListaEncadeada<V> circuito) {
        if (circuito.isEmpty()) {
            System.out.println(mensagem + ": Nenhum encontrado.");
            return;
        }
        System.out.print(mensagem + ": ");
        for (int i = 0; i < circuito.size(); i++) {
            System.out.print(circuito.get(i) + (i == circuito.size() - 1 ? "" : " -> "));
        }
        System.out.println();
    }
}