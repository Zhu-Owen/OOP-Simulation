import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * BackBtn is a Button (Greenfoot Actor) that
 * returns to the previous world when clicked.
 * 
 * @author Daniel Wei
 * @version April 27, 2020
 */
public class BackBtn extends Button
{
    private World previousWorld; // the world this button should return to when clicked
    
    /**
     * Create a BackBtn that returns the user to a specified world.
     * 
     * @param previousWorld The world to return the user to when this button is clicked.
     */
    public BackBtn(World previousWorld)
    {
        this.previousWorld = previousWorld;
        selectedImg = new GreenfootImage("backBtnselected.png");
        unselectedImg = new GreenfootImage("backBtnUnselected.png");
        setImage(unselectedImg);
    }
    
    /**
     * Behaviour when clicked.
     */
    public void onClick()
    {
        Greenfoot.setWorld(previousWorld);
    }
}
