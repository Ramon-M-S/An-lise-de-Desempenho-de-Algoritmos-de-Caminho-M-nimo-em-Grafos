package algoritmos.tarjan_componentes_fortemente_conexos;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.leitoDeArquivo.LeitorDeGrafo; // 1. Importar a classe
import util.lista.ListaEncadeada;

public class RunTarjan {
    public static void main(String[] args) {

        System.out.println("--- Executando Algoritmo de Tarjan para encontrar SCCs (com arquivo) ---");
        String arquivoTarjan = "src/algoritmos/tarjan_componentes_fortemente_conexos/grafo_tarjan.txt";
        System.out.println("Carregando grafo de: " + arquivoTarjan);

        // 2. Carregar o grafo do arquivo. Note que o tipo agora é String.
        Grafo<String> grafo = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoTarjan);

        if (grafo == null) {
            System.out.println("Falha ao carregar o arquivo: " + arquivoTarjan);
            return;
        }

        // O restante do código permanece, mas com o tipo <String>
        Tarjan<String> tarjan = new Tarjan<>(grafo);
        ListaEncadeada<HashSet<String>> sccs = tarjan.encontrarSccs();

        System.out.println("Número de Componentes Fortemente Conexos encontrados: " + sccs.size());

        for (int i = 0; i < sccs.size(); i++) {
            System.out.print("Componente " + (i + 1) + ": { ");
            HashSet<String> componente = sccs.get(i);

            HashSet<String>.MeuIteradorDeHashSet iterador = componente.iterator();
            while (iterador.hasNext()) {
                System.out.print(iterador.next() + " ");
            }
            System.out.println("}");
        }
    }
}