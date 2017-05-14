/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.awt.Graphics;
import java.util.ArrayList;

/**
 *
 * @author PauloAfonso
 */
public class Plano {

    private ArrayList<Ponto> pontos;
    private double[][] matrizCiclo = new double[1000][3];
    private double taxa, wX, wY, wZ;
    private int ciclo;
    private Ponto inicio, fim;
    private Ponto resultado;
    private ArrayList<Reta> retas;
    private int cont;

    public Plano() {
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 3; j++) {
                matrizCiclo[i][j] = 0;
            }
        }
        taxa = 0.5;
        retas = new ArrayList<>();
        retas.add(new Reta(260, 260, 260, 260));
        resultado = new Ponto();
        pontos = new ArrayList<>();
        wX = wY = wZ = 1;
        ciclo = 0;
        cont = 1;
        inicio = fim = new Ponto();
    }

    public boolean conferirCor(Ponto p) {
        return p.confereCor();

    }

    public Reta calcularReta(Graphics g) {
        Reta r = new Reta();
        r.setY(0);
        r.setX((int) ((-wZ - wY * r.getY()) / wX) + 260);
        System.out.println("xz " + wZ + "wX " + wX);
        System.out.println(((-wZ - wY * r.getY()) / wX) + 260);
        r.setY2(520);
        r.setX2((int) ((-wZ - wY * r.getY2()) / wX) + 260);
        System.out.println(((-wZ - wY * r.getY2()) / wX) + 260);
        // r.desenhar(g);

        return r;
    }

    /*
    //public Reta 
    public Ponto resultadoPontoFinalReta() {
        Ponto p = new Ponto();
        p.setX((int) ((-pontos.get(ciclo).getY() * wY - pontos.get(ciclo).getZ() * wZ) / wX));
        p.setY((int) ((-pontos.get(ciclo).getX() * wX - pontos.get(ciclo).getZ() * wZ) / wY));

        return p;
    }

    public Ponto resultadoPontoIncialReta() {
        Ponto p = new Ponto();
        p.setX((int) ((-pontos.get(0).getY() * wY - pontos.get(0).getZ() * wZ) / wX));
        p.setY((int) ((-pontos.get(0).getX() * wX - pontos.get(0).getZ() * wZ) / wY));

        return p;
    }*/

    public void confereCiclo(Graphics g) {
        System.out.println("Ciclo " + getCiclo() + ": ");
        imprimirSaidas();
        setCiclo(getCiclo() + 1);
        matrizCiclo[ciclo][0] = wX;
        matrizCiclo[ciclo][1] = wY;
        matrizCiclo[ciclo][2] = wZ;
        resultado.setX(ciclo);
        boolean flag = true;
        for (Ponto p : pontos) {
            p.defineCor(wX, wY, getwZ());
            if (conferirCor(p)) {

            } else {
                flag = false;
                calculaW(p, g);
            }
        }
        if (flag == false) {
            confereCiclo(g);
        }

    }

    public void calculaW(Ponto p, Graphics g) {
        System.out.println("Consertando P(" + p.getX() + ", " + p.getY() + ", " + p.getZ() + "):");
        double result1;
        double result2;
        double result3;
        if (p.saida(wX, wY, getwZ()) > 0) {
            result1 = wX - (taxa * p.getX());
            System.out.println("wX = " + wX + " - (" + taxa + " * " + p.getX() + ") = " + result1);
            wX = result1;
            result2 = wY - (taxa * p.getY());
            System.out.println("wY = " + wY + " - (" + taxa + " * " + p.getY() + ") = " + result2);
            wY = result2;
            result3 = getwZ() - (taxa * p.getZ());
            System.out.println("wZ = " + getwZ() + " - (" + taxa + " * " + p.getZ() + ") = " + result3);
            setwZ(result3);
        } else {
            result1 = wX + (taxa * p.getX());
            System.out.println("wX = " + wX + " + (" + taxa + " * " + p.getX() + ") = " + result1);
            wX = result1;
            result2 = wY + (taxa * p.getY());
            System.out.println("wY = " + wY + " + (" + taxa + " * " + p.getY() + ") = " + result2);
            wY = result2;
            result3 = getwZ() + (taxa * p.getZ());
            System.out.println("wZ = " + getwZ() + " + (" + taxa + " * " + p.getZ() + ") = " + result3);
            setwZ(result3);
        }
        getRetas().add(calcularReta(g));
    }

    /*public String pontoCSV() {
        String texto = "X;Y;Z\r\n";
        for (Ponto p : pontos) {
            texto += p.CSV();
        }
        return texto;
    }
     */
 /*    public void salvarArq(String pontotxt) {
        FileManager.escreverArq(pontoCSV(), pontotxt);
    }

    public void lerArq(String pontotxt) {
        FileManager.lerArq(pontotxt);
    }
     */
    public void carregarDadosPonto(String pontotxt) {
        String[] texto = FileManager.getLinhas(FileManager.lerArq(pontotxt));
        for (String texto1 : texto) {
            Ponto p = new Ponto();
            p.carregarCSV(texto1);
            getPontos().add(p);
        }

    }

    public void carregarDadosPontoW(String pontotxt) {
        String[] texto = FileManager.getLinhas(FileManager.lerArqW(pontotxt));
        for (Ponto p : getPontos()) {
            for (int i = 0; i < texto.length; i++) {
                //Ponto p = new Ponto();
                carregarCSVW(texto[i]);
                //    pontos.add(p);
            }
            p.defineCor(wX, wY, getwZ());
        }

    }

    public void carregarCSVW(String texto) {
        String[] arrayTexto = texto.split(";");
        this.setwX(Integer.parseInt(arrayTexto[0]));
        //System.out.println("wX: "+ wX);
        this.setwY(Integer.parseInt(arrayTexto[1]));
        //System.out.println("wY: "+ wY);
        this.setwZ(Integer.parseInt(arrayTexto[2]));
        //System.out.println("wZ: "+ wZ);
    }

    public void imprimirPontos() {
        int i = 1;

        System.out.println("WX = " + getwX() + ", WY = " + getwY() + ", WZ = " + getwZ());
        for (Ponto p : getPontos()) {
            System.out.print("P" + i + " = ");
            p.imprimirPonto();

            i++;
        }
    }

    public void imprimirSaidas() {
        int i = 1;
        System.out.println("WX = " + getwX() + ", WY = " + getwY() + ", WZ = " + getwZ());
        for (Ponto p : getPontos()) {
            p.defineCor(wX, wY, getwZ());
            System.out.println("P" + i + " = " + "(" + p.getX() + ", " + p.getY() + ", " + p.getZ() + ") Atual: " + p.getCorAtual() + " - Certa: " + p.getCor());
            System.out.println("Saída " + i + " = " + p.saida(wX, wY, getwZ()) + "\n");
            i++;
        }
    }

    /**
     * @return the pontos
     */
    public ArrayList<Ponto> getPontos() {
        return pontos;
    }

    /**
     * @param pontos the pontos to set
     */
    public void setPontos(ArrayList<Ponto> pontos) {
        this.pontos = pontos;
    }

    /**
     * @return the taxa
     */
    public double getTaxa() {
        return taxa;
    }

    /**
     * @param taxa the taxa to set
     */
    public void setTaxa(int taxa) {
        this.taxa = taxa;
    }

    /**
     * @return the wX
     */
    public double getwX() {
        return wX;
    }

    /**
     * @param wX the wX to set
     */
    public void setwX(double wX) {
        this.wX = wX;
    }

    /**
     * @return the wY
     */
    public double getwY() {
        return wY;
    }

    /**
     * @param wY the wY to set
     */
    public void setwY(double wY) {
        this.wY = wY;
    }

    /**
     * @return the wZ
     */
    public double getwZ() {
        return wZ;
    }

    /**
     * @param wZ the wZ to set
     */
    public void setwZ(double wZ) {
        this.wZ = wZ;
    }

    /**
     * @return the ciclo
     */
    public int getCiclo() {
        return ciclo;
    }

    /**
     * @param ciclo the ciclo to set
     */
    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    /**
     * @return the inicio
     */
    public Ponto getInicio() {
        return inicio;
    }

    /**
     * @param inicio the inicio to set
     */
    public void setInicio(Ponto inicio) {
        this.inicio = inicio;
    }

    /**
     * @return the fim
     */
    public Ponto getFim() {
        return fim;
    }

    /**
     * @param fim the fim to set
     */
    public void setFim(Ponto fim) {
        this.fim = fim;
    }

    /**
     * @return the retas
     */
    public ArrayList<Reta> getRetas() {
        return retas;
    }

    /**
     * @param retas the retas to set
     */
    public void setRetas(ArrayList<Reta> retas) {
        this.retas = retas;
    }

}
