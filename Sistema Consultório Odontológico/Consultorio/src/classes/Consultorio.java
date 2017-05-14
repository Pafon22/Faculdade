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
    protected Paciente paciente;
    protected PacienteBD pacienteBD;
    protected FuncionarioBD funcionarioBD;
    protected Funcionario funcionario;
    protected Dentista dentista;
    protected DentistaBD dentistaBD;
    protected Consulta consulta;
    protected ConsultaBD consultaBD;
    protected Produto produto;
    protected ProdutoBD produtoBD;
    protected Exames exames;
    protected ExamesBD examesBD;

    public Consultorio() {
        pessoaBD = new PessoaBD();
        pessoa = new Pessoa();
        paciente = new Paciente();
        pacienteBD = new PacienteBD();
        funcionario = new Funcionario();
        funcionarioBD = new FuncionarioBD();
        consulta = new Consulta();
        consultaBD = new ConsultaBD();
        dentista = new Dentista();
        dentistaBD = new DentistaBD();
        produto = new Produto();
        produtoBD = new ProdutoBD();
        exames = new Exames();
        examesBD = new ExamesBD();
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
    public void cadastrarPaciente() {
        paciente.preencherPaciente();
        pacienteBD.gravarPacienteNoBD(paciente);
    }
    
    public void cadastrarConsulta(){
        
    }
    
    public void cadastrarProduto() {
        produto.preencherProduto();
        produtoBD.setP(produto);
        produtoBD.gravarProdutoNoBD(produto);
    }
    
    public void cadastrarDentista() {
        dentista.preencherDentista();
        System.out.println("0");
        dentistaBD.gravarDentistaNoBD(dentista);
    }
    
    public void cadastrarExame(){
        exames.preencherExame();
        examesBD.setE(exames);
        examesBD.gravarExameNoBD(exames);
    }

    public void menuPrincipal() {
        int op;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Cadastrar\t\t\t\t*");
            System.out.println("*\t2 - Alterar\t\t\t\t*");
            System.out.println("*\t3 - Remover\t\t\t\t*");
            System.out.println("*\t4 - Buscar\t\t\t\t*");
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
            System.out.println("*\t4 - Cadastrar Exames\t\t\t*");
            System.out.println("*\t5 - Cadastrar Dentista\t\t\t*");
            System.out.println("*\t6 - Cadastrar Pessoa\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.print("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    cadastrarFuncionario();
                    break;

                case 2:
                    cadastrarPaciente();
                    break;

                case 3:
                    cadastrarProduto();
                    break;

                case 4:
                    cadastrarExame();
                    break;

                case 5:
                    cadastrarDentista();
                    break;
                case 6:
                    cadastrarPessoa();
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
            System.out.println("*\t4 - Alterar Exames\t\t\t*");
            System.out.println("*\t5 - Alterar Dentista\t\t\t*");
            System.out.println("*\t6 - Alterar Pessoa\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.println("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    funcionarioBD.imprimirListaFuncionariosCadastrados();
                    System.out.println("Digite o código do Funcionário a ser alterado: ");
                    cod = ler.nextInt();
                    funcionarioBD.alterarFuncionarioNoBD(cod);
                    break;

                case 2:
                    pacienteBD.imprimirListaPacientesCadastrados();
                    System.out.println("Digite o código do Paciente a ser alterado: ");
                    cod = ler.nextInt();
                    pacienteBD.alterarPacienteNoBD(cod);

                    break;

                case 3:
                    produtoBD.imprimirListaProdutosCadastrados();
                    System.out.println("Digite o código do Produto a ser alterado: ");
                    cod = ler.nextInt();
                    produtoBD.alterarProdutoNoBD(cod);
                    break;

                case 4:
                    examesBD.imprimirListaExamesCadastrados();
                    System.out.println("Digite o código do Produto a ser alterado: ");
                    cod = ler.nextInt();
                    examesBD.alterarExamesNoBD(cod);
                    break;
                    
                case 5:
                    dentistaBD.imprimirListaDentistasCadastrados();
                    System.out.println("Digite o código do Dentista a ser alterado: ");
                    cod = ler.nextInt();
                    dentistaBD.alterarDentistaNoBD(cod);
                    break;
                case 6:
                    pessoaBD.imprimirListaPessoasCadastradas();
                    System.out.println("Digite o código da Pessoa a ser alterada: ");
                    cod = ler.nextInt();
                    pessoaBD.alterarPessoaNoBD(cod);
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
        int op, cod;
        Scanner ler = new Scanner(System.in);
        do {
            System.out.println("*************************************************");
            System.out.println("*\t1 - Remover Funcionário\t\t\t*");
            System.out.println("*\t2 - Remover Paciente\t\t\t*");
            System.out.println("*\t3 - Remover Produto\t\t\t*");
            System.out.println("*\t4 - Remover Exames\t\t\t*");
            System.out.println("*\t5 - Remover Dentisa\t\t\t*");
            System.out.println("*\t6 - Remover Pessoa\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.println("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    funcionarioBD.imprimirListaFuncionariosCadastrados();
                    System.out.println("Digite o código do Funcionario a ser removido: ");
                    cod = ler.nextInt();
                    funcionarioBD.removerFuncionario(cod);
                    break;
                case 2:
                    pacienteBD.imprimirListaPacientesCadastrados();
                    System.out.println("Digite o código do Paciente a ser removido: ");
                    cod = ler.nextInt();
                    pacienteBD.removerPaciente(cod);
                    break;
                case 3:
                    produtoBD.imprimirListaProdutosCadastrados();
                    System.out.println("Digite o código do Produto a ser removido: ");
                    cod = ler.nextInt();
                    produtoBD.removerProduto(cod);
                    break;
                case 4:
                    examesBD.imprimirListaExamesCadastrados();
                    System.out.println("Digite o código do Produto a ser removido: ");
                    cod = ler.nextInt();
                    examesBD.removerExames(cod);
                    break;
                case 5:
                    dentistaBD.imprimirListaDentistasCadastrados();
                    System.out.println("Digite o código do Dentista a ser removido: ");
                    cod = ler.nextInt();
                    dentistaBD.removerDentista(cod);
                    break;
                case 6:
                    pessoaBD.imprimirListaPessoasCadastradas();
                    System.out.println("Digite o código da Pessoa a ser removida: ");
                    cod = ler.nextInt();
                    pessoaBD.removerPessoa(cod);
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
            System.out.println("*\t4 - Buscar Exames\t\t\t*");
            System.out.println("*\t99 - Voltar\t\t\t\t*");
            System.out.println("*************************************************");
            System.out.println("\tDigite uma opção: ");
            op = ler.nextInt();
            switch (op) {
                case 1:
                    System.out.println("digite");
                    int cod=ler.nextInt();
                    funcionarioBD.buscarIdPessoa(cod);
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
                    funcionarioBD.imprimirListaFuncionariosCadastrados();
                    break;

                case 2:
                    pacienteBD.imprimirListaPacientesCadastrados();
                    break;

                case 3:
                    produtoBD.imprimirListaProdutosCadastrados();
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
