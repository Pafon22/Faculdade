/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.table.DefaultTableModel;
import Classes.Aluno;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.JFrameAluno;
import view.JFrameAlunoAlterar;

/**
 *
 * @author PauloAfonso
 */
public class ControllerAluno2 {

    Aluno aluno;
    JFrameAluno jFrameAluno;

    /**
     *
     * @param jFrameAluno
     */
    public ControllerAluno2(JFrameAluno jFrameAluno) {
        this.aluno = new Aluno();
        this.jFrameAluno = jFrameAluno;
    }

    public void imprimirAlunoController(javax.swing.JTable jTabelaAluno, String busca) {
        jTabelaAluno.removeAll();
        jTabelaAluno.setModel(aluno.dtmImprimirAluno(busca));
    }

    public void imprimirTodosAlunosController() {
        PreparedStatement ps;
        ResultSet rs;
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Código", "Nome", "CPF"}, 0);
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select * from aluno");
            rs = ps.executeQuery();

            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt("codigoAluno"), rs.getString("nomeAluno"), rs.getString("cpfAluno")});
            }
            rs.close();
            jFrameAluno.getJTabelaAluno().setModel(dtm);
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    /**
     *
     * @param jFrameAlunoAlterar
     */
    public void alterarAluno(JFrameAlunoAlterar jFrameAlunoAlterar) {
        try {
            
                if (jFrameAlunoAlterar.getJTextFieldNome().getText().equals("") || jFrameAlunoAlterar.getJTextFieldCpf().getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Cadastro Inválido", 0);
                } else {
                    aluno.setNomeAluno(jFrameAlunoAlterar.getJTextFieldNome().getText());
                    aluno.setCpfAluno(jFrameAlunoAlterar.getJTextFieldCpf().getText());
                    aluno.alterarAluno(Integer.parseInt(jFrameAlunoAlterar.getTelaAnterior().getJTabelaAluno().getValueAt(jFrameAlunoAlterar.getTelaAnterior().getJTabelaAluno().getSelectedRow(), 0).toString()));
                    JOptionPane.showMessageDialog(null, "Aluno alterado com sucesso.");
                    /*                hide();
                this.dispose();
                telaAnterior.imprimirTodosAlunosController();
                telaAnterior.getJTabelaAluno().changeSelection(0, telaAnterior.getJTabelaAluno().getColumnCount(), false, false);
                telaAnterior.setVisible(true);
                telaAnterior.setEnabled(true);
                     */
                }
            
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar o aluno." + e);
        }
    }

    public void cadastrarAluno(javax.swing.JTextField jTextFieldNome, javax.swing.JTextField jTextFieldCpf) {
        try {
            if (jTextFieldNome.getText().equals("") || jTextFieldCpf.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Cadastro Inválido", 0);
            } else {
                aluno.setNomeAluno(jTextFieldNome.getText());
                aluno.setCpfAluno(jTextFieldCpf.getText());
                aluno.gravarBancoAluno();
                JOptionPane.showMessageDialog(null, "Aluno cadastrado com sucesso.");
                /*       hide();
                this.dispose();
                telaAnterior.imprimirTodosAlunosController();
                telaAnterior.getJTabelaAluno().changeSelection(0, telaAnterior.getJTabelaAluno().getColumnCount(), false, false);
                telaAnterior.setVisible(true);
                telaAnterior.setEnabled(true);
                 */
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar o aluno.");
        }
    }

    public void removerAluno(javax.swing.JTable jTabelaAluno) {
        try {
            aluno.removerAluno(Integer.parseInt(jTabelaAluno.getValueAt(jTabelaAluno.getSelectedRow(), 0).toString()));
            JOptionPane.showMessageDialog(null, "Aluno removido com sucesso.");
            /*            imprimirTodosAlunosController();
            jTabelaAluno.changeSelection(0, jTabelaAluno.getColumnCount(), false, false);*/
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover o aluno.");
        }
    }

}
