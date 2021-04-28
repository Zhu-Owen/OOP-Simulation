import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Screen for when the Blue Hub is destroyed, and the
 * red team has won.
 * 
 * @author Daniel Wei
 * @version May 12, 2020
 */
public class RedVictory extends World
{
    // Background
    private GreenfootImage bkgrnd = new GreenfootImage("RedVictory.png");
    
    // Victory sound
    public final GreenfootSound victorySound = new GreenfootSound("VictorySound.mp3");
    public RedVictory()
    {    
        super(960, 640, 1); 
        bkgrnd.scale(960, 640);
        setBackground(bkgrnd);
        victorySound.play();
    }
    
    public void act()
    {
        if (!victorySound.isPlaying())
        {
            MainMenu.menuMusic.playLoop();
            Greenfoot.setWorld(new MainMenu());
        }
    }
}
