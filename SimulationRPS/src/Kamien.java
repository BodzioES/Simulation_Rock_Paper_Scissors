public class Kamien extends Obiekt{
    public Kamien(double x, double y){
        super(x,y);
    }

    @Override
    public void ruch(Plansza plansza){
        plansza.losowyRuch(this);
    }
}