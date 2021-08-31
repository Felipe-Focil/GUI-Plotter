package Plotter;

/**
 * Clase PuntoEcuacion
 * 
 * @author Felipe Focil
 * @version 1.0
 */

import java.util.Vector;
import java.util.Random;
import java.awt.*;
import java.awt.geom.*;

import javax.swing.*;

public class PuntoEcuacion extends Canvas implements Runnable {
    Thread hilo;
    Punto centro = new Punto(this.getWidth() / 2, this.getHeight() / 2);

    private Vector<Punto> puntos;
    private Polinomio poli;
    private double linf, lsup, inc;

    public PuntoEcuacion() {
        hilo = new Thread();
    }

    public void calculaPuntos(Polinomio poli, double linf, double lsup, double inc) {
        this.poli = poli;
        puntos = new Vector();
        for (double i = linf; i <= lsup; i += inc) {
            puntos.add(new Punto(i, poli.evalua(i)));
        }
    }

    public Vector<Punto> getPuntosEcuacion() {
        return puntos;
    }

    public Punto getPunto(int num) {
        return puntos.get(num);
    }

    public void paint(Graphics gc) {

        Graphics2D gc2d = (Graphics2D) gc;
        Punto centro = new Punto(this.getWidth() / 2, this.getHeight() / 2);
        // Declaración de objetos Gŕaficos
        Line2D.Float ejeX = new Line2D.Float(0, this.getHeight() / 2, this.getWidth(), this.getHeight() / 2);
        Line2D.Float ejeY = new Line2D.Float(this.getWidth() / 2, 0, this.getWidth() / 2, this.getHeight());
        Font fuente = new Font("Times New Roman", Font.PLAIN, 20);

        // Declaración de la función

        // Grafiación del fondo
        gc.setColor(new Color(194, 193, 243));
        gc.fillRect(0, 0, this.getWidth(), this.getHeight());

        // Graficación del eje X y Y
        gc.setColor(new Color(172, 50, 252));
        gc2d.setStroke(new BasicStroke(2.5f));
        gc2d.draw(ejeX);
        gc2d.draw(ejeY);

        // Gráficación del Punto Ecuación

        gc.setColor(Color.black);
        gc2d.setStroke(new BasicStroke(3.2f));

        try {
            for (int i = 1; i < getPuntosEcuacion().size() - 1; i++) {
                run();
                gc2d.draw(new Line2D.Double(

                        Double.valueOf(centro.getX().toString())
                                + ((Double.valueOf(getPunto(i).getX().toString())) * (this.getWidth() / 10)),
                        Double.valueOf(centro.getY().toString())
                                - ((Double.valueOf(getPunto(i).getY().toString())) * (this.getHeight() / 10)),
                        Double.valueOf(centro.getX().toString())
                                + ((Double.valueOf(getPunto(i + 1).getX().toString())) * (this.getWidth() / 10)),
                        Double.valueOf(centro.getY().toString())
                                - ((Double.valueOf(getPunto(i + 1).getY().toString())) * (this.getHeight() / 10))));
            }

        } catch (Exception e) {

        }

        // Graficación del termino
        gc.setColor(new Color(242, 132, 48));
        gc.setFont(fuente);
        gc2d.drawString(poli.toString(), Float.valueOf(centro.getX().toString()) + this.getWidth() / 7,
                Float.valueOf(centro.getY().toString()) - this.getHeight() / 7);

        setSize(1067, 733);
    }

    public void run() {
        try {
            hilo.sleep(500);
        } catch (InterruptedException exp) {
            exp.printStackTrace();
        }

    }

}