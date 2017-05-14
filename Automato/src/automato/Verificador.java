/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package automato;

import java.util.Scanner;

/**
 *
 * @author PauloAfonso
 */
public class Verificador {

    String estadoAtual;                 //estado de destino em que se encontra a ultima letra verificada
    boolean isFinal, letraValida;       // isFinal = O estado é final? letraValida = Existe caminho para a letra atual?
    String palavra;                     // conjunto de caracteres escrito pelo usuário
    char[] letras;                      // divisão da palavra escrita em um vetor, letra a letra

    public Verificador() {              // Inicialização das variáveis
        estadoAtual = "q1";             // Estado Inicial
        isFinal = false;                // q1 não é final
        palavra = "";                   // palavra vazia no momento
        letraValida = true;             // letraValida = true para poder entrar na primeira iteração do while mais abaixo
    }

    public void preencher() {                           // Entrada de Dados para o Autômato
        Scanner ler = new Scanner(System.in);           // Declaração do tipo Scanner para leitura do teclado
        System.out.println("Digite a palavra:");        // Texto de orientação ao usuário
        palavra = ler.next();                           // palavra recebe a próxima String que for escrita pelo usuário no teclado
    }

    public void conferirPalavra() {                 // Verificação de palavra no autômato criado
        letras = new char[palavra.length()];        // Criação de vetor de caracteres do tamanho da palavra
        letras = palavra.toCharArray();             // Cada posição do vetor é preenchido com uma letra da palavra (ordenadamente)
        int cont = 0;                               // Inicialização de um contador para rodar o automato em todas as letras

        while (cont < palavra.length() && letraValida == true) {        //Confere se ainda tem letras para verificar e se a ultima letra verificada é válida
            System.out.print("{" + estadoAtual +"," + letras[cont]+"} = "); // Escreve a primeira parte do quadro de transição, exemplo: {q1, a} =
            if (estadoAtual == "q1") {                                     //Se o estado for o q1...
                switch (letras[cont]) {                                     //Verifica qual é a letra que estamos no momento
                    case 'a':                                               // Se for a
                        estadoAtual = "q2";                                 // Vai para q2
                        isFinal = false;                                    // q2 não é final
                        break;                                              // fim do case 'a'
                    case 'b':                                               //Análogo ao anterior
                        estadoAtual = "q4";
                        isFinal = false;
                        break;
                    default:                                                // Se não for nenhum desses caminhos
                        letraValida = false;                                // A letra não é válida
                        break;
                }
            } else if (estadoAtual == "q2") {
                switch (letras[cont]) {
                    case 'b':
                        estadoAtual = "q7";
                        isFinal = true;
                        break;
                    case 'c':
                        estadoAtual = "q6";
                        isFinal = true;
                        break;
                    default:
                        letraValida = false;
                        break;
                }
            } else if (estadoAtual == "q3") {
                switch (letras[cont]) {
                    case 'a':
                        estadoAtual = "q5";
                        isFinal = false;
                        break;
                    default:
                        letraValida = false;
                        break;
                }
            } else if (estadoAtual == "q4") {
                switch (letras[cont]) {
                    case 'b':
                        estadoAtual = "q6q7";
                        isFinal = true;
                        break;
                    default:
                        letraValida = false;
                        break;
                }
            } else if (estadoAtual == "q5") {
                switch (letras[cont]) {
                    case 'a':
                        estadoAtual = "q2";
                        isFinal = false;
                        break;
                    case 'b':
                        estadoAtual = "q6";
                        isFinal = true;
                        break;
                    default:
                        letraValida = false;
                        break;
                }
            } else if (estadoAtual == "q6") {
                switch (letras[cont]) {
                    case 'a':
                        estadoAtual = "q3";
                        isFinal = false;
                        break;
                    default:
                        letraValida = false;
                        break;
                }
            } else if (estadoAtual == "q7") {
                switch (letras[cont]) {
                    case 'c':
                        estadoAtual = "q5";
                        isFinal = false;
                        break;
                    default:
                        letraValida = false;
                        break;
                }
            } else if (estadoAtual == "q6q7") {
                switch (letras[cont]) {
                    case 'a':
                        estadoAtual = "q3";
                        isFinal = false;
                        break;
                    case 'c':
                        estadoAtual = "q5";
                        isFinal = false;
                        break;
                    default:
                        letraValida = false;
                        break;
                }                                                           // Fim dos blocos análogos
            }
            if(letraValida)                                                 // Se a última letra verificada for válida
                System.out.println("{"+estadoAtual + "}\n");                // Termina a linha do quadro de transição que começamos (linha 40), exemplo: {q2}
            else                                                            // Se a última letra verificada não for válida
                System.out.println("{ Caminho Inválido }");                 //Termina a linha do quadro de produção, dizendo que não há caminho
            cont++;                                                         // Incremento do contador

        }

        if (!letraValida || !isFinal) {                                     // Se alguma letra não tiver sido válida ou a palavra tiver terminado em um estado não final
            System.out.println("Palavra não pertence à Linguagem.");        // A palavra é inválida para este autômato
        } else {                                                            // Caso contrário,
            System.out.println("Palavra aceita pelo autômato!");            // A palavra pertence! xD 
        }
    }

}
