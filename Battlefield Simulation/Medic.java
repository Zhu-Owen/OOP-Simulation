import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;
/**
 * Medic is a slightly downgraded infantry with the ability to shoot healing bullets at
 * injured allies.
 * 
 * @author Howard Yang
 * @version April 2020
 */
public class Medic extends Troops
{
    /**
     * Act - do whatever the Medic wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    private int healAmount;
    
    /**
     * Create a Medic with specified team.
     * 
     * @param isRed True if red team, false if blue team.
     */
    public Medic(boolean isRed){
        this.isRed = isRed;
        cooldown = 25;
        cooldownTimer = cooldown;
        maxHp = maxHp_Medic;
        this.hp = maxHp;
        this.damage = damage_Medic;
        this.speed = speed_Medic;
        this.sight = sight_Medic;
        this.healAmount = healAmount_Medic;
        if(isRed == true){
            this.setImage("medicR.png");
        }
        else{
            this.setImage("medicB.png");
        }
    }
    
    public void act() 
    {
        // Add your action code here.
        target();
        if(cooldownTimer > 0){
            cooldownTimer--;
        }
    } 
    
    /**
     * Targets allies for healing before acting like a regular troop
     */
    @Override
    protected void target(){//functions the same as regular except it checks for allies first
        boolean allyInRange = false;
        
        for(Troops t: getObjectsInRange(sight, Troops.class)){
            if (t.getTeam() == getTeam() && t.getHp() < t.getMaxHp()){//target damaged allies
                allyInRange = true;
                turnTowards(t.getX(),t.getY());
                break;
            }
        }
        
        if(allyInRange){
            if(cooldownTimer <= 0){
                healAlly();
                cooldownTimer = cooldown;
            }
        }
        else super.target();//if no allies in range, attack enemy troops/buildings normally
    }
    
    /**
     * Creates a bullet that travels in the direction the troop is facing and hurts enemies
     * that it comes in contact with
     */
    public void attackEnemy(){
        getWorld().addObject(new Bullet(getTeam(),10, getRotation(), damage), getX(), getY());
    }
    
    /**
     * Creates a healing bullet that travels in the direction the troop is facing
     */
    public void healAlly(){
       getWorld().addObject(new HealBullet(this, getTeam(),10, getRotation(), healAmount), getX(), getY());
       
    }
}
