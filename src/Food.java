import java.awt.Rectangle;

public class Food
{
    //location of the food (x,y)
    private int x;
    private int y;

    private int w = Game.width;
    private int l = Game.length;
    private int d = Game.dimension;

    public Food(Snake player)
    {
        this.randomSpawn(player);
    }

    //Randomly spawns the location of the food
    public void randomSpawn(Snake player)
    {
        //Determines whether or not the food spawns on the snake,
        //We do not want the food to spawn on top of the snake
        boolean onSnake = true;

        //Checks if the food was spawn on the body of the snake, checks each rectangle
        while(onSnake)
        {
            onSnake = false;
            x = (int)(Math.random() * w);
            y = (int)(Math.random() * l);

            for(Rectangle r : player.getBody())
            {
                if (r.x == x && r.y ==y)
                {
                    onSnake = true;
                }

            }
        }

    }

    //returns x coordinate of the food
    public int getX() {
        return x;
    }

    //returns y coordinate of the food
    public int getY() {
        return y;
    }
}


