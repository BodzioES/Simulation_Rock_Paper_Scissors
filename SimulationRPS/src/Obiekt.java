public abstract class Obiekt {
    protected  double x,y;

    public Obiekt(double x, double y){
        this.x = x;
        this.y = y;
    }

    public abstract void ruch(Plansza plansza);

    public abstract void kolizja(Obiekt inny, Plansza plansza);

    public abstract boolean czyGoni(Obiekt inny);

    public abstract boolean czyUciekaPrzed(Obiekt inny);

    public void ruchZPredkoscia(double dx, double dy, double predkosc){
        x += dx * predkosc;
        y += dy * predkosc;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
