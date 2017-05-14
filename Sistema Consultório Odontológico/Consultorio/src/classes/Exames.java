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
public class Exames {
    
    private Scanner ler = new Scanner(System.in);
    
    private String nomeExame;
    private float custoExame;
    
    public Exames() {
        nomeExame = "";
        custoExame = 0;

    }

    public Exames(String nomeExame, float custoExame) {

        this.nomeExame = nomeExame;
        this.custoExame = custoExame;

    }
    
    public void preencherExame() {
        System.out.println("Digite o nome do exame: ");
        setNomeExame(getLer().next());
        System.out.println("Digite o preço do Exame:");
        setCustoExame(getLer().nextFloat());

    }

    /**
     * @return the ler
     */
    public Scanner getLer() {
        return ler;
    }

    /**
     * @param ler the ler to set
     */
    public void setLer(Scanner ler) {
        this.ler = ler;
    }

    /**
     * @return the nomeExame
     */
    public String getNomeExame() {
        return nomeExame;
    }

    /**
     * @param nomeExame the nomeExame to set
     */
    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    /**
     * @return the custoExame
     */
    public float getCustoExame() {
        return custoExame;
    }

    /**
     * @param custoExame the custoExame to set
     */
    public void setCustoExame(float custoExame) {
        this.custoExame = custoExame;
    }

}
