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
public class Facul {

    private int index;
    private Aluno[] aluno;
    private Curso[] curso;
    private Matricula[] matricula;
    private Scanner scan;
    private int tam;

    public Facul() {
        tam = 10;
        aluno = new Aluno[tam];
        curso = new Curso[tam];
        matricula = new Matricula[tam];

        this.scan = new Scanner(System.in);
        index = 0;

        for (int i = 1; i < tam; i++) {
            aluno[i] = new Aluno();
            curso[i] = new Curso();
            matricula[i] = new Matricula();
        }

    }

    public Facul(Aluno[] aluno, Curso[] curso, Matricula[] matricula) {
        index = 0;
        this.scan = new Scanner(System.in);
        this.aluno = aluno;
        this.curso = curso;
        this.matricula = matricula;
    }

    public void matricular() {
        index++;
        aluno[index].preencher();
        aluno[index].setIndex(index);
        curso[index].preencher();
        curso[index].setIndex(index);
        matricula[index].preencher();
        matricula[index].setIndex(index);
        matricula[index].setNumMat(index + 202020);

    }

    public void menu() throws InterruptedException {
        int op;
        do {
            System.out.println("------------------");
            System.out.println("|1 - Matricular  |");
            System.out.println("|2 - Imprimir    |");
            System.out.println("|0 - Sair        |");
            System.out.println("------------------");
            System.out.println("Digite uma opção: ");
           
            op = scan.nextInt();

            switch (op) {
                case 1:
                    matricular();
                    break;
                case 2:
                    imprimir();
                    break;
                case -2:
                    autor();
                    break;
                case 0:
                    System.out.println("Você saiu!");
                    break;
                default:
                    System.out.println("Opção Inválida.");
                    break;

            }

        } while (op != 0);
    }

    public void imprimir() {
        for (int i = 1; i <= index; i++) {
            System.out.println("-----------------------");
            aluno[i].imprimir();
            curso[i].imprimir();
            matricula[i].imprimir();

        }

    }

    /**
     * @return the index
     */
    public int getCodigo() {
        return getIndex();
    }

    /**
     * @param index the index to set
     */
    public void setCodigo(int index) {
        this.setIndex(index);
    }

    @SuppressWarnings("SleepWhileInLoop")
    public static void autor() throws InterruptedException {
        int x = 500;
        System.out.println("*****     *****     *****     *****     *    *");
        Thread.sleep(x);
        System.out.println("*   *     *   *     *         *   *     * *  *");
        Thread.sleep(x);
        System.out.println("*****     *****     ****      *   *     *  * *");
        Thread.sleep(x);
        System.out.println("*         *   *     *         *   *     *   **");
        Thread.sleep(x);
        System.out.println("*         *   *     *         *****     *    *");
        String autor = "Paulo Afonso Cardoso Franco";
        char[] autor2 = autor.toCharArray();
        for (int i = 0; i < autor.length(); i++) {
            Thread.sleep(300);
            System.out.print(autor2[i]);
        }
        System.out.println();
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
     * @return the aluno
     */
    public Aluno[] getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno[] aluno) {
        this.aluno = aluno;
    }

    /**
     * @return the curso
     */
    public Curso[] getCurso() {
        return curso;
    }

    /**
     * @param curso the curso to set
     */
    public void setCurso(Curso[] curso) {
        this.curso = curso;
    }

    /**
     * @return the matricula
     */
    public Matricula[] getMatricula() {
        return matricula;
    }

    /**
     * @param matricula the matricula to set
     */
    public void setMatricula(Matricula[] matricula) {
        this.matricula = matricula;
    }
}
