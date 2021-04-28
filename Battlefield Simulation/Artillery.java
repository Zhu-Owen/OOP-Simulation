import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
import java.lang.Math;

/**
 * Artilleries are Buildings (Greenfoot Actors) that only target
 * and shoot the nearest enemy building. They will ignore all troops and do not
 * shoot allied buildings. Artilleries shoot by turning towards their
 * target and rapidly firing projectiles at them.
 * 
 * @author Daniel Wei
 * @version April 27, 2020
 */
public class Artillery extends Building
{
    private GreenfootImage artilleryRed = new GreenfootImage("artilleryRed.png");
    private GreenfootImage artilleryBlue = new GreenfootImage("artilleryBlue.png");
    
    private int fireRate;
    private int fireDelay;
    private int damage;
    
    private final int artillery_Width = 72;
    private final int artillery_Height = 72;
    private final int artillery_maxHp = 200;
    private final int artillery_damage = 10;
    
    /**
     * Creates an Artillery with the specified team colour (red or blue).
     * 
     * @param isRed True if this Artillery belongs to the red team, false if it belongs to the blue team.
     */
    public Artillery(boolean isRed)
    {
       this.isRed = isRed;
       this.width = artillery_Width;
       this.height = artillery_Height;
       this.maxHP = artillery_maxHp;
       this.currHP = artillery_maxHp;
       
       this.damage = artillery_damage;
       statBar = new OZDWStatBar(this.width, this.height / 6, 2, this.maxHP, this.maxHP, 0, 1);
       if (isRed)
        {
            this.fireRate = Battleground.RED_ART_FIRE_RATE;
            artilleryRed.scale(this.width, this.height);
            setImage(artilleryRed);
        }
        else
        {
            this.fireRate = Battleground.BLUE_ART_FIRE_RATE;
            artilleryBlue.scale(this.width, this.height);
            setImage(artilleryBlue);
        }
    }
    
    /**
     * Creates an Artillery with the specified team colour (red or blue), dimensions, fire rate, and damage.
     * 
     * @param isRed True if this Artillery belongs to the red team, false if it belongs to the blue team.
     * @param width Width of this Artillery.
     * @param height Height of this Artillery.
     * @param maxHP Maximum HP of this Artillery.
     * @param fireRate Firing rate/delay of this Artillery. Lower numbers equal faster rates of fire (shorter delay between shots).
     * @param damage Damage each projectile shot by the Artillery will deal to a target.
     */
    public Artillery(boolean isRed, int width, int height, int maxHP, int fireRate, int damage)
    {
        statBar = new OZDWStatBar(width, height / 6, 2, maxHP, maxHP, 0, maxCharge);
        this.isRed = isRed;
        this.width = width;
        this.height = height;
        this.maxHP = maxHP;
        this.currHP = maxHP;
        this.currCharge = 0;
        this.maxCharge = 1;
        this.fireRate = fireRate;
        this.damage = damage;
        if (isRed)
        {
            artilleryRed.scale(width, height);
            setImage(artilleryRed);
        }
        else
        {
            artilleryBlue.scale(width, height);
            setImage(artilleryBlue);
        }
    }
    
    /**
     * Act - do whatever the Artillery wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (exploding)
        {
            doExplosion();
            return;
        }
        
        target();
        fireDelay++;
        if (fireDelay > fireRate)
        {
            fireDelay = 0;
        }

        if (!statBarDisplayed)
        {
            statBarDisplayed = true;
            summonStatBar(false);
        }
        else
        {
            statBar.update(true, currHP);
        }
    }    
       
    /**
     * Attack the targetted building by shooting an ArtilleryBolt at it.
     */
    public void attack()
    {
        getWorld().addObject(new ArtilleryBolt(isRed, this, 15, getRotation(), damage), getX(), getY());
    }   
    
    /**
     * Target the nearest enemy building.
     */
    public void target()
    {
        List<Building> buildings = (List<Building>) getWorld().getObjects(Building.class);
        if (buildings.size() == 0) {return;} // If there are no buildings to target
        
        Building closestBuilding = null;
        double closestDistance = Double.MAX_VALUE;
        for (Building b: buildings)
        {
            if (b.getTeam() != this.getTeam() && b != this && !b.isExploding()) 
            {
                double distanceTo = getDistance(b);
                if (distanceTo < closestDistance)
                {
                    closestDistance = distanceTo;
                    closestBuilding = b;
                }
            }
        }
        
        if (closestBuilding != null)
        {
            turnTowards(closestBuilding.getX(), closestBuilding.getY());
            if (fireDelay == fireRate)
            {
                attack();
            }
        }
    }
    
    /**
     * Utility function to get the distance a building is away
     * from the artillery.
     * 
     * @param other The building to get distance from.
     * @return double The distance to the other building.
     */
    private double getDistance(Building other)
    {
        return Math.sqrt(Math.pow(other.getX() - this.getX(), 2) + Math.pow(other.getY() - this.getY(), 2));
    }
}
