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

    private int indexCurso, indexAluno, indexMatricula;

    private Aluno[] aluno;
    private Curso[] curso;
    private Matricula[] matricula;
    private Scanner scan;
    private int tam;

    public Facul() {
        this.scan = new Scanner(System.in);
        indexAluno = 0;
        indexCurso = 0;
        indexMatricula = 0;
        System.out.println("Digite o tamanho do vetor de alunos: ");
        tam = scan.nextInt();
        aluno = new Aluno[tam];
        for (int i = 0; i < tam; i++) {
            aluno[i] = new Aluno();
        }
        System.out.println("Digite o tamanho do vetor de cursos: ");
        tam = scan.nextInt();
        curso = new Curso[tam];
        for (int i = 0; i < tam; i++) {
            curso[i] = new Curso();
        }

        System.out.println("Digite o tamanho do vetor de matrículas: ");
        tam = scan.nextInt();
        matricula = new Matricula[tam];
        for (int i = 0; i < tam; i++) {
            matricula[i] = new Matricula();
        }

        aluno[0].setIndex(0);
        curso[0].setIndex(0);
        matricula[0].setIndex(0);
        matricula[0].setNumMat(0);

    }

    public Facul(Aluno[] aluno, Curso[] curso, Matricula[] matricula) {

        indexAluno = 0;
        indexCurso = 0;
        indexMatricula = 0;

        aluno[0].setIndex(0);
        curso[0].setIndex(0);
        matricula[0].setIndex(0);
        matricula[0].setNumMat(0);
        this.scan = new Scanner(System.in);
        this.aluno = aluno;
        this.curso = curso;
        this.matricula = matricula;
    }

    public void cadastrarCurso() {
        getCurso()[getIndexCurso()].preencher();
        getCurso()[getIndexCurso()].setIndex(getIndexCurso());
        setIndexCurso(getIndexCurso() + 1);

    }

    public void cadastrarAluno() {
        int op;
        boolean opcaoValida = false;
        getAluno()[getIndexAluno()].preencher();
        getAluno()[getIndexAluno()].setIndex(getIndexAluno());
        matricula[indexMatricula].setIndex(indexMatricula);
        System.out.println("Deseja se cadastrar em qual curso?");
        do {
            for (int i = 0; i < getIndexCurso(); i++) {
                System.out.print((i + 1) + " - Curso: " + getCurso()[i].getNome());
                System.out.println(" - CH: " + getCurso()[i].getCargaHoraria());
            }
            System.out.println("Digite uma opção: ");
            op = getScan().nextInt();
            if (op > 0 && op <= (getIndexCurso() + 1)) {
                getAluno()[getIndexAluno()].setCurso(getCurso()[op - 1].getNome());
                opcaoValida = true;
            } else {
                System.out.println("Opção Inválida. Digite uma nova opção: ");

            }

        } while (opcaoValida == false);
        getMatricula()[getIndexMatricula()].preencher();
        indexAluno++;
        indexMatricula++;

    }

    public void menu() throws InterruptedException {
        int op;
        do {
            System.out.println("-----------------------");
            System.out.println("|1 - Cadastrar Aluno  |");
            System.out.println("|2 - Cadastrar Curso  |");
            System.out.println("|3 - Imprimir         |");
            System.out.println("|0 - Sair             |");
            System.out.println("-----------------------");
            System.out.println("Digite uma opção: ");

            op = getScan().nextInt();

            switch (op) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    cadastrarCurso();
                    break;
                case 3:
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

    public int buscarIndexCurso(String curso) {
        for (int i = 0; i <= getIndexCurso(); i++) {
            if (curso.equals(this.getCurso()[i].getNome())) {
                return this.getCurso()[i].getIndex();
            }
        }
        return -1;
    }

    public void imprimir() {
        for (int i = 0; i < getIndexAluno(); i++) {
            System.out.println("-----------------------");
            getAluno()[i].imprimir();
            if (buscarIndexCurso(getAluno()[i].getCurso()) == -1) {
                System.out.println("Não cadastrado em curso algum.");
            } else {
                getCurso()[buscarIndexCurso(getAluno()[i].getCurso())].imprimir();
                getMatricula()[i].imprimir();
            }

        }

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
     * @return the aluno
     */
    public Aluno[] getAluno() {
        return aluno;
    }

    /**
     * @param aluno the aluno to set
     */
    public void setAluno(Aluno[] aluno) {
        this.setAluno(aluno);
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
        this.setCurso(curso);
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
        this.setMatricula(matricula);
    }

    /**
     * @return the indexCurso
     */
    public int getIndexCurso() {
        return indexCurso;
    }

    /**
     * @param indexCurso the indexCurso to set
     */
    public void setIndexCurso(int indexCurso) {
        this.indexCurso = indexCurso;
    }

    /**
     * @return the indexAluno
     */
    public int getIndexAluno() {
        return indexAluno;
    }

    /**
     * @param indexAluno the indexAluno to set
     */
    public void setIndexAluno(int indexAluno) {
        this.indexAluno = indexAluno;
    }

    /**
     * @return the indexMatricula
     */
    public int getIndexMatricula() {
        return indexMatricula;
    }

    /**
     * @param indexMatricula the indexMatricula to set
     */
    public void setIndexMatricula(int indexMatricula) {
        this.indexMatricula = indexMatricula;
    }

    /**
     * @return the tam
     */
    public int getTam() {
        return tam;
    }

    /**
     * @param tam the tam to set
     */
    public void setTam(int tam) {
        this.tam = tam;
    }
}
