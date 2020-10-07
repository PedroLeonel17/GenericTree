/*
* String Nomes: "Pedro Cardoso, João Benno";
* String Data = "06/07/2020";
*/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dicionario {
    private Letra raiz;

    private int totalLetras = 0;
    private int totalPalavras = 0;

    private class Letra {// comentario
        private char letra;
        private String significado;
        private Letra pai;
        private List<Letra> filhos;

        private Letra(char letra) {
            this.letra = letra;
            this.pai = null;
            this.filhos = new LinkedList<>();
            this.significado = null;
        }

        private void addFilho(Letra letra) {
            letra.pai = this;
            filhos.add(letra);
        }

        private int getNumeroDeFilhos() {
            return filhos.size();
        }

        private Letra getFilho(int index) {
            return filhos.get(index);
        }

        private String getPalavra() {
            String palavra = "";
            Letra aux = this;
            ArrayStack pilha = new ArrayStack();

            if (this.significado != null) {
                while (aux != null) {
                    pilha.push(aux.letra);
                    aux = aux.pai;
                }
                while (!pilha.isEmpty()) {
                    palavra += pilha.pop();
                }
            }
            return palavra;
        }

        private Letra findChildChar(char character) {
            if (filhos.isEmpty()) {
                return null;
            } else {
                for (int i = 0; i < filhos.size(); i++) {
                    if (getFilho(i).letra == character) {
                        return getFilho(i);
                    }
                }
                return null;
            }
        }
    }

    public Dicionario(List<Palavra> lista) {
        raiz = new Letra('*');
        for (Palavra palavra : lista) {
            addPalavra(palavra.getPalavra(), palavra.getSignificado());
        }
    }

    public int getTotalPalavras() {
        return totalPalavras;
    }

    public int getTotalLetras() {
        return totalLetras;
    }

    private void addPalavra(String word, String significado) {

        if (raiz.findChildChar(word.charAt(0)) == null) {
            raiz.filhos.add(new Letra(word.charAt(0)));
            totalLetras++;
        }

        Letra nodoRaiz = raiz.findChildChar(word.charAt(0));
        Letra filho;
        Letra novaLetra;

        for (int i = 1; i < word.length(); i++) {
            filho = nodoRaiz.findChildChar(word.charAt(i));
            novaLetra = new Letra(word.charAt(i));

            if (filho == null) {
                nodoRaiz.addFilho(novaLetra);
                filho = novaLetra;
                totalLetras++;
            }

            if (i == (word.length() - 1)) {
                novaLetra.significado = significado;
                totalPalavras++;
            }
            nodoRaiz = filho;
        }
    }

    private List<String> palavrasComSignificado(Letra l) {
        List<String> lista = new ArrayList<>();
        palavrasComSignificadoAux(l, lista);
        return lista;
    }

    private void palavrasComSignificadoAux(Letra n, List<String> lista) {
        if (n.significado != null) {
            lista.add(n.getPalavra());
        }
        Letra aux = null;
        int i = 0;
        while ((i < n.filhos.size())) {
            palavrasComSignificadoAux(n.getFilho(i), lista);
            i++;
        }
    }

    private Letra encontraUltimaLetraDaPalavra(String word) {
        Letra nodoAtual = raiz.findChildChar(word.charAt(0));
        if (nodoAtual == null) {
            return null;
        }
        Letra filho = raiz;
        for (int i = 1; i < word.length(); i++) {
            if (nodoAtual.findChildChar(word.charAt(i)) == null) {
                return null;
            } else {
                nodoAtual = nodoAtual.findChildChar(word.charAt(i));
            }
        }
        return nodoAtual;
    }

    public String getSignificado(String word) {
        return encontraUltimaLetraDaPalavra(word).significado;
    }

    public List<String> pesquisarPalavrasComPrefixoX(String prefix) {
        Letra nodoAtual = raiz.findChildChar(prefix.charAt(0));
        if (nodoAtual == null) {
            return null;
        }
        for (int i = 1; i < prefix.length(); i++) {
            if (nodoAtual.findChildChar(prefix.charAt(i)) == null) {
                return null;
            } else {
                nodoAtual = nodoAtual.findChildChar(prefix.charAt(i));
            }
        }

        return palavrasComSignificado(nodoAtual);
    }

    /*
     * public String getPalavra(char letra) { Letra aux = searchNodeRef(letra,
     * raiz); return aux.getPalavra(); }
     * 
     * private Letra searchNodeRef(char letra, Letra n) { if (n == null) { return
     * null; }
     * 
     * if (letra == (n.letra) && n.significado != null) { return n; } else { Letra
     * aux = null; int i = 0; while ((aux == null) && (i < n.filhos.size())) { aux =
     * searchNodeRef(letra, n.filhos.get(i)); i++; } return aux; } }
     */
}