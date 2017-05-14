/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author PauloAfonso
 */
public class SelectSort {

    public void selectionSort(int[] A) {
        int n = A.length; // N RECEBE O TAMANHO DO VETOR
        for (int j = 0; j < n - 1; j++) { // J PASSA DO PRIMEIRO ELEMENTO ATÉ O PENULTIMO
            int menor = j; // MENOR ELEMENTO RECEBE JOTA
            for (int i = j + 1; i < n; i++) { // I PASSA DO SEGUNDO ELEMENTO ATÉ O ULTIMO
                if (A[i] < A[menor]) { // SE ELEMENTO I FOR MENOR QUE ELEMENTO J (menor recebeu jota ali em cima)
                    menor = i; // MENOR RECEBE I
                }
            }
            int aux = A[menor]; //TROCA POSIÇÃO I COM POSIÇÃO J
            A[menor] = A[j];
            A[j] = aux;
        }
    }

    public void testeImprimir() {
        SelectSort S = new SelectSort();
        int[] A = {1, 7, 2, 6, 9, 5};
        S.selectionSort(A);
        System.out.print("A = {");
        for (int i = 0; i < A.length; i++) {
            System.out.print(A[i] + ",");
        }
        System.out.println("}");
    }

    public static void main(String[] args) {

    }

}
