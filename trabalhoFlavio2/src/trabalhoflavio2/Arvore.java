package trabalhoflavio2;

import java.util.Scanner;

public class Arvore {

    Scanner ler;
    No perg = null;

    

    public Arvore() {
        this.ler = new Scanner(System.in);
    }

    static class No {

        No nao;
        No sim;
        int valor;
        String pergunta;
        boolean resposta;

        public No(int valor, String pergunta, boolean resposta) {
            this.valor = valor;

            this.pergunta = pergunta;

        }

    }

    public void Menu() {
        System.out.println("********************");
        System.out.println("Carlos Roberto e Orlando");
        System.out.println("0826-14 / 2226-14");
        System.out.println("ciencia da computacao");
        System.out.println("2 trabalho pratico-20 questoes");
        System.out.println("dcc 254 --2017 --ifsemg -- turma especial");
        System.out.println("prof flavio augusto");
        System.out.println("compilador");
        System.out.println("sistema operacional");
        System.out.println("********************");
    }

    public void criar() {
        Menu();
        No perg = new No(40, "É um animal?", false);
        //System.out.println(perg.perg);
        
        /*System.out.println("Exemplo de Arvore Binaria");
         System.out.println(/"Criando arvore com a perg " + perg.valor);*/
        inserir(perg, 40, "É um animal?", false);//pergunta
        inserir(perg, 60, "Tem 4 patas?", false);
        inserir(perg, 70, "É doméstico?", false);
        inserir(perg, 75, "mia?", false);
        inserir(perg, 72, "A resposta é cachorro?", true);//resposta
        inserir(perg, 77, "A resposta é gato?", true);
        inserir(perg, 65, "É animal silvestre?", false);
        inserir(perg, 67, "A resposta é Leão?", true);
        inserir(perg, 62, "A resposta é rato?", true);
        inserir(perg, 50, "É marítimo?", false);
        inserir(perg, 55, "É mamífero?", false);
        inserir(perg, 57, "A resposta é baleia?", true);
        inserir(perg, 52, "A resposta é peixe?", true);
        inserir(perg, 45, "Voa?", false);
        inserir(perg, 47, "A resposta é pássaro?", true);
        inserir(perg, 42, "A resposta é galinha?", true);
        inserir(perg, 20, "É uma pessoa?", false);
        inserir(perg, 30, "É jogador?", false);
        inserir(perg, 35, "A resposta é Neymar?", true);
        inserir(perg, 25, "É professor?", false);
        inserir(perg, 27, "A resposta é Flávio?", true);
        inserir(perg, 22, "A resposta é Carlos?", true);
        inserir(perg, 15, "É um desenho?", false);
        inserir(perg, 17, "A resposta é Pernalonga?", true);
        inserir(perg, 12, "É filme?", false);
        inserir(perg, 13, "A resposta é Logan?", true);
        inserir(perg, 10, "É time ?", false);
        inserir(perg, 11, "A resposta é Flamengo?", true);
        inserir(perg, 5, "A resposta é televisão?", true);

        Menu(perg);//ele que mostra as pergs

    }

    public void Menu(No a) {

        if (a != null) {
            System.out.print(a.pergunta);
            String f = ler.next();
            if (f.equals("nao")) {

                Menu(a.nao);
            } else if (f.equals("sim")) {

                Menu(a.sim);
            } else {
                System.out.println("Código inválido!!");
                Menu(a);
            }

        }

        System.out.println("");
    }

    public void inserir(No node, int valor, String pergunta, boolean resposta) {

        if (perg == null) {
            perg = node;
        } else if (valor < node.valor) {
            if (node.nao != null) {
                inserir(node.nao, valor, pergunta, resposta);
            } else {
                //System.out.println("  Inserindo " + valor + " a esquerda de " + node.valor);
                node.nao = new No(valor, pergunta, resposta);
//                System.out.println(valor+node.perg+node.resposta);
            }
        } else if (valor > node.valor) {
            if (node.sim != null) {
                inserir(node.sim, valor, pergunta, resposta);
            } else {
                //System.out.println("  Inserindo " + valor + " a sim de " + node.valor);
                node.sim = new No(valor, pergunta, resposta);
            }
        }
    }
  
}