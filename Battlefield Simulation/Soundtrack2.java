import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Soundtrack2 is a simple Button that allows
 * the user to select and listen to the second
 * of three soundtracks in the battle simulation.
 * 
 * @author Daniel Wei
 * @version April 27, 2020
 */
public class Soundtrack2 extends Button
{
    /**
     * Create a Soundtrack2 button.
     */
    public Soundtrack2()
    {
        selectedImg = new GreenfootImage("sTrack2selected.png");
        unselectedImg = new GreenfootImage("sTrack2Unselected.png");
        setImage(unselectedImg);
    }
    
    /**
     * Behaviour when clicked.
     */
    public void onClick()
    {
        Battleground.selectedTrack = MusicSelect.battleMusic2;
        World w = new Battleground();
        Greenfoot.setWorld(w);
    }
}
