import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
* Buildings are Greenfoot Actors that remain stationary
* on the battlefield. They have different uses, such as training troops,
* attacking other buildings, defending their territory, or building
* more buildings. Each building has its own health. When its health reaches zero,
* the building will explode and be destroyed.
* 
* @author Daniel Wei & Owen Zhu
* @version April 27, 2020
*/
public abstract class Building extends Actor
{
    // Every building has a stat bar
    protected OZDWStatBar statBar;
    
    // Store if the stat bar is on screen
    protected boolean statBarDisplayed = false;
    
    // Every building has some amount of maximum HP
    protected int maxHP;
    
    // Building's current HP
    protected int currHP;
    
    // Some buildings will require a "charge value" (like a cooldown on abilities)
    protected int maxCharge;
    
    // Building's current charge
    protected int currCharge;
    
    // Dimensions
    protected int width;
    protected int height;
    
    // Boolean to store which team this building belongs to
    protected boolean isRed;
    
    // An array of keep track of explosion gif frames
    protected GreenfootImage[] explosionFrames = {new GreenfootImage("explosion1.gif"), new GreenfootImage("explosion2.gif"), new GreenfootImage("explosion3.gif"), 
                                        new GreenfootImage("explosion4.gif"), new GreenfootImage("explosion5.gif"), new GreenfootImage("explosion6.gif"), 
                                        new GreenfootImage("explosion7.gif"), new GreenfootImage("explosion8.gif"), new GreenfootImage("explosion9.gif"), 
                                        new GreenfootImage("explosion10.gif"), new GreenfootImage("explosion11.gif"), new GreenfootImage("explosion12.gif"), 
                                        new GreenfootImage("explosion13.gif"), new GreenfootImage("explosion14.gif"), new GreenfootImage("explosion15.gif"), 
                                        new GreenfootImage("explosion16.gif"), new GreenfootImage("explosion17.gif"), new GreenfootImage("explosion18.gif"), 
                                        new GreenfootImage("explosion19.gif")};
                                        
    // Boolean to track whether or not this building is currently exploding
    protected boolean exploding = false;
    
    // Explosion array index tracker
    protected int explodeIndex = 0;
    
    /**
     * Put the building's stat bar either above or below the building
     * 
     * @param above True if above building, false if below
     */
    protected void summonStatBar(boolean above)
    {
        if (above) 
        {
            getWorld().addObject(statBar, getX(), getY() - height / 2);
        }
        else
        {
            getWorld().addObject(statBar, getX(), getY() + height / 2);
        }
        statBarDisplayed = true;
    }
    
    /**
     * Make the building take some amount of damage.
     * 
     * @param damage Amount of damage dealt
     */
    protected void takeDamage(int damage)
    {
        currHP -= damage;
        checkDestroyed();
        statBar.update(true, currHP);
    }
    
    /**
     * Get the team that this building belongs to.
     * 
     * @return boolean True if red team, false if blue team
     */
    protected boolean getTeam()
    {
        return isRed;
    }
    
    /**
     * Check if this building has been destroyed.
     */
    protected void checkDestroyed()
    {
        if (currHP <= 0)
        {
            exploding = true;
        }
    }
   
    /**
     * Handle animating the explosion and object removal
     */
    protected void doExplosion()
    {
        if (!exploding) {return;}
        if (statBarDisplayed) 
        {
            getWorld().removeObject(statBar);
            statBarDisplayed = false;
        }
        
        if (explodeIndex < 75)
        {
            GreenfootImage explodeFrame = explosionFrames[explodeIndex / 4];
            explodeFrame.scale(width, height);
            setImage(explodeFrame);
            explodeIndex++;
        }
        else
        {
            getWorld().removeObject(this);
        }
    }
    
    /**
     * Get whether or not the building is in a state of exploding.
     * 
     * @return boolean True if it is exploding, false otherwise.
     */
    protected boolean isExploding()
    {
        return exploding;
    }
}
