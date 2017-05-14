/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Scanner;

/**
 *
 * @author EMCOMP2
 */
public class Matricula {

    private int numMat;
    private int index;
    private String data;
    private Scanner scan;

    public Matricula() {
        this.scan = new Scanner(System.in);
        numMat = 202020;
        data = " ";

    }

    /**
     *
     * @param numMat
     * @param data
     */
    public Matricula(int numMat, String data) {
        this.numMat = numMat;
        scan = new Scanner(System.in);
        this.data = data;

    }

    public Matricula(String nome, String cpf, int index) {
    }

    /**
     * @return the numMat
     */
    public int getNumMat() {
        return numMat;
    }

    /**
     * @param numMat the numMat to set
     */
    public void setNumMat(int numMat) {
        this.numMat = numMat;
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
     * @return the data
     */
    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
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

        numMat = index + 202020;
        System.out.println("Data: ");
        data = scan.next();
        index++;

    }

    public void imprimir() {
        System.out.println("Número da Matrícula: " + numMat);
        System.out.println("Data: " + data);

    }
}
