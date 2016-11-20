import greenfoot.*;

/**
 * Ice that is shot from the snowman
 * 
 * @author Jonas Hatschek
 * @version 2015-10-06
 */
public class Ice extends Mover
{

    /**
     * Creates a web with the given direction
     */
    public Ice(int direction)
    {
        super(5, new String[]{"ice.png"});
        setRotation(direction);
    }

    /**
     * Act - do whatever the Ice wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if(move() || isAtEdge())
        {
            getWorld().removeObject(this); //removes the object if a brick wall is hit and simulates the moving sequence
        }
        else
        {
            checkForFires(); //method call for checkForFires-method
        }

    }    

    /**
     * Method that exchanges the fire for a trapped fire
     */
    private void checkForFires()
    {
        Fire fire = (Fire) getOneIntersectingObject(Fire.class); //instantiates a new variable fire if it intesects with the ice

        if(fire != null) //if fire has something in it
        {
            TrappedFire trappedFire = new TrappedFire(fire.getRotation());

            getWorld().addObject(trappedFire, fire.getX(), fire.getY()); //replaces the Fire-object with a trappedFire-object
            getWorld().removeObject(fire); //removes the fire-object
            getWorld().removeObject(this); //removes the ice
            
        }
    }
}
