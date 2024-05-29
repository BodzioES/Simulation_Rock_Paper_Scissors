import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plansza {
    private final List<Obiekt> obiekty;
    private final double szerokosc;
    private final double wysokosc;
    private final Random random;
    private static final double PREDKOSC_GONIACA = 4.0;
    private static final double PREDKOSC_UCIEKAJACA = 2.0;

    public Plansza(double szerokosc, double wysokosc) {
        this.szerokosc = szerokosc;
        this.wysokosc = wysokosc;
        this.obiekty = new ArrayList<>();
        this.random = new Random();
    }

    public void dodajObiekt(Obiekt obiekt) {
        obiekty.add(obiekt);
    }

    public void zamienObiekt(Obiekt stary, Obiekt nowy) {
        stary.x = nowy.getX();
        stary.y = nowy.getY();
        obiekty.remove(stary);
        obiekty.add(nowy);
    }

    public void symuluj() {
        for (Obiekt obiekt : new ArrayList<>(obiekty)) {
            obiekt.ruch(this);
            sprawdzKolizje(obiekt);
        }
        sprawdzKoniecSymulacji();
    }

    public void sprawdzKolizje(Obiekt obiekt) {
        for (Obiekt inny : new ArrayList<>(obiekty)) {
            if (obiekt != inny && odleglosc(obiekt, inny) < 10.0) {
                obiekt.kolizja(inny, this);
            }
        }
    }

    private double odleglosc(Obiekt a, Obiekt b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public void losowyRuch(Obiekt obiekt) {
        double dx = random.nextDouble() * 2 - 1;
        double dy = random.nextDouble() * 2 - 1;

        obiekt.ruchZPredkoscia(dx, dy, 1.0);
        ograniczRuch(obiekt);
    }

    public void ruchObiektu(Obiekt obiekt) {
        Obiekt cel = znajdzCel(obiekt);
        if (cel != null) {
            double dx = cel.getX() - obiekt.getX();
            double dy = cel.getY() - obiekt.getY();
            double odleglosc = Math.sqrt(dx * dx + dy * dy);

            if (odleglosc > 0) {
                dx /= odleglosc;
                dy /= odleglosc;
            }

            if (obiekt.czyGoni(cel)) {
                obiekt.ruchZPredkoscia(dx, dy, PREDKOSC_GONIACA);
            } else if (obiekt.czyUciekaPrzed(cel)) {
                obiekt.ruchZPredkoscia(-dx, -dy, PREDKOSC_UCIEKAJACA);
            }
        } else {
            losowyRuch(obiekt);
        }
        ograniczRuch(obiekt);
    }

    private Obiekt znajdzCel(Obiekt obiekt) {
        for (Obiekt inny : obiekty) {
            if (obiekt.czyGoni(inny) || obiekt.czyUciekaPrzed(inny)) {
                return inny;
            }
        }
        return null;
    }

    private void ograniczRuch(Obiekt obiekt) {
        if (obiekt.getX() < 0) {
            obiekt.x = 0;
            obiekt.odbijOdSciany();
        }
        if (obiekt.getY() < 0) {
            obiekt.y = 0;
            obiekt.odbijOdSciany();
        }
        if (obiekt.getX() > szerokosc - 10) {
            obiekt.x = szerokosc - 10;
            obiekt.odbijOdSciany();
        }
        if (obiekt.getY() > wysokosc - 10) {
            obiekt.y = wysokosc - 10;
            obiekt.odbijOdSciany();
        }
    }

    private void sprawdzKoniecSymulacji() {
        long liczbaNozyczek = obiekty.stream().filter(o -> o instanceof Nozyczki).count();
        long liczbaKamieni = obiekty.stream().filter(o -> o instanceof Kamien).count();
        long liczbaPapierow = obiekty.stream().filter(o -> o instanceof Papier).count();

        if (liczbaNozyczek == 0 && liczbaKamieni == 0) {
            zamrozSymulacje("Papier wygrywa!");
        } else if (liczbaKamieni == 0 && liczbaPapierow == 0) {
            zamrozSymulacje("Nozyczki wygrywaja!");
        } else if (liczbaNozyczek == 0 && liczbaPapierow == 0) {
            zamrozSymulacje("Kamien wygrywaja!");
        }
    }

    private void zamrozSymulacje(String wiadomosc) {
        JOptionPane.showMessageDialog(null, wiadomosc);
        System.exit(0);
    }

    public List<Obiekt> getObiekty() {
        return obiekty;
    }
}
