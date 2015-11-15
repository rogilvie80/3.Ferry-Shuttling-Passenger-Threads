/*
 * Ferry.java
 *
 */

package tma03q2;

import java.util.ArrayList;
import java.util.List;

import utilities.Direction;

import static utilities.Delays.*;
import static utilities.Direction.*;

/**
 *
 * @author M257
 */
public class Ferry
{
    private Direction direction;
    
    private List<Passenger> passengerList;

    private int capacity;

    private boolean waiting;
    
    /** Creates a new instance of Ferry, initially eastbound */
    public Ferry()
    {
        direction = EASTBOUND;
        passengerList = new ArrayList<Passenger>();
        capacity = 2;
        waiting = false;
    }
    
    public synchronized void board(Passenger passenger)
    {
        while((passenger.getDirection() != getDirection()) || !(passengerList.size() < capacity))
        {
            try
            {
                waiting = true;
                wait();                
            }
            catch (InterruptedException e)
            {
                String errMessage = e.getMessage();
                System.out.println("Error " + errMessage);
            }
        }
        System.out.println("Passenger " + passenger.getPassengerID()
        + " " + passenger.getDirection() + " boards");
        passengerList.add(passenger);
    }
    
    public synchronized void cross()
    {
        while((!(passengerList.size() > 0)) || (waiting == false))
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                String errMessage = e.getMessage();
                System.out.println("Error " + errMessage);
            }
        }
        System.out.println("Ferry crossing " + getDirection() + "...");
        delay(2000);
        System.out.println("Ferry arrives at opposite bank");
        for (Passenger eachTraveller : passengerList)
        {
            System.out.println("Passenger " + eachTraveller.getPassengerID()
                               + " alights");
        }
        passengerList.clear();
        swapDirection();
        notifyAll();
    }
    
    public Direction getDirection()
    {
        return direction;
    }
    
    public void setDirection(Direction aDirection)
    {
        direction = aDirection;
    }
    
    private void swapDirection()
    {
        if (getDirection() == EASTBOUND)
        {
            setDirection(WESTBOUND);
        }
        else
        {
            setDirection(EASTBOUND);
        }
    }
    
}
