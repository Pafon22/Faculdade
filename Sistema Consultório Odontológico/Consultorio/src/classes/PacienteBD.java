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
public class PacienteBD extends PessoaBD {

    Paciente p;

    public PacienteBD() {
        p = new Paciente();
    }

    public void gravarPacienteNoBD(Paciente p) {
        try {
            super.gravarPessoaNoBD(p);
            ps = Classes.Persistencia.conexao().prepareStatement("Insert into paciente(convenio, observacao, Pessoa_idPessoa) values (?,?,?)");
            ps.setBoolean(1, p.isConvenio());
            ps.setString(2, p.getObservacao());
            ps.setInt(3, super.buscar(p.getCpf()));
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }

    @Override
    public boolean jaExiste(int codigo) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Select * from paciente where idPaciente=?");
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

    public void alterarPacienteNoBD(int codigo) {
        if (jaExiste(codigo)) {
            p.preencherPaciente();
            super.atualizarPessoa(p);
            try {
                super.alterarPessoaNoBD(buscarIdPessoa(codigo));
                System.out.println(buscarIdPessoa(codigo));
                ps = Classes.Persistencia.conexao().prepareStatement("Update paciente set convenio=?, observacao=? where idPaciente=?");
                ps.setBoolean(1, p.isConvenio());
                ps.setString(2, p.getObservacao());
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

    public void imprimirListaPacientesCadastrados() {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select* from paciente");
            rs = ps.executeQuery();
            System.out.println("_________________________________");
            System.out.println("|ID\t|Convenio\t\t|Observacao\t\t\t\t|ID-Pessoa\t");
            while (rs.next()) {
                int id = rs.getInt("idPaciente");
                boolean convenio = rs.getBoolean("convenio");
                String observacao = rs.getString("observacao");
                int idPessoa = rs.getInt("Pessoa_idPessoa");
                System.out.println("|" + id + "\t|" + convenio + "\t\t\t|" + observacao + "\t\t\t\t\t|" + idPessoa);
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
            ps = Classes.Persistencia.conexao().prepareStatement("SELECT * FROM paciente Where idPaciente=?");
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
    public void remover2(int aux){
        super.removerPessoa(aux);
        System.out.println("Exclusão bem sucedida!");
    }

    public void removerPaciente(int codigo) {
        int aux;
         aux = buscarIdPessoa(codigo);

        if (jaExiste(codigo)) {
            try {
                ps = Classes.Persistencia.conexao().prepareStatement("Delete from paciente where idPaciente=?");
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
