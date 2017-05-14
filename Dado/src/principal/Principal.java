/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import classes.Tabuleiro;

/**
 *
 * @author PauloAfonso
 */
public class Principal {


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        Tabuleiro T = new Tabuleiro();
        T.menu();
    }

}
