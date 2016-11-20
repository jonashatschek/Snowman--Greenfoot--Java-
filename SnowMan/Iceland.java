import greenfoot.*;

/**
 * Creates the world of Iceland, inhabited by a snowman and his enemies: the fire that could potentially melts him
 * 
 * @author Jonas Hatschek
 * @version 2015-10-06
 */
public class Iceland extends World
{

    /**
     * Creats a default world, populated with Snowman and fires.
     * 
     */
    public Iceland()
    {    
        // Create a new world with 800x530 cells with a cell size of 1x1 pixels.
        /**
         * An example of a method - replace this comment with your own
         *
         * @param  y   a sample parameter for a method
         * @return     the sum of x and y
         */

        super(800, 530, 1);
        populateWorld();
    }

    /**
     * Populates the world
     */
    private void populateWorld()
    {
        addObject(new Snowman(), 600, 265);

        createFire(0); //calls the fire-creating method

        int counter = 0;
        for( int i = 0; i < 5; i++) //for-loop that helps out creating the bricks
        {
            createBricks(counter); //calls the brick-creating method
            counter++; //increases the value of counter with 1 every loop
        }

        for(int i = 0; i < 50; i++) //adds the brick edges of the map
        {
            addObject(new Brick(), 1000, 1 + i * 50);
            addObject(new Brick(), 1, 1 + i * 50);
            addObject(new Brick(), 1 + i * 50, 1);
            addObject(new Brick(), 20 + i * 50, 670);

        }
    }

    /**
     * Method that creates and decides the amount of and location(s) of the bricks randomly
     *
     * @param  counter  increases with every turn and defines the location of y coordinator
     */
    public void createBricks(int counter)
    {
        int buildOrNot = Greenfoot.getRandomNumber(2); //generates a number that is either 1 or 2.

        if (buildOrNot == 1)
        {
            addObject(new Brick(), 400, 250 + (counter*120)); //if the number == 1 a brick is added at given location based on value of counter-variable

        }
    }

    /**
     * Method that creates and locates the fires in the game
     *
     * @param   x   starts the process of populating the world with fires  
     */
    public void createFire(int x)
    {
        int[] fireArrayX = new int[4]; //creates an array with 4 elements
        int[] fireArrayY = new int[4];//-"-
        int fireCount = -1; //creates a new variable that helps out with placing the right numbers in the right elements of the arrays

        for(int i = 0; i < 4; i++) //for-loop that helps creating the 4 fires
        {

            for(int j = 0; j < 1; j++)
            {
                int tempFireX = populatingCoordinates(200); //calls the populatingCoordinates method for random placing
                int tempFireY = populatingCoordinates(400);//-"-

                fireCount = fireCount + 1; //adds 1 to the current value of fireCount

                fireArrayX[fireCount] = tempFireX; //gives the randomized number to the array with element value of fireCount
                fireArrayY[fireCount] = tempFireY; //-"-

            }

            addObject(new Fire(), fireArrayX[i], fireArrayY[i]);
        }
    }

    /**
     * Method that gives the fires their random location
     *
     * @param   x   int that starts up the process of locating the fires
     * @return  either the fires x or y coordinate
     */
    public static int populatingCoordinates(int x)
    {
        x = Greenfoot.getRandomNumber(x); //generates a number between 1 and recieved x

        if(x < 100) //prevents the fires from ending up under the brick wall-edges
        {
            for(int i = 0; i < 1; i++)
            {
                x = 200; //gives the variable a given number that is not under the bricks to avoid problem
            }

            return x; //returns the coordinate
        }
        else
        {
            return x; //-"-
        }
    }   
}