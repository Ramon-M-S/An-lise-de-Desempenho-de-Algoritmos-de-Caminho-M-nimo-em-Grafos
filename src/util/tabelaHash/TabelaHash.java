package util.tabelaHash;

import util.lista.ListaEncadeada;

public class TabelaHash<K, V> {
    private int tamanho;
    private int elementosInseridos;
    private ListaEncadeada<Entrada<K, V>>[] tabela;

    public TabelaHash() {
        this(10);
    }

    public TabelaHash(int tamanho) {
        this.tamanho = tamanho;
        this.elementosInseridos = 0;
        this.tabela = new ListaEncadeada[tamanho];
        for (int i = 0; i < tamanho; i++) {
            tabela[i] = new ListaEncadeada<>();
        }
    }

    public void put(K chave, V valor) {
        inserir(chave, valor);
    }

    public V get(K chave) {
        return buscar(chave);
    }

    public boolean containsKey(K chave) {
        return contemChave(chave);
    }

    public ListaEncadeada<K> keySet() {
        ListaEncadeada<K> listaChaves = new ListaEncadeada<>();
        for (ListaEncadeada<Entrada<K, V>> lista : tabela) {
            ListaEncadeada<Entrada<K, V>>.MeuIteradorDeLista iterador = lista.iterador();
            while (iterador.temProximo()) {
                Entrada<K, V> entrada = iterador.proximo();
                listaChaves.add(entrada.getChave());
            }
        }
        return listaChaves;
    }

    public void inserir(K chave, V valor) {
        int indice = funcaoHash(chave, tamanho);
        ListaEncadeada<Entrada<K, V>> listaDoIndice = tabela[indice];

        // Itera pela lista para verificar se a chave já existe
        ListaEncadeada<Entrada<K, V>>.MeuIteradorDeLista iterador = listaDoIndice.iterador();
        while (iterador.temProximo()) {
            Entrada<K, V> entrada = iterador.proximo();
            if (entrada.getChave().equals(chave)) {
                // Se a chave já existe, ATUALIZA o valor e encerra o método.
                entrada.setValor(valor);
                return;
            }
        }

        // Se o loop terminar, a chave não foi encontrada.
        // Então, INSERE uma nova entrada.
        Entrada<K, V> novaEntrada = new Entrada<>(chave, valor);
        listaDoIndice.add(novaEntrada);
        elementosInseridos++;

        // Verifica se precisa redimensionar a tabela.
        if ((double) elementosInseridos / tamanho >= 0.7) {
            redimensionarTabela();
        }
    }

    private int funcaoHash(K chave, int tamanho) {
        double A = 0.6180339887;
        if (chave instanceof Integer) {
            int chaveInt = (Integer) chave;
            return Math.abs((int) (tamanho * ((chaveInt * A) % 1)));
        } else if (chave instanceof String) {
            String chaveString = (String) chave;
            int soma = 0;
            for (int i = 0; i < chaveString.length(); i++) {
                soma += chaveString.charAt(i);
            }
            return Math.abs((int) (tamanho * ((soma * A) % 1)));
        } else {
            throw new IllegalArgumentException("Chave inválida.");
        }
    }


    private void redimensionarTabela() {
        int novoTamanho = tamanho * 2;
        ListaEncadeada<Entrada<K, V>>[] novaTabela = new ListaEncadeada[novoTamanho];
        for (int i = 0; i < novoTamanho; i++) {
            novaTabela[i] = new ListaEncadeada<>();
        }

        for (int i = 0; i < tamanho; i++) {
            ListaEncadeada<Entrada<K, V>> lista = tabela[i];
            ListaEncadeada<Entrada<K, V>>.MeuIteradorDeLista iterador = lista.iterador();
            while (iterador.temProximo()) {
                Entrada<K, V> entrada = iterador.proximo();
                int novoIndice = funcaoHash(entrada.getChave(), novoTamanho);
                novaTabela[novoIndice].add(entrada);
            }
        }

        tamanho = novoTamanho;
        tabela = novaTabela;
    }

    public V buscar(K chave) {
        int indice = funcaoHash(chave, tamanho);
        ListaEncadeada<Entrada<K, V>> lista = tabela[indice];
        ListaEncadeada<Entrada<K, V>>.MeuIteradorDeLista iterador = lista.iterador();
        while (iterador.temProximo()) {
            Entrada<K, V> entrada = iterador.proximo();
            if (entrada.getChave().equals(chave)) {
                return entrada.getValor();
            }
        }
        return null;
    }

    public boolean contemChave(K chave) {
        return buscar(chave) != null;
    }

    public V remover(K chave) {
        int indice = funcaoHash(chave, tamanho);
        ListaEncadeada<Entrada<K, V>> lista = tabela[indice];
        ListaEncadeada<Entrada<K, V>>.MeuIteradorDeLista iterador = lista.iterador();
        while (iterador.temProximo()) {
            Entrada<K, V> entrada = iterador.proximo();
            if (entrada.getChave().equals(chave)) {
                lista.remove(entrada);
                elementosInseridos--;
                return entrada.getValor();
            }
        }
        return null;
    }

    public void imprimir() {
        System.out.println("Tabela Hash:");
        for (int i = 0; i < tamanho; i++) {
            ListaEncadeada<Entrada<K, V>> lista = tabela[i];
            System.out.print("Slot " + i + ": ");
            ListaEncadeada<Entrada<K, V>>.MeuIteradorDeLista iterador = lista.iterador();
            if (!iterador.temProximo()) {
                System.out.println("(vazio)");
            } else {
                while (iterador.temProximo()) {
                    Entrada<K, V> entrada = iterador.proximo();
                    System.out.print("(" + entrada.getChave() + ", " + entrada.getValor() + ") ");
                }
                System.out.println();
            }
        }
    }

    private static class Entrada<K, V> {
        private final K chave;
        private  V valor;

        public Entrada(K chave, V valor) {
            this.chave = chave;
            this.valor = valor;
        }

        public K getChave() {
            return chave;
        }

        public V getValor() {
            return valor;
        }

        public void setValor(V valor) {
            this.valor = valor;
        }
    }
}
