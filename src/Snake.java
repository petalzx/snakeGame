import java.awt.Rectangle;
import java.util.ArrayList;

public class Snake
{
    //body of the snake will be an arraylist of rectangles
    private ArrayList<Rectangle> body;
    private int w = Game.width;
    private int l = Game.length;
    private int d = Game.dimension;

    //indicates the direction that the snake is moving
    //NOTHING, UP, DOWN, LEFT, RIGHT
    private String move;

    public Snake()
    {
        body = new ArrayList<>();

        Rectangle temp = new Rectangle(d, d);
        temp.setLocation(w/2 * d, l/2 * d);
        body.add(temp);

        temp = new Rectangle(d, d);
        temp.setLocation((w/2-1) * d, l/2 * d);
        body.add(temp);

        temp = new Rectangle(d, d);
        temp.setLocation((w/2-2) * d, l/2 * d);
        body.add(temp);

        move = "NOTHING";

    }

    //Moves the snake
    //Adds a rectangle to the front of the body and deletes a rectangle from the back
    public void move()
    {
        if (!move.equals("NOTHING"))
        {
            //First rectangle in the direction that the snake is moving
            Rectangle first = body.get(0);

            //Temporary rectangle to be added to the head of the snake
            Rectangle temp = new Rectangle(d,d);

            //Tests to see in which direction the snake is moving
            //Determines the coordinates of the temp head
            if(move.equals("UP"))
            {
                temp.setLocation(first.x, first.y-d);
            }
            else if(move.equals("DOWN"))
            {
                temp.setLocation(first.x, first.y+d);
            }
            else if(move.equals("LEFT"))
            {
                temp.setLocation(first.x-d, first.y);
            }
            else if(move.equals("RIGHT"))
            {
                temp.setLocation(first.x+d, first.y);
            }

            //Adds updated head of the snake
            body.add(0,temp);

            //Removes the tail of the snake
            body.remove(body.size()-1);

        }
    }

    //Allows the snake to grow in size when it passes a food rectangle
    public void grow()
    {
        //First rectangle in the direction that the snake is moving
        Rectangle first = body.get(0);

        //Rectangle to be added to the head of the snake
        Rectangle temp = new Rectangle(d,d);

        //Tests to see in which direction the snake is moving
        //Determines the coordinates of the temp head
        if(move.equals("UP"))
        {
            temp.setLocation(first.x, first.y-d);
        }
        else if(move.equals("DOWN"))
        {
            temp.setLocation(first.x, first.y+d);
        }
        else if(move.equals("LEFT"))
        {
            temp.setLocation(first.x-d, first.y);
        }
        else if(move.equals("RIGHT"))
        {
            temp.setLocation(first.x+d, first.y);
        }

        //Adds updated head of the snake
        body.add(0,temp);
    }


    //returns body of the snake
    public ArrayList<Rectangle> getBody()
    {
        return body;
    }

    //sets body of the snake to a new body parameter
    public void setBody(ArrayList<Rectangle> body)
    {
        this.body = body;
    }

    //Returns x coordinate of the head of the snake
    public int getX()
    {
        return body.get(0).x;
    }

    //Returns y coordinate of the head of the snake
    public int getY()
    {
        return body.get(0).y;
    }

    //sets movement to up
    public void up()
    {
        if (!move.equals("DOWN"))
        {
            move = "UP";
        }
    }

    //sets movement to down
    public void down()
    {
        if (!move.equals("UP"))
        {
            move = "DOWN";
        }
    }

    //sets movement to left
    public void left()
    {
        if (!move.equals("RIGHT"))
        {
            move = "LEFT";
        }
    }

    //sets movement to right
    public void right()
    {
        if (!move.equals("LEFT"))
        {
            move = "RIGHT";
        }
    }

}

