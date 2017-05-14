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
public class Dentista extends Funcionario {

    private int idDentista;
    private String registro;
    private String especialidade;
    private Scanner ler = new Scanner(System.in);

    public Dentista() {
        super();
        registro = "";
        especialidade = "";

    }

    public Dentista(String registro, String especialidade) {
        this.registro = registro;
        this.especialidade = especialidade;
    }

    public void preencherDentista() {
        super.preencherFuncionario();
        System.out.println("Digite o registro: ");
        setRegistro(getLer().next());
        System.out.println("Digite a especialidade: ");
        setEspecialidade(getLer().next());

    }

    /**
     * @return the idDentista
     */
    public int getIdDentista() {
        return idDentista;
    }

    /**
     * @param idDentista the idDentista to set
     */
    public void setIdDentista(int idDentista) {
        this.idDentista = idDentista;
    }

    /**
     * @return the registro
     */
    public String getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(String registro) {
        this.registro = registro;
    }

    /**
     * @return the especialidade
     */
    public String getEspecialidade() {
        return especialidade;
    }

    /**
     * @param especialidade the especialidade to set
     */
    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
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

}
