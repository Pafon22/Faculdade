/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import classes.Plano;
import classes.Ponto;
import classes.Reta;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author PauloAfonso
 */
public class PlanoCartesiano extends javax.swing.JFrame {

    int cont = 0;
    int recX = 260;
    int recY = 260;
    Plano plano;

    /**
     * Creates new form PlanoCartesiano
     */
    public PlanoCartesiano() {

        initComponents();
        plano = new Plano();
        plano.carregarDadosPonto("ponto.txt");
        plano.carregarDadosPontoW("ponto.txt");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPlano = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                formWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelPlano.setBackground(new java.awt.Color(255, 255, 255));
        jPanelPlano.setMaximumSize(new java.awt.Dimension(1000, 1000));
        jPanelPlano.setPreferredSize(new java.awt.Dimension(500, 500));

        javax.swing.GroupLayout jPanelPlanoLayout = new javax.swing.GroupLayout(jPanelPlano);
        jPanelPlano.setLayout(jPanelPlanoLayout);
        jPanelPlanoLayout.setHorizontalGroup(
            jPanelPlanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );
        jPanelPlanoLayout.setVerticalGroup(
            jPanelPlanoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 520, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jPanelPlano, javax.swing.GroupLayout.PREFERRED_SIZE, 520, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPlano, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 520, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        System.exit(0);
    }//GEN-LAST:event_formWindowClosed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated


    }//GEN-LAST:event_formWindowActivated

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        paintComponent(this.getGraphics());
        plano.imprimirPontos();
        plano.confereCiclo(getjPanelPlano().getGraphics());
        //Reta r = new Reta(0,0,500,500);
        Reta r1 = new Reta(260, 0, 260, 520);
        Reta r2 = new Reta(0, 260, 520, 260);

        //Reta r = new Reta(plano.resultadoPontoIncialReta().getX(), plano.resultadoPontoIncialReta().getY(), plano.resultadoPontoFinalReta().getX(), plano.resultadoPontoFinalReta().getY());
        for (Ponto p : plano.getPontos()) {
            p.setX(p.getX() * 20 + 260);
            p.setY(260 - p.getY() * 20);
            p.desenhar(getjPanelPlano().getGraphics());

        }
        r1.desenhar(getjPanelPlano().getGraphics());
        r2.desenhar(getjPanelPlano().getGraphics());
        for (int c = 0; c < 26; c++) {
            r1.setX(260);
            r1.setX2(264);  
            r1.setY(c * 20);
            r1.setY2(c * 20);
            r1.desenhar(getjPanelPlano().getGraphics());

            r2.setX(c * 20);
            r2.setX2(c * 20);
            r2.setY(260);
            r2.setY2(256);
            r2.desenhar(getjPanelPlano().getGraphics());
        }


    }//GEN-LAST:event_formWindowOpened

    private void formWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowGainedFocus
    }//GEN-LAST:event_formWindowGainedFocus

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        /*
        if (cont < plano.getRetas().size()) {
            Reta m = plano.getRetas().get(cont);
            if (m.getX2() > 520) {
                m.setX2(520);
            }
            if (m.getY2() > 520) {
                m.setY2(520);
            }
            if (m.getX2() < 520) {
                m.setX2(10 * m.getX2());
            }
            if (m.getY2() < 520) {
                m.setY2(-520);
            }
            m.desenhar(getjPanelPlano().getGraphics());

            plano.getRetas().get(cont).setX(m.getX());
            plano.getRetas().get(cont).setX2(m.getX2());
            plano.getRetas().get(cont).setY(m.getY());
            plano.getRetas().get(cont).setY2(m.getY2());

            System.out.println(m.getX());
            System.out.println(m.getY());
            System.out.println(m.getX2());
            System.out.println(m.getY2());

            // plano.getRetas().get(2).desenhar(jPanelPlano.getGraphics());
            cont++;
        
        }
         */
        if (cont < plano.getRetas().size()) {
            plano.getRetas().get(cont).desenhar(jPanelPlano.getGraphics());
            cont++;
        }
        
        
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PlanoCartesiano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlanoCartesiano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlanoCartesiano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlanoCartesiano.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlanoCartesiano().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanelPlano;
    // End of variables declaration//GEN-END:variables
    public void paintComponent(Graphics g) {
        //1. Crie a cópia do contexto gráfico
        Graphics2D g2d = (Graphics2D) g.create();
        //2. Altere a escala. Nesse caso, dobraremos:
        g2d.setTransform(AffineTransform.getScaleInstance(2.0f, 2.0f));
        //3. Desenhe usando o g2d
        //4. Libere o contexto gráfico
        g2d.dispose();
    }

    /*  public void calcularReta(Plano plano) {
        int xReta, yReta = -250;
        int xReta2, yReta2 = 250;
//        xReta = (plano.getwY()*yReta + plano.getwZ()*1) / plano.getwX();

    }*/
    /**
     * @return the jPanelPlano
     */
    public javax.swing.JPanel getjPanelPlano() {
        return jPanelPlano;
    }

    /**
     * @param jPanelPlano the jPanelPlano to set
     */
    public void setjPanelPlano(javax.swing.JPanel jPanelPlano) {
        this.jPanelPlano = jPanelPlano;
    }
}
