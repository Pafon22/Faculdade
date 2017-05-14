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
public class DentistaBD extends FuncionarioBD {

    Dentista d;

    public DentistaBD() {
        d = new Dentista();
    }

    public void gravarDentistaNoBD(Dentista d) {
        try {
            super.gravarFuncionarioNoBD(d);
            ps = Classes.Persistencia.conexao().prepareStatement("Insert into dentista(registro, especialidade, Funcionario_idFuncionario) values (?,?,?)");
            ps.setString(1, d.getRegistro());
            ps.setString(2, d.getEspecialidade());
            ps.setInt(3, super.buscar(d.getLogin()));
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    @Override
    public boolean jaExiste(int codigo) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Select * from dentista where idDoutor=?");
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            if (rs.wasNull()) {
                return false;
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Este comando está errado!" + e);
        }
        return true;
    }

    public int buscarDentista(int cod) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {

            ps = Classes.Persistencia.conexao().prepareStatement("select * from dentista WHERE idDoutor=?");
            ps.setInt(1, d.getIdDentista());
            rs = ps.executeQuery();
            while (rs.next()) {
                cod = rs.getInt("idDoutor");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }
        return cod;
    }

    public void alterarDentistaNoBD(int codigo) {
        if (jaExiste(codigo)) {
            d.preencherDentista();
            super.atualizarFuncionario(d);
            try {
                super.alterarFuncionarioNoBD(codigo,d);
                ps = Classes.Persistencia.conexao().prepareStatement("Update dentista set registro=?, especialidade=? where idDoutor=?");
                ps.setString(1, d.getRegistro());
                ps.setString(2, d.getEspecialidade());
                ps.setInt(3, codigo);
                ps.execute();
                System.out.println("Alterado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }

    public void imprimirListaDentistasCadastrados() {

        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select* from dentista");
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t\t|Registro\t|Especialidade\t");
            while (rs.next()) {

                int id = rs.getInt("idDoutor");
                String reg = rs.getString("registro");
                String esp = rs.getString("especialidade");
                int idFuncionario = rs.getInt("Funcionario_idFuncionario");
                System.out.println("|"+ id + "\t\t|" + reg + "\t|" + esp + "\t");
                System.out.println("");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public int buscarIdFuncionario(int codigo) {
       int idFuncionario = 0;

        try {
            ps = Classes.Persistencia.conexao().prepareStatement("SELECT * FROM dentista Where idDoutor=?");
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
               idFuncionario = rs.getInt("Funcionario_idFuncionario");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        return idFuncionario;
    }
    
    public void remover2(int aux){
        super.removerFuncionario(aux);
        System.out.println("Exclusão bem sucedida!");
    }

   public void removerDentista(int codigo) {
        int aux;
         aux = buscarIdFuncionario(codigo);

        if (jaExiste(codigo)) {
            try {
                ps = Classes.Persistencia.conexao().prepareStatement("Delete from dentista where idDoutor=?");
                ps.setInt(1, codigo);
                ps.execute();
                remover2(aux);
            } catch (SQLException e) {
                Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, e);
            }

        } else {
            System.out.println("Código não encontrado!");
        }
    }

}
