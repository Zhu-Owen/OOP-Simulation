import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * StartBtn is a Button that
 * starts the battle simulation when clicked.
 * 
 * @author Daniel Wei
 * @version April 27, 2020
 */
public class StartBtn extends Button
{
    /**
     * Create a StartBtn.
     */
    public StartBtn()
    {
        selectedImg = new GreenfootImage("startBtnselected.png");
        unselectedImg = new GreenfootImage("startBtnUnselected.png");
        setImage(unselectedImg);
    }
    
    /**
     * Behaviour when clicked.
     */
    public void onClick()
    {
        World w = new MusicSelect();
        Greenfoot.setWorld(w);
    }
}
