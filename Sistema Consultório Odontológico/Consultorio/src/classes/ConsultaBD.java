/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author PauloAfonso
 */
public class ConsultaBD {
    protected PreparedStatement ps;
    protected ResultSet rs;
    protected Consulta c;
    
    public ConsultaBD() {
        ps = null;
        rs = null;
        c = new Consulta();
    }

    public ConsultaBD(PreparedStatement ps, ResultSet rs, Consulta c) {
        this.ps = ps;
        this.rs = rs;
        this.c = c;
    }
    
    
    public void gravarConsultaNoBD(Consulta c) {
        try {
            ps = Classes.Persistencia.conexao().prepareStatement("Insert into pessoa(data, horario, Dentista_idDentista, Exames_idExames, PrescricaoMedica_idPrescricaoMedica, Paciente_idPaciente) values (?,?,?,?,?,?)");
            ps.setString(1, c.getData().getDate()+"/" + c.getData().getMonth() + "/" + c.getData().getYear());
            ps.setTime(2, c.getHorario());
            //ps.setString(3, c.getIdDentista());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e);
        }
    }
    

}
