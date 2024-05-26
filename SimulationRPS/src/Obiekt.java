public abstract class Obiekt {
    protected  double x,y;

    public Obiekt(double x, double y){
        this.x = x;
        this.y = y;
    }

    public abstract void ruch(Plansza plansza);
}
