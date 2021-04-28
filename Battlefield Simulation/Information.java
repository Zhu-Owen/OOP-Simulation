import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Information is a world that displays
 * information about each element of the simulation.
 * 
 * @author Daniel Wei
 * @version April 28, 2020
 */
public class Information extends World
{
    private GreenfootImage bkgrnd = new GreenfootImage("infoBkgrnd.png");
    /**
     * Constructor for objects of class Information.
     * 
     */
    public Information()
    {    
        super(960, 640, 1); 
        bkgrnd.scale(960, 640);
        setBackground(bkgrnd);
        
        BackBtn backBtn = new BackBtn(new MainMenu());
        addObject(backBtn, 140, 50);
    }
}
