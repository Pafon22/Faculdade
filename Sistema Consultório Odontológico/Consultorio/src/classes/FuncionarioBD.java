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
public class FuncionarioBD extends PessoaBD {

    Funcionario f;

    public FuncionarioBD() {
        f = new Funcionario();
    }

    public void gravarFuncionarioNoBD(Funcionario f) {
        try {
            super.gravarPessoaNoBD(f);
            ps = Classes.Persistencia.conexao().prepareStatement("Insert into funcionario(login, senha, salario, horario, permissao, Pessoa_idPessoa) values (?,?,?,?,?,?)");
            ps.setString(1, f.getLogin());
            ps.setString(2, f.getSenha());
            ps.setFloat(3, f.getSalario());
            ps.setFloat(4, f.getHorario());
            ps.setBoolean(5, f.isPermissao());
            ps.setInt(6, super.buscar(f.getCpf()));
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    
    public int buscar(String login) {
       PreparedStatement ps = null;
        ResultSet rs = null;

        int idFuncionario = 0;
        try {

            ps = Classes.Persistencia.conexao().prepareStatement("select * from funcionario WHERE login=?");
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
                idFuncionario = rs.getInt("idFuncionario");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }
        return idFuncionario;
    }

    @Override
    public boolean jaExiste(int codigo) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Select * from funcionario where idFuncionario=?");
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
    
    public boolean jaExisteLogin(String login) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Select * from funcionario where login=?");
            ps.setString(1, login);
            rs = ps.executeQuery();
            if (rs.wasNull()) {
                System.out.println("-------------");
                return false;
            }
            rs.close();

        } catch (SQLException e) {
            System.out.println("Esse comando tá errado, pow!" + e);
        }
        return true;
    }
    
    public boolean jaExisteSenha(String senha, String login) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Select * from funcionario where login=? and senha=?");
            ps.setString(1,login );
            ps.setString(2, senha);
            System.out.println(login +"-----"+senha);
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
    
    public void atualizarFuncionario(Dentista d) {
        super.atualizarPessoa(d);
        d.setSalario(d.getSalario());
        d.setHorario(d.getHorario());
        d.setLogin(d.getLogin());
        d.setSenha(d.getSenha());
        d.setPermissao(d.isPermissao());

    }

    public void alterarFuncionarioNoBD(int codigo) {
        if (jaExiste(codigo)) {
            f.preencherFuncionario();
            super.atualizarPessoa(f);
            try {
                super.alterarPessoaNoBD(buscarIdPessoa(codigo));
                System.out.println(buscarIdPessoa(codigo));
                ps = Classes.Persistencia.conexao().prepareStatement("Update funcionario set login=?, senha=?, salario=?, horario=?, permissao=? where idFuncionario=?");
                ps.setString(1, f.getLogin());
                ps.setString(2, f.getSenha());
                ps.setFloat(3, f.getSalario());
                ps.setFloat(4, f.getHorario());
                ps.setBoolean(5, f.isPermissao());
                ps.setInt(6, codigo);
                ps.execute();
                System.out.println("Alterado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }
    public void alterarFuncionarioNoBD(int codigo, Dentista d) {
        if (jaExiste(codigo)) {
            super.atualizarPessoa(d);
            try {
                super.alterarPessoaNoBD(buscarIdPessoa(codigo));
                System.out.println(buscarIdPessoa(codigo));
                ps = Classes.Persistencia.conexao().prepareStatement("Update funcionario set login=?, senha=?, salario=?, horario=?, permissao=? where idFuncionario=?");
                ps.setString(1, d.getLogin());
                ps.setString(2, d.getSenha());
                ps.setFloat(3, d.getSalario());
                ps.setFloat(4, d.getHorario());
                ps.setBoolean(5, d.isPermissao());
                ps.setInt(6, codigo);
                ps.execute();
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }

    public void imprimirListaFuncionariosCadastrados() {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select* from funcionario");
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t|Login\t\t|Senha\t\t|Salario\t\t\t|Horario\t\t\t|Permissao\t\t\t|ID-Pessoa\t");
            while (rs.next()) {
                int id = rs.getInt("idFuncionario");
                String login = rs.getString("login");
                String senha = rs.getString("senha");
                double salario = rs.getDouble("salario");
                double horario = rs.getDouble("horario");
                boolean permissao = rs.getBoolean("permissao");
                int idPessoa = rs.getInt("Pessoa_idPessoa");
                System.out.println("|" + id + "\t|" + login + "\t\t|" + senha + "\t\t|" + salario + "\t\t\t\t|" + horario + "\t\t\t\t|" + permissao + "\t\t\t\t|" + idPessoa);
                System.out.println("");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    public int buscarIdPessoa(int codigo) {
       int idPessoa = 0;

        try {
            ps = Classes.Persistencia.conexao().prepareStatement("SELECT * FROM funcionario Where idFuncionario=?");
            ps.setInt(1, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
               idPessoa = rs.getInt("Pessoa_idPessoa");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        return idPessoa;
    }
    
    public boolean buscarPermissao(String login) {
        boolean permissao = false;
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("SELECT * FROM funcionario Where login=?");
            ps.setString(1, login);
            rs = ps.executeQuery();
            while (rs.next()) {
               permissao = rs.getBoolean("permissao");
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
        return permissao;
    }
    
    public void remover2(int aux){
        super.removerPessoa(aux);
        System.out.println("Exclusão bem sucedida!");
    }

    public void removerFuncionario(int codigo) {
        int aux;
         aux = buscarIdPessoa(codigo);

        if (jaExiste(codigo)) {
            try {
                ps = Classes.Persistencia.conexao().prepareStatement("Delete from funcionario where idFuncionario=?");
               //sql: chave estrangeira - on delete cascate
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
