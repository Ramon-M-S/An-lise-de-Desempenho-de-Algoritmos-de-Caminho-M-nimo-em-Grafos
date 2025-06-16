package graph_structure;


import util.hashSet.HashSet;
import util.lista.ListaEncadeada;
import util.tabelaHash.TabelaHash;

public class Grafo<V> {

    public static record Aresta<V>(V origem, V destino, double peso) {}
    public static record Vizinho<V>(V no, double peso) {}

    private final TabelaHash<V, ListaEncadeada<Vizinho<V>>> adjacencias;
    private final HashSet<V> vertices;
    private final ListaEncadeada<Aresta<V>> arestas;
    private final boolean direcionado;

    public Grafo(boolean direcionado) {
        this.direcionado = direcionado;
        this.adjacencias = new TabelaHash<>();
        this.vertices = new HashSet<>();
        this.arestas = new ListaEncadeada<>();
    }

    public void adicionarVertice(V vertice) {
        if (!vertices.contains(vertice)) {
            vertices.add(vertice);
            adjacencias.put(vertice, new ListaEncadeada<>());
        }
    }

    public void adicionarAresta(V origem, V destino) {
        adicionarAresta(origem, destino, 1.0);
    }

    public void adicionarAresta(V origem, V destino, double peso) {
        // 1. Garante que os vértices existem no grafo.
        adicionarVertice(origem);
        adicionarVertice(destino);

        // 2. Adiciona a aresta (origem -> destino) apenas se ela ainda não existir.
        Vizinho<V> vizinhoFrente = new Vizinho<>(destino, peso);
        if (!adjacencias.get(origem).contains(vizinhoFrente)) {
            adjacencias.get(origem).add(vizinhoFrente);
        }

        // A lista global de arestas pode conter duplicatas se o arquivo de origem as tiver.
        // Isso é geralmente aceitável e não afeta os algoritmos de travessia.
        arestas.add(new Aresta<>(origem, destino, peso));

        // 3. Se o grafo não for direcionado, adiciona a aresta de volta (destino -> origem)
        //    apenas se ela também não existir.
        if (!this.direcionado) {
            Vizinho<V> vizinhoTras = new Vizinho<>(origem, peso);
            if (!adjacencias.get(destino).contains(vizinhoTras)) {
                adjacencias.get(destino).add(vizinhoTras);
            }
        }
    }

    public HashSet<V> getVertices() {
        return this.vertices;
    }

    public ListaEncadeada<Aresta<V>> getArestas() {
        return this.arestas;
    }

    public ListaEncadeada<Vizinho<V>> getVizinhos(V vertice) {
        ListaEncadeada<Vizinho<V>> vizinhos = adjacencias.get(vertice);
        if (vizinhos == null) return new ListaEncadeada<>();
        return vizinhos;
    }

    public int getNumeroDeVertices() {
        return this.vertices.size();
    }

    public int getNumeroDeArestas() {
        return this.arestas.size();
    }

    public boolean isDirecionado() {
        return this.direcionado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Grafo (Direcionado: ").append(direcionado).append(")\n");
        sb.append("Vértices: ").append(getNumeroDeVertices()).append(", Arestas: ").append(getNumeroDeArestas()).append("\n");

        ListaEncadeada<V> todasAsChaves = adjacencias.keySet();
        ListaEncadeada<V>.MeuIteradorDeLista iteradorVertices = todasAsChaves.iterador();

        while (iteradorVertices.temProximo()) {
            V vertice = iteradorVertices.proximo();
            sb.append(vertice).append(" -> ");
            ListaEncadeada<Vizinho<V>> vizinhos = getVizinhos(vertice);
            ListaEncadeada<Vizinho<V>>.MeuIteradorDeLista iteradorVizinhos = vizinhos.iterador();

            while (iteradorVizinhos.temProximo()) {
                Vizinho<V> vizinho = iteradorVizinhos.proximo();
                sb.append(vizinho.no()).append(" (").append(vizinho.peso()).append(") ");
            }
            sb.append("\n");
        }

        return sb.toString();
    }

}
