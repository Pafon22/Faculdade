/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author PauloAfonso, William, Jonas
 */
public class selectMediana {

    int[] a;

    public selectMediana() {
        int[] b = {1, 6, 3, 8, 9, 4, 7, 2, 5};
        a = b;

    }

    public int troca(int b, int c) {
        return b;
    }

    public int selection(int[] a, int ini, int fim, int k) {
        int pivoIndice, pivoValor, auxIndice;
        int i;
        pivoIndice = (ini + fim) / 2;
        pivoValor = a[pivoIndice];
        troca(a[pivoIndice], a[fim]);
        auxIndice = ini;
        for (i = ini; i <= fim; i++) {
            if (a[i] < pivoValor) {
                a[auxIndice] = troca(a[i], a[i]=a[auxIndice]);
                auxIndice = auxIndice + 1;
            }
            for (int j = 0; j < 9; j++) {
                System.out.print("aj[" + j + "] = " + a[j] + "; ");
            }
            System.out.println("\n");
            
        }
        troca(a[auxIndice], a[fim]);
        if (k == auxIndice) {
            for (int j = 0; j < 9; j++) {
                System.out.print("aj[" + j + "] = " + a[j] + "; ");
            }
            System.out.println("\n");
            return a[k];
        } else if (k < auxIndice) {
            return selection(a, ini, auxIndice - 1, k);
        } else {
            return selection(a, auxIndice + 1, fim, k);
        }
    }

    /**
     *
     */
    public void menu() {
        
            
            System.out.println(selection(a, 0, 8, 7));
        
    }

}
