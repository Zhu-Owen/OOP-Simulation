import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/**
 * A world where the user can change the simulation
 * by manipulating some variables.
 * 
 * @author Owen Zhu
 * @version May 13, 2020
 */
public class Options extends World
{
    private GreenfootImage bkgrnd = new GreenfootImage("optionsBkgrnd.png");
    /**
     * Constructor for objects of class Options.
     * 
     */
    GreenfootImage image;
    
    // Input textboxes
    private Textbox blueHubHp,redHubHp,blueHubCharge,redHubCharge,blueTroopRate,redTroopRate,blueDefMaxCharge,redDefMaxCharge,blueArtFireRate,redArtFireRate;
    public Options()
    {    
        super(960, 640, 1); 
        bkgrnd.scale(960, 640);
        setBackground(bkgrnd);
        
        showText("Blue Hub Hp:",200,175);
        showText("Red Hub Hp:",650,175);
        showText("Blue Hub Charge:",200,275);
        showText("Red Hub Charge:",650,275);
        showText("Blue Troop Rate:",200,375);
        showText("Red Troop Rate:",650,375);
        showText("Blue Defensive Max Charge:",190,475);
        showText("Red Defensive Max Charge:",640,475);
        showText("Blue Artillary Fire Rate:",200,575);
        showText("Red Artillary Fire Rate:",650,575);
        
        blueHubHp = new Textbox();
        addObject(blueHubHp, 400, 175);
        
        redHubHp = new Textbox();  
        addObject(redHubHp, 850, 175);
        
        blueHubCharge = new Textbox();
        addObject(blueHubCharge, 400, 275);
        
        redHubCharge = new Textbox();
        addObject(redHubCharge, 850, 275);
        
        blueTroopRate = new Textbox();
        addObject(blueTroopRate, 400, 375);
        
        redTroopRate = new Textbox();
        addObject(redTroopRate, 850, 375);
        
        blueDefMaxCharge = new Textbox();
        addObject(blueDefMaxCharge, 400, 475);
        
        redDefMaxCharge = new Textbox();
        addObject(redDefMaxCharge, 850, 475);
        
        blueArtFireRate = new Textbox();
        addObject(blueArtFireRate, 400, 575);
        
        redArtFireRate = new Textbox();
        addObject(redArtFireRate, 850, 575);
        
        BackBtn backBtn = new BackBtn(new MainMenu());
        addObject(backBtn, 140, 50);
    }
    
    public void act()
    {
        // Input reading
        try{int i = Integer.parseInt(blueHubHp.transferValue());
            if(i>0) Battleground.BLUE_HUB_HP=i;
        }catch(NumberFormatException e){} 
        
        try{ int i = Integer.parseInt(redHubHp.transferValue());
            if(i>0) Battleground.RED_HUB_HP=i;
        }catch(NumberFormatException e){}
        
        try{ int i = Integer.parseInt(blueHubCharge.transferValue());
            if(i>0) Battleground.BLUE_HUB_CHARGE_DELAY=i;
        }catch(NumberFormatException e){}
        
        try{ int i = Integer.parseInt(redHubCharge.transferValue());
            if(i>0) Battleground.RED_HUB_CHARGE_DELAY=i;
        }catch(NumberFormatException e){}
        
        try{ int i = Integer.parseInt(blueTroopRate.transferValue());
            if(i>0) Battleground.BLUE_TROOP_SPAWN_RATE=i;
        }catch(NumberFormatException e){}
        
        try{ int i = Integer.parseInt(redTroopRate.transferValue());
            if(i>0) Battleground.RED_TROOP_SPAWN_RATE=i;
        }catch(NumberFormatException e){}
        
        try{ int i = Integer.parseInt(blueDefMaxCharge.transferValue());
            if(i>0) Battleground.BLUE_DEF_MAX_CHARGE=i;
        }catch(NumberFormatException e){}
        
        try{ int i = Integer.parseInt(redDefMaxCharge.transferValue());
            if(i>0) Battleground.RED_DEF_MAX_CHARGE=i;
        }catch(NumberFormatException e){}
        
        try{ int i = Integer.parseInt(blueArtFireRate.transferValue());
            if(i>0) Battleground.BLUE_ART_FIRE_RATE=i;
        }catch(NumberFormatException e){}
        
        try{ int i = Integer.parseInt(redArtFireRate.transferValue());
            if(i>0) Battleground.RED_ART_FIRE_RATE=i;
        }catch(NumberFormatException e){}
    }
}
