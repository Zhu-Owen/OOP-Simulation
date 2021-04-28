import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The main world for the simulation. In this simulation, two teams,
 * red and blue, fight each other until one comes out victorious. Each
 * team starts with one Hub, two TroopSpawners, and two DefenseTowers and
 * begin spawning troops to march down to attack the enemy buildings/troops.
 * A team wins when they manage to destroy the enemy Hub.
 * 
 * 
 * @author Daniel Wei, Owen Zhu, Stanley Wang, Howard Yang, Roy Sun
 * @version May 14, 2020
 */
public class Battleground extends World
{
    // Some constants for building locations
    
    // Red Barracks/troop spawners
    public static final int RED_BARRACK_X = 960 / 9;
    public static final int RED_BARRACK_1_Y = 110;
    public static final int RED_BARRACK_2_Y = 530;
    
    // Red Defense Towers
    public static final int RED_DEF_X = 240;
    public static final int RED_DEF_1_Y = 190;
    public static final int RED_DEF_2_Y = 450;
    
    // Red Artilleries
    public static final int RED_ART_X = 400;
    public static final int RED_ART_OFFSET = 120;
    
    // Blue Barracks/troop spawners
    public static final int BLUE_BARRACK_X = 960 / 9 * 8;
    public static final int BLUE_BARRACK_1_Y = 110;
    public static final int BLUE_BARRACK_2_Y = 530;
    
    // Blue Defense Towers
    public static final int BLUE_DEF_X = 720;
    public static final int BLUE_DEF_1_Y = 190;
    public static final int BLUE_DEF_2_Y = 450;
    
    // Blue Artilleries
    public static final int BLUE_ART_X = 560;
    public static final int BLUE_ART_OFFSET = 120;
    
    // Battle music
    public static GreenfootSound selectedTrack; // This needs to be public so that other classes (the buttons) can set it
    
    // Variables for simulation changes by the user
    public static int RED_HUB_HP = 5000;
    public static int RED_HUB_CHARGE_DELAY = 5;
    
    public static int BLUE_HUB_HP = 5000;
    public static int BLUE_HUB_CHARGE_DELAY = 5;
    
    public static int RED_TROOP_SPAWN_RATE = 2;
    public static int BLUE_TROOP_SPAWN_RATE = 2;
    
    public static int RED_DEF_MAX_CHARGE = 100;
    public static int BLUE_DEF_MAX_CHARGE = 100;
    
    public static int RED_ART_FIRE_RATE = 15;
    public static int BLUE_ART_FIRE_RATE = 15;
    
    // Buildings present on the battlefield at the start of the simulation
    private Hub redHub, blueHub;
    private TroopSpawner redSpawn1 ,redSpawn2, blueSpawn1, blueSpawn2;
    private DefenseTower redtower1, redtower2, bluetower1, bluetower2;
    
    public Battleground()
    {    
        super(960, 640, 1);
        MainMenu.menuMusic.stop();
        GreenfootImage background = new GreenfootImage("SimBG.png");
        setBackground(background);
        
        redHub = new Hub(true, 125, 125, RED_HUB_HP, 200, RED_HUB_CHARGE_DELAY);
        blueHub = new Hub(false, 125, 125, BLUE_HUB_HP, 200, BLUE_HUB_CHARGE_DELAY);
        
        addObject(redHub, getWidth() / 14, getHeight() / 2);
        addObject(blueHub, getWidth() / 14 * 13, getHeight() / 2);
        
        redSpawn1 = new TroopSpawner(true);
        redSpawn2 = new TroopSpawner(true);
        
        addObject(redSpawn1, RED_BARRACK_X, RED_BARRACK_1_Y);
        addObject(redSpawn2, RED_BARRACK_X, RED_BARRACK_2_Y);
        
        blueSpawn1 = new TroopSpawner(false);
        blueSpawn2 = new TroopSpawner(false);
        
        addObject(blueSpawn1, BLUE_BARRACK_X, BLUE_BARRACK_1_Y);
        addObject(blueSpawn2, BLUE_BARRACK_X, BLUE_BARRACK_2_Y);
        
        redtower1 = new DefenseTower(true, 130, 130, 999, RED_DEF_MAX_CHARGE, 1);
        redtower2 = new DefenseTower(true, 130, 130, 999, RED_DEF_MAX_CHARGE, 1);
        bluetower1 = new DefenseTower(false, 130, 130, 999, BLUE_DEF_MAX_CHARGE, 1);
        bluetower2 = new DefenseTower(false, 130, 130, 999, BLUE_DEF_MAX_CHARGE, 1);
        
        addObject(redtower1, RED_DEF_X, RED_DEF_1_Y);
        addObject(redtower2, RED_DEF_X, RED_DEF_2_Y);
        
        addObject(bluetower1, BLUE_DEF_X, BLUE_DEF_1_Y);
        addObject(bluetower2, BLUE_DEF_X, BLUE_DEF_2_Y);
        
        selectedTrack.setVolume(69);
        selectedTrack.playLoop();
    }
    
    public void act()
    {
        if (redHub.isDestroyed())
        {
            selectedTrack.stop();
            Greenfoot.setWorld(new BlueVictory());
        }
        else if (blueHub.isDestroyed())
        {
            selectedTrack.stop();
            Greenfoot.setWorld(new RedVictory());
        }
    }
}
