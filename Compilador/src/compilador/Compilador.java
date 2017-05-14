/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author PauloAfonsO''''''''''''''''''''''''''''''''''''''''''''''''o
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Verificador ver = new Verificador();
        ver.preencher();
        ver.separarTexto(ver.palavra);
        for (int i = 0; i < ver.contPalavras; i++) {
            ver.conferirPalavra(ver.palavras[i]);
        }
    }

}
