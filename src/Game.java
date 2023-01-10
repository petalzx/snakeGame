import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener
{
    private Snake player;
    private Food food;
    private Graphics graphics;

    private JFrame window;

    //dimensions of the game
    public static final int width = 30;
    public static final int length = 30;
    public static final int dimension = 20;

    public Game()
    {
        window = new JFrame();
        player = new Snake();
        food = new Food(player);

        graphics = new Graphics(this);

        //Adds graphics onto the JFrame window
        window.add(graphics);

        window.setTitle("Snake Game");
        window.setSize(width*dimension+1, length*dimension+3);
        //Frame appears on the middle of the screen instead of on the corner
        window.setLocationRelativeTo(null);
        //Makes frame visible
        window.setVisible(true);

        //Allows us to close window by hitting the exit button "x"
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Start the game
    public void start()
    {
        graphics.state = "RUNNING";
    }

    //Manages the game and updates it depending on what happens
    public void update()
    {
        if(graphics.state.equals("RUNNING"))
        {
            //Checks if snake touches food
            if (checkFoodCollision())
            {
                player.grow();
                food.randomSpawn(player);
            }
            //Checks if snake touches its own body or the border
            else if(checkBorderCollision()||checkSelfCollision())
            {
                graphics.state = "END";
            }
            else
            {
                player.move();
            }
        }

    }

    //Method of KeyListener that determines when key is typed
    @Override
    public void keyTyped(KeyEvent e) { }

    //Method of KeyListener that determines when key is pressed
    @Override
    public void keyPressed(KeyEvent e)
    {
        //keyCode is an int that helps determine which key was actually pressed
        int keyCode = e.getKeyCode();

        if(graphics.state.equals("RUNNING"))
        {
            //snake now moves up
            if(keyCode == KeyEvent.VK_UP)
            {
                player.up();
            }

            //snake now moves down
            else if(keyCode == KeyEvent.VK_DOWN)
            {
                player.down();
            }

            //snake now moves left
            else if(keyCode == KeyEvent.VK_LEFT)
            {
                player.left();
            }

            //snake now moves right
            else if(keyCode == KeyEvent.VK_RIGHT)
            {
                player.right();
            }
        }
        else
        {
            this.start();
        }
    }

    //Checks if the snake collides with the border of the game
    private boolean checkBorderCollision()
    {
        if (player.getX() < 0 || player.getX() >= width * dimension
                || player.getY() < 0 || player.getY() >= length * dimension)
        {
            return true;
        }
        return false;
    }

    //Checks if the snake collides with food
    private boolean checkFoodCollision()
    {
        if (player.getX() == food.getX() * dimension
                && player.getY() == food.getY() * dimension)
        {
            return true;
        }
        return false;
    }

    //Checks if the snake collides with its own body
    private boolean checkSelfCollision()
    {
        for(int i = 3; i < player.getBody().size();i++)
        {
            if (player.getX() == player.getBody().get(i).x
                    && player.getY() == player.getBody().get(i).y)
            {
                return true;
            }
        }
        return false;
    }

    //Method of KeyListener that determine when key is released
    @Override
    public void keyReleased(KeyEvent e) { }

    //Returns player object
    public Snake getPlayer() {
        return player;
    }

    //Sets player object to parameter
    public void setPlayer(Snake player) {
        this.player = player;
    }

    //Returns food object
    public Food getFood() {
        return food;
    }

    //Sets food object to parameter
    public void setFood(Food food) {
        this.food = food;
    }

    //Returns window object
    public JFrame getWindow() {
        return window;
    }

    //Sets window object to parameter
    public void setWindow(JFrame window) {
        this.window = window;
    }

}
