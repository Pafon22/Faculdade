package classes;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author PauloAfonso
 */
public class Jogador {

    private String nome;
    private ArrayList<Integer> jogadas;
    private Random dado;
    private Scanner scan;
    private int total;
    private int index;

    public Jogador() {
        total = 0;
        dado = new Random();
        scan = new Scanner(System.in);
        nome = " ";
        jogadas = new ArrayList<Integer>(10);
        for (int i = 0; i < 10; i++) {
            jogadas.add(i, 0);
        }
    }

    /**
     *
     * @param nome
     * @param jogadas
     * @param dado
     */
    public Jogador(String nome, ArrayList<Integer> jogadas, Random dado) {
        total = 0;
        scan = new Scanner(System.in);
        this.nome = nome;
        this.jogadas = jogadas;
        this.dado = dado;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the jogadas
     */
    public ArrayList<Integer> getJogadas() {
        return jogadas;
    }

    /**
     * @param jogadas the jogadas to set
     */
    public void setJogadas(ArrayList<Integer> jogadas) {
        this.jogadas = jogadas;
    }

    /**
     * @return the dado
     */
    public Random getDado() {
        return dado;
    }

    /**
     * @param dado the dado to set
     */
    public void setDado(Random dado) {
        this.dado = dado;
    }

    /**
     * @return the scan
     */
    public Scanner getScan() {
        return scan;
    }

    /**
     * @param scan the scan to set
     */
    public void setScan(Scanner scan) {
        this.scan = scan;
    }

    public void preencher() {
        System.out.println("\tQual é o nome do jogador? ");
        System.out.print("\t");
        setNome(getScan().next());

    }

    public void jogarDado() {
        int n;
        total = 0;
        for (int i = 0; i < 10; i++) {
            n = dado.nextInt(6) + 1;
            jogadas.set(i, n);
            total += n;
        }
    }

    public void imprimir() {

        System.out.println("\t| Jogador: " + getNome() + "\t\t\t\t\t|");
        System.out.print("\t| Jogada:  ");
        imprimirJogada();
    }

    public void imprimirJogada() {
        System.out.print("{ ");
        for (int i = 0; i < 10; i++) {
            System.out.print(jogadas.get(i) + ", ");
        }
        System.out.print("}    |  \n \t| TOTAL:   " + getTotal() + "\t\t\t\t\t|\n \t-------------------------------------------------");

    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

    /**
     * @return the total
     */
    public int getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(int total) {
        this.total = total;
    }
}
