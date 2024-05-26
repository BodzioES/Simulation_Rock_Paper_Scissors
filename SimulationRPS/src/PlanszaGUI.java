import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlanszaGUI extends JPanel implements ActionListener {
    private Plansza plansza;

    public  PlanszaGUI(Plansza plansza){
        this.plansza = plansza;
        Timer timer = new Timer(100,this);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        for (Obiekt obiekt : plansza.getObiekty()){
            if (obiekt instanceof Nozyczki){
                g.setColor(Color.red);
                g.fillRect((int) obiekt.getX(), (int) obiekt.getY(),10,10);
            }else if (obiekt instanceof Kamien){
                g.setColor(Color.gray);
                g.fillRect((int) obiekt.getX(), (int) obiekt.getY(),10,10);
            }else if (obiekt instanceof Papier){
                g.setColor(Color.GREEN);
                g.fillRect((int) obiekt.getX(), (int) obiekt.getY(),10,10);
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
        Plansza plansza = new Plansza(800,800);

        plansza.dodajObiekt(new Nozyczki(100,100));
        plansza.dodajObiekt(new Kamien(200,200));
        plansza.dodajObiekt(new Papier(300,300));

        PlanszaGUI panel = new PlanszaGUI(plansza);
        frame.add(panel);
        frame.setSize(800,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
