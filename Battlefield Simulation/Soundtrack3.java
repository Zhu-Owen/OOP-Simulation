import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Soundtrack3 is a simple Button that allows
 * the user to select and listen to the last
 * of three soundtracks in the battle simulation.
 * 
 * @author Daniel Wei
 * @version April 27, 2020
 */
public class Soundtrack3 extends Button
{
    /**
     * Create a Soundtrack3 button.
     */
    public Soundtrack3()
    {
        selectedImg = new GreenfootImage("sTrack3selected.png");
        unselectedImg = new GreenfootImage("sTrack3Unselected.png");
        setImage(unselectedImg);
    }
    
    /**
     * Behaviour when clicked.
     */
    public void onClick()
    {
        Battleground.selectedTrack = MusicSelect.battleMusic3;
        World w = new Battleground();
        Greenfoot.setWorld(w);
    }
}
