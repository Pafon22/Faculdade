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
public class Curso {

    private String nome;
    private int cargaHoraria;
    private int index;
    private Scanner scan;

    public Curso() {
        this.scan = new Scanner(System.in);
        nome = " ";
        cargaHoraria = 0;

    }

    public Curso(String nome, int cargaHoraria) {
        this.scan = new Scanner(System.in);
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
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
     * @return the cargaHoraria
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @param cargaHoraria the cargaHoraria to set
     */
    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
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

    public void menu() {
        System.out.println("1 - Computação");
        System.out.println("2 - Zootecnia");
        System.out.println("3 - Administração");
    }

    public void preencher() {
        int op;
        do {
            menu();
            System.out.println("Selecione o curso");
            op = scan.nextInt();
            switch (op) {
                case 1:
                    nome = "Computação";
                    cargaHoraria = 3600;

                    break;
                case 2:
                    nome = "Zootecnia";
                    cargaHoraria = 3500;

                    break;
                case 3:
                    nome = "Administração";
                    cargaHoraria = 3400;

                    break;
                default:
                    op = 0;
                    System.out.println("Opção Inválida");
                    break;
            }

        } while (op == 0);
    }

    public void imprimir() {
        System.out.println("Curso: " + nome);
        System.out.println("Carga Horária: " + cargaHoraria);
    }
}
