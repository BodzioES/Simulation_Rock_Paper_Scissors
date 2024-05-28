public class Kamien extends Obiekt{
    public Kamien(double x, double y){
        super(x,y);
    }

    @Override
    public void ruch(Plansza plansza){
        plansza.ruchObiektu(this);
    }

    @Override
    public void kolizja(Obiekt inny, Plansza plansza) {
        if (inny instanceof Papier) {
            plansza.zamienObiekt(this, new Papier(x, y));
        }
    }

    @Override
    public boolean czyGoni(Obiekt inny){
        return inny instanceof Nozyczki;
    }

    @Override
    public boolean czyUciekaPrzed(Obiekt inny){
        return  inny instanceof Papier;
    }
}