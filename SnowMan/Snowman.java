import greenfoot.*;

/**
 * A snowman
 * 
 * @author Jonas Hatschek
 * @version 2015-10-06
 */
public class Snowman extends Mover
{
    private int mIceTimeMax = 50; //regulates how often ice can be shot (every 50th turn)
    private int mProduceIce = 50; //instatiates a variable for regulating how often ice can me shot

    private int eatFireCount = 0; //instantiates a new variable for counting the amount of fires that has been extinguished

    private GreenfootImage snowmanRight = new GreenfootImage("snowman_right.png");
    private GreenfootImage snowmanLeft = new GreenfootImage("snowman_left.png");
    private GreenfootImage snowmanDown = new GreenfootImage("snowman_down.png");
    private GreenfootImage snowmanUp = new GreenfootImage("snowman_up.png");

    /**
     * Constructs a default Snowman
     */
    public Snowman()
    {
        super(2, new String[]{"starting_picture/snowman_left.png"});

    }

    /**
     * Act - do whatever the Mary wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        checkInput(); //regulates the steering functions
        mProduceIce++; //increases the value of mProduceIce with 1
        collectedFires(); //call for the method collectedFires
    }

    /**
     * Method that counts the amount of fires that has been extinguished
     */
    public void collectedFires(){
        Actor trappedFire = getOneIntersectingObject(TrappedFire.class); //if snowman bumps into an actor from the Fire class

        if(trappedFire != null) //if trappedFire contains something
        {
            getWorld().removeObject(trappedFire); //removes the fire
            eatFireCount = eatFireCount + 1; //increases eatFireCounts value by 1
            Greenfoot.playSound("slurp.wav"); //plays according sound for player feedback

            if(eatFireCount == 4) //if all the fires have been eaten
            {
                Greenfoot.stop(); //game ends
                Greenfoot.playSound("fanfare.wav"); //plays a celebrating fanfare to celebrate the victory with the player
            }
        }

    }

    /**
     * Checks user input and acts accordingly
     */
    private void checkInput()
    {
        if(Greenfoot.isKeyDown("right"))
        {
            travel(1); //method call for the travel-method
            setRotation(359); 
            animate();
            setImage(snowmanRight); //sets the image of the snowman accordingly

        }
        else if(Greenfoot.isKeyDown("left"))
        {
            travel(1);
            setRotation(180);
            animate();
            setImage(snowmanLeft);
        }
        else if(Greenfoot.isKeyDown("up"))
        {
            travel(1);
            setRotation(270);
            animate();
            setImage(snowmanUp);
        }
        else if(Greenfoot.isKeyDown("down"))
        {
            travel(1);
            setRotation(90);
            animate();
            setImage(snowmanDown);
        }

        if(Greenfoot.isKeyDown("space"))
        {
            if(mProduceIce >= mIceTimeMax) //limits the amount of ice that can be shot to one every 50th turn
            {
                getWorld().addObject(new Ice(getRotation()), getX(),getY()); //if sufficient amount of turns has gone by the snowman shoots ice
                mProduceIce = 0; //resets the variable to 0
            }
        }
    }

    /**
     * Method that regulates so that the snowman cannot move under bricks
     *
     * @param  howFar   default set to 1, so the for-loop will only execute once
     */
    private void travel(int howFar)
    {
        {
            for (int i = 0; i < howFar; i++)
            {
                move(2); //moves forward, unless the snowman is intersecting with an object from Brick-class
                if (!getIntersectingObjects(Brick.class).isEmpty()) //if snowman intersects with a brick
                {
                    move(-2); //moves backwards
                }
            }
        }
    }

}
