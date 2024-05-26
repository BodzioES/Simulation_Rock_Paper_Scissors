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

    public void dodajObiekt(Obiekt obiekt){
        obiekty.add(obiekt);
    }

    public void symuluj(){
        for (Obiekt obiekt : new ArrayList<>(obiekty)){
            obiekt.ruch(this);
        }
    }

    public void losowyRuch(Obiekt obiekt){
        double dx = random.nextDouble() * 2 - 1;
        double dy = random.nextDouble() * 2 - 1;

        obiekt.x += dx;
        obiekt.y += dy;

        if (obiekt.x < 0) obiekt.x = 0;
        if (obiekt.y < 0) obiekt.y = 0;
        if (obiekt.x > szerokosc) obiekt.x = szerokosc;
        if (obiekt.y > wysokosc) obiekt.y = wysokosc;
    }

}
