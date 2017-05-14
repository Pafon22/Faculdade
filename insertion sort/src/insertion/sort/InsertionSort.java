/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insertion.sort;

/*@author PauloAfonso
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] vetor = new int[5];
        for (int i = 0; i < 5; i++) {
            vetor[i] = i;
        }

        System.out.println("ANTES");
        for(int k=0;k<5;k++){
            
            System.out.println(vetor[k]);
            
        }
        
        
        int i, j, num;
        for (j = 1; j < vetor.length; j++) {
            num = vetor[j];
            for (i = j - 1; (i >= 0) && (vetor[i] < num); i--) {
                vetor[i + 1] = vetor[i];
            }
            vetor[i + 1] = num;
        }
        System.out.println("DEPOIS");
        for(int k=0;k<5;k++){
            
            System.out.println(vetor[k]);
        }
    }
}
