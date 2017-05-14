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
public class Paciente extends Pessoa {
    private int idPaciente;
    private boolean convenio;
    private String observacao;
    private Scanner ler = new Scanner(System.in);

    public Paciente() {
        super();
        convenio = false;
        observacao = "";
    }

    public Paciente(boolean convenio, String observacao) {
        
        this.convenio = convenio;
        this.observacao = observacao;
    }

    public void menuConvenio() {
        int op;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\tPOSSUI CONVENIO?\t\t\t\t*");
            System.out.println("*\t1 - Sim\t\t\t\t*");
            System.out.println("*\t2 - Não\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.print("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    setConvenio(true);
                    break;

                case 2:
                    setConvenio(false);
                    break;

                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 1 && op != 2);
    }

    public void preencherPaciente() {
        super.preencher();
        menuConvenio();
        System.out.println("Digite alguma observação:");
        observacao = ler.next();
    }
    
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
     * @return the idPaciente
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the convenio
     */
    public boolean isConvenio() {
        return convenio;
    }

    /**
     * @param convenio the convenio to set
     */
    public void setConvenio(boolean convenio) {
        this.convenio = convenio;
    }

    /**
     * @return the observacao
     */
    public String getObservacao() {
        return observacao;
    }

    /**
     * @param observacao the observacao to set
     */
    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

}
