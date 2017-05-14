/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import javax.swing.table.DefaultTableModel;
import Classes.Curso;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.JFrameCurso;
import view.JFrameCursoAlterar;

/**
 *
 * @author PauloAfonso
 */
public class ControllerCurso {

    Curso curso;
    JFrameCurso jFrameCurso;

    /**
     *
     * @param jFrameCurso
     */
    public ControllerCurso(JFrameCurso jFrameCurso) {
        this.curso = new Curso();
        this.jFrameCurso = jFrameCurso;
    }

    public void imprimirCursoController(javax.swing.JTable jTabelaCurso, String busca) {
        jTabelaCurso.removeAll();
        jTabelaCurso.setModel(curso.dtmImprimirCurso(busca));
    }

    public void imprimirTodosCursosController() {
        PreparedStatement ps;
        ResultSet rs;
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Código", "Nome", "Carga Horária"}, 0);
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select * from curso");
            rs = ps.executeQuery();

            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt("codigoCurso"), rs.getString("nomeCurso"), rs.getInt("cargaHorariaCurso")});
            }
            rs.close();
            jFrameCurso.getJTabelaCurso().setModel(dtm);
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    /**
     *
     * @param jFrameCursoAlterar
     */
    public void alterarCurso(JFrameCursoAlterar jFrameCursoAlterar) {
        try {
            if (jFrameCursoAlterar.getJTextFieldNome().getText().equals("") || jFrameCursoAlterar.getJTextFieldCargaHoraria().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Cadastro Inválido", 0);
            } else {
                curso.setNomeCurso(jFrameCursoAlterar.getJTextFieldNome().getText());
                curso.setCargaHorariaCurso(Integer.parseInt(jFrameCursoAlterar.getJTextFieldCargaHoraria().getText()));
                curso.alterarCurso(Integer.parseInt(jFrameCursoAlterar.getTelaAnterior().getJTabelaCurso().getValueAt(jFrameCursoAlterar.getTelaAnterior().getJTabelaCurso().getSelectedRow(), 0).toString()));
                JOptionPane.showMessageDialog(null, "Curso alterado com sucesso.");
                /*                hide();
                this.dispose();
                telaAnterior.imprimirTodosCursosController();
                telaAnterior.getJTabelaCurso().changeSelection(0, telaAnterior.getJTabelaCurso().getColumnCount(), false, false);
                telaAnterior.setVisible(true);
                telaAnterior.setEnabled(true);
                 */
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar o curso." + e);
        }
    }

    public void cadastrarCurso(javax.swing.JTextField jTextFieldNome, javax.swing.JTextField jTextFieldCargaHoraria) {
        try {
            if (jTextFieldNome.getText().equals("") || jTextFieldCargaHoraria.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Cadastro Inválido", 0);
            } else {
                curso.setNomeCurso(jTextFieldNome.getText());
                curso.setCargaHorariaCurso(Integer.parseInt(jTextFieldCargaHoraria.getText()));
                curso.gravarBancoCurso();
                JOptionPane.showMessageDialog(null, "Curso cadastrado com sucesso.");
                /*       hide();
                this.dispose();
                telaAnterior.imprimirTodosCursosController();
                telaAnterior.getJTabelaCurso().changeSelection(0, telaAnterior.getJTabelaCurso().getColumnCount(), false, false);
                telaAnterior.setVisible(true);
                telaAnterior.setEnabled(true);
                 */
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar o curso.");
        }
    }

    public void removerCurso(javax.swing.JTable jTabelaCurso) {
        try {
            curso.removerCurso(Integer.parseInt(jTabelaCurso.getValueAt(jTabelaCurso.getSelectedRow(), 0).toString()));
            JOptionPane.showMessageDialog(null, "Curso removido com sucesso.");
            /*            imprimirTodosCursosController();
            jTabelaCurso.changeSelection(0, jTabelaCurso.getColumnCount(), false, false);*/
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover o curso.");
        }
    }

}
