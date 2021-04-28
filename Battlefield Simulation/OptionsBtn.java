import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * OptionsBtn is a button than when clicked takes
 * the user to the options selection screen for the
 * simulation.
 * 
 * @author Daniel Wei
 * @version April 28, 2020
 */
public class OptionsBtn extends Button
{
    /**
     * Create an OptionsBtn
     */
    public OptionsBtn()
    {
        selectedImg = new GreenfootImage("optionBtnselected.png");
        unselectedImg = new GreenfootImage("optionBtnUnselected.png");
        setImage(unselectedImg);
    }
    
    /**
     * Behaviour when clicked
     */
    public void onClick()
    {
        World w = new Options();
        Greenfoot.setWorld(w);
    }
}
