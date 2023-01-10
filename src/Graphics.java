import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JPanel implements ActionListener {
    //Timer for the events
    private Timer t = new Timer(130, this);
    //Indicated the state of the game (START,
    public String state;

    private Snake s;
    private Food f;
    private Game game;

    private int w = Game.width;
    private int l = Game.length;
    private int d = Game.dimension;

    public Graphics(Game g) {
        t.start();
        state = "START";

        game = g;
        s = g.getPlayer();
        f = g.getFood();

        //Add a keyListener that registers our key actions
        this.addKeyListener(g);

        //Events are dispatched to the component that has focus
        this.setFocusable(true);

        //Prevents traversal keys from being accepted when current component has focus
        this.setFocusTraversalKeysEnabled(false);
    }

    //Calls the UI delegate's paint method that refreshed the display
    public void paintComponent(java.awt.Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;

        //Paints the background black
        g2d.setColor(Color.black);

        //Fills the game panel with black color
        g2d.fillRect(0, 0, w * d, l * d);

        //Start menu
        if (state.equals("START")) {
            g2d.setColor(Color.white);
            g2d.drawString("Press Any Key", w / 2 * d - 40, l / 2 * d - 20);

        }

        //Game display when playing
        else if (state.equals("RUNNING")) {
            //Draws food
            g2d.setColor(Color.red);
            g2d.fillRect(f.getX() * d, f.getY() * d, d, d);

            //Draws snake
            g2d.setColor(Color.green);
            for (Rectangle r : s.getBody()) {
                g2d.fill(r);
            }
        }

        //Game over screen
        else if (state.equals("END")) {
            g2d.setColor(Color.white);
            g2d.drawString("Score: " + (s.getBody().size() - 3), w / 2 * d - 40, l / 2 * d - 20);
        }

    }

    //Indicates that a meaningful action occured
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }

}