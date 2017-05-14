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
public class Curso {

    private int codigoCurso;
    private String nomeCurso;
    private double cargaHorariaCurso;

    public Curso() {
        codigoCurso = 0;
        nomeCurso = "";
        cargaHorariaCurso = 0;
    }

    public Curso(int codigoCurso, String nomeCurso, double cargaHorariaCurso) {
        this.codigoCurso = codigoCurso;
        this.nomeCurso = nomeCurso;
        this.cargaHorariaCurso = cargaHorariaCurso;
    }

    public void preencher(int codigoCurso) {
        Scanner ler = new Scanner(System.in);
        System.out.println("Digite o nome do Curso: ");
        setNomeCurso(ler.next());
        System.out.println("Digite a Carga Horária do Curso: ");
        setCargaHorariaCurso(ler.nextDouble());

    }

    public void preencherCursoPeloBanco(int codigoCurso, String nomeCurso, double cargaHorariaCurso) {
        this.setCodigoCurso(codigoCurso);
        this.setNomeCurso(nomeCurso);
        this.setCargaHorariaCurso(cargaHorariaCurso);
    }

    public void imprimir() {
        System.out.println("Curso: " + nomeCurso);
        System.out.println("Carga Horária: " + cargaHorariaCurso);
        System.out.println("Código: " + codigoCurso);
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
        this.codigoCurso = codigoCurso;
    }

    /**
     * @return the nomeCurso
     */
    public String getNomeCurso() {
        return nomeCurso;
    }

    /**
     * @param nomeCurso the nomeCurso to set
     */
    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    /**
     * @return the cargaHorariaCurso
     */
    public double getCargaHorariaCurso() {
        return cargaHorariaCurso;
    }

    /**
     * @param cargaHorariaCurso the cargaHorariaCurso to set
     */
    public void setCargaHorariaCurso(double cargaHorariaCurso) {
        this.cargaHorariaCurso = cargaHorariaCurso;
    }

    public String CSV() {
        String texto = codigoCurso + ";" + nomeCurso + ";" + cargaHorariaCurso + "\r\n";
        return texto;
    }

    public void carregarCSV(String texto) {
        String[] arrayTexto = texto.split(";");
        this.codigoCurso = Integer.parseInt(arrayTexto[0]);
        this.nomeCurso = arrayTexto[1];
        this.cargaHorariaCurso = Integer.parseInt(arrayTexto[2]);
    }

    public void inserirCursoBD() {
        PreparedStatement ps = null;
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("insert into curso (nomeCurso, cargaHorariaCurso) values (?,?)");
            ps.setString(1, this.nomeCurso);
            ps.setDouble(2, this.cargaHorariaCurso);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Não foi possível executar o comando SQL: " + e);
        }
    }

    public void carregarCursoBD() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select * from curso");
            rs = ps.executeQuery();

            DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeCurso", "cargaHorariaCurso"}, 0);
            while (rs.next()) {
                String dados[] = {rs.getString("nomeCurso"), rs.getString("cargaHorariaCurso")};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Não foi possivel carregar o comando SQL");
        }
    }

}
