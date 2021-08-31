package Plotter;

/**
 * Write a description of class PuntoEcuacionUI here.
 * 
 * @author Felipe Focil
 * @version 1.0
 *
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PuntoEcuacionUI extends JFrame {

    private PuntoEcuacion lienzo;
    private Polinomio polinomio;
    private Polinomio polBlanco = new Polinomio(0);
    private JLabel l1, l2, l3, l4, l5, l6, l7;
    private JTextField tf1, tf2, tf3, tf4, tf5, tf6;
    private JButton b1, b2, b3, b4;

    private String tituloGrado = "Inserte una Función";
    private JPanel panel;
    private JPanel izq, der;
    private JPanel aba, med, arr;
    private JPanel r1;
    private JPanel r2, r2_1, r2_2;
    private JPanel r3, r3_1, r3_2, r3_3;
    private JPanel r4;
    private Font l;
    private Dimension tf;

    /**
     * Constructor for objects of class Calculadora
     */
    public PuntoEcuacionUI() {
        setTitle(tituloGrado);
        Término terBlanco = new Término(0, 0);
        polBlanco.agregaTermino(terBlanco);
        lienzo = new PuntoEcuacion();
        lienzo.calculaPuntos(polBlanco, 0.0, 0, 0.1);

        l = new Font("Times New Roman", Font.PLAIN, 15);
        tf = new Dimension(100, 25);

        izq = new JPanel();
        der = new JPanel();

        arr = new JPanel();
        r1 = new JPanel();
        l1 = new JLabel("Parametro de la Gráfica");
        l1.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        l2 = new JLabel("Grado del Polinomio : ");
        l2.setFont(l);
        tf1 = new JTextField();
        tf1.setPreferredSize(tf);
        b1 = new JButton("Crear Polinomio");

        med = new JPanel();
        r2 = new JPanel();
        r2_1 = new JPanel();
        r2_2 = new JPanel();
        l3 = new JLabel("Coeficiente del Término : ");
        l3.setFont(l);
        tf2 = new JTextField();
        tf2.setPreferredSize(tf);
        l4 = new JLabel("Exponente del Término : ");
        l4.setFont(l);
        tf3 = new JTextField();
        tf3.setPreferredSize(tf);
        b2 = new JButton("Añadir Termino");

        aba = new JPanel();
        r3 = new JPanel();
        r3_1 = new JPanel();
        r3_2 = new JPanel();
        r3_3 = new JPanel();
        r4 = new JPanel();
        l5 = new JLabel("Limite Inferior : ");
        l5.setFont(l);
        tf4 = new JTextField();
        tf4.setPreferredSize(tf);
        l6 = new JLabel("Limite Superior : ");
        l6.setFont(l);
        tf5 = new JTextField();
        tf5.setPreferredSize(tf);
        l7 = new JLabel("Intervalo : ");
        l7.setFont(l);
        tf6 = new JTextField();
        tf6.setPreferredSize(tf);
        b3 = new JButton("Gráficar");

        b4 = new JButton("Limpiar");

        panel = new JPanel();

        // Configuración de la parte de la izquierda
        izq.setLayout(new GridLayout(3, 1));

        // Izquierda Arriba
        arr.setLayout(new BorderLayout());
        arr.add(l1, "North");
        r1.setLayout(new FlowLayout());
        r1.add(l2);
        r1.add(tf1);
        arr.add(r1, "Center");
        arr.add(b1, "South");
        izq.add(arr);

        // Izquierda Media
        med.setLayout(new BorderLayout());
        r2.setLayout(new GridLayout(2, 1));
        r2_1.setLayout(new FlowLayout());
        r2_1.add(l3);
        r2_1.add(tf2);

        r2_2.setLayout(new FlowLayout());
        r2_2.add(l4);
        r2_2.add(tf3);

        r2.add(r2_1);
        r2.add(r2_2);
        med.add(r2, "Center");
        med.add(b2, "South");
        izq.add(med);

        // Izquierda Baja

        aba.setLayout(new BorderLayout());
        r3.setLayout(new GridLayout(4, 1));
        r3_1.setLayout(new FlowLayout());
        r3_1.add(l5);
        r3_1.add(tf4);
        r3_2.setLayout(new FlowLayout());
        r3_2.add(l6);
        r3_2.add(tf5);
        r3_3.setLayout(new FlowLayout());
        r3_3.add(l7);
        r3_3.add(tf6);

        r3.add(r3_1);
        r3.add(r3_2);
        r3.add(r3_3);

        r4.setLayout(new GridLayout(2, 1));
        r4.add(b3);
        r4.add(b4);
        aba.add(r3, "Center");
        aba.add(r4, "South");
        izq.add(aba);

        der.setLayout(new BorderLayout());
        der.add(lienzo);

        panel.setLayout(new BorderLayout());
        panel.add(izq, "West");
        panel.add(der, "Center");
        add(panel);

        addWindowListener(new WinC());
        b1.addActionListener(new GradoPolinomio());
        b2.addActionListener(new AgregaTermino());
        b3.addActionListener(new Graficar());
        b4.addActionListener(new Limpiar());

        tf2.setEnabled(false);
        tf3.setEnabled(false);
        tf4.setEnabled(false);
        tf5.setEnabled(false);
        tf6.setEnabled(false);
        b2.setEnabled(false);
        b3.setEnabled(false);
        b4.setEnabled(false);

        setSize(1365, 733);
        setVisible(true);
        setLocationRelativeTo(null);
        setResizable(false);

    }

    private class WinC extends WindowAdapter {
        public void windowClosing(WindowEvent e) {
            setVisible(false);
            dispose();
            JOptionPane.showMessageDialog(null, "Gracias por utilizar este Software",
                    "El Programa Ha Finalizado Correctamente", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    private class GradoPolinomio implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                if (Integer.valueOf(tf1.getText()) > 0) {
                    polinomio = new Polinomio(Integer.valueOf(tf1.getText()));
                    tituloGrado = "Función grado " + Integer.valueOf(tf1.getText()) + " -> ";
                    setTitle(tituloGrado);

                    tf2.setEnabled(true);
                    tf3.setEnabled(true);
                    b2.setEnabled(true);

                } else {
                    JOptionPane.showMessageDialog(null, "El Grado del Polinomio debe ser mayor a 0",
                            "No se pudo crear el Polinomio ", JOptionPane.WARNING_MESSAGE);
                }
                tf1.setText("");

            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(null, "Asegurese de Rellenar Los Campos Correctamente", error.toString(),
                        JOptionPane.WARNING_MESSAGE);
                tf1.setText("");
            }
        }
    }

    private class AgregaTermino implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double cof;
                int exp;
                cof = Double.valueOf(tf2.getText());
                exp = Integer.valueOf(tf3.getText());

                if (polinomio.getGrado() >= exp) {
                    polinomio.agregaTermino(new Término(cof, exp));
                    setTitle(tituloGrado + polinomio.toString());
                    tf4.setEnabled(true);
                    tf5.setEnabled(true);
                    tf6.setEnabled(true);
                    b3.setEnabled(true);
                    b4.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "No puede insertar un término de grado " + exp + " en un polinomio de grado  "
                                    + polinomio.getGrado(),
                            "Asegurese de Rellenar los Campos Correctamente", JOptionPane.WARNING_MESSAGE);
                }

                tf2.setText("");
                tf3.setText("");

            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(null, "Asegurese de Rellenar Los Campos Correctamente", error.toString(),
                        JOptionPane.WARNING_MESSAGE);
                tf2.setText("");
                tf3.setText("");
            }
        }
    }

    private class Graficar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                double linf, lsup, inc;

                linf = Double.valueOf(tf4.getText());
                lsup = Double.valueOf(tf5.getText());
                inc = Double.valueOf(tf6.getText());

                if (linf < lsup) {
                    der.remove(lienzo);
                    lienzo.calculaPuntos(polinomio, linf, lsup, inc);
                    der.add(lienzo);
                    repaint();
                    tf1.setEnabled(false);
                    tf2.setEnabled(false);
                    tf3.setEnabled(false);
                    tf4.setEnabled(false);
                    tf5.setEnabled(false);
                    tf6.setEnabled(false);
                    b1.setEnabled(false);
                    b2.setEnabled(false);
                    b3.setEnabled(false);
                } else {

                    JOptionPane.showMessageDialog(null,
                            "El Limite Inferior tiene que ser Estrictamente Menor que el Limite Superior",
                            "Asegurese de Insertar Los Datos Correctamente", JOptionPane.WARNING_MESSAGE);
                }
                tf4.setText("");
                tf5.setText("");
                tf6.setText("");

            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(null, "Asegurese de Rellenar Los Campos Correctamente", error.toString(),
                        JOptionPane.WARNING_MESSAGE);
                tf4.setText("");
                tf5.setText("");
                tf6.setText("");
            }
        }
    }

    private class Limpiar implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                tf1.setText("");
                tf2.setText("");
                tf3.setText("");
                tf4.setText("");
                tf5.setText("");
                tf6.setText("");

                der.remove(lienzo);
                lienzo.calculaPuntos(polBlanco, -1, 1, 2);
                der.add(lienzo);
                repaint();

                tf1.setEnabled(true);
                b1.setEnabled(true);
                tf2.setEnabled(false);
                tf3.setEnabled(false);
                tf4.setEnabled(false);
                tf5.setEnabled(false);
                tf6.setEnabled(false);
                b2.setEnabled(false);
                b3.setEnabled(false);
                b4.setEnabled(false);
                setTitle("Inserte una Función");

            } catch (Exception error) {
                JOptionPane.showMessageDialog(null, "Error Inesperado", error.toString(), JOptionPane.ERROR_MESSAGE);
            }
        }

    }

}
