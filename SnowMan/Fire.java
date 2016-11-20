import greenfoot.*;

/**
 * A fire
 * 
 * @author Jonas Hatschek 
 * @version 2015-10-06
 */
public class Fire extends Mover
{
    /**
     * Constructs a default Fire
     */
    public Fire()
    {
        super(4, new String[]{"fire1.png", "fire2.png"});

    }

    /**
     * Act - do whatever the fire wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        animate();
        randomMovement();
        move();
        snowmanMelt();
    }

    /**
     * Makes the fire move around randomly.
     */
    private void randomMovement()
    {
        if(Greenfoot.getRandomNumber(100) < 15)
        {
            setRotation(Greenfoot.getRandomNumber(4) * 90); 
        }

        if(getX() <= 0)
        {
            setRotation(359);
        }
        if(getX() >= getWorld().getWidth()-1)
        {
            setRotation(180);
        }
        if(getY() <= 0)
        {
            setRotation(90);
        }
        if(getY() >= getWorld().getHeight()-1)
        {
            setRotation(270);
        }
    }

    /**
     * Melts the snowman
     */
    public void snowmanMelt(){
        Actor snowman = getOneIntersectingObject(Snowman.class); //if the fires bumps into an actor of the class Snowman

        if(snowman != null)
        {
            getWorld().removeObject(snowman); //removes the snowman
            Greenfoot.stop(); //then ends game
        }
    }


}
