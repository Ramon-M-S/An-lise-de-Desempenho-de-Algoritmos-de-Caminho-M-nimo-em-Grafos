package algoritmos.ordenacao_topologica.ordenacao_topologicaDFS;

import graph_structure.Grafo;
import util.leitoDeArquivo.LeitorDeGrafo; // 1. Importar a classe
import util.lista.ListaEncadeada;

public class RunTopologicoDFS {
    public static void main(String[] args) {

        // Cenário 1: Grafo Acíclico Direcionado (DAG)
        System.out.println("--- TESTANDO ORDENAÇÃO TOPOLÓGICA (DFS) EM UM DAG ---");
        String arquivoDag = "src/algoritmos/ordenacao_topologica/dadosDeEntrada_dag.txt";
        System.out.println("Carregando grafo de: " + arquivoDag);
        // 2. Carregar o grafo do arquivo
        Grafo<String> dag = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoDag);

        if (dag != null) {
            OrdenacaoTopologicaDFS<String> topologicoDFS = new OrdenacaoTopologicaDFS<>(dag);
            ListaEncadeada<String> ordem = topologicoDFS.ordenar();

            System.out.print("Ordem topológica encontrada: ");
            for (int i = 0; i < ordem.size(); i++) {
                System.out.print(ordem.get(i) + " ");
            }
            System.out.println();
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoDag);
        }
        System.out.println("------------------------------------------------\n");


        // Cenário 2: Grafo com Ciclo
        System.out.println("--- TESTANDO DETECÇÃO DE CICLO (DFS) ---");
        String arquivoCiclico = "src/algoritmos/ordenacao_topologica/dadosDeEntrada_ciclico.txt";
        System.out.println("Carregando grafo de: " + arquivoCiclico);
        // 2. Carregar o grafo do arquivo
        Grafo<String> grafoComCiclo = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoCiclico);

        if (grafoComCiclo != null) {
            OrdenacaoTopologicaDFS<String> topologicoCiclo = new OrdenacaoTopologicaDFS<>(grafoComCiclo);
            ListaEncadeada<String> ordemCiclo = topologicoCiclo.ordenar();

            if (ordemCiclo.isEmpty()) {
                System.out.println("O resultado foi uma lista vazia, indicando que um ciclo foi detectado corretamente.");
            } else {
                System.out.println("Algo deu errado, o ciclo não foi detectado.");
            }
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoCiclico);
        }
        System.out.println("------------------------------------------------\n");
    }
}