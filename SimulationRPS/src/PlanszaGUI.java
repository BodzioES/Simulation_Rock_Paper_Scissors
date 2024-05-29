import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanszaGUI extends JPanel implements ActionListener {
    private final Plansza plansza;
    private final ImageIcon nozyczkiIkona;
    private final ImageIcon kamienIkona;
    private final ImageIcon papierIkona;

    public  PlanszaGUI(Plansza plansza){
        this.plansza = plansza;

        nozyczkiIkona = new ImageIcon("scissor.png");
        kamienIkona = new ImageIcon("rock.png");
        papierIkona = new ImageIcon("paper.png");

        Timer timer = new Timer(50,this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Obiekt obiekt : plansza.getObiekty()) {
            if (obiekt instanceof Nozyczki) {
                g.setColor(Color.RED);
            } else if (obiekt instanceof Kamien) {
                g.setColor(Color.GRAY);
            } else if (obiekt instanceof Papier) {
                g.setColor(Color.BLUE);
            }
            g.fillRect((int) obiekt.getX(), (int) obiekt.getY(), 10, 10);
        }
    }


    @Override
    public void  actionPerformed(ActionEvent e){
        plansza.symuluj();
        repaint();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Symulacja Planszy");
        Plansza plansza = new Plansza(800,800);

        for (int i = 0; i < 20; i++){
            plansza.dodajObiekt(new Nozyczki(Math.random() * 800, Math.random() * 600));
            plansza.dodajObiekt(new Kamien(Math.random() * 800, Math.random() * 600));
            plansza.dodajObiekt(new Papier(Math.random() * 800, Math.random() * 600));
        }

        PlanszaGUI panel = new PlanszaGUI(plansza);
        frame.add(panel);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
