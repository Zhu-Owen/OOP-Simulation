import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Infantries are standard troops that march down the
 * battlefield and shoot the nearest enemy troop/building
 * 
 * @author Howard Yang, Roy Sun
 * @version April 2020
 */
public class Infantry extends Troops
{
    /**
     * Act - do whatever the Infantry wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    /**
     * Create an Infantry of the specified team.
     * 
     * @param isRed True if red team, false if blue team.
     */
    public Infantry(boolean isRed){
        cooldown = 25;
        cooldownTimer = cooldown;
        this.isRed = isRed;
        maxHp = maxHp_Infantry;
        this.hp = maxHp;
        this.damage = damage_Infantry;
        this.speed = speed_Infantry;
        this.sight = sight_Infantry;
        if(isRed == true){
            setImage("infantryR.png");
            //direction = 0;
        }
        else{
            setImage("infantryB.png");
            //direction = 180;
        }
    }
    
    /**
     * Create an Infantry of the specified team, with specified hp, speed, damage, and sight range.
     * 
     * @param isRed True if red team, false if blue team.
     * @param hp HP the infantry starts with.
     * @param speed Speed the infantry marches downt the battlefield.
     * @param damage Damage the infantry's bullets deal.
     * @param sight Sight range of the infantry.
     */
    public Infantry(boolean isRed, int hp, int speed, int damage, int sight){
        cooldown = 25;
        cooldownTimer = cooldown;
        this.isRed = isRed;
        maxHp = hp;
        this.hp = hp;
        this.damage = damage;
        this.speed = speed;
        this.sight = sight;
        
        if(isRed == true){
            setImage("infantryR.png");
            //direction = 0;
        }
        else{
            setImage("infantryB.png");
            //direction = 180;
        }
    }
    
    /**
     * Create an Infantry of the specified team, with specified current hp, max hp, speed, damage, and sight range.
     * 
     * @param isRed True if red team, false if blue team.
     * @param hp HP the infantry starts with.
     * @param maxHp MaxHP of the infantry.
     * @param speed Speed the infantry marches downt the battlefield.
     * @param damage Damage the infantry's bullets deal.
     * @param sight Sight range of the infantry.
     */
    public Infantry(boolean isRed, int hp, int maxHp, int speed, int damage, int sight){
        cooldown = 25;
        cooldownTimer = cooldown;
        this.isRed = isRed;
        this.maxHp = maxHp;
        this.hp = hp;
        this.speed = speed;
        this.damage = damage;
        this.sight = sight;
        
        if(isRed == true){
            //this.setImage("red soldier");
            //direction = up;
        }
        else{
            //this.setImage("blue soldier");
            //direction = down;
        }
    }
    
    public void act() 
    {
        target();
        if(cooldownTimer > 0){
            cooldownTimer--;
        }
    }
    
    /**
     * Creates a bullet that travels in the direction the troop is facing
     */
    public void attackEnemy(){
        getWorld().addObject(new Bullet(getTeam(),10, getRotation(), damage), getX(), getY());
    }

}
