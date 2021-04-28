import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Parent class for all the objects shot by troops/buildings in the simulation
 * 
 * @author Howard Yang
 * @version April 2020
 */
public abstract class Projectiles extends Actor
{
    /**
     * Act - do whatever the Projectiles wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    protected int direction;
    protected int speed;
    
    // Damage this projectile deals
    protected int damage;
    
    // The actor that shot this projectile
    protected Actor shooter;
    //which team the projectile belongs to
    protected boolean team;
    
    /**
     * Check if the projectile collides with any buildings/troops
     * or moves off the world.
     */
    protected void checkCollision()
    {
        Building b = (Building) getOneIntersectingObject(Building.class);
        Troops t = (Troops) getOneIntersectingObject(Troops.class);
        
        if (b != null && b.getTeam() != team) //b != shooter) this messes up my building shooting - owen
        {
            b.takeDamage(damage);
            getWorld().removeObject(this);
            return;
        }

        if(t != null && t.getTeam() != team){
            t.takeDamage(damage);
            getWorld().removeObject(this);
            return;
        }
        
        if(isAtEdge()){
            getWorld().removeObject(this);
        }
    }
}
