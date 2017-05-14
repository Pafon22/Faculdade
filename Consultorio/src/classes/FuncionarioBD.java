/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

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
        System.out.println("TESTE-4");
        try {
            System.out.println("TESTE -3");
            super.gravarPessoaNoBD(f);
            System.out.println("TESTE 4");
            ps = Classes.Persistencia.conexao().prepareStatement("Insert into funcionario(login, senha, salario, horario, permissao, Pessoa_idPessoa) values (?,?,?,?,?,?)");
            System.out.println("TESTE 5");
            ps.setString(1, f.getLogin());
            System.out.println("TESTE 6");
            ps.setString(2, f.getSenha());
            ps.setDouble(3, f.getSalario());
            ps.setDouble(4, f.getHorario());
            ps.setBoolean(5, f.isPermissao());
            System.out.println("TESTE 7");
            ps.setInt(6, super.buscar(f.getCpf())); //erro aq
            System.out.println("TESTE 8");
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
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

    public void alterarFuncionarioNoBD(int codigo) {
        if (jaExiste(codigo)) {
            f.preencherFuncionario();
            try {
                super.alterarPessoaNoBD(super.buscar(f.getCpf()));
                ps = Classes.Persistencia.conexao().prepareStatement("Update funcionario set login=?, senha=?, salario=?, horario=?, permissao=? where Pessoa_idPessoa=?");
                ps.setString(1, f.getLogin());
                ps.setString(2, f.getSenha());
                ps.setDouble(3, f.getSalario());
                ps.setDouble(4, f.getHorario());
                ps.setBoolean(5, f.isPermissao());
                ps.setInt(6, super.buscar(f.getCpf()));
                ps.execute();
                System.out.println("Alterado com sucesso!");
            } catch (SQLException e) {
                System.out.println("Erro: " + e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }

    public void remover(int codigo) {
        if (jaExiste(codigo)) {
            try {
                ps = Classes.Persistencia.conexao().prepareStatement("Delete from funcionario where pessoa_idPessoa=?");
                ps.setInt(1, super.buscar(f.getCpf()));
                ps.executeUpdate();
                System.out.println("Excluído com sucesso!");

            } catch (SQLException e) {
                Logger.getLogger(Pessoa.class.getName()).log(Level.SEVERE, null, e);
            }
        } else {
            System.out.println("Código não encontrado!");
        }
    }

}
