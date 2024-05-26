public abstract class Obiekt {
    protected  double x,y;

    public Obiekt(double x, double y){
        this.x = x;
        this.y = y;
    }

    public abstract void ruch(Plansza plansza);

    public abstract void kolizja(Obiekt inny, Plansza plansza);

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }
}
