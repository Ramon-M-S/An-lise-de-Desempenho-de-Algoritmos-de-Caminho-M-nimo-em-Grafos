package algoritmos.conectividade;

import graph_structure.Grafo;
import util.hashSet.HashSet;
import util.leitoDeArquivo.LeitorDeGrafo; // 1. Importar a classe
import util.lista.ListaEncadeada;

public class RunConectividade {
    public static void main(String[] args) {

        // --- Cenário 1: Grafo Conectado (lido de arquivo) ---
        System.out.println("--- INICIANDO TESTE COM GRAFO CONECTADO ---");
        String arquivoConexo = "src/algoritmos/conectividade/grafo_conexo.txt";
        System.out.println("Carregando grafo de: " + arquivoConexo);
        // 2. Carregar o grafo
        Grafo<String> grafoConexo = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoConexo);

        if (grafoConexo != null) {
            System.out.println("Grafo Conectado carregado.");
            VerificadorConectividade<String> verificadorConexo = new VerificadorConectividade<>(grafoConexo);

            boolean conectado = verificadorConexo.isConexo();
            System.out.println("Verificando se o grafo é conexo...");
            System.out.println("Resultado: " + conectado);

            if (conectado) {
                System.out.println("SAÍDA ESPERADA: O teste passou!");
            } else {
                System.out.println("SAÍDA INESPERADA: O teste falhou!");
            }
        } else {
            System.out.println("Falha ao carregar " + arquivoConexo);
        }
        System.out.println("---------------------------------------------\n");


        // --- Cenário 2: Grafo Desconectado (lido de arquivo) ---
        System.out.println("--- INICIANDO TESTE COM GRAFO DESCONECTADO ---");
        String arquivoDesconexo = "src/algoritmos/conectividade/grafo_desconectado.txt";
        System.out.println("Carregando grafo de: " + arquivoDesconexo);
        // 2. Carregar o grafo
        Grafo<String> grafoDesconectado = LeitorDeGrafo.carregarGrafoComDeteccao(arquivoDesconexo);

        if (grafoDesconectado != null) {
            System.out.println("Grafo Desconectado carregado.");
            VerificadorConectividade<String> verificadorDesconectado = new VerificadorConectividade<>(grafoDesconectado);

            boolean desconectado = verificadorDesconectado.isConexo();
            System.out.println("Verificando se o grafo é conexo...");
            System.out.println("Resultado: " + desconectado);

            if (!desconectado) {
                System.out.println("SAÍDA ESPERADA: O teste passou, o grafo não é conexo.");
            } else {
                System.out.println("SAÍDA INESPERADA: O teste falhou!");
            }

            System.out.println("\nEncontrando os componentes conexos...");
            ListaEncadeada<HashSet<String>> componentes = verificadorDesconectado.encontrarComponentesConexos();

            System.out.println("Número de componentes encontrados: " + componentes.size());

            for (int i = 0; i < componentes.size(); i++) {
                HashSet<String> componente = componentes.get(i);
                System.out.print("Componente " + (i + 1) + ": { ");

                HashSet<String>.MeuIteradorDeHashSet iterador = componente.iterator();
                while (iterador.hasNext()) {
                    System.out.print(iterador.next() + " ");
                }
                System.out.println("}");
            }
        } else {
            System.out.println("Falha ao carregar " + arquivoDesconexo);
        }
        System.out.println("---------------------------------------------");
    }
}