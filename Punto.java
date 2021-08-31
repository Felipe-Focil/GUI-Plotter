package Plotter;

/**
 * Write a description of class Punto here.
 * 
 * @author (your name)
 * @version (a version number or a date)
 */
public class Punto<E> {
    private E x;
    private E y;

    public Punto(E x, E y) {
        setX(x);
        setY(y);
    }

    public void setX(E x) {
        this.x = x;
    }

    public void setY(E y) {
        this.y = y;
    }

    public E getX() {
        return x;
    }

    public E getY() {
        return y;
    }

    public String toString() {
        return new String("( " + getX() + " , " + getY() + " )");
    }

    public static void main(String args[]) {
        Punto p1 = new Punto(1, 2);
        Punto p2 = new Punto(3.2, -23.2);

        System.out.println(p1.toString());
        System.out.println(p2.toString());
    }
}
