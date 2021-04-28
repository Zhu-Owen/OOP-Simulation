
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.*;

/**
 * Troops are the the basic units in the simulation,
 * some have special abilities or specialized stats
 * 
 * all use a basic targeting AI to shoot at the enemy team
 * 
 * @author Howard Yang, Roy Sun, Stanley Wang
 * @version April 2020
 */
public abstract class Troops extends Actor
{
    protected final int maxHp_Infantry = 100;
    protected final int speed_Infantry = 2;
    protected final int damage_Infantry = 20;
    protected final int sight_Infantry = 200;
    
    // Ninja stats
    protected final int maxHp_Ninja = 50;
    protected final int speed_Ninja = 8;
    protected final int damage_Ninja = 40;
    protected final int sight_Ninja = 100;
    
    // Medic stats
    protected final int maxHp_Medic = 80;
    protected final int speed_Medic = 2;
    protected final int damage_Medic = 10;
    protected final int sight_Medic = 200;
    protected final int healAmount_Medic = 10;
    
    // Tank stats
    protected final int maxHp_Tank = 300;
    protected final int damage_Tank = 15; // change this later
    protected final int speed_Tank = 1;
    protected final int sight_Tank = 135;
    
    protected final int healthBarY = -30;//healthbar offset
    
    protected int speed;
    protected int maxHp;
    protected int hp;
    protected int damage;
    protected int sight;
    
    protected boolean isRed; // If troop is on team red, then statement would be true
    protected int direction;
    
    protected int cooldown;
    protected int cooldownTimer;
    
    protected TroopHealthBar healthBar;
    
    protected GreenfootSound dieSound = new GreenfootSound("troopDeathSound.mp3");
    
    /**
     * Overrides the addedToWorld method in actor
     */
    @Override
    protected void addedToWorld(World w){
        createHealthbar();
    }
    
    /**
     * Creates a healthbar that follows the troop
     */
    private void createHealthbar(){
        healthBar = new TroopHealthBar(50, 10, 1, hp, maxHp, this, 0, healthBarY);
        getWorld().addObject(healthBar, getX(), getY());
    }
    
    /**
     * Removes the troop and it's healthbar
     */
    protected void die(){
        dieSound.play();
        getWorld().removeObject(healthBar);
        getWorld().removeObject(this);
    }
    
    /**
     * Setter for the range of the troop's line of vision
     * 
     * @param newSight the new attacking range for the troop
     */
    protected void changeSight(int newSight){
        sight = newSight;
    }
    
    /**
     * Decrease the troop's health with a set amount of damage
     * 
     * @param damage the amount of health to decrease by
     */
    protected void takeDamage(int damage){
        this.hp -= damage;
        healthBar.update(hp);
        if(hp <= 0){
            die();
        }
    }
    
    /**
     * Main AI for the troop, targets enemy troops in range before enemy buildings
     */
    protected void target(){
        boolean enemyInRange = false;
        List<Troops> troopList = getObjectsInRange(sight, Troops.class);
        Building closestBuilding;
        
        for(Troops t : troopList){
            
            if(t.getTeam() != getTeam()){
                turnTowards(t.getX(), t.getY());
                enemyInRange = true;
                break;
            }
        }
        
        
        if(enemyInRange){
            if(cooldownTimer <= 0){// limit the amount of shots the troop can fire per act
                attackEnemy();
                cooldownTimer = cooldown;
            }
        }
        else{// if no troops in range target closest building
    
            closestBuilding = getClosestBuilding(getWorld().getObjects(Building.class));
            
            if(closestBuilding != null){
                turnTowards(closestBuilding.getX(), closestBuilding.getY());
                if(distance(closestBuilding, this) <= sight){//only attack the building when in range
                    if(cooldownTimer <= 0){
                        attackEnemy();
                        cooldownTimer = cooldown;
                    }
                }
                else march();
            }
        }
      }
      
    /**
     * Moves the troop in the direction the actor is facing
     */
    protected void march(){
         move(speed); 
    }
    
    /**
     * Helper method to find the closest building to the current troop instance
     * 
     * @param buildings a list of buildings to search in
     */
    protected Building getClosestBuilding(List<Building> buildings){
        Building closestBuilding = null;
        int marker = 0;
        for(int i = 0; i < buildings.size(); i++){
            marker++;
            if(buildings.get(i).getTeam() != getTeam()){//once found enemy building in list break loop
                closestBuilding = buildings.get(i);
                break;
            }
        }
        for(int i = marker; i < buildings.size(); i++){//loop starts where the previous loop ended
            if(buildings.get(i).getTeam() != getTeam()){
                if(distance(buildings.get(i), this) < distance(closestBuilding, this)){
                    closestBuilding = buildings.get(i);
                }
            }
        }
        return closestBuilding;
    }
    
    /**
     * Helper to get distance between 2 actors
     */
    protected static int distance(Actor a, Actor b){
        int xx = a.getX() -b.getX();
        int yy = a.getY() - b.getY();
        
        
        return (int)Math.sqrt(xx*xx +yy*yy);
    }
    
    /**
     * Returns the boolean reference for the troops team
     * 
     * @return true if red/ false if blue
     */
    protected boolean getTeam(){
        return isRed;
    }
    
    /**
     * Increases the hp of the current hp of the troop, checks if the heal will go over the max hp
     * 
     * @param health the amount of hp to increase by
     */
    public void healMe(int health){
        hp += health;
        if(hp >= maxHp){
            hp = maxHp;
        }
        healthBar.update(hp);
    }
    /**
     * Getter for current hp
     * 
     * @return int Current HP of the Troop.
     */
    public int getHp(){
        return hp;
    }
    
    /**
     * Getter for max hp
     * 
     * @return int Maximum HP of the Troop.
     */
    public int getMaxHp(){
        return maxHp;
    }
    public abstract void attackEnemy();
}
