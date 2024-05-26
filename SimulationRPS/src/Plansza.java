import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plansza {
    private final List<Obiekt> obiekty;
    private final double szerokosc;
    private final double wysokosc;
    private final Random random;

    public Plansza(double szerokosc, double wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.obiekty = new ArrayList<>();
        this.random = new Random();
    }

    public void dodajObiekt(Obiekt obiekt){
        obiekty.add(obiekt);
    }

    public void zamienObiekt(Obiekt stary, Obiekt nowy){
        obiekty.remove(stary);
        obiekty.add(nowy);
    }

    public void symuluj(){
        for (Obiekt obiekt : new ArrayList<>(obiekty)){
            obiekt.ruch(this);
            sprawdzKolizje(obiekt);
        }
    }

    public void sprawdzKolizje(Obiekt obiekt){
        for (Obiekt inny : new ArrayList<>(obiekty)){
            if (obiekt != inny && odleglosc(obiekt, inny) < 1.0){
                obiekt.kolizja(inny,this);
            }
        }
    }

    private double odleglosc(Obiekt a, Obiekt b){
        return Math.sqrt(Math.pow(a.getX() - b.getX(),2)) + Math.pow(a.getY() - b.getY(),2);
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

    public List<Obiekt> getObiekty(){
        return obiekty;
    }

}
