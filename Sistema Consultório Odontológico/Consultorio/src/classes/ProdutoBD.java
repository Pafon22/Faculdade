/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PauloAfonso
 */
public class ProdutoBD {
    
    protected PreparedStatement ps;
    protected ResultSet rs;
    private Produto p;

    public ProdutoBD() {
        ps = null;
        rs = null;
        p = new Produto();
    }

    public ProdutoBD(PreparedStatement ps, ResultSet rs, Produto p) {
        this.ps = ps;
        this.rs = rs;
        this.p = p;
    }
    
    public void gravarProdutoNoBD(Produto p) {
        try {
            String data;
            ps = Classes.Persistencia.conexao().prepareStatement("Insert into produto(nome, estoque, preco, quantidadeMinima, descricao, validade) values (?,?,?,?,?,?)");
            ps.setString(1, p.getNome());
            ps.setInt(2, p.getEstoque());
            ps.setDouble(3, p.getPreco());
            ps.setInt(4, p.getQtdMinima());
            ps.setString(5, p.getDescricao());
            data = (p.getValidade().getDate()+"/"+p.getValidade().getMonth()+"/"+p.getValidade().getYear());
            ps.setString(6, data);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public void imprimirListaProdutosCadastrados() {

        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select* from produto");
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t|Nome\t\t|Estoque\t\t\t|Preco\t\t\t|Quantidade Minima\t|"
                    + "Descricao\t\t|validade");
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nome = rs.getString("nome");
                int estoque = rs.getInt("estoque");
                float preco = rs.getFloat("preco");
                int qtdMinima = rs.getInt("quantidadeMinima");

                System.out.println("|" + idProduto + "\t|" + nome + "\t\t|" + estoque + "\t\t|" + preco + "\t\t\t|" + qtdMinima);
                System.out.println("");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public void imprimirProduto(int cod) {

        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select* from produto where idProduto=?");
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t|Nome\t\t|Estoque\t\t|Preco\t\t\t|Quantidade Minima");
            while (rs.next()) {
                int idProduto = rs.getInt("idProduto");
                String nome = rs.getString("nome");
                int estoque = rs.getInt("estoque");
                float preco = rs.getFloat("preco");
                String qtdMinima = rs.getString("quantidadeMinima");

                System.out.println("|" + idProduto + "\t|" + nome + "\t\t|" + estoque + "\t\t|" + preco + "\t\t\t|" + qtdMinima);
                System.out.println("");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public boolean jaExiste(int codigo) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Select * from produto where idProduto=?");
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
    
    public void alterarProdutoNoBD(int codigo) {
        if (jaExiste(codigo)) {
              p.preencherProduto();
            try {
                ps = Classes.Persistencia.conexao().prepareStatement("Update produto set nome=?, estoque=?, preco=?, quantidadeMinima=? where idProduto=?");
                ps.setString(1, getP().getNome());
                ps.setInt(2, getP().getEstoque());
                ps.setFloat(3, getP().getPreco());
                ps.setInt(4, getP().getQtdMinima());
                ps.setInt(5, codigo);
                ps.execute();

            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }
    
    public void removerProduto(int codigo) {
        if (jaExiste(codigo)) {
            try {
                ps = Classes.Persistencia.conexao().prepareStatement("Delete from produto where idProduto=?");
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
     * @return the p
     */
    public Produto getP() {
        return p;
    }

    /**
     * @param p the p to set
     */
    public void setP(Produto p) {
        this.p = p;
    }

}
