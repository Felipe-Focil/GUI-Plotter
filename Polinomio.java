package Plotter;

/**
 * Clase Polinomio
 * 
 * @author Felipe Focil
 * @version 1.0
 */
public class Polinomio {
    private Término[] Términos;
    private int cont = 0;
    private int grado;

    public Polinomio(int grado) {
        this.grado = grado;
        Términos = new Término[grado + 1];
    }

    public void agregaTermino(Término term, int index) {
        if (index < Términos.length - 1) {

            if (Términos[index] == null) {
                cont++;
            }

            Términos[index] = term;

        }
    }

    public void agregaTermino(Término term) {
        if (cont < Términos.length) {
            Términos[cont] = term;
            cont++;
        }
    }

    public Double evalua(double x) {
        Double evaluacion = 0.0;
        for (Término term : Términos) {
            if (term != null) {
                evaluacion += term.evalua(x);
            }
        }
        return evaluacion;
    }

    public String toString() {

        String s = "y=";

        for (int i = 0; i < Términos.length; i++) {
            if (Términos[i] != null) {
                if (Términos[i].getCoeficiente() > 0) {
                    s += "+";
                }
                s += Términos[i].toString();
            }

        }
        return new String(s);
    }

    public int getGrado() {
        return grado;
    }
}
