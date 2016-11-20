import greenfoot.*;

/**
 * Superclass for moving actors in the game
 * 
 * @author Thomas Jakobsson 
 * @version 2015-09-22
 */
public class Mover extends Actor
{
    private GreenfootImage[] mImages;
    private int mCurrentImage;
    private int mFrameCount;
    private int mSpeed;

    /**
     * Constructs a Mover with given speed and imagenames
     */
    public Mover(int speed, String[] imageNames)
    {
        mSpeed = speed;
        mImages = new GreenfootImage[imageNames.length];
        for(int i = 0 ; i < imageNames.length; i++)
        {
            mImages[i] = new GreenfootImage(imageNames[i]);
        }
        mCurrentImage = 0;
        setImage(mImages[0]);
    }

    /**
     * Act - do whatever the Mover wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move();
        animate();
    }  

    /**
     * Tries to move the Mover in the current direction.
     * Returns true if succesful.
     */
    public boolean move()
    {
        boolean hasCollided = false;
        move(mSpeed);
        if(isTouching(Brick.class))
        {
            move(-mSpeed);
            hasCollided = true;
        }
        return hasCollided;
    }

    /**
     * Animates the Mover
     */
    protected void animate()
    {
        mFrameCount++;
        if(mFrameCount % 5 == 1)
        {
            mCurrentImage = (mCurrentImage + 1) % mImages.length;
            setImage(mImages[mCurrentImage]);
        }
    }
}
