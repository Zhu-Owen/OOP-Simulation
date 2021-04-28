import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * InfoBtn is a button that takes the user to the
 * Information display world.
 * 
 * @author Daniel Wei
 * @version April 28, 2020
 */
public class InfoBtn extends Button
{
    /**
     * Create an InfoBtn
     */
    public InfoBtn()
    {
        selectedImg = new GreenfootImage("infoBtnselected.png");
        unselectedImg = new GreenfootImage("infoBtnUnselected.png");
        setImage(unselectedImg);
    }
    
    /**
     * Behaviour when clicked
     */
    public void onClick()
    {
        World w = new Information();
        Greenfoot.setWorld(w);
    }
}
