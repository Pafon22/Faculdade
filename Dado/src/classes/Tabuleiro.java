/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author PauloAfonso
 */
public class Tabuleiro {

    ArrayList<Jogador> jogadores;
    int indexJogador;
    Scanner scan;

    public Tabuleiro() {
        scan = new Scanner(System.in);
        jogadores = new ArrayList(10);
        indexJogador = -1;
        for (int i = 0; i < 10; i++) {
            jogadores.add(0, new Jogador());
        }
    }

    public Tabuleiro(ArrayList<Jogador> jogadores) {
        scan = new Scanner(System.in);
        indexJogador = jogadores.size() - 2;
        this.jogadores = jogadores;
    }

    public void topicos() {
        System.out.println("");
        System.out.println("\t****************************");
        System.out.println("\t*  1 - Cadastrar jogador   *");
        System.out.println("\t*  2 - Jogar               *");
        System.out.println("\t*  3 - Ver o placar        *");
        System.out.println("\t*  4 - Ver o vencedor      *");
        System.out.println("\t*  0 - Sair                *");
        System.out.println("\t****************************");

    }

    public void menu() throws InterruptedException {
        int op;
        do {
            topicos();
            System.out.println("\tEscolha uma opção: ");
            System.out.print("\t");
            op = scan.nextInt();
            System.out.println("\t-------------------------------------------------");
            switch (op) {
                case 1:
                    cadastrarJogador();
                    break;
                case 2:
                    jogar();
                    break;
                case 3:
                    imprimirPlacar();
                    System.out.println("\tDigite qualquer coisa para continuar.");
                    System.out.print("\t");
                    scan.next();
                    break;
                case 4:
                    System.out.println("\tO vencedor é: " + imprimirVencedor());
                    System.out.println("\tDigite qualquer coisa para continuar.");
                    System.out.print("\t");
                    scan.next();
                    break;
                case 0:
                    autor();
                    break;
                default:
                    System.out.println("Opção Inválida!\n");
                    break;
            }
        } while (op != 0);
    }

    public void imprimirPlacar() {
        for (int i = 0; i <= indexJogador; i++) {
            jogadores.get(i).imprimir();
            System.out.print("\n");
        }
    }

    public String imprimirVencedor() {
        int idVencedor = 0;

        for (int i = 0; i < indexJogador; i++) {
            if (jogadores.get(idVencedor).getTotal() < jogadores.get(i + 1).getTotal()) {
                idVencedor = i + 1;
            }

        }
        String vencedor = jogadores.get(idVencedor).getNome() + " - " + jogadores.get(idVencedor).getTotal() + " pontos.";
        return vencedor;
    }

    public void cadastrarJogador() {
        indexJogador++;
        jogadores.get(indexJogador).preencher();
        jogadores.get(indexJogador).setIndex(indexJogador);

    }

    public void jogar() {
        int op;
        do {
            System.out.println("\tLista de jogadores: ");
            for (int i = 0; i <= indexJogador; i++) {
                System.out.println("\t" + jogadores.get(i).getIndex() + " - " + jogadores.get(i).getNome());
            }

            System.out.println("\tEscolha um jogador: ");
            System.out.print("\t");
            op = scan.nextInt();
            System.out.println("\t-------------------------------------------------");
            if (op >= 0 && op <= indexJogador) {
                jogadores.get(op).jogarDado();
                jogadores.get(op).imprimir();
            } else {
                System.out.println("\tOpção Inválida! \n");

            }
        } while (op < 0 || op > indexJogador);
    }

    @SuppressWarnings("SleepWhileInLoop")
    public static void autor() throws InterruptedException {
        int x = 250;
        System.out.println("\t*****\t\t *****\t\t *****\t\t ***** \t\t*    *");
        Thread.sleep(x);
        System.out.println("\t*   *\t\t *   *\t\t *    \t\t *   * \t\t* *  *");
        Thread.sleep(x);
        System.out.println("\t*****\t\t *****\t\t **** \t\t *   * \t\t*  * *");
        Thread.sleep(x);
        System.out.println("\t*    \t\t *   *\t\t *    \t\t *   * \t\t*   **");
        Thread.sleep(x);
        System.out.println("\t*    \t\t *   *\t\t *    \t\t ***** \t\t*    *");
        String autor = "\tPaulo Afonso Cardoso Franco";
        char[] autor2 = autor.toCharArray();
        for (int i = 0; i < autor.length(); i++) {
            Thread.sleep(300);
            System.out.print(autor2[i]);
        }
        System.out.println();
    }
}
