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
public class Matricula {

    private int codigoMatricula;
    private int codigoAluno;
    private int codigoCurso;
    private String data;

    public Matricula() {
        codigoMatricula = 0;
        codigoAluno = 0;
        codigoCurso = 0;
        data = "";
    }

    public Matricula(int codigoMatricula, int codigoAluno, int codigoCurso, String data) {
        this.codigoMatricula = codigoMatricula;
        this.codigoAluno = codigoAluno;
        this.codigoCurso = codigoCurso;
        this.data = data;
    }

    public void preencher(int codigoMatricula) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o código do Aluno: ");
        setCodigoAluno(ler.nextInt());
        System.out.println("Digite o código do Curso: ");
        setCodigoCurso(ler.nextInt());
        System.out.println("Digite a data da Matrícula: ");

    }

    public void preencherMatriculaPeloBanco(int codigoMatricula, int codigoAluno, int codigoCurso, String data) {
        this.setCodigoMatricula(codigoMatricula);
        this.setCodigoAluno(codigoAluno);
        this.setCodigoCurso(codigoCurso);
        this.setData(data);
    }

    public void imprimir() {
        System.out.println("Matrícula: " + codigoMatricula);
        System.out.println("Data: " + data);
    }

    /**
     * @return the codigoMatricula
     */
    public int getCodigoMatricula() {
        return codigoMatricula;
    }

    /**
     * @param codigoMatricula the codigoMatricula to set
     */
    public void setCodigoMatricula(int codigoMatricula) {
        this.codigoMatricula = codigoMatricula;
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
        this.setCodigoAluno(codigoAluno);
    }

    /**
     * @return the codigoCurso
     */
    public int getCodigoCurso() {
        return codigoCurso;
    }

    /**
     * @param codigoCurso the codigoCurso to set
     */
    public void setCodigoCurso(int codigoCurso) {
        this.setCodigoCurso(codigoCurso);
    }

    public String getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(String data) {
        this.data = data;
    }

    public String CSV() {
        String texto = getCodigoMatricula() + ";" + getCodigoAluno() + ";" + getCodigoCurso() + ";" + getData() + "\r\n";
        return texto;
    }

    public void carregarCSV(String texto) {
        String[] arrayTexto = texto.split(";");
        this.setCodigoMatricula(Integer.parseInt(arrayTexto[0]));
        this.setCodigoAluno(Integer.parseInt(arrayTexto[1]));
        this.setCodigoCurso(Integer.parseInt(arrayTexto[2]));
        this.setData(arrayTexto[3]);

    }

    public void inserirMatriculaBD() {
        try {
            PreparedStatement ps = Classes.Persistencia.conexao().prepareStatement("insert into matricula (codigoAluno, codigoCurso, data) values(?,?,?)");
            ps.setInt(1, codigoAluno);
            ps.setInt(2, codigoCurso);
            ps.setString(3, data);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Não foi possível executar o comando SQL: " + e);
        }
    }

    public void carregarMatriculaBD() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select * from matricula");
            rs = ps.executeQuery();

            DefaultTableModel dtm = new DefaultTableModel(new String[]{"codigoAluno", "codigoCurso"}, 0);
            while (rs.next()) {
                String dados[] = {rs.getString("codigoAluno"), rs.getString("codigoCurso")};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
    }
}
