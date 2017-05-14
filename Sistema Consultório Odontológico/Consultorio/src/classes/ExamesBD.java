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
public class ExamesBD {
    
    protected PreparedStatement ps;
    protected ResultSet rs;
    private Exames e;
    
    public ExamesBD() {
        ps = null;
        rs = null;
        e = new Exames();
    }
    
    public void gravarExameNoBD(Exames e) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Insert into exames(nomeExame, custoExame) values (?,?)");
            ps.setString(1, e.getNomeExame());
            ps.setFloat(2, e.getCustoExame());
            ps.execute();
            ps.close();
        }catch (SQLException ex) {
            System.out.println("Erro: " + ex);
        }
    }
    
    public void imprimirListaExamesCadastrados() {

        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select* from exames");
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t|Nome Exame\t\t|Custo Exame");
            while (rs.next()) {
                int idExames = rs.getInt("idExames");
                String nomeExame = rs.getString("nomeExame");
                float custoExame = rs.getFloat("custoExame");

                System.out.println("|" + idExames + "\t|" + nomeExame + "\t\t|" + custoExame);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public void imprimirExames(int cod) {

        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select* from exames where idExames=?");
            ps.setInt(1, cod);
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t|Nome Exames\t\t|Custo Exames");
            while (rs.next()) {
                int idExames = rs.getInt("idExames");
                String nomeExames = rs.getString("nomeExames");
                int custoExames = rs.getInt("custoExames");

                System.out.println("|" + idExames + "\t|" + nomeExames + "\t\t|" + custoExames);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public boolean jaExiste(int codigo) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Select * from exames where idExames=?");
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
    
    public void alterarExamesNoBD(int codigo) {
        if (jaExiste(codigo)) {
              getE().preencherExame();
            try {
                ps = Classes.Persistencia.conexao().prepareStatement("Update exames set nomeExame=?, custoExame=? where idExames=?");
                ps.setString(1, getE().getNomeExame());
                ps.setFloat(2, getE().getCustoExame());
                ps.setInt(3, codigo);
                ps.execute();

            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }
    
    public void removerExames(int codigo) {
        if (jaExiste(codigo)) {
            try {
                ps = Classes.Persistencia.conexao().prepareStatement("Delete from exames where idExames=?");
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
     * @return the e
     */
    public Exames getE() {
        return e;
    }

    /**
     * @param e the e to set
     */
    public void setE(Exames e) {
        this.e = e;
    }

}
