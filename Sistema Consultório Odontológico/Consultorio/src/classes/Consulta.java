/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.sql.Time;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author PauloAfonso
 */
public class Consulta {
    private int idConsulta;
    private Date data;
    private Time horario;
    private int idDentista;
    private int idExame;
    private int idPrescricaoMedica;
    private int idPaciente;
    private Scanner ler = new Scanner(System.in);
    private DentistaBD dentista;
    private ExamesBD exames;
    private PrescricaoMedicaBD prescricaoMedica;
    private PacienteBD paciente;
    
    public Consulta() {
        dentista = new DentistaBD();
        exames = new ExamesBD();
        prescricaoMedica = new PrescricaoMedicaBD();
        paciente = new PacienteBD();
        data = new Date();
        horario = new Time(0,0,0);

    }

    public Consulta(Date data, Time horario) {
        this.data = data;
        this.horario = horario;
    }
    
    public void preencherConsulta() {
        System.out.println("Digite o dia: ");
        int aux = getLer().nextInt();
        getData().setDate(aux);
        System.out.println("Digite o mes: ");
        int mes = getLer().nextInt();   
        getData().setMonth(mes);
        System.out.println("Digite o ano: ");
        aux = getLer().nextInt();
        getData().setYear(aux);
        System.out.println(getData().getTime());
        System.out.println("Digite a hora da consulta: ");
        aux =  getLer().nextInt();
        getHorario().setHours(aux);
        System.out.println("Digite os minutos: ");
        aux = getLer().nextInt();
        getHorario().setMinutes(aux);
        dentista.imprimirListaDentistasCadastrados();
        System.out.println("Digite o ID do dentista: ");
        setIdDentista(getLer().nextInt());
        //exames.
        System.out.println("Digite o ID do exame: ");
        setIdExame(getLer().nextInt());
        System.out.println("Digite o ID da prescição medica: ");
        setIdPrescricaoMedica(getLer().nextInt());
        paciente.imprimirListaPacientesCadastrados();
        System.out.println("Digite o ID do paciente: ");
        setIdPaciente(getLer().nextInt());
        
    }

    /**
     * @return the idConsulta
     */
    public int getIdConsulta() {
        return idConsulta;
    }

    /**
     * @param idConsulta the idConsulta to set
     */
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    /**
     * @return the data
     */
    public Date getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Date data) {
        this.data = data;
    }

    /**
     * @return the horario
     */
    public Time getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Time horario) {
        this.horario = horario;
    }

    /**
     * @return the idDentista
     */
    public int getIdDentista() {
        return idDentista;
    }

    /**
     * @param idDentista the idDentista to set
     */
    public void setIdDentista(int idDentista) {
        this.idDentista = idDentista;
    }

    /**
     * @return the idExame
     */
    public int getIdExame() {
        return idExame;
    }

    /**
     * @param idExame the idExame to set
     */
    public void setIdExame(int idExame) {
        this.idExame = idExame;
    }

    /**
     * @return the idPrescricaoMedica
     */
    public int getIdPrescricaoMedica() {
        return idPrescricaoMedica;
    }

    /**
     * @param idPrescricaoMedica the idPrescricaoMedica to set
     */
    public void setIdPrescricaoMedica(int idPrescricaoMedica) {
        this.idPrescricaoMedica = idPrescricaoMedica;
    }

    /**
     * @return the idPaciente
     */
    public int getIdPaciente() {
        return idPaciente;
    }

    /**
     * @param idPaciente the idPaciente to set
     */
    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    /**
     * @return the ler
     */
    public Scanner getLer() {
        return ler;
    }

    /**
     * @param ler the ler to set
     */
    public void setLer(Scanner ler) {
        this.ler = ler;
    }

    /**
     * @return the dentista
     */
    public DentistaBD getDentista() {
        return dentista;
    }

    /**
     * @param dentista the dentista to set
     */
    public void setDentista(DentistaBD dentista) {
        this.dentista = dentista;
    }

    /**
     * @return the exames
     */
    public ExamesBD getExames() {
        return exames;
    }

    /**
     * @param exames the exames to set
     */
    public void setExames(ExamesBD exames) {
        this.exames = exames;
    }

    /**
     * @return the prescricaoMedica
     */
    public PrescricaoMedicaBD getPrescricaoMedica() {
        return prescricaoMedica;
    }

    /**
     * @param prescricaoMedica the prescricaoMedica to set
     */
    public void setPrescricaoMedica(PrescricaoMedicaBD prescricaoMedica) {
        this.prescricaoMedica = prescricaoMedica;
    }

    /**
     * @return the paciente
     */
    public PacienteBD getPaciente() {
        return paciente;
    }

    /**
     * @param paciente the paciente to set
     */
    public void setPaciente(PacienteBD paciente) {
        this.paciente = paciente;
    }

}
