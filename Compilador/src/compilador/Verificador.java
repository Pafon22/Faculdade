/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

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
    String[] palavras;                  //vetor que receberá todas as palavras separadas por espaço
    boolean reservada = false;          // define se a palavra é reservada ou não
    int contPalavras;                   // contador de palavras escritas 
    String tipo;                        // tipo da palavra reservada

    public Verificador() {              // Inicialização das variáveis
        estadoAtual = "q1";             // Estado Inicial
        isFinal = false;                // q1 não é final
        palavra = "";                   // palavra vazia no momento
        letraValida = true;             // letraValida = true para poder entrar na primeira iteração do while mais abaixo
    }

    public void preencher() {                           // Entrada de Dados para o Autômato
        Scanner ler = new Scanner(System.in);           // Declaração do tipo Scanner para leitura do teclado
        System.out.println("Digite a palavra:");        // Texto de orientação ao usuário
        palavra = ler.nextLine();                           // palavra recebe a próxima String que for escrita pelo usuário no teclado

    }

    public String[] separarTexto(String texto) {        //Separa o que foi digitado pelo usuário em um vetor de palavras
        contPalavras = 0;                               //Começa o contador com 0 palavras
        letras = new char[texto.length()];              // Vetor de char do tamanho do que foi escrito pelo usuário (texto)
        letras = texto.toCharArray();                   // O texto é quebrado em um vetor de char
        for (int i = 0; i < texto.length(); i++) {          //Varredura do texto escrito pelo Usuário
            if ((int) letras[i] != 0 && letras[i] != ' ') { // Compara se o char atual é diferente de nulo e de espaço
                if ((i + 1) != texto.length()) {            // Confere se existe próximo char
                    if (texto.charAt(i + 1) == ' ') {       // Confere se o próximo é um espaço em branco
                        contPalavras++;                     // Se o próximo for espaço em branco, finaliza uma palavra (incrementa 1 no contador de palavras)
                    }
                } else {                                //Se não houver mais caracteres no texto, conta-se a última palavra
                    contPalavras++;
                }
            }
        }
        System.out.println("Total de Palavras: "+ contPalavras);                   //Printa quantas palavras têm

        palavras = new String[contPalavras];                //Vetor de String em que cada posição será preenchida por uma palavra do texto

        int contChar = 0;                                   //Contador de Char para varrer o texto e escrever na palavra
        for (int i = 0; i < contPalavras; i++) {            //For para preencher todas as posiçoes do vetor de palavras

            while (contChar < texto.length() && texto.charAt(contChar) != ' ' && (int) texto.charAt(contChar) != 0) { // Enquanto não tiver acabado o texto, o caracter atual não for espaço e nem nulo, execute...
                if (palavras[i] == null) {                             //Se a palavra atual for nula
                    palavras[i] = String.valueOf(letras[contChar]); // palavra atual recebe o char (isso é para evitar que a palavra recebesse "nulla" em vez de "a", por exemplo
                } else {                                                //Se já tiver algo escrito
                    palavras[i] += String.valueOf(letras[contChar]); // ela recebe o que já tá escrito + o char atual
                }
                contChar++;             // Vamos ao próximo char para continuar o while
            }
            contChar++;             //Indo para o próximo char porque chegamos a um espaço em branco

        }

        return palavras;        //Retornando vetor de palavras para usar uma a uma (main- Compilador.java) no conferirPalavra (Verificador.java)
    }

    public void gerarTipoDaPalavra() {      //Professor recomendou que eu colocasse pelo menos 3 tipologias diferentes
        if (reservada) {    //Se for reservada (pode ser 3 tipos: reservada, símbolo, comentário
            if ("q17".equals(estadoAtual) || "q18".equals(estadoAtual) || "q19".equals(estadoAtual) || "q20".equals(estadoAtual) || "q21".equals(estadoAtual) || "q22".equals(estadoAtual) || "q23".equals(estadoAtual)) { //estados que ficam os símbolos
                tipo = "símbolos";
            } else if ("q2".equals(estadoAtual)) { //estado final de comentário
                tipo = "comentário";
            } else {            //se for reservada e não for comentário/símbolo
                tipo = "reservada";
            }
        } else {            //se não for reservada é variável
            tipo = "variável";
        }
    }

    public void conferirPalavra(String palavra) {                 // Verificação de palavra no autômato criado
        reservada = false;                      //reservada começa de false
        estadoAtual = "q1";                     //estado inicial
        System.out.println("PALAVRA: " + palavra); // Imprime palavra atual
        //int contPalavras=0;                         // Verifica quantas palavras foram escritas pelo Mauricio
        letras = new char[palavra.length()];        // Criação de vetor de caracteres do tamanho da palavra
        letras = palavra.toCharArray();             // Cada posição do vetor é preenchido com uma letra da palavra (ordenadamente)
        int cont = 0;                               // Inicialização de um contador para rodar o automato em todas as letras

        while (cont < palavra.length() && letraValida == true) {        //Confere se ainda tem letras para verificar e se a ultima letra verificada é válida
            System.out.print("{" + estadoAtual + "," + letras[cont] + "} = "); // Escreve a primeira parte do quadro de transição, exemplo: {q1, a} =
            switch (estadoAtual) {          //Recomendado pelo professor: alterar meus if's para switches
                case "q1":
                    isFinal = false;            // É estado final? Não.
                    switch (letras[0]) {
                        case '+':
                            if (palavra.length() == 1) {        //Confere se o tamanho é 1, para o caso de ter algo escrito antes
                                letraValida = true;         // O dígito atual é válido?
                                reservada = true;
                                isFinal = true;
                            }
                            estadoAtual = "q18";
                            break;
                        case '-':
                            if (palavra.length() == 1) {
                                letraValida = true;
                                reservada = true;
                                isFinal = true;
                            }
                            estadoAtual = "q17";
                            break;
                        case '*':
                            if (palavra.length() == 1) {
                                letraValida = true;
                                reservada = true;
                                isFinal = true;
                            }
                            estadoAtual = "q20";
                            break;
                        case '/':
                            if (palavra.length() == 1) {
                                letraValida = true;
                                reservada = true;
                                isFinal = true;
                            }
                            estadoAtual = "q24";
                            break;
                        case '=':
                            if (palavra.length() == 1) {
                                letraValida = true;
                                reservada = true;
                                isFinal = true;
                            }
                            estadoAtual = "q19";
                            break;
                        case '(':
                            if (palavra.length() == 1) {
                                letraValida = true;
                                reservada = true;
                                isFinal = true;
                            }
                            estadoAtual = "q22";
                            break;
                        case ')':
                            if (palavra.length() == 1) {
                                letraValida = true;
                                reservada = true;
                                isFinal = true;
                            }
                            estadoAtual = "q23";
                            break;
                        case ';':
                            if (palavra.length() == 1) {
                                letraValida = true;
                                reservada = true;
                                isFinal = true;
                            }
                            estadoAtual = "q21";
                            break;
                        case 'i':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q3";
                            break;
                        case 'f':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q10";
                            break;
                        case '0':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '1':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '2':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '3':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '4':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '5':
                            isFinal = true;
                            estadoAtual = "q15";
                            letraValida = true;
                            break;
                        case '6':
                            estadoAtual = "q15";
                            isFinal = true;
                            letraValida = true;
                            break;
                        case '7':
                            estadoAtual = "q15";
                            isFinal = true;
                            letraValida = true;
                            break;
                        case '8':
                            estadoAtual = "q15";
                            isFinal = true;
                            letraValida = true;
                            break;
                        case '9':
                            estadoAtual = "q15";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            isFinal = true;
                            estadoAtual = "q2";
                            letraValida = true;
                            break;
                    }
                    break;
                case "q2":
                    reservada = false;
                    break;
                case "q3":
                    switch (letras[cont]) {
                        case 'n':
                            estadoAtual = "q4";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;
                case "q4":
                    switch (letras[cont]) {
                        case 'i':
                            estadoAtual = "q5";
                            isFinal = true;
                            letraValida = true;
                            break;
                        case 't':
                            if (palavra.equals("int")) {
                                reservada = true;
                            }
                            estadoAtual = "q9";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            //reservada = false;
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;
                case "q5":
                    switch (letras[cont]) {
                        case 'c':
                            estadoAtual = "q6";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;

                case "q6":
                    switch (letras[cont]) {
                        case 'i':
                            estadoAtual = "q7";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;

                case "q7":
                    switch (letras[cont]) {
                        case 'o':
                            reservada = false;
                            estadoAtual = "q8";
                            if (palavra.equals("inicio")) {
                                reservada = true;
                            }
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;

                case "q8":
                    switch (letras[cont]) {
                        case 'o':
                            if (palavra.equals("inicio")) {
                                reservada = true;
                            }
                            estadoAtual = "q8";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;
                case "q9":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;

                case "q10":
                    switch (letras[cont]) {
                        case 'l':
                            estadoAtual = "q11";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;

                case "q11":
                    switch (letras[cont]) {
                        case 'o':
                            estadoAtual = "q12";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;
                case "q12":
                    switch (letras[cont]) {
                        case 'a':
                            estadoAtual = "q13";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;
                case "q13":
                    switch (letras[cont]) {
                        case 't':
                            reservada = false;
                            if (palavra.equals("float")) {
                                reservada = true;
                            }
                            estadoAtual = "q14";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;
                case "q14":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;
                case "q15":
                    switch (letras[cont]) {
                        case '0':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '1':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '2':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '3':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '4':
                            isFinal = true;
                            letraValida = true;
                            estadoAtual = "q15";
                            break;
                        case '5':
                            isFinal = true;
                            estadoAtual = "q15";
                            letraValida = true;
                            break;
                        case '6':
                            estadoAtual = "q15";
                            isFinal = true;
                            letraValida = true;
                            break;
                        case '7':
                            estadoAtual = "q15";
                            isFinal = true;
                            letraValida = true;
                            break;
                        case '8':
                            estadoAtual = "q15";
                            isFinal = true;
                            letraValida = true;
                            break;
                        case '9':
                            estadoAtual = "q15";
                            isFinal = true;
                            letraValida = true;
                            break;
                        default:
                            isFinal = false;
                            letraValida = false;
                            break;
                    }
                    break;
                case "q17":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;
                case "q18":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;
                case "q19":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;
                case "q20":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;

                case "q21":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;

                case "q22":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;
                case "q23":
                    estadoAtual = "q2";
                    reservada = false;
                    isFinal = true;
                    letraValida = true;
                    break;
                case "q24":
                    switch (letras[cont]) {
                        case '*':
                            estadoAtual = "q25";
                            reservada = false;
                            isFinal = false;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q2";
                            reservada = false;
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;
                case "q25":
                    switch (letras[cont]) {
                        case '*':
                            estadoAtual = "q26";
                            reservada = false;
                            isFinal = false;
                            letraValida = true;
                            break;
                        default:
                            estadoAtual = "q25";
                            reservada = false;
                            isFinal = true;
                            letraValida = true;
                            break;
                    }
                    break;
                case "q26":
                    switch (letras[cont]) {
                        case '/':
                            estadoAtual = "q2";
                            reservada = true;
                            isFinal = true;
                            letraValida = true;
                            break;
                        case '*':
                            break;
                        default:
                            estadoAtual = "q25";
                            reservada = false;
                            isFinal = false;
                            letraValida = true;
                            break;
                    }
                    break;

            }
            if (letraValida) // Se a última letra verificada for válida
            {
                System.out.println("{" + estadoAtual + "}\n");                // Termina a linha do quadro de transição que começamos (linha 40), exemplo: {q2}
            } else // Se a última letra verificada não for válida
            {
                System.out.println("{ Caminho Inválido }");                 //Termina a linha do quadro de produção, dizendo que não há caminho
            }
            cont++;                                                         // Incremento do contador

        }

        if (!letraValida || !isFinal) {                                     // Se alguma letra não tiver sido válida ou a palavra tiver terminado em um estado não final
            System.out.println("Palavra não pertence à Linguagem.");        // A palavra é inválida para este autômato
        } else {                                                            // Caso contrário,
            gerarTipoDaPalavra();
            System.out.println("Palavra aceita pelo autômato! (" + tipo + ")");            // A palavra pertence! xD
        }
    }

}
