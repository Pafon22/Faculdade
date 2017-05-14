/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package consultorio;

import Interfaces.TelaLogin;
import Interfaces.TelaPrincipal;
import classes.Consultorio;
import classes.FuncionarioBD;
import classes.Produto;

/**
 *
 * @author PauloAfonso
 */
public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       //Consultorio c = new Consultorio();
       //c.menuPrincipal();
       
      TelaLogin t = new TelaLogin();
      t.setVisible(true);
    }

}
