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
public class Aluno {

    private Scanner ler = new Scanner(System.in);
    private int codigoAluno;
    private String nomeAluno;
    private String cpfAluno;

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

    public void gravarBancoAluno() {
        PreparedStatement ps;
        try {
            ps = Persistencia.conexao().prepareStatement("insert into aluno(nomeAluno, cpfAluno) values (?,?)");
            ps.setString(1, nomeAluno);
            ps.setString(2, cpfAluno);
            ps.execute();
            ps.close();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void imprimirTodosAlunos() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from aluno WHERE nomeAluno=? or cpfAluno=?");
            ps.setString(1, nomeAluno);
            ps.setString(2, cpfAluno);
            rs = ps.executeQuery();
            System.out.println("\tCódigo;\tNome;\tCpfAluno\t");
            //  DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeAluno", "cpfAluno"}, 0);
            while (rs.next()) {

                int codigoAluno2 = rs.getInt("codigoAluno");
                String nomeAluno2 = rs.getString("nomeAluno");
                String cpfAluno2 = rs.getString("cpfAluno");
                System.out.println("\t" + codigoAluno2 + ";\t" + nomeAluno2 + ";\t" + cpfAluno2);
                //String dados[] = {rs.getString("nomeAluno"), rs.getString("cpfAluno")};
                //   dtm.addRow(dados);
            }
            System.out.println("\t--------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    /*
//DICAS DO MADAEL:
    //<!-- CARREGAR BANCO -->
    public ArrayList<Aluno> listaComTodosAlunos() {
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Aluno> a = new ArrayList<>();
        Aluno al = new Aluno();

        try {
            ps = Persistencia.conexao().prepareStatement("select * from aluno");
            rs = ps.executeQuery();
            //System.out.println("\tCódigo;\tNome;\tCpfAluno\t");
            //  DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeAluno", "cpfAluno"}, 0);
            while (rs.next()) {

                int codigoAluno2 = rs.getInt("codigoAluno");
                String nomeAluno2 = rs.getString("nomeAluno");
                String cpfAluno2 = rs.getString("cpfAluno");
                //System.out.println("\t" + codigoAluno2 + ";\t" + nomeAluno2 + ";\t" + cpfAluno2);
                //String dados[] = {rs.getString("nomeAluno"), rs.getString("cpfAluno")};
                //   dtm.addRow(dados); 
                al.setCodigoAluno(codigoAluno2);
                al.setNomeAluno(nomeAluno2);
                al.setCpfAluno(cpfAluno2);
                a.add(al);

            }
            System.out.println("\t--------------------------------------");
            rs.close();

        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }
        return a;
    }
     */
    public void buscar() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            String nome;
            System.out.println("Digite o nome do Aluno: ");
            nome = ler.next();
            ps = Persistencia.conexao().prepareStatement("select * from aluno WHERE nomeAluno=?");
            ps.setString(1, nome);
            rs = ps.executeQuery();
            System.out.println("\tCódigo;\tNome;\tCpfAluno\t");
            //  DefaultTableModel dtm = new DefaultTableModel(new String[]{"nomeAluno", "cpfAluno"}, 0);
            while (rs.next()) {
                int codigoAluno2 = rs.getInt("codigoAluno");
                String nomeAluno2 = rs.getString("nomeAluno");
                String cpfAluno2 = rs.getString("cpfAluno");
                System.out.println("\t" + codigoAluno2 + ";\t" + nomeAluno2 + ";\t" + cpfAluno2);
                //String dados[] = {rs.getString("nomeAluno"), rs.getString("cpfAluno")};
                //   dtm.addRow(dados);
            }
            System.out.println("\t--------------------------------------");
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public boolean jaExisteAluno() {
        try {
            java.sql.PreparedStatement ps;
            ps = Persistencia.conexao().prepareStatement("Select * from aluno where codigoAluno=?");
            ps.setInt(1, codigoAluno);
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

    public void removerAluno(int codigoAluno) {
        PreparedStatement ps;
        if (jaExisteAluno()) {
            try {
                ps = Persistencia.conexao().prepareStatement("DELETE from aluno where codigoAluno=?");
                ps.setInt(1, codigoAluno);
                ps.executeUpdate();
                System.out.println("Aluno removido com sucesso.");
            } catch (SQLException ex) {
                Logger.getLogger(Aluno.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            System.out.println("Código não encontrado.");
        }
    }

    /*    public void alterarAluno(int codigoAluno) {
        PreparedStatement ps = null;
        if (jaExisteAluno()) {
            System.out.println("Digite o novo nome do aluno: ");
            setNomeAluno(ler.next());
            System.out.println("Digite o novo cpf do aluno");
            setCpfAluno(ler.next());
            try {
                ps = Persistencia.conexao().prepareStatement("UPDATE aluno set nomeAluno =?, cpfAluno = ? WHERE codigoAluno = ? ");
                ps.setString(1, nomeAluno);
                ps.setString(2, cpfAluno);
                ps.setInt(3, codigoAluno);
                ps.execute();
                System.out.println("Alterado com sucesso");
            } catch (SQLException e) {
                System.out.println("Não foi possivel executar o comando sql de alterar" + e);
            }
        } else {
            System.out.println("Codigo não encontrado");
        }
    }
     */
    public void alterarAluno(int codigoAluno) {
        PreparedStatement ps;
        if (jaExisteAluno()) {
            /*
            System.out.println("Digite o nome do aluno: ");
            setNomeAluno(ler.next());
            System.out.println("Digite o cpf do aluno: ");
            setCpfAluno(ler.next());
             */
            try {

                ps = Persistencia.conexao().prepareStatement("UPDATE aluno set nomeAluno =?, cpfAluno = ? WHERE codigoAluno = ? ");
                ps.setString(1, nomeAluno);
                ps.setString(2, cpfAluno);
                ps.setInt(3, codigoAluno);
                ps.execute();
                System.out.println("Alterado com sucesso");
            } catch (SQLException e) {
                System.out.println("Não foi possivel executar o comando sql de alterar" + e);
            }
        } else {
            System.out.println("Codigo não encontrado");
        }
    }

    public void preencher() {
        System.out.println("Digite o nome do aluno: ");
        nomeAluno = ler.next();
        System.out.println("Digite o CPF: ");
        cpfAluno = ler.next();
    }

    public void preencher(int codAluno) {
        System.out.println("Informe o nome do Aluno: ");
        nomeAluno = ler.next();
        System.out.println("Informe o cpf do Aluno:");
        cpfAluno = ler.next();
        this.codigoAluno = codAluno;

    }

    public void preencher(String nome, String cpfAluno, int codigo) {
        nomeAluno = nome;
        this.cpfAluno = cpfAluno;
        codigoAluno = codigo;
    }

    public void imprimir() {
        System.out.println("----------------------------");
        System.out.println("Nome do Aluno: " + nomeAluno);
        System.out.println("CPF do Aluno: " + cpfAluno);

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
     * @return the codigo
     */
    public int getCodigoAluno() {
        return codigoAluno;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigoAluno(int codigo) {
        this.codigoAluno = codigo;
    }

    /**
     * @return the nome
     */
    public String getNomeAluno() {
        return nomeAluno;
    }

    /**
     * @param nome the nome to set
     */
    public void setNomeAluno(String nome) {
        this.nomeAluno = nome;
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

    public void carregarAlunoBD() {
        PreparedStatement ps;
        ResultSet rs;
        try {
            ps = Persistencia.conexao().prepareStatement("select * from aluno");
            rs = ps.executeQuery();
            DefaultTableModel dtm = new DefaultTableModel(new String[]{"codigoAluno", "nomeAluno", "cpfAluno"}, codigoAluno);
            while (rs.next()) {
                String dados[] = {rs.getString("nomeAluno"), rs.getString("cpfAluno")};
                dtm.addRow(dados);
            }
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    public DefaultTableModel dtmImprimirAluno(String busca) {
        PreparedStatement ps;
        ResultSet rs;
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Código", "Nome", "CPF"}, 0);
        try {
            ps = Persistencia.conexao().prepareStatement("select * from aluno where nomeAluno like '%" + busca + "%' or cpfAluno like '%" + busca + "%' or codigoAluno like '%" + busca + "%'");
            rs = ps.executeQuery();

            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt("codigoAluno"), rs.getString("nomeAluno"), rs.getString("cpfAluno")});
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }
        return dtm;
    }

}
