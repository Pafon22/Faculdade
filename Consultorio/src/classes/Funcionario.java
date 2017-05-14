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
public class Funcionario extends Pessoa {

    private double salario;
    private double horario;
    private String login;
    private String senha;
    private boolean permissao;
    private Scanner ler = new Scanner(System.in);

    public Funcionario() {
        super();
        salario = 0;
        horario = 0;
        login = "";
        senha = "";
        permissao = false;
    }

    public Funcionario(double salario, double horario, String login, String senha, boolean permissao) {
        this.salario = salario;
        this.horario = horario;
        this.login = login;
        this.senha = senha;
        this.permissao = permissao;
    }

    public void menuPermitir() {
        int op;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Admin\t\t\t\t*");
            System.out.println("*\t2 - Funcionario\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.print("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    setPermissao(true);
                    break;

                case 2:
                    permissao = false;
                    break;

                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 1 && op != 2);
    }

    public void preencherFuncionario() {
        super.preencher();
        System.out.println("Digite o salario: ");
        setSalario(getLer().nextDouble());
        System.out.println("Digite a carga horaria: ");
        setHorario(getLer().nextDouble());
        System.out.println("Digite o login: ");
        setLogin(getLer().next());
        System.out.println("Digite a senha: ");
        setSenha(getLer().next());
        System.out.println("Digite a permissão do funcionario: ");
        menuPermitir();
        System.out.println("TESTE -1");
    }

    /**
     * @return the salario
     */
    public double getSalario() {
        return salario;
    }

    /**
     * @param salario the salario to set
     */
    public void setSalario(double salario) {
        this.salario = salario;
    }

    /**
     * @return the horario
     */
    public double getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(double horario) {
        this.horario = horario;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the permissao
     */
    public boolean isPermissao() {
        return permissao;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setPermissao(boolean permissao) {
        this.permissao = permissao;
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
