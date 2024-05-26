import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plansza {
    private List<Obiekt> obiekty;
    private double szerokosc, wysokosc;
    private Random random;

    public Plansza(double szerokosc, double wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.obiekty = new ArrayList<>();
        this.random = new Random();
    }

}
