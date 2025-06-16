package util.leitoDeArquivo;

import graph_structure.Grafo;
import graph_structure.Grafo.Aresta;
import util.hashSet.HashSet;
import util.lista.ListaEncadeada;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorDeGrafo {

    public static Grafo<String> carregarGrafoComDeteccao(String caminhoArquivo) {
        ListaEncadeada<Aresta<String>> listaArestas = new ListaEncadeada<>();

        try (BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo))) {
            String linha;
            while ((linha = leitor.readLine()) != null) {
                linha = linha.trim();
                if (linha.isEmpty() || linha.startsWith("#")) continue;

                String[] partes = linha.split("\\s+");
                if (partes.length < 3) continue;

                String origem = partes[0];
                String destino = partes[1];
                double peso = Double.parseDouble(partes[2]);

                listaArestas.add(new Aresta<>(origem, destino, peso));
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return null;
        }

        boolean direcionado = !ehNaoDirecionado(listaArestas);

        Grafo<String> grafo = new Grafo<>(direcionado);
        for (int i = 0; i < listaArestas.size(); i++) {
            Aresta<String> aresta = listaArestas.get(i);
            grafo.adicionarAresta(aresta.origem(), aresta.destino(), aresta.peso());
        }

        System.out.println("Tipo detectado: " + (direcionado ? "Direcionado" : "Não-Direcionado"));
        return grafo;
    }

    private static boolean ehNaoDirecionado(ListaEncadeada<Aresta<String>> arestas) {
        // Usando o seu HashSet customizado
        HashSet<String> arestasSemPar = new HashSet<>();

        for (int i = 0; i < arestas.size(); i++) {
            Aresta<String> aresta = arestas.get(i);
            String chaveDireta = aresta.origem() + "->" + aresta.destino();
            String chaveInversa = aresta.destino() + "->" + aresta.origem();

            if (arestasSemPar.contains(chaveInversa)) {
                arestasSemPar.remove(chaveInversa);
            } else {
                arestasSemPar.add(chaveDireta);
            }
        }

        return arestasSemPar.isEmpty();
    }
}
