import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Projectiles shot by the troops in the simulation.
 * These projectiles damage whatever they hit.
 * 
 * @author Howard Yang
 * @version April 2020
 */
public class Bullet extends Projectiles
{
    /**
     * Act - do whatever the Bullet wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    /**
     * Create a Bullet with specified team association, bullet speed, direction, and damage.
     * 
     * @param team The team the artillery that shot this bolt belongs to. True if red, false if blue.
     * @param bulletSpeed Speed at which the bullet will travel.
     * @param bulletDirection Direction at which the bullet will travel.
     * @param bulletDamage Damage this bullet deals when it hits a troop or building.
     */
    public Bullet(boolean team, int bulletSpeed, int bulletDirection, int bulletDamage){
        this.team = team;
        speed = bulletSpeed;
        direction = bulletDirection;
        damage = bulletDamage;
        setRotation(direction);
    }
    
    /**
     * Create a Bullet with specified team association, bullet speed, direction, damage, and custom image.
     * 
     * @param team The team the artillery that shot this bolt belongs to. True if red, false if blue.
     * @param bulletSpeed Speed at which the bullet will travel.
     * @param bulletDirection Direction at which the bullet will travel.
     * @param bulletDamage Damage this bullet deals when it hits a troop or building.
     * @param customImageName Name of a custom image to be applied to the bullet.
     */
    public Bullet(boolean team, int bulletSpeed, int bulletDirection, int bulletDamage, String customImageName){
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
