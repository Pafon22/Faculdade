/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PauloAfonso
 */
public class Aluno {

    private int codigoAluno;
    private String nomeAluno;
    private String cpfAluno;
    PreparedStatement ps = null;

    public Aluno() {
        codigoAluno = 0;
        nomeAluno = "";
        cpfAluno = "";
    }

    public Aluno(int codigoAluno, String nomeAluno, String cpfAluno) {
        this.codigoAluno = codigoAluno;
        this.nomeAluno = nomeAluno;
        this.cpfAluno = cpfAluno;
    }

    public void preencher() {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o nome do Aluno: ");
        setNomeAluno(ler.next());
        System.out.println("Digite o CPF: ");
        setCpfAluno(ler.next());
        

    }

    public void preencherAlunoPeloBanco(int codigoAluno, String nomeAluno, String cpfAluno) {
        this.setCodigoAluno(codigoAluno);
        this.setNomeAluno(nomeAluno);
        this.setCpfAluno(cpfAluno);
    }

    public void imprimir() {
        System.out.println("Nome: " + nomeAluno);
        System.out.println("CPF: " + cpfAluno);
        System.out.println("Código: " + codigoAluno);
    }

    /**
     * @return the codigoAluno
     */
    public int getCodigoAluno() {
        return codigoAluno;
    }

    /**
     * @param codigoAluno the codigoAluno to set
     */
    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
    }

    /**
     * @return the nomeAluno
     */
    public String getNomeAluno() {
        return nomeAluno;
    }

    /**
     * @param nomeAluno the nomeAluno to set
     */
    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    /**
     * @return the cpfAluno
     */
    public String getCpfAluno() {
        return cpfAluno;
    }

    /**
     * @param cpfAluno the cpfAluno to set
     */
    public void setCpfAluno(String cpfAluno) {
        this.cpfAluno = cpfAluno;
    }

    public String CSV() {
        String texto = codigoAluno + ";" + nomeAluno + ";" + cpfAluno + "\r\n";
        return texto;
    }

    public void carregarCSV(String texto) {
        String[] arrayTexto = texto.split(";");
        this.codigoAluno = Integer.parseInt(arrayTexto[0]);
        this.nomeAluno = arrayTexto[1];
        this.cpfAluno = arrayTexto[2];
    }

    public void inserirAlunoBD() {

        try {
            ps = Persistencia.conexao().prepareStatement("Insert into aluno (nomeAluno, cpfAluno) values (?,?)");
            ps.setString(1, nomeAluno);
            ps.setString(2, cpfAluno);
            

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Não foi possível executar o comando SQL: " + e);
        }
    }

    public void carregarAlunoBD() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select * from aluno");
            rs = ps.executeQuery();

            DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeAluno", "cpf"}, 0);
            while (rs.next()) {
                String dados[] = {rs.getString("nomeAluno"), rs.getString("cpf")};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
    }
}
