public class Papier extends Obiekt {
    public Papier(double x, double y) {
        super(x, y);
    }

    @Override
    public void ruch(Plansza plansza) {
        plansza.losowyRuch(this);
    }
}