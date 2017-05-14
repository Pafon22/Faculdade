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
import classes.Pessoa;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PauloAfonso
 */
public class PessoaBD {

    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Pessoa p;

    public PessoaBD() {
        ps = null;
        rs = null;
        p = new Pessoa();
    }

    public PessoaBD(PreparedStatement ps, ResultSet rs, Pessoa p) {
        this.ps = ps;
        this.rs = rs;
        this.p = p;
    }

    public int buscar(String cpf) {
       PreparedStatement ps = null;
        ResultSet rs = null;

        int idPessoa = 0;
        try {

            ps = Persistencia.conexao().prepareStatement("select * from pessoa WHERE cpf=?");
            ps.setString(1, cpf);
            rs = ps.executeQuery();
            while (rs.next()) {
                idPessoa = rs.getInt("idPessoa");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }
        return idPessoa;
    }

    public void gravarPessoaNoBD(Pessoa p) {
        try {
            ps = Persistencia.conexao().prepareStatement("Insert into pessoa(nome, cpf, email, sexo, telefone, rua, numero, complemento, bairro, cidade, uf) values (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1, p.getNome());
            ps.setString(2, p.getCpf());
            ps.setString(3, p.getEmail());
            ps.setString(4, p.getSexo());
            ps.setString(5, p.getTelefone());
            ps.setString(6, p.getRua());
            ps.setInt(7, p.getNumero());
            ps.setString(8, p.getComplemento());
            ps.setString(9, p.getBairro());
            ps.setString(10, p.getCidade());
            ps.setString(11, p.getUf());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void imprimirListaPessoasCadastradas() {

        try {
            ps = Persistencia.conexao().prepareStatement("select* from pessoa");
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t|Nome\t\t|CPF\t\t|E-mail\t\t\t|Sexo\t|Telefone");
            while (rs.next()) {
                int id = rs.getInt("idPessoa");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                String sexo = rs.getString("sexo");
                String telefone = rs.getString("telefone");

                System.out.println("|" + id + "\t|" + nome + "\t\t|" + cpf + "\t\t|" + email + "\t\t\t|" + sexo + "\t|" + telefone);
                System.out.println("");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public void imprimirPessoa(int cod) {

        try {
            ps = Persistencia.conexao().prepareStatement("select* from pessoa where idPessoa=?");
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t|Nome\t\t|CPF\t\t|E-mail\t\t\t|Sexo\t|Telefone");
            while (rs.next()) {
                int id = rs.getInt("idPessoa");
                String nome = rs.getString("nome");
                String cpf = rs.getString("cpf");
                String email = rs.getString("email");
                String sexo = rs.getString("sexo");
                String telefone = rs.getString("telefone");

                System.out.println("|" + id + "\t|" + nome + "\t\t|" + cpf + "\t\t|" + email + "\t\t\t|" + sexo + "\t|" + telefone);
                System.out.println("");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public boolean jaExiste(int codigo) {
        try {
            ps = Persistencia.conexao().prepareStatement("Select * from pessoa where idPessoa=?");
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.wasNull()) {
                return false;
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Esse comando tá errado, pow!" + e);
        }
        return true;
    }
    
    public void atualizarPessoa(Paciente pac) {
        p.setNome(pac.getNome());
        p.setCpf(pac.getCpf());
        p.setEmail(pac.getEmail());
        p.setSexo(pac.getSexo());
        p.setTelefone(pac.getTelefone());
        p.setRua(pac.getRua());
        p.setNumero(pac.getNumero());
        p.setComplemento(pac.getComplemento());
        p.setBairro(pac.getBairro());
        p.setCidade(pac.getCidade());
        p.setUf(pac.getUf());

    }

    public void atualizarPessoa(Funcionario f) {
        p.setNome(f.getNome());
        p.setCpf(f.getCpf());
        p.setEmail(f.getEmail());
        p.setSexo(f.getSexo());
        p.setTelefone(f.getTelefone());
        p.setRua(f.getRua());
        p.setNumero(f.getNumero());
        p.setComplemento(f.getComplemento());
        p.setBairro(f.getBairro());
        p.setCidade(f.getCidade());
        p.setUf(f.getUf());

    }

    
    
public void atualizarPessoa(Dentista dent) {
        p.setNome(dent.getNome());
        p.setCpf(dent.getCpf());
        p.setEmail(dent.getEmail());
        p.setSexo(dent.getSexo());
        p.setTelefone(dent.getTelefone());
        p.setRua(dent.getRua());
        p.setNumero(dent.getNumero());
        p.setComplemento(dent.getComplemento());
        p.setBairro(dent.getBairro());
        p.setCidade(dent.getCidade());
        p.setUf(dent.getUf());

    }

    public void alterarPessoaNoBD(int codigo) {
        if (jaExiste(codigo)) {

            try {
                ps = Persistencia.conexao().prepareStatement("Update pessoa set nome=?, cpf=?, email=?, sexo=?, telefone=?, rua=?, numero=?, complemento=?, bairro=?, cidade=?, uf=? where idPessoa=?");
                ps.setString(1, p.getNome());
                ps.setString(2, p.getCpf());
                ps.setString(3, p.getEmail());
                ps.setString(4, p.getSexo());
                ps.setString(5, p.getTelefone());
                ps.setString(6, p.getRua());
                ps.setInt(7, p.getNumero());
                ps.setString(8, p.getComplemento());
                ps.setString(9, p.getBairro());
                ps.setString(10, p.getCidade());
                ps.setString(11, p.getUf());
                ps.setInt(12, codigo);
                ps.execute();

            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }

    public void removerPessoa(int codigo) {
        if (jaExiste(codigo)) {
            try {
                ps = Persistencia.conexao().prepareStatement("Delete from pessoa where idPessoa=?");
                ps.setInt(1, codigo);
                ps.execute();
                

            } catch (SQLException e) {
                Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }

    /**
     * @return the ps
     */
    public PreparedStatement getPs() {
        return ps;
    }

    /**
     * @param ps the ps to set
     */
    public void setPs(PreparedStatement ps) {
        this.ps = ps;
    }

    /**
     * @return the rs
     */
    public ResultSet getRs() {
        return rs;
    }

    /**
     * @param rs the rs to set
     */
    public void setRs(ResultSet rs) {
        this.rs = rs;
    }

    /**
     * @return the p
     */
    public Pessoa getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Pessoa p) {
        this.p = p;
    }
}
