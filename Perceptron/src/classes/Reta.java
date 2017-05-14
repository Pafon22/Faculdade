package classes;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Paulo Afonso
 */
public class Reta {

    //Ponto p1, p2;
    private int x;
    //Ponto p1, p2;
    private int y;
    //Ponto p1, p2;
    private int x2;
    //Ponto p1, p2;
    private int y2;
    public double equacao;

    public Reta() {
        /*p1.setX(0);
        p1.setY(0);
        p2.setX(0);
        p2.setY(0);*/
    }
    public Reta(int x, int y, int x2, int y2) {
        this.x = x;
        this.y = y;
        this.x2 = x2;
        this.y2 = y2;
    }

 /*   public void desenhar(Graphics g) {
        g.drawLine(p1.x, p1.y, p2.x, p2.y);
    }
*/
    
        public void desenhar(Graphics g) {
        g.setColor(Color.black);
        g.drawLine(getX(), getY(), getX2(), getY2());
    }
        
    /*public double comprimento() {
        return Math.sqrt(((x2 - x) * (x2 - x)) + ((y2 - y) * (y2 - y)));
    }*/

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
     * @return the x2
     */
    public int getX2() {
        return x2;
    }

    /**
     * @param x2 the x2 to set
     */
    public void setX2(int x2) {
        this.x2 = x2;
    }

    /**
     * @return the y2
     */
    public int getY2() {
        return y2;
    }

    /**
     * @param y2 the y2 to set
     */
    public void setY2(int y2) {
        this.y2 = y2;
    }
}
