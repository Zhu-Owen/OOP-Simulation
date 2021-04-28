import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * MusicSelect is the World that the user is sent to
 * when they choose to start the simulation. From here
 * they can choose one of three soundtracks to play
 * during the simulation, or go back to the main menu.
 * 
 * @author Daniel Wei
 * @version April 27, 2020
 */
public class MusicSelect extends World
{
    // Background image
    private GreenfootImage bkgrnd = new GreenfootImage("music select.png");
    
    // Song choices
    public final static GreenfootSound battleMusic1 = new GreenfootSound("battleMusic1.mp3");
    public final static GreenfootSound battleMusic2 = new GreenfootSound("battleMusic2.mp3");
    public final static GreenfootSound battleMusic3 = new GreenfootSound("battleMusic3.mp3");
    
    /**
     * Constructor for objects of class MusicSelect.
     * 
     */
    public MusicSelect()
    {    
        super(960, 640, 1); 
        bkgrnd.scale(960, 640);
        setBackground(bkgrnd);
        
        // Track 1 button
        Soundtrack1 btn1 = new Soundtrack1();
        addObject(btn1, getWidth() / 2, getHeight() / 2 - 120);
        
        // Track 2 button
        Soundtrack2 btn2 = new Soundtrack2();
        addObject(btn2, getWidth() / 2, getHeight() / 2 + 20);
        
        // Track 3 button
        Soundtrack3 btn3 = new Soundtrack3();
        addObject(btn3, getWidth() / 2, getHeight() / 2 + 150);
        
        // Back button
        BackBtn backBtn = new BackBtn(new MainMenu());
        addObject(backBtn, getWidth() - 140, getHeight() - 65);
    }
}
