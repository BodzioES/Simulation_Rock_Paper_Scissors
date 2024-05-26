public class Simulation {
    public static void main(String[] args) {
        double szerokosc = 100.0;
        double wysokosc = 100.0;
        Plansza plansza = new Plansza(szerokosc,wysokosc);

        plansza.dodajObiekt(new Obiekty.Nozyczki(10,10));

    }
}
