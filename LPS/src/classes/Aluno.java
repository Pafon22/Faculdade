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
    private String curso;
    private Scanner scan;

    public Aluno() {
        this.scan = new Scanner(System.in);
        nome = " ";
        cpf = " ";
        curso = " ";

    }

    public Aluno(String nome, String cpf, String curso) {
        this.scan = new Scanner(System.in);
        this.nome = nome;
        this.cpf = cpf;
        this.curso = curso;
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
        setNome(getScan().next());
        System.out.println("Digite o cpf do Aluno: ");
        setCpf(getScan().next());
        setIndex(getIndex() + 1);

    }

    public void imprimir() {
        System.out.println("Nome: " + getNome());
        System.out.println("CPF: " + getCpf());
    }

    /**
     * @return the curso
     */
    public String getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(String curso) {
        this.curso = curso;
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
}
