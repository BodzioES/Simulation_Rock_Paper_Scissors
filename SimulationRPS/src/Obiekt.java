public abstract class Obiekt {
    protected double x, y;
    protected double vx, vy;
    protected static final int OBIEKT_SIZE = 10;

    public Obiekt(double x, double y) {
        this.x = x;
        this.y = y;
        this.vx = 0;
        this.vy = 0;
    }

    public abstract void ruch(Plansza plansza);

    public abstract void kolizja(Obiekt inny, Plansza plansza);

    public abstract boolean czyGoni(Obiekt inny);

    public abstract boolean czyUciekaPrzed(Obiekt inny);

    public void ruchZPredkoscia(double dx, double dy, double predkosc) {
        vx = dx * predkosc;
        vy = dy * predkosc;
        x += vx;
        y += vy;
    }

    public void odbijOdSciany() {
        vx = -vx;
        vy = -vy;
    }

    public void odbijOdObiekty() {
        vx = -vx;
        vy = -vy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}