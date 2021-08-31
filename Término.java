package Plotter;

/**
 * Clase Termino
 * 
 * @author Felipe Focil
 * @version 1.0
 */
public class Término {
    private double coeficiente;
    private int exponente;

    Término(double coef, int exp) {
        setCoeficiente(coef);
        setExponente(exp);
    }

    public void setCoeficiente(double coef) {
        coeficiente = coef;
    }

    public void setExponente(int exp) {
        exponente = exp;
    }

    public double getCoeficiente() {
        return coeficiente;
    }

    public int getExponente() {
        return exponente;
    }

    public double evalua(double x) {
        return coeficiente * Math.pow(x, exponente);
    }

    public String toString() {
        if (getCoeficiente() != 0) {
            if (getExponente() >= 1 || getExponente() < 0) {
                return new String(getCoeficiente() + "x^" + getExponente());
            } else if (getExponente() == 0) {
                return new String("" + getCoeficiente());
            }
        }
        return new String("");

    }

}
