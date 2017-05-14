/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Scanner;

/**
 *
 * @author PauloAfonso
 */
public class BuscaBinaria {

    public int busca(int[] A, int v, int menor, int maior) {
        // while(menor<=maior){
        if (menor > maior) {
            return -1;
        }
        int meio = (maior + menor) / 2;
        if (v == A[meio]) {
            return meio;
        } else if (v > A[meio]) {
            //      menor = meio+1;

            busca(A, v, meio + 1, maior);
        } else {
            //   maior = meio - 1;
            busca(A, v, menor, meio - 1);
        }
    }
    
    

    public static void main(String[] args) {
        // TODO code application logic here
        int[] A = {1, 7, 2, 6, 9, 5};
        BuscaBinaria B = new BuscaBinaria();
        SelectSort S = new SelectSort();
        S.selectionSort(A);
        Scanner ler = new Scanner(System.in);
        S.testeImprimir();
        System.out.println("Qual valor deseja buscar?");
        int v = ler.nextInt();
        System.out.println("O número " + v + " está no índice: " + B.busca(A, v, 0, 5));

    }

}
