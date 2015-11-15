/*
 * Passenger.java
 *
 */

package tma03q2;
import utilities.Direction;

/**
 *
 * @author M257
 */

// You do not need to change this class.
public class Passenger extends Thread
{
    private int idNum;
    private Direction direction;
    private Ferry ferry;
    
    /**
     * Creates a new instance of Passenger 
     */
    public Passenger(int anIdNum, Direction aDirection, Ferry aFerry)
    {
        idNum = anIdNum;
        direction = aDirection;
        ferry = aFerry;
    }
    
    public int getPassengerID()
    {
        return idNum;
    }
    
    public Direction getDirection()
    {
        return direction;
    }
    
    public void run()
    {       
        System.out.println("Traveller " + getPassengerID() +
                 " " + getDirection() + " arrives");
        ferry.board(this);
    }
    
}
