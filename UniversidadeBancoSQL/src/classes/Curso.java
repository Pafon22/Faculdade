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
public class Curso {

    private Scanner ler = new Scanner(System.in);
    private int codigoCurso;
    private String nomeCurso;
    private int cargaHorariaCurso;

    public Curso() {
        codigoCurso = 0;
        nomeCurso = "";
        cargaHorariaCurso = 0;

    }

    public Curso(int codigoCurso, String nomeCurso, int cargaHorariaCurso) {
        this.codigoCurso = codigoCurso;
        this.nomeCurso = nomeCurso;
        this.cargaHorariaCurso = cargaHorariaCurso;

    }

    public void gravarBancoCurso() {
        PreparedStatement ps;
        try {
            ps = Persistencia.conexao().prepareStatement("insert into curso(nomeCurso, cargaHorariaCurso) values (?,?)");
            ps.setString(1, nomeCurso);
            ps.setInt(2, cargaHorariaCurso);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void imprimirTodosCursos() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from curso WHERE nomeCurso like? or cargaHorariaCurso Like ?");
            ps.setString(1, nomeCurso + "%");
            ps.setString(2, cargaHorariaCurso + "%");
            rs = ps.executeQuery();
            System.out.println("\tCódigo;\tNome;\tCarga Horária\t");
            //  DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeCurso", "cargaHorariaCurso"}, 0);
            while (rs.next()) {

                int codigoCurso2 = rs.getInt("codigoCurso");
                String nomeCurso2 = rs.getString("nomeCurso");
                int cargaHorariaCurso2 = rs.getInt("cargaHorariaCurso");
                System.out.println("\t" + codigoCurso2 + ";\t" + nomeCurso2 + ";\t" + cargaHorariaCurso2);
                //String dados[] = {rs.getString("nomeCurso"), rs.getString("cargaHorariaCurso")};
                //   dtm.addRow(dados);
            }
            System.out.println("\t--------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public void buscar() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String nome;
            System.out.println("Digite o nome do Curso: ");
            nome = ler.next();
            ps = Persistencia.conexao().prepareStatement("select * from curso WHERE nomeCurso=?");
            ps.setString(1, nome);
            rs = ps.executeQuery();
            System.out.println("\tCódigo;\tNome;\tCarga Horária\t");
            //  DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeCurso", "cargaHorariaCurso"}, 0);
            while (rs.next()) {
                int codigoCurso2 = rs.getInt("codigoCurso");
                String nomeCurso2 = rs.getString("nomeCurso");
                int cargaHorariaCurso2 = rs.getInt("cargaHorariaCurso");
                System.out.println("\t" + codigoCurso2 + ";\t" + nomeCurso2 + ";\t" + cargaHorariaCurso2);
                //String dados[] = {rs.getString("nomeCurso"), rs.getString("cargaHorariaCurso")};
                //   dtm.addRow(dados);
            }
            System.out.println("\t--------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public boolean jaExisteCurso() {
        try {
            java.sql.PreparedStatement ps;
            ps = Persistencia.conexao().prepareStatement("Select * from curso where codigoCurso=?");
            ps.setInt(1, codigoCurso);
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

    public void removerCurso(int codigoCurso) {
        PreparedStatement ps;
        if (jaExisteCurso()) {
            try {
                ps = Persistencia.conexao().prepareStatement("DELETE from curso where codigoCurso=?");
                ps.setInt(1, codigoCurso);
                ps.executeUpdate();
                System.out.println("Curso removido com sucesso.");
            } catch (SQLException ex) {
                Logger.getLogger(Curso.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Codigo não encontrado.");
        }
    }

    public void alterarCurso(int codigoCurso) {
        PreparedStatement ps;
        if (jaExisteCurso()) {
            /*
            System.out.println("Digite o novo nome do curso: ");
            setNomeCurso(ler.next());
            System.out.println("Digite a nova carga horária do curso: ");
            setCargaHorariaCurso(ler.nextInt());
             */
            try {
                ps = Persistencia.conexao().prepareStatement("UPDATE curso set nomeCurso =?, cargaHorariaCurso= ? WHERE codigoCurso= ? ");
                ps.setString(1, nomeCurso);
                ps.setInt(2, cargaHorariaCurso);
                ps.setInt(3, codigoCurso);
                ps.execute();
                System.out.println("Alterado com sucesso");
            } catch (SQLException e) {
                System.out.println("Não foi possivel executar o comando sql de alterar" + e);
            }
        } else {
            System.out.println("Codigo não encontrado");
        }
    }

    public void preencherCurso() {
        System.out.println("Digite o nome do curso: ");
        nomeCurso = ler.next();
        System.out.println("Digite a carga horária do curso: ");
        cargaHorariaCurso = ler.nextInt();
    }

    public void preencher(int codigoCurso) {
        System.out.println("\nDigite o nome do curso: ");
        nomeCurso = ler.next();
        System.out.println("Digite a carga horaria do curso: ");
        cargaHorariaCurso = ler.nextInt();
        this.codigoCurso = codigoCurso;
    }

    public void preencher(String nomeCurso, int cargaHorariaCurso, int codigoCurso) {
        this.nomeCurso = nomeCurso;
        this.cargaHorariaCurso = cargaHorariaCurso;
        this.codigoCurso = codigoCurso;
    }

    public void imprimir() {
        System.out.println("------------------------------");
        System.out.println("Nome do Curso: " + nomeCurso);
        System.out.println("Carga Horária: " + cargaHorariaCurso);
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
    public int getCargaHorariaCurso() {
        return cargaHorariaCurso;
    }

    /**
     * @param cargaHorariaCurso the cargaHorariaCurso to set
     */
    public void setCargaHorariaCurso(int cargaHorariaCurso) {
        this.cargaHorariaCurso = cargaHorariaCurso;
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

    public int getCodigo() {
        return codigoCurso;
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

    public void carregarCursoBD() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from curso");
            rs = ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"codigoCurso", "nomeCurso", "cargaHorariaCurso"}, codigoCurso);
            while (rs.next()) {
                String dados[] = {rs.getString("nomeCurso"), rs.getString("cargaHorariaCurso")};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    /**
     *
     * @param busca
     * @return
     */
    public DefaultTableModel dtmImprimirCurso(String busca) {
        PreparedStatement ps;
        ResultSet rs;
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Código", "Nome", "Carga Horária"}, 0);
        try {
            ps = Persistencia.conexao().prepareStatement("select * from curso where nomeCurso like '%" + busca + "%' or cargaHorariaCurso like '%" + busca + "%' or codigoCurso like '%" + busca + "%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt("codigoCurso"), rs.getString("nomeCurso"), rs.getInt("cargaHorariaAluno")});
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }
        return dtm;
    }

}
