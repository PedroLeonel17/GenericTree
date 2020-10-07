/*
* String Nomes: "Pedro Cardoso, João Benno";
* String Matrículas = "19204021-0 , 19204045-9";
* String Data = "06/07/2020";
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        String linhas[] = new String[1000];
        Scanner sc = new Scanner(System.in);
        LinkedList<Palavra> lista = new LinkedList<>();
        String aux[];

        Path path1 = Paths.get("nomes.csv");

        try (BufferedReader reader = Files.newBufferedReader(path1, Charset.defaultCharset())) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                aux = line.split(";");
                Palavra p = new Palavra(aux[0], aux[1]);
                lista.add(p);
            }
        } catch (IOException e) {
            System.err.format("Erro na leitura do arquivo: ", e);
        }

        Dicionario dicionario = new Dicionario(lista);

        System.out.println("\ninformacoes do dicionario:\n");
        System.out.println("Quantidade de letras: " + dicionario.getTotalLetras() + "\n");
        System.out.println("Quantidade de palavras: " + dicionario.getTotalPalavras() + "\n");
        System.out.println("Digite o prefixo das palavras que deseja buscar: ");

        String busca = sc.nextLine();
        busca = busca.substring(0, 1).toUpperCase() + busca.substring(1, busca.length());

        System.out.println("Escolha a palavra que deseja ver o significado");
        String listarPalavra = "";

        int count = 1;
        for (String nome : dicionario.pesquisarPalavrasComPrefixoX(busca)) {
            listarPalavra += count + "-" + nome + "\n";
            count++;
        }

        System.out.println("\nNomes encontrados: " + "\n");
        System.out.println(listarPalavra);
        System.out.println("Digite o nome que deseja saber o significado!" + "\n");

        busca = sc.nextLine();
        busca = busca.substring(0, 1).toUpperCase() + busca.substring(1, busca.length());
        System.out.println(dicionario.getSignificado(busca));

    }
}
