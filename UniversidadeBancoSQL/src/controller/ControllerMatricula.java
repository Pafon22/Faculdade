/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Classes.Aluno;
import Classes.Curso;
import javax.swing.table.DefaultTableModel;
import Classes.Matricula;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import view.JFrameMatricula;
import view.JFrameMatriculaAlterar;
import view.JFrameMatriculaCadastrar;

/**
 *
 * @author PauloAfonso
 */
public class ControllerMatricula {

    Matricula matricula;
    Aluno aluno;
    Curso curso;
    JFrameMatricula jFrameMatricula;
    JFrameMatriculaCadastrar jFrameMatriculaCadastrar;

    /**
     *
     * @param jFrameMatricula
     */
    public ControllerMatricula(JFrameMatricula jFrameMatricula) {
        this.matricula = new Matricula();
        this.aluno = new Aluno();
        this.curso = new Curso();
        this.jFrameMatricula = jFrameMatricula;
    }
    
    
    public ControllerMatricula(JFrameMatriculaCadastrar jFrameMatriculaCadastrar) {
        this.matricula = new Matricula();
        this.aluno = new Aluno();
        this.curso = new Curso();
        this.jFrameMatriculaCadastrar = jFrameMatriculaCadastrar;
    }

    public ControllerMatricula() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void imprimirMatriculaController(javax.swing.JTable jTabelaMatricula, String busca) {
        jTabelaMatricula.removeAll();
        jTabelaMatricula.setModel(matricula.dtmImprimirMatricula(busca));
    }

    public void imprimirTodasMatriculasController() {
        PreparedStatement ps;
        ResultSet rs;
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Matrícula", "Código do Aluno", "Código do Curso", "Data da Matrícula"}, 0);
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("select * from matricula");
            rs = ps.executeQuery();

            while (rs.next()) {
                dtm.addRow(new Object[]{rs.getInt("codigoMatricula"), rs.getInt("aluno_codigoAluno"), rs.getInt("curso_codigoCurso"), rs.getString("dataMatricula")});
            }
            rs.close();
            jFrameMatricula.getJTabelaMatricula().setModel(dtm);
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }

    /**
     *
     * @param jFrameMatriculaAlterar
     */
    public void alterarMatricula(JFrameMatriculaAlterar jFrameMatriculaAlterar) {
        try {
            if (jFrameMatriculaAlterar.getJTextFieldCodAluno().getText().equals("") || jFrameMatriculaAlterar.getJTextFieldCodCurso().getText().equals("") || jFrameMatriculaAlterar.getJTextFieldDataMatricula().getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Cadastro Inválido", 0);
            } else {
                matricula.setCodigoAluno(Integer.parseInt(jFrameMatriculaAlterar.getJTextFieldCodAluno().getText()));
                matricula.setCodigoCurso(Integer.parseInt(jFrameMatriculaAlterar.getJTextFieldCodCurso().getText()));
                matricula.setDataMatricula(jFrameMatriculaAlterar.getJTextFieldDataMatricula().getText());
                matricula.alterarMatricula(Integer.parseInt(jFrameMatriculaAlterar.getTelaAnterior().getJTabelaMatricula().getValueAt(jFrameMatriculaAlterar.getTelaAnterior().getJTabelaMatricula().getSelectedRow(), 0).toString()));
                JOptionPane.showMessageDialog(null, "Matrícula alterada com sucesso.");
                /*                hide();
                this.dispose();
                telaAnterior.imprimirTodasMatriculasController();
                telaAnterior.getJTabelaMatricula().changeSelection(0, telaAnterior.getJTabelaMatricula().getColumnCount(), false, false);
                telaAnterior.setVisible(true);
                telaAnterior.setEnabled(true);
                 */
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Falha ao alterar a matrícula." + e);
        }
    }

    public void cadastrarMatricula(javax.swing.JTextField jTextFieldCodAluno, javax.swing.JTextField jTextFieldCodCurso, javax.swing.JTextField jTextFieldDataMatricula) {
        try {
            if (jTextFieldCodAluno.getText().equals("") || jTextFieldCodCurso.getText().equals("") || jTextFieldDataMatricula.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Cadastro Inválido", 0);
            } else {
                matricula.setCodigoAluno(Integer.parseInt(jTextFieldCodAluno.getText()));
                matricula.setCodigoCurso(Integer.parseInt(jTextFieldCodCurso.getText()));
                matricula.setDataMatricula(jTextFieldDataMatricula.getText());
                matricula.gravarBancoMatricula();
                JOptionPane.showMessageDialog(null, "Matrícula cadastrada com sucesso.");
                /*       hide();
                this.dispose();
                telaAnterior.imprimirTodasMatriculasController();
                telaAnterior.getJTabelaMatricula().changeSelection(0, telaAnterior.getJTabelaMatricula().getColumnCount(), false, false);
                telaAnterior.setVisible(true);
                telaAnterior.setEnabled(true);
                 */
            }
        } catch (HeadlessException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Falha ao cadastrar a matrícula.");
        }
    }

    public void removerMatricula(javax.swing.JTable jTabelaMatricula) {
        try {
            matricula.removerMatricula(Integer.parseInt(jTabelaMatricula.getValueAt(jTabelaMatricula.getSelectedRow(), 0).toString()));
            JOptionPane.showMessageDialog(null, "Matrícula removida com sucesso.");
            /*            imprimirTodasMatriculasController();
            jTabelaMatricula.changeSelection(0, jTabelaMatricula.getColumnCount(), false, false);*/
        } catch (NumberFormatException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Falha ao remover a matrícula.");
        }
    }
    
    
    public void imprimirTodosAlunosControllerCadastrar() {
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
            jFrameMatriculaCadastrar.getJTabelaAluno().setModel(dtm);
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }

    }
    
     public void imprimirTodosCursosControllerCadastrar() {
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
            jFrameMatriculaCadastrar.getJTabelaCurso().setModel(dtm);
        } catch (SQLException e) {
            System.out.println("Erro: " + e);

        }


    }

}
