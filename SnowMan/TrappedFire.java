import greenfoot.*;

/**
 * A trapped fire
 * 
 * @author Jonas Hatschek
 * @version 2015-10-22
 */
public class TrappedFire extends Actor
{
    private int mIceMelt = 0;
    private int mIceTimeMax = 100;

    /**
     * Creates a TrappedFire with the given rotation
     */
    public TrappedFire(int rotation)
    {
        setRotation(rotation);

    }

    public void act() 
    {
        backToFire();
        mIceMelt++;
    }

    /**
     * Method that turns the trapped fires back to "just" fires
     */
    private void backToFire()
    {
        Fire fire = new Fire();

        if(mIceMelt >= mIceTimeMax)
        {
            getWorld().addObject(new Fire(), this.getX(), getY());
            getWorld().removeObject(this);
            mIceMelt = 0;
        }

    }

}
