import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Soundtrack1 is a simple Button that allows
 * the user to select and listen to the first
 * of three soundtracks in the battle simulation.
 * 
 * @author Daniel Wei
 * @version April 27, 2020
 */
public class Soundtrack1 extends Button
{
    /**
     * Create a Soundtrack1 button.
     */
    public Soundtrack1()
    {
        selectedImg = new GreenfootImage("sTrack1selected.png");
        unselectedImg = new GreenfootImage("sTrack1Unselected.png");
        setImage(unselectedImg);
    }
    
    /**
     * Behaviour when clicked.
     */
    public void onClick()
    {
        Battleground.selectedTrack = MusicSelect.battleMusic1;
        World w = new Battleground();
        Greenfoot.setWorld(w);
    }
}
