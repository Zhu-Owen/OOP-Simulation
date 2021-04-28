import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * DefenseTowers are defensive buildings with limited sight ranges
 * that target troops. They deal massive damage.
 * 
 * @author Owen Zhu
 * @version May 14, 2020
 */
public class DefenseTower extends Building
{
    private GreenfootImage img = new GreenfootImage("barracks.png");
    
    // Tower's shooting attributes
    private int shootingDelay;
    private int damage = 50;
    private int sight = 400;
    private int cooldown = 25;
    private int cooldownTimer = 25;
    
    // Spawn variable used with spawn delay
    private int spawn = 0;
    
    /**
     * Create a Troop Spawner building.
     * 
     * @param isRed True if this building belongs to the red team, false if blue team.
     * @param width Width of the building.
     * @param height Height of the building.
     * @param maxHP Maximum HP this building has.
     * @param maxCharge Amount of charge the building must accumulate to spawn a new troop.
     * @param delay Rate at which charge is acquired. Larger numbers mean slower charge acquisition.
     */
    public DefenseTower(boolean isRed, int width, int height, int maxHP, int maxCharge, int delay)
    {
        statBar = new OZDWStatBar(width, height / 6, 5, maxHP, maxHP, 0, maxCharge);
        img.scale(width, height - 10);
        setImage(img);
        
        this.isRed = isRed;
        this.width = width;
        this.height = height;
        this.maxHP = maxHP;
        this.currHP = maxHP;
        this.currCharge = 0;
        this.maxCharge = maxCharge;
        shootingDelay = delay;
        
        if(isRed == true){
            this.setImage("redTower.png");
        }else{
            this.setImage("blueTower.png");
        }
    }
    
    /**
     * Act - do whatever the TroopSpawner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
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
        
        currCharge += spawn / shootingDelay;
        if (spawn == shootingDelay) {spawn = 0;}
        else {spawn++;}
        if (currCharge >= maxCharge) {
            target();
        }
        statBar.update(false, currCharge);
    }
    
    /**
     * Target an enemy troop.
     */
    public void target(){
        boolean enemyInRange = false;
        List<Troops> troopList = getObjectsInRange(sight, Troops.class);
        
        int targetListSize = troopList.size();
        if(targetListSize > 0){
         
            for(Troops t : troopList){
                
                if(t.getTeam() != getTeam() && t instanceof Troops){
                    if(isRed) turnTowards(t.getX()-25, t.getY()+40);
                    else turnTowards(t.getX()+25, t.getY()+40);
                    enemyInRange = true;
                    shoot();
                     turnTowards(this.getX(), this.getY());
                     currCharge = 0;
                     statBar.update(false, currCharge);
                     break;
                }
            }
        } 
    }
    
    /**
     * Shoot the targetted enemy with a Laser.
     */
    public void shoot(){
        if(isRed)
            getWorld().addObject(new Laser(getTeam(),10, getRotation(), damage, "redShots.png"), getX()+25, getY()-40);
        else
            getWorld().addObject(new Laser(getTeam(),10, getRotation(), damage, "blueShots.png"), getX()-25, getY()-40);
    }
}
