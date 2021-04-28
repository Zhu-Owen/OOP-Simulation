import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Button is the parent class for numerous Greenfoot Actors that does
 * what its name implies, it acts as a button that can be clicked to do something.
 * Buttons will indicate when they are being hovered over by changing their images
 * accordingly.
 * 
 * @author Daniel Wei 
 * @version April 27, 2020
 */
public abstract class Button extends Actor
{
    protected GreenfootImage selectedImg;
    protected GreenfootImage unselectedImg;
    
    // Hover over sound
    protected GreenfootSound hoverSound = new GreenfootSound("hoverSound.mp3");
    
    // Track if the sound has been played yet
    protected boolean playedHoverSound = false;
    
    // Clicked sound
    protected GreenfootSound clickSound = new GreenfootSound("btnClicked.wav");
    /**
     * Act - do whatever the Button wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        hoverCheck();
        
        if (Greenfoot.mouseClicked(this))
        {
            clickSound.play();
            onClick();
        }
    }    
    
    /**
     * Checks if the cursor is hovering over the button
     * and changes image accordingly
     */
    protected void hoverCheck()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        
        if (mouse != null)
        {
            int x = mouse.getX();
            int y = mouse.getY();
            List<Button> btn = (List<Button>) getWorld().getObjectsAt(x, y, Button.class);
            
            if (btn.size() > 0 && btn.get(0) == this)
            {
                setImage(selectedImg);
                if (!playedHoverSound) 
                {
                    hoverSound.play();
                    playedHoverSound = true;
                }
            }
            else
            {
                setImage(unselectedImg);
                playedHoverSound = false;
            }
        }
    }
    
    /**
     * What the button should do when clicked
     */ 
    public abstract void onClick();
}
