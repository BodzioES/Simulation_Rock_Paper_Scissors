public class Simulation {
    public static void main(String[] args) {
        double szerokosc = 100.0;
        double wysokosc = 100.0;
        Plansza plansza = new Plansza();

        plansza.dodajObiekt(new Nozyczki(10,10));
        plansza.dodajObiekt(new Kamien(20,20));
        plansza.dodajObiekt(new Papier(30,30));

        for (int i = 0; i < 100; i++){
            plansza.symuluj();
            wyswietlPlansze(plansza);
        }
    }
    public static void wyswietlPlansze(Plansza plansza){
        System.out.println("symulacja");
    }
}
