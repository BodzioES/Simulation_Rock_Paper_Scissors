import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanszaGUI extends JPanel implements ActionListener {
    private final Plansza plansza;
    private static final int OBIEKT_SIZE = 10;
    private final ImageIcon nozyczkiIkona;
    private final ImageIcon kamienIkona;
    private final ImageIcon papierIkona;

    public  PlanszaGUI(Plansza plansza){
        this.plansza = plansza;

        nozyczkiIkona = new ImageIcon("D:\\Programowanie\\JAVA\\SimulationRPS\\SimulationRPS\\scissor.png");
        kamienIkona = new ImageIcon("D:\\Programowanie\\JAVA\\SimulationRPS\\SimulationRPS\\rock.png");
        papierIkona = new ImageIcon("D:\\Programowanie\\JAVA\\SimulationRPS\\SimulationRPS\\paper.png");

        Timer timer = new Timer(50,this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Obiekt obiekt : plansza.getObiekty()) {
            if (obiekt instanceof Nozyczki) {
                nozyczkiIkona.paintIcon(this, g, (int) obiekt.getX(), (int) obiekt.getY());
            } else if (obiekt instanceof Kamien) {
                kamienIkona.paintIcon(this, g, (int) obiekt.getX(), (int) obiekt.getY());
            } else if (obiekt instanceof Papier) {
                papierIkona.paintIcon(this, g, (int) obiekt.getX(), (int) obiekt.getY());
            }
        }
    }


    @Override
    public void  actionPerformed(ActionEvent e){
        plansza.symuluj();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Symulacja Planszy");
        Plansza plansza = new Plansza();

        for (int i = 0; i < 20; i++){
            plansza.dodajObiekt(new Nozyczki(Math.random() * 800, Math.random() * 800));
            plansza.dodajObiekt(new Kamien(Math.random() * 800, Math.random() * 800));
            plansza.dodajObiekt(new Papier(Math.random() * 800, Math.random() * 800));
        }

        PlanszaGUI panel = new PlanszaGUI(plansza);
        frame.add(panel);
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
