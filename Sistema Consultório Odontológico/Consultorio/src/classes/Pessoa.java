/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import Classes.Persistencia;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author PauloAfonso
 */
public class Pessoa {

    private int idPessoa;
    private String cpf;
    private String email;
    private int idade;
    private String nome;
    private String sexo;
    private String telefone;
    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String uf;

    public Pessoa() {
        idPessoa = 0;
        cpf = "";
        email = "";
        // endereco = "";
        idade = 0;
        //   nascimento = "1/1/1";
        nome = "";
        sexo = "";
        telefone = "";
        rua = "";
        numero = 0;
        complemento = "";
        bairro = "";
        cidade = "";
        uf = "";

    }

    public Pessoa(int idPessoa, String cpf, String email, String endereco, int idade, String nascimento, String nome, String sexo, String telefone, String rua, int numero, String complemento, String bairro, String cidade, String uf) {
        this.idPessoa = idPessoa;
        this.cpf = cpf;
        this.email = email;
        this.idade = idade;
        this.nome = nome;
        this.sexo = sexo;
        this.telefone = telefone;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the idade to set
     */
    public void setIdade(int idade) {
        this.idade = idade;
    }

    /**
     * @return the sexo
     */
    public String getSexo() {
        return sexo;
    }

    /**
     * @param sexo the sexo to set
     */
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void preencher() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o nome: ");
        setNome(ler.next());
        System.out.println("Digite o cpf: ");
        setCpf(ler.next());
        System.out.println("Digite o idade: ");
        //setIdade(ler.next());
        System.out.println("Digite o e-mail: ");
        setEmail(ler.next());
        System.out.println("Digite o sexo: ");
        setSexo(ler.next());
        System.out.println("Digite o telefone: ");
        setTelefone(ler.next());
        System.out.println("Digite o nome da rua: ");
        setRua(ler.next());
        System.out.println("Digite o numero : ");
        setNumero(ler.nextInt());
        System.out.println("Digite o complemento: ");
        setComplemento(ler.next());
        System.out.println("Digite o nome do bairro: ");
        setBairro(ler.next());
        System.out.println("Digite o nome da cidade: ");
        setCidade(ler.next());
        System.out.println("Digite o UF");
        setUf(ler.next());
    }

    /**
     * @return the idPessoa
     */
    public int getIdPessoa() {
        return idPessoa;
    }

    /**
     * @param idPessoa the idPessoa to set
     */
    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

    /**
     * @param rua the rua to set
     */
    public void setRua(String rua) {
        this.rua = rua;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf;
    }

}
