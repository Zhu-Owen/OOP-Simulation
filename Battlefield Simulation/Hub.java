import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Hubs are Buildings (Greenfoot Actors) that serve as the "command centre"
 * for a team. If the Hub is destroyed (if its health reaches 0), the team
 * who owns that Hub will lose the battle. Hubs construct buildings if it
 * detects there is an open spot for a building to be built and it has enough
 * charge to do so.
 * 
 * @author Daniel Wei 
 * @version April 27, 2020
 */
public class Hub extends Building
{
    private GreenfootImage blueHubImg = new GreenfootImage("blueHubImage.png");
    private GreenfootImage redHubImg = new GreenfootImage("redHubImage.png");
    
    // Building spawn delay
    private int spawnDelay;
    
    // Spawn variable used with spawn delay
    private int spawn = 0;
    
    // Used for RNG
    private Random random = new Random();
    
    // Track the active buildings on the field
    private boolean[] activeBuildings = new boolean[8];
    
    /**
     * Create a Hub building of the specified team (red or blue), width, height, maximum HP, maximum charge, and charge accumulation delay.
     * 
     * @param isRed True if this Hub belongs to the red team, false if it belongs to the blue team.
     * @param width Width of this Hub.
     * @param height Height of this Hub.
     * @param maxHP Maximum HP of this Hub.
     * @param maxCharge Total charge this Hub must accumulate to use its ability.
     * @param delay Rate at which this Hub accumulates charge. Lower delay results in faster charge accumulation.
     */
    public Hub(boolean isRed, int width, int height, int maxHP, int maxCharge, int delay)
    {
        statBar = new OZDWStatBar(width - 50, height / 6, 5, maxHP, maxHP, 0, maxCharge);
        this.width = width;
        this.height = height;
        this.maxHP = maxHP;
        this.currHP = maxHP;
        this.currCharge = 0;
        this.maxCharge = maxCharge;
        this.isRed = isRed;
        spawnDelay = delay;
        if (isRed)
        {
            redHubImg.scale(width, height);
            setImage(redHubImg);
        }
        else
        {
            blueHubImg.scale(width, height);
            setImage(blueHubImg);
        }
    }
    
    public void act() 
    {
        if (exploding)
        {
            doExplosion();
            return;
        }
        
        if (!statBarDisplayed)
        {
            statBarDisplayed = true;
            summonStatBar(false);
        }
        
        updateBuildings();
        if (currCharge < maxCharge)
        {
            currCharge += spawn / spawnDelay;
        }
        if (spawn == spawnDelay) {spawn = 0;}
        else {spawn++;}
        if (currCharge >= maxCharge && !allBuildingsActive()) {
            spawnBuilding(); 
            currCharge = 0;
        }
        statBar.update(false, currCharge);
    }    
    
    /**
     * Check if the Hub is destroyed
     * 
     * @return boolean True if Hub HP is less than or equal to 0.
     */
    public boolean isDestroyed()
    {
        return currHP <= 0;
    }
    
    /**
     * Spawn a building in an empty preset spot.
     */
    private void spawnBuilding()
    {
        if (isRed)
        {
            for (int i = 0; i < 8; i++)
            {
                if (activeBuildings[i]) {continue;}
                World w = getWorld();
                if (i == 0){w.addObject(new TroopSpawner(true), Battleground.RED_BARRACK_X, Battleground.RED_BARRACK_1_Y); return;}
                else if (i == 1) {w.addObject(new TroopSpawner(true), Battleground.RED_BARRACK_X, Battleground.RED_BARRACK_2_Y); return;}
                else if (i == 2) {w.addObject(new DefenseTower(true, 130, 130, 999, Battleground.RED_DEF_MAX_CHARGE, 1), Battleground.RED_DEF_X, Battleground.RED_DEF_1_Y); return;}
                else if (i == 3) {w.addObject(new DefenseTower(true, 130, 130, 999, Battleground.RED_DEF_MAX_CHARGE, 1), Battleground.RED_DEF_X, Battleground.RED_DEF_2_Y); return;}
                else {w.addObject(new Artillery(true), Battleground.RED_ART_X, Battleground.RED_ART_OFFSET * (i - 3)); return;} 
            }
        }
        else
        {
            for (int i = 0; i < 8; i++)
            {
                if (activeBuildings[i]) {continue;}
                World w = getWorld();
                if (i == 0){w.addObject(new TroopSpawner(false), Battleground.BLUE_BARRACK_X, Battleground.BLUE_BARRACK_1_Y); return;}
                else if (i == 1) {w.addObject(new TroopSpawner(false), Battleground.BLUE_BARRACK_X, Battleground.BLUE_BARRACK_2_Y); return;}
                else if (i == 2) {w.addObject(new DefenseTower(false, 130, 130, 999, Battleground.BLUE_DEF_MAX_CHARGE, 1), Battleground.BLUE_DEF_X, Battleground.BLUE_DEF_1_Y); return;}
                else if (i == 3) {w.addObject(new DefenseTower(false, 130, 130, 999, Battleground.BLUE_DEF_MAX_CHARGE, 1), Battleground.BLUE_DEF_X, Battleground.BLUE_DEF_2_Y); return;}
                else {w.addObject(new Artillery(false), Battleground.BLUE_ART_X, Battleground.BLUE_ART_OFFSET * (i - 3)); return;} 
                
            }
        }
    }
    
    /**
     * Sees which buildings are still standing
     */
    private void updateBuildings()
    {
        if (isRed) // Building detection for the red hub
        {
            for (int i = 0; i < 8; i++)
            {
                if (i == 0) // Upper spawner
                {
                    List<TroopSpawner> b = (List<TroopSpawner>) getWorld().getObjectsAt(Battleground.RED_BARRACK_X, Battleground.RED_BARRACK_1_Y, TroopSpawner.class);
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
                else if (i == 1) // Lower spawner
                {
                    List<TroopSpawner> b = (List<TroopSpawner>) getWorld().getObjectsAt(Battleground.RED_BARRACK_X, Battleground.RED_BARRACK_2_Y, TroopSpawner.class);
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
                else if (i == 2) // Upper defense tower
                {
                    List<DefenseTower> b = (List<DefenseTower>) getWorld().getObjectsAt(Battleground.RED_DEF_X, Battleground.RED_DEF_1_Y, DefenseTower.class);
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
                else if (i == 3) // Lower defense tower
                {
                    List<DefenseTower> b = (List<DefenseTower>) getWorld().getObjectsAt(Battleground.RED_DEF_X, Battleground.RED_DEF_2_Y, DefenseTower.class);
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
                else 
                {
                    List<Artillery> b = (List<Artillery>) getWorld().getObjectsAt(Battleground.RED_ART_X, Battleground.RED_ART_OFFSET * (i - 3), Artillery.class); 
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
            }
        }
        else // Building detection for the blue hub
        {
            for (int i = 0; i < 8; i++)
            {
                if (i == 0) // Upper spawner
                {
                    List<TroopSpawner> b = (List<TroopSpawner>) getWorld().getObjectsAt(Battleground.BLUE_BARRACK_X, Battleground.BLUE_BARRACK_1_Y, TroopSpawner.class);
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
                else if (i == 1) // Lower spawner
                {
                    List<TroopSpawner> b = (List<TroopSpawner>) getWorld().getObjectsAt(Battleground.BLUE_BARRACK_X, Battleground.BLUE_BARRACK_2_Y, TroopSpawner.class);
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
                else if (i == 2) // Upper defense tower
                {
                    List<DefenseTower> b = (List<DefenseTower>) getWorld().getObjectsAt(Battleground.BLUE_DEF_X, Battleground.BLUE_DEF_1_Y, DefenseTower.class);
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
                else if (i == 3) // Lower defense tower
                {
                    List<DefenseTower> b = (List<DefenseTower>) getWorld().getObjectsAt(Battleground.BLUE_DEF_X, Battleground.BLUE_DEF_2_Y, DefenseTower.class);
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
                else 
                {
                    List<Artillery> b = (List<Artillery>) getWorld().getObjectsAt(Battleground.BLUE_ART_X, Battleground.BLUE_ART_OFFSET * (i - 3), Artillery.class); 
                    if (b.size() == 0) {activeBuildings[i] = false;}
                    else {activeBuildings[i] = true;}
                }
            }
        }
    }
    
    /**
     * Determine whether or not all buildings are currently still standing on the Battlefield.
     */
    private boolean allBuildingsActive()
    {
        for (int i = 0; i < 8; i++)
        {
            if (!activeBuildings[i]) {return false;}
        }
        return true;
    }
    
    /**
     * Utility method to get world width
     */
    private int getWidth()
    {
        return getWorld().getWidth();
    }
    
    /**
     * Utility method to get world height
     */
    private int getHeight()
    {
        return getWorld().getHeight();
    }
}
