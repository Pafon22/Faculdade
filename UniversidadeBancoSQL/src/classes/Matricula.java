/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Pafon
 */
public class Matricula {

    private Scanner ler = new Scanner(System.in);
    private int codigoMatricula = 0;
    private String dataMatricula;
    private int codigoAluno;
    private int codigoCurso;

    public Matricula() {
        codigoMatricula = 0;
        dataMatricula = null;
        codigoAluno = 0;
        codigoCurso = 0;
    }

    public Matricula(int codigoMatricula, String dataMatricula, int codigoAluno, int codigoCurso) {
        this.codigoMatricula = codigoMatricula;
        this.dataMatricula = dataMatricula;
        this.codigoAluno = codigoAluno;
        this.codigoCurso = codigoCurso;

    }

    public void gravarBancoMatricula() {
        PreparedStatement ps;
        try {
            ps = Persistencia.conexao().prepareStatement("insert into matricula(aluno_codigoAluno, curso_codigoCurso, dataMatricula) values (?,?,?)");

            ps.setInt(1, codigoAluno);
            ps.setInt(2, codigoCurso);
            ps.setString(3, dataMatricula);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void imprimirTodasMatriculas() {
        PreparedStatement ps;
        PreparedStatement ps1;
        PreparedStatement ps2;
        ResultSet rs;
        ResultSet rs1;
        ResultSet rs2;
        try {

            ps = Persistencia.conexao().prepareStatement("select * from matricula");
            rs = ps.executeQuery();

            System.out.println("\tCódigo Matrícula;\t Data Matrícula;\t Código Aluno;\t Código Curso\t");
            //  DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeAluno", "cpfAluno"}, 0);
            while (rs.next()) {

                ps1 = Persistencia.conexao().prepareStatement("select * from aluno where codigoAluno =" + rs.getInt("aluno_codigoAluno"));
                rs1 = ps1.executeQuery();
                rs1.next();
                ps2 = Persistencia.conexao().prepareStatement("select * from curso where codigoCurso =" + rs.getInt("curso_codigoCurso"));
                rs2 = ps2.executeQuery();
                rs2.next();
                int codigoMatricula2 = rs.getInt("codigoMatricula");
                String dataMatricula2 = rs.getString("dataMatricula");
                String nomeAluno = rs1.getString("nomeAluno");
                String nomeCurso = rs2.getString("nomeCurso");
                System.out.println("\t" + codigoMatricula2 + ";\t " + dataMatricula2 + ";\t " + nomeAluno + "\t; " + nomeCurso + "\t");
                rs1.close();
                rs2.close();
            }

            //String dados[] = {rs.getString("nomeAluno"), rs.getString("cpfAluno")};
            //   dtm.addRow(dados);
            System.out.println("\t--------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public void buscar() {
        PreparedStatement ps;
        PreparedStatement ps1;
        PreparedStatement ps2;
        ResultSet rs;
        ResultSet rs1;
        ResultSet rs2;
        try {
            System.out.println("Digite o número da Matrícula: ");
            int codigoMatricula2 = ler.nextInt();
            ps = Persistencia.conexao().prepareStatement("select * from matricula where codigoMatricula=?");
            ps.setInt(1, codigoMatricula2);
            rs = ps.executeQuery();

            System.out.println("\tCódigo Matrícula;\t Data Matrícula;\t Código Aluno;\t Código Curso\t");
            //  DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeAluno", "cpfAluno"}, 0);
            while (rs.next()) {

                ps1 = Persistencia.conexao().prepareStatement("select * from aluno where codigoAluno =" + rs.getInt("aluno_codigoAluno"));
                rs1 = ps1.executeQuery();
                rs1.next();
                ps2 = Persistencia.conexao().prepareStatement("select * from curso where codigoCurso =" + rs.getInt("curso_codigoCurso"));
                rs2 = ps2.executeQuery();
                rs2.next();
                int codigoMatricula3 = rs.getInt("codigoMatricula");
                String dataMatricula3 = rs.getString("dataMatricula");
                String nomeAluno = rs1.getString("nomeAluno");
                String nomeCurso = rs2.getString("nomeCurso");
                System.out.println("\t" + codigoMatricula3 + ";\t " + dataMatricula3 + ";\t " + nomeAluno + "\t; " + nomeCurso + "\t");
                rs1.close();
                rs2.close();
            }

            //String dados[] = {rs.getString("nomeAluno"), rs.getString("cpfAluno")};
            //   dtm.addRow(dados);
            System.out.println("\t--------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public boolean jaExisteMatricula() {
        try {
            java.sql.PreparedStatement ps;
            ps = Persistencia.conexao().prepareStatement("Select * from matricula where codigoMatricula=?");
            ps.setInt(1, codigoMatricula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.wasNull()) {
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Esse comando tá errado, pow!" + e);
        }
        return true;
    }

    public void removerMatricula(int codigoMatricula) {
        PreparedStatement ps;
        if (jaExisteMatricula()) {
            try {
                ps = Persistencia.conexao().prepareStatement("DELETE from matricula where codigoMatricula=?");
                ps.setInt(1, codigoMatricula);
                ps.executeUpdate();
                System.out.println("Matricula removida com sucesso.");
            } catch (SQLException ex) {
                Logger.getLogger(Aluno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Código não encontrado.");
        }
    }

    public void alterarMatricula(int codigoMatricula) {
        //System.out.println(codigoMatricula);
        PreparedStatement ps;
        if (jaExisteMatricula()) {
            /*System.out.println("Digite a nova Data Matricula: ");
            setDataMatricula(ler.next());*/
            try {
                ps = Persistencia.conexao().prepareStatement("UPDATE matricula set dataMatricula=?, aluno_codigoAluno=?, curso_codigoCurso=? WHERE codigoMatricula= ? ");
                ps.setString(1, dataMatricula);
                ps.setInt(2, codigoAluno);
                ps.setInt(3, codigoCurso);
                ps.setInt(4, codigoMatricula);
                ps.execute();
                System.out.println("Alterado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Não foi possivel executar o comando sql de alterar" + e);
            }
        } else {
            System.out.println("Codigo não encontrado!");
        }
    }

    public void preencher() {
        System.out.println("Informe o código do Aluno");
        codigoAluno = ler.nextInt();
        System.out.println("Informe o código do Curso");
        codigoCurso = ler.nextInt();
        System.out.println("Informe a Data da Matrícula");
        dataMatricula = ler.next();
    }

    public void preencher(int codigo) {
        System.out.println("Informe o código do Aluno");
        codigoAluno = ler.nextInt();
        System.out.println("Informe o código do Curso");
        codigoCurso = ler.nextInt();
        System.out.println("Informe a Data da Matrícula");
        dataMatricula = ler.next();
        this.codigoMatricula = codigo;
    }

    public void preencher(int codigoAluno, int codigoCurso, String data) {
        this.codigoAluno = codigoAluno;
        this.codigoCurso = codigoCurso;
        dataMatricula = data;
    }

    public void preencher(int codigoMatricula, int codigoAluno, int codigoCurso, String data) {
        this.codigoMatricula = codigoMatricula;
        this.codigoAluno = codigoAluno;
        this.codigoCurso = codigoCurso;
        dataMatricula = data;
    }

    public void imprimir() {
        System.out.println("Matrícula: " + codigoMatricula);
        System.out.println("Código do aluno: " + codigoAluno);
        System.out.println("Código do aluno: " + codigoCurso);
        System.out.println("Data da matricula: " + dataMatricula);

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
     * @return the dataMatricula
     */
    public String getDataMatricula() {
        return dataMatricula;
    }

    /**
     * @param dataMatricula the dataMatricula to set
     */
    public void setDataMatricula(String dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    /**
     * @return the codigo
     */
    public int getCodigoAluno() {
        return codigoAluno;
    }

    /**
     * @param codigoAluno
     */
    public void setCodigoAluno(int codigoAluno) {
        this.codigoAluno = codigoAluno;
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

    public String CSV() {
        String texto = getCodigoMatricula() + ";" + getCodigoAluno() + ";" + getCodigoCurso() + ";" + getDataMatricula() + "\r\n";
        return texto;
    }

    public void carregarCSV(String texto) {
        String[] arrayTexto = texto.split(";");
        this.setCodigoMatricula(Integer.parseInt(arrayTexto[0]));
        this.setCodigoAluno(Integer.parseInt(arrayTexto[1]));
        this.setCodigoCurso(Integer.parseInt(arrayTexto[2]));
        this.setDataMatricula(arrayTexto[3]);

    }

    public void carregarMatriculaBD() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from matricula");
            rs = ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"codigoMatricula", "aluno_codigoAluno", "curso_codigoCurso"}, codigoMatricula);
            while (rs.next()) {
                String dados[] = {rs.getString("codigoAluno"), rs.getString("codigoCurso")};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public DefaultTableModel dtmImprimirMatricula(String busca) {
        PreparedStatement ps;
        ResultSet rs;
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Matrícula", "Código do Aluno", "Código do Curso", "Data da Matrícula"}, 0);
        try {
            ps = Persistencia.conexao().prepareStatement("select * from matricula where codigoMatricula like '%" + busca + "%' or aluno_codigoAluno like '%" + busca + "%' or curso_codigoCurso like '%" + busca + "%' or dataMatricula like '%" + busca + "%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt("codigoMatricula"), rs.getString("aluno_codigoAluno"), rs.getInt("curso_codigoCurso"), rs.getString("dataMatricula")});
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }
        return dtm;
    }

}
