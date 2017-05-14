/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author PauloAfonso
 */
public class Produto {

    private Scanner ler = new Scanner(System.in);
    private String nome;
    private int estoque;
    private float preco;
    private int qtdMinima;
    private String descricao;
    private Date validade;

    public Produto() {
        nome = "";
        estoque = 0;
        preco = 0;
        qtdMinima = 0;
        descricao = "";
        validade = new Date();

    }

    public Produto(String nome, int estoque, float preco, int qtdMinima, String descricao, Date validade) {

        this.nome = nome;
        this.preco = preco;
        this.qtdMinima = qtdMinima;
        this.descricao = descricao;
        this.validade = validade;

    }

    public void preencherProduto() {
        System.out.println("Digite o nome do Produto: ");
        nome = ler.next();
        System.out.println("Digite o preço do Produto:");
        preco = ler.nextFloat();
        System.out.println("Digite o estoque disponível:");
        estoque = ler.nextInt();
        System.out.println("Digite a quantidade mínima que deve ter no estoque:");
        qtdMinima = ler.nextInt();
        System.out.println("Digite a descrição do Produto:");
        descricao = ler.next();
        System.out.println("Digite a validade:");
        System.out.println("Digite o dia: ");
        int aux = ler.nextInt();
        validade.setDate(aux);
        System.out.println("Digite o mês: ");
        aux = ler.nextInt();
        validade.setMonth(aux);
        System.out.println("Digite o ano: ");
        aux = ler.nextInt();
        validade.setYear(aux);
        System.out.println("Validade:" + validade.getDate() + "/" + validade.getMonth() + "/+" + validade.getYear());

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the estoque
     */
    public int getEstoque() {
        return estoque;
    }

    /**
     * @param estoque the estoque to set
     */
    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    /**
     * @return the preco
     */
    public float getPreco() {
        return preco;
    }

    /**
     * @param preco the preco to set
     */
    public void setPreco(float preco) {
        this.preco = preco;
    }

    /**
     * @return the qtdMinima
     */
    public int getQtdMinima() {
        return qtdMinima;
    }

    /**
     * @param qtdMinima the qtdMinima to set
     */
    public void setQtdMinima(int qtdMinima) {
        this.qtdMinima = qtdMinima;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the validade
     */
    public Date getValidade() {
        return validade;
    }

    /**
     * @param validade the validade to set
     */
    public void setValidade(Date validade) {
        this.validade = validade;
    }

}
