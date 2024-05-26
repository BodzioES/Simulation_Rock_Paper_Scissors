public class Nozyczki extends Obiekt {
    public Nozyczki(double x, double y) {
        super(x, y);
    }

    @Override
    public void ruch(Plansza plansza) {
        plansza.losowyRuch(this);
    }

    @Override
    public void kolizja(Obiekt inny, Plansza plansza){
        if (inny instanceof Kamien){
            plansza.zamienObiekt(this, new Kamien(x,y));
        }
    }
}