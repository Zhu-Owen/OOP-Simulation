import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Lasers are powerful projectiles fired by
 * DefenseTowers.
 * 
 * @author Owen Zhu 
 * @version April 2020
 */
public class Laser extends Projectiles
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    /**
     * Create a Laser with specified team, speed, direction, and damage.
     * 
     * @param team The team the artillery that shot this bolt belongs to. True if red, false if blue.
     * @param bulletSpeed Speed at which the laser travels.
     * @param bulletDirection Direction at which the laser will travel.
     * @param bulletDamage Damage the laser deals when it hits a target.
     */
    public Laser(boolean team, int bulletSpeed, int bulletDirection, int bulletDamage){
        this.team = team;
        speed = bulletSpeed;
        direction = bulletDirection;
        damage = bulletDamage;
        setRotation(direction);
    }
    
    /**
     * Create a Laser with specified team, speed, direction, damage, and custom image.
     * 
     * @param team The team the artillery that shot this bolt belongs to. True if red, false if blue.
     * @param bulletSpeed Speed at which the laser travels.
     * @param bulletDirection Direction at which the laser will travel.
     * @param bulletDamage Damage the laser deals when it hits a target.
     * @param customImageName Name of the custom image to be applied to the laser.
     */
    public Laser(boolean team, int bulletSpeed, int bulletDirection, int bulletDamage, String customImageName){
        this.team = team;
        speed = bulletSpeed;
        direction = bulletDirection;
        damage = bulletDamage;
        setRotation(direction);
        GreenfootImage customImage = new GreenfootImage(customImageName);
        customImage.scale(30,30);
        setImage(customImage);
    }
    
    public void act() 
    {
        // Add your action code here.
        move(speed);
        checkCollision();
    } 
}
