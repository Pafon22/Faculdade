package classes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Paulo Afonso
 */
public class Ponto {
    
    
    

    private int x;
    private int y;
    private int z;

    private String cor;
    private String corAtual;

    public Ponto() {
        x = 0;
        y = 0;
        z = 1;

    }

    public Ponto(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void desenhar(Graphics g) {
        if ("black".equals(cor)) {
            g.setColor(Color.black);
        }
        if ("white".equals(cor)) {
            g.setColor(Color.white);
        }
        g.drawLine(x, y, x, y);
    }

    /*public String CSV() {
        String texto = x + ";" + y + ";" + z + ";" + cor + "\r\n";
        return texto;
    }*/
    public double saida(double wX, double wY, double wZ) {

        double saida = getX() * wX + getY() * wY + getZ() * wZ;
        /*System.out.println("Px:"+ p.x);
        System.out.println("Py:"+ p.y);
        System.out.println("Pz:"+ p.z);
        
        System.out.println("Pwx:"+ p.wX);
        System.out.println("Pwy:"+ p.wY);
        System.out.println("Pwz:"+ p.wZ);*/

        return saida;
    }

    public void defineCor(double wX, double wY, double wZ) {
        //   for (Ponto p : pontos) {
        // System.out.println("PwZ"+p.wZ);
        if (saida(wX, wY, wZ) <= 0) {
            setCorAtual("preto");
        } else {
            setCorAtual("vermelho");
        }
        // }
    }

    public boolean confereCor() {
        if (getCorAtual().equals(getCor())) {
            return true;
        }
        return false;
    }

    public void imprimirPonto() {

        //for (Ponto p : pontos) {
        System.out.println("(" + x + ", " + y + ", " + z + ")");
        //System.out.println("WX = " + wX+ ", WY = " + wY + ", WZ = " + wZ);
        System.out.println("Cor: " + corAtual + "\n");

        //}
    }

    public void carregarCSV(String texto) {
        String[] arrayTexto = texto.split(";");
        this.setX(Integer.parseInt(arrayTexto[0]));
        this.setY(Integer.parseInt(arrayTexto[1]));
        this.setZ(Integer.parseInt(arrayTexto[2]));
        this.setCor(arrayTexto[3]);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the z
     */
    public int getZ() {
        return z;
    }

    /**
     * @param z the z to set
     */
    public void setZ(int z) {
        this.z = z;
    }

    /**
     * @return the cor
     */
    public String getCor() {
        return cor;
    }

    /**
     * @param cor the cor to set
     */
    public void setCor(String cor) {
        this.cor = cor;
    }

    /**
     * @return the corAtual
     */
    public String getCorAtual() {
        return corAtual;
    }

    /**
     * @param corAtual the corAtual to set
     */
    public void setCorAtual(String corAtual) {
        this.corAtual = corAtual;
    }

}
