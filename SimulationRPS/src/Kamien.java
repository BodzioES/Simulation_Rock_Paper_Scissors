public class Kamien extends Obiekt{
    public Kamien(double x, double y){
        super(x,y);
    }

    @Override
    public void ruch(Plansza plansza){
        plansza.losowyRuch(this);
    }

    @Override
    public void kolizja(Obiekt inny, Plansza plansza) {
        if (inny instanceof Papier) {
            plansza.zamienObiekt(this, new Papier(x, y));
        }
    }
}