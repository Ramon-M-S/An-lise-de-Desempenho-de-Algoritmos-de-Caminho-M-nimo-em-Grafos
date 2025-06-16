package algoritmos.hamiltoniano_backtracking;

import util.leitoDeArquivo.LeitorDeGrafo; // 1. Importar a classe
import util.lista.ListaEncadeada;
import graph_structure.Grafo;

public class RunBacktracking {
    public static void main(String[] args) {

        // --- Cenário 1: Grafo com Circuito Hamiltoniano (lido de arquivo) ---
        System.out.println("--- TESTANDO GRAFO COM CIRCUITO ---");
        String arquivoCircuito = "grafo_hamiltoniano_circuito.txt";
        System.out.println("Carregando grafo de: " + arquivoCircuito);
        // 2. Carregar o grafo do arquivo
        Grafo<String> grafoComCircuito = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoCircuito);

        if (grafoComCircuito != null) {
            Backtracking<String> h1 = new Backtracking<>(grafoComCircuito);
            ListaEncadeada<String> circuito = h1.encontrarCircuito();
            imprimirCaminho("Circuito Hamiltoniano encontrado", circuito);
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoCircuito);
        }
        System.out.println("-------------------------------------\n");


        // --- Cenário 2: Grafo com Caminho, mas sem Circuito (lido de arquivo) ---
        System.out.println("--- TESTANDO GRAFO COM CAMINHO, MAS SEM CIRCUITO ---");
        String arquivoCaminho = "grafo_hamiltoniano_caminho.txt";
        System.out.println("Carregando grafo de: " + arquivoCaminho);
        // 2. Carregar o grafo do arquivo
        Grafo<String> grafoComCaminho = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoCaminho);

        if(grafoComCaminho != null) {
            Backtracking<String> h2 = new Backtracking<>(grafoComCaminho);

            ListaEncadeada<String> caminho = h2.encontrarCaminho();
            imprimirCaminho("Caminho Hamiltoniano encontrado", caminho);

            ListaEncadeada<String> circuito2 = h2.encontrarCircuito();
            if(circuito2.isEmpty()){
                System.out.println("Teste de circuito: Nenhum circuito encontrado (Correto)");
            } else {
                imprimirCaminho("Teste de circuito: Circuito encontrado (Inesperado)", circuito2);
            }
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoCaminho);
        }
        System.out.println("-------------------------------------\n");
    }

    private static <V> void imprimirCaminho(String mensagem, ListaEncadeada<V> caminho) {
        if (caminho.isEmpty()) {
            System.out.println(mensagem + ": Nenhum encontrado.");
            return;
        }
        System.out.print(mensagem + ": ");
        for (int i = 0; i < caminho.size(); i++) {
            System.out.print(caminho.get(i) + (i == caminho.size() - 1 ? "" : " -> "));
        }
        System.out.println();
    }
}