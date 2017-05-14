/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

/**
 *
 * @author PauloAfonso
 */
public class FileManager {

    public static void escreverArq(String texto, String caminho) {
        File arquivo = new File(caminho);
        FileWriter fw;
        try {
            fw = new FileWriter(arquivo);
            fw.write(texto);
            fw.flush();
            fw.close();
        } catch (Exception e) {
        }
    }

    public static String lerArq(String caminho) {
        try {
            String lido = "";
            FileReader fr = new FileReader(caminho);
            try (Scanner arquivoLido = new Scanner(fr)) {
                arquivoLido.useDelimiter("\n");
                arquivoLido.next(); //Pula linha '0', fazendo ir para a primeira linha escrita do arquivo.
                while (arquivoLido.hasNext()) {
                    lido += arquivoLido.next() + "\n";
                    //   System.out.println(lido);
                }
            }
            return lido;
        } catch (Exception e) {
            return "";
        }
    }

    public static String lerArqW(String caminho) {
        try {
            String lido = "";
            FileReader fr = new FileReader(caminho);
            try (Scanner arquivoLido = new Scanner(fr)) {
                arquivoLido.useDelimiter("\n");
                //arquivoLido.next(); //Pula linha '0', fazendo ir para a primeira linha escrita do arquivo.
                //while (arquivoLido.hasNext()) {
                lido += arquivoLido.next() + "\n";
                //   System.out.println(lido);
                //}
            }
            return lido;
        } catch (Exception e) {
            return "";
        }
    }

    public static String[] getLinhas(String lido) {
        String texto = lido;
        String[] arrayTexto = texto.split("\r\n");
        return arrayTexto;
    }
}
