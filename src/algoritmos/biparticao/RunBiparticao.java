package algoritmos.biparticao;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.leitoDeArquivo.LeitorDeGrafo;
import util.lista.ListaEncadeada;

public class RunBiparticao {

    public static void main(String[] args) {

        // --- Cenário 1: Grafo Bipartido ---
        System.out.println("--- TESTANDO GRAFO QUE  BIPARTIDO  ---");
        String arquivoBipartido = "src/algoritmos/biparticao/dados_de_entrada/grafo_bipartido.txt";
        Grafo<String> grafoBipartido = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoBipartido);

        if (grafoBipartido != null) {
            VerificadorBiparticao<String> verificador1 = new VerificadorBiparticao<>(grafoBipartido);
            // Chama o novo método para obter as partições
            ListaEncadeada<HashSet<String>> particoes1 = verificador1.obterParticoes();
            // Imprime o resultado de forma organizada
            imprimirParticoes(arquivoBipartido, particoes1);
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoBipartido);
        }
        System.out.println("----------------------------------------\n");


        // --- Cenário 2: Grafo Não Bipartido ---
        System.out.println("--- TESTANDO GRAFO QUE NÃO  BIPARTIDO  ---");
        String arquivoNaoBipartido = "src/algoritmos/biparticao/dados_de_entrada/grafo_nao_bipartido.txt";
        Grafo<String> grafoNaoBipartido = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoNaoBipartido);

        if (grafoNaoBipartido != null) {
            VerificadorBiparticao<String> verificador2 = new VerificadorBiparticao<>(grafoNaoBipartido);
            // Chama o método e espera um resultado null
            ListaEncadeada<HashSet<String>> particoes2 = verificador2.obterParticoes();
            imprimirParticoes(arquivoNaoBipartido, particoes2);
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoNaoBipartido);
        }
        System.out.println("----------------------------------------\n");


        // --- Cenário 3: Grafo Desconectado e Bipartido ---
        System.out.println("--- TESTANDO GRAFO DESCONECTADO E BIPARTIDO  ---");
        String arquivoDesconectado = "src/algoritmos/biparticao/dados_de_entrada/grafo_desconectado.txt";
        Grafo<String> grafoDesconectado = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoDesconectado);

        if (grafoDesconectado != null) {
            VerificadorBiparticao<String> verificador3 = new VerificadorBiparticao<>(grafoDesconectado);
            ListaEncadeada<HashSet<String>> particoes3 = verificador3.obterParticoes();
            imprimirParticoes(arquivoDesconectado, particoes3);
        } else {
            System.out.println("Falha ao carregar o arquivo: " + arquivoDesconectado);
        }
        System.out.println("----------------------------------------\n");
    }

    /**
     * @param nomeArquivo O nome do arquivo testado.
     * @param particoes A lista de partições retornada pelo algoritmo. Pode ser null.
     */
    private static <V> void imprimirParticoes(String nomeArquivo, ListaEncadeada<HashSet<V>> particoes) {
        if (particoes != null) {
            System.out.println("O grafo '" + nomeArquivo + "' é bipartido. Partições encontradas:");

            for (int i = 0; i < particoes.size(); i++) {
                HashSet<V> particao = particoes.get(i);
                System.out.print("  Partição " + (i + 1) + ": { ");

                HashSet<V>.MeuIteradorDeHashSet iterador = particao.iterator();
                while (iterador.hasNext()) {
                    System.out.print(iterador.next() + " ");
                }
                System.out.println("}");
            }
        } else {
            System.out.println("O grafo '" + nomeArquivo + "' não é bipartido (nenhuma partição encontrada).");
        }
    }
}