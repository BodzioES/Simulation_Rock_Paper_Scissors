import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Plansza {
    private final List<Obiekt> obiekty;
    private static final double SZEROKOSC = 800.0;
    private static final double WYSOKOSC = 800.0;
    private final Random random;
    private static final double PREDKOSC_GONIACA = 4.0;
    private static final double PREDKOSC_UCIEKAJACA = 2.0;

    public Plansza() {
        this.obiekty = new ArrayList<>();
        this.random = new Random();
    }

    public void dodajObiekt(Obiekt obiekt) {
        obiekty.add(obiekt);
    }

    public void zamienObiekt(Obiekt stary, Obiekt nowy) {
        nowy.x = stary.getX();
        nowy.y = stary.getY();
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
            if (obiekt != inny && odleglosc(obiekt, inny) < Obiekt.OBIEKT_SIZE) {
                obiekt.kolizja(inny, this);
                odbijObiekty(obiekt, inny);
            }
        }
        ograniczRuch(obiekt);
    }

    private void odbijObiekty(Obiekt a, Obiekt b) {
        double tempVx = a.vx;
        double tempVy = a.vy;
        a.vx = b.vx;
        a.vy = b.vy;
        b.vx = tempVx;
        b.vy = tempVy;
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
        Obiekt najblizszy = null;
        double minOdleglosc = Double.MAX_VALUE;

        for (Obiekt inny : obiekty) {
            if (obiekt != inny && (obiekt.czyGoni(inny) || obiekt.czyUciekaPrzed(inny))) {
                double odleglosc = odleglosc(obiekt, inny);
                if (odleglosc < minOdleglosc) {
                    minOdleglosc = odleglosc;
                    najblizszy = inny;
                }
            }
        }
        return najblizszy;
    }

    private void ograniczRuch(Obiekt obiekt) {
        if (obiekt.getX() < 0) {
            obiekt.setX(0);
            obiekt.odbijOdSciany();
        }
        if (obiekt.getY() < 0) {
            obiekt.setY(0);
            obiekt.odbijOdSciany();
        }
        if (obiekt.getX() > SZEROKOSC - Obiekt.OBIEKT_SIZE) {
            obiekt.setX(SZEROKOSC - Obiekt.OBIEKT_SIZE);
            obiekt.odbijOdSciany();
        }
        if (obiekt.getY() > WYSOKOSC - Obiekt.OBIEKT_SIZE) {
            obiekt.setY(WYSOKOSC - Obiekt.OBIEKT_SIZE);
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
            zamrozSymulacje("Nożyczki wygrywają!");
        } else if (liczbaNozyczek == 0 && liczbaPapierow == 0) {
            zamrozSymulacje("Kamień wygrywa!");
        }
    }

    private void zamrozSymulacje(String wiadomosc) {
        JOptionPane.showMessageDialog(null, wiadomosc);
        System.exit(0);
    }

    public List<Obiekt> getObiekty() {
        return obiekty;
    }

    public double getSzerokosc() {
        return SZEROKOSC;
    }

    public double getWysokosc() {
        return WYSOKOSC;
    }
}