/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author PauloAfonso
 */
public class Consultorio {

    protected PessoaBD pessoaBD;
    protected Pessoa pessoa;
    protected FuncionarioBD funcionarioBD;
    protected Funcionario funcionario;

    public Consultorio() {
        pessoaBD = new PessoaBD();
        pessoa = new Pessoa();
        funcionario = new Funcionario();
        funcionarioBD = new FuncionarioBD();
    }

    public void cadastrarPessoa() {
        pessoa.preencher();
        pessoaBD.setP(pessoa);
        pessoaBD.gravarPessoaNoBD(pessoa);
    }
    
    public void cadastrarFuncionario() {
        funcionario.preencherFuncionario();
        funcionarioBD.gravarFuncionarioNoBD(funcionario);
    }

    public void menuPrincipal() {
        int op;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Cadastrar\t\t\t\t*");
            System.out.println("*\t2 - Alterar\t\t\t\t*");
            System.out.println("*\t3 - Remover\t\t\t\t*");
            //System.out.println("*\t4 - Buscar\t\t\t\t*");
            System.out.println("*\t5 - Imprimir\t\t\t\t*");
            System.out.println("*\t0 - Sair\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.print("Digite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    menuCadastrar();
                    break;

                case 2:
                    menuAlterar();
                    break;

                case 3:
                    menuRemover();
                    break;

                case 4:
                    menuBuscar();
                    break;

                case 5:
                    menuImprimir();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 0);
    }

    public void menuCadastrar() {
        int op;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Cadastrar Funcionário\t\t*");
            System.out.println("*\t2 - Cadastrar Paciente\t\t\t*");
            System.out.println("*\t3 - Cadastrar Produto\t\t\t*");
            System.out.println("*\t4 - Cadastrar Consulta\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.print("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    cadastrarFuncionario();
                    break;

                case 2:
                    cadastrarPessoa();
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 99:
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 99);
    }

    public void menuAlterar() {
        int op, cod;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Alterar Funcionário\t\t\t*");
            System.out.println("*\t2 - Alterar Paciente\t\t\t*");
            System.out.println("*\t3 - Alterar Produto\t\t\t*");
            System.out.println("*\t4 - Alterar Consulta\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.println("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Digite o código do funcionário a ser alterado: ");
                    cod = ler.nextInt();
                    funcionarioBD.alterarFuncionarioNoBD(funcionario.getIdPessoa());
                    break;

                case 2:
                    pessoaBD.imprimirListaPessoasCadastradas();
                    System.out.println("Digite o código da Pessoa a ser alterada: ");
                    cod = ler.nextInt();
                    pessoaBD.alterarPessoaNoBD(cod);
                    
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 99:
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 99);
    }

    public void menuRemover() {
        int op;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Remover Funcionário\t\t\t*");
            System.out.println("*\t2 - Remover Paciente\t\t\t*");
            System.out.println("*\t3 - Remover Produto\t\t\t*");
            System.out.println("*\t4 - Remover Consulta\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.println("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    pessoaBD.imprimirListaPessoasCadastradas();
                    System.out.println("Digite o código da Pessoa a ser removida: ");
                    int cod = ler.nextInt();
                    pessoaBD.remover(cod);
                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 99:
                    break;
                default:
                    
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 99);
    }

    public void menuBuscar() {
        int op;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Buscar Funcionário\t\t\t*");
            System.out.println("*\t2 - Buscar Paciente\t\t\t*");
            System.out.println("*\t3 - Buscar Produto\t\t\t*");
            System.out.println("*\t4 - Buscar Consulta\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.println("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:

                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 99:
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 99);
    }

    public void menuImprimir() {
        int op;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Imprimir Funcionário\t\t*");
            System.out.println("*\t2 - Imprimir Paciente\t\t\t*");
            System.out.println("*\t3 - Imprimir Produto\t\t\t*");
            System.out.println("*\t4 - Imprimir Consulta\t\t\t*");
            System.out.println("*\t5 - Imprimir Todos\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.println("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:

                    break;

                case 2:
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    pessoaBD.imprimirListaPessoasCadastradas();
                    break;
                case 99:
                    break;
                default:
                    System.out.println("Opção Inválida!");
                    break;
            }
        } while (op != 99);
    }

}
