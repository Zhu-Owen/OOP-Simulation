import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Tank is a troop that has high health, low speed, and medium damage
 * 
 * @author Roy Sun 
 * @version April 2020
 */
public class Tank extends Troops
{
    /**
     * Create a Tank of the specified team.
     * 
     * @param isRed True if red team, false if blue team.
     */
    public Tank(boolean isRed){
        cooldownTimer = 70;
        cooldown = cooldownTimer;
        this.isRed = isRed;
        maxHp = maxHp_Tank;
        this.hp = maxHp_Tank;
        this.damage = damage_Tank;
        this.speed = speed_Tank;
        this.sight = sight_Tank;
        if(isRed == true){
            this.setImage("TANKR.png");
        }
        else{
            this.setImage("TANKB.png");
        }
    }
    
    /**
     * Create a Tank of the specified team, with specified hp, speed, damage, and sight range.
     * 
     * @param isRed True if red team, false if blue team.
     * @param hp HP the tank starts with.
     * @param speed Speed the tank marches downt the battlefield.
     * @param damage Damage the tank's bullets deal.
     * @param sight Sight range of the tank.
     */
    public Tank(boolean isRed, int hp, int maxHp, int speed, int damage, int sight){
        cooldownTimer = 70;
        cooldown = cooldownTimer;
        this.isRed = isRed;
        this.hp = hp;
        this.maxHp = maxHp;
        this.speed = speed;
        this.damage = damage;
        this.sight = sight;
        if(isRed == true){
            this.setImage("TANKR.png");
        }
        else{
            this.setImage("TANKB.png");
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
