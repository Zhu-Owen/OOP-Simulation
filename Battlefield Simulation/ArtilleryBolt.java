import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * ArtilleryBolt is a Projectile fired
 * by Artilleries. 
 * 
 * @author Daniel Wei
 * @version April 27, 2020
 */
public class ArtilleryBolt extends Projectiles
{
    /**
     * Create an ArtilleryBolt.
     * 
     * @param team The team the artillery that shot this bolt belongs to. True if red, false if blue.
     * @param shooter The artillery that shot this bolt.
     * @param boltSpeed Speed at which the bolt travels to its target.
     * @param boltDamage Damage the bolt deals when it hits a target.
     */
    public ArtilleryBolt(boolean team, Building shooter, int boltSpeed, int boltDirection, int boltDamage)
    {
        this.shooter = shooter;
        this.team = team;
        speed = boltSpeed;
        direction = boltDirection;
        damage = boltDamage;
        setRotation(direction);
    }
    
    /**
     * Act - do whatever the ArtilleryBolt wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        move(speed);
        checkCollision();
    }    
}
