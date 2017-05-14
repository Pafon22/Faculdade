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
public class Aluno {

    private String nome;
    private String cpf;
    private int index;
    private Scanner scan;

    public Aluno() {
        this.scan = new Scanner(System.in);
        nome = " ";
        cpf = " ";
    }

    public Aluno(String nome, String cpf) {
        this.scan = new Scanner(System.in);
        this.nome = nome;
        this.cpf = cpf;
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
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public void preencher() {
        System.out.println("Digite o nome do Aluno: ");
        nome = scan.next();
        System.out.println("Digite o cpf do Aluno: ");
        cpf = scan.next();

    }

    public void imprimir() {
        System.out.println("Nome: " + nome);
        System.out.println("CPF: " + cpf);
    }
}
