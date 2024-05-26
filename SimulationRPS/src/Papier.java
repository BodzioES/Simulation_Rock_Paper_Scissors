public class Papier extends Obiekt {
    public Papier(double x, double y) {
        super(x, y);
    }

    @Override
    public void ruch(Plansza plansza) {
        plansza.losowyRuch(this);
    }

    @Override
    public void kolizja(Obiekt inny, Plansza plansza) {
        if (inny instanceof Nozyczki) {
            plansza.zamienObiekt(this, new Nozyczki(x, y));
        }
    }
}