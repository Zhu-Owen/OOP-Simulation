import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
/**
 * TroopHealthBar is a Greenfoot actor created based off of OZDWStatBar. It is a variant of OZDWStatBar
 * that does not have the circular ability bar. It is a rectangular bar that is intended to be used as an HP/Shield bar, but
 * it can be used for other applicable uses. 
 * <p>
 * The purpose of this class is to provide a well integrated statistical bar that can help
 * provide visuals for health and any type of numerical bar that is required. This
 * can provide a programmer easy access to a stats bar that is typically used in a RPG game.
 * <p>
 * This class contains accessor (getter) and setter (update) methods.
 * 
 * @author Daniel Wei & Owen Zhu
 * @version April 23, 2020
 */
public class TroopHealthBar extends Actor
{
    private static final int baseHealthValue = 100;

    private boolean updtOverflow = false;
    
    // Track values
    private int hpVal;
    private int maxHpVal;
    private int updtVal;
    
    // Bar colors
    private Color filledColor;
    private Color emptyColor;
    private Color borderColor;
    private Color updateUpColor;
    private Color updateDownColor;
    private Color txtColor;
    
    // Bar dimensions
    private int width;
    private int height;
    private int borderWidth;
    
    private int xOffset;
    private int yOffset;
    // Canvas
    private GreenfootImage img;
    private GreenfootImage textImg;
    
    private Troops target;
    /**
     * Creates a generic TroopHealthBar with default dimensions and values.
     */
    public TroopHealthBar()
    {
        width = 400;
        height = 60;
        hpVal = 100;
        borderWidth = 10;
        maxHpVal = 100;
        
        img = new GreenfootImage(width, height);

        filledColor = Color.GREEN;
        emptyColor = Color.BLACK;
        borderColor = Color.BLACK;
        updateDownColor = Color.RED;
        updateUpColor = new Color(200, 255, 200);
        txtColor = Color.BLACK;
        
        img.setColor(Color.BLACK);
        img.fillRect(0, 0, width, height);
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);

        
        setImage(img);
    }
    
    /**
     * Creates an TroopHealthBar of the dimensions specified and with the values specified. All values must not be negative.
     * 
     * @param width Width of the bar.
     * @param height Height of the bar.
     * @param borderWidth Width of the border surrounding the bar.
     * @param hpVal Initial/starting value for the bar.
     * @param maxHpVal Maximum possible value for the bar.
     */
    public TroopHealthBar(int width, int height, int borderWidth, int hpVal, int maxHpVal, Troops target, int x, int y)
    {
        this.maxHpVal = maxHpVal;
        this.hpVal = hpVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        img = new GreenfootImage(width, height);

        filledColor = Color.GREEN;
        emptyColor = Color.BLACK;
        borderColor = Color.BLACK;
        updateDownColor = Color.RED;
        updateUpColor = new Color(200, 255, 200);
        txtColor = Color.BLACK;
        
        img.setColor(borderColor);
        img.fillRect(0, 0, width, height);
        
        int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal;
        int emptyWidth = (width - 2 * borderWidth) - filledWidth;
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
        
        img.setColor(emptyColor);
        img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - 2 * borderWidth);

        setImage(img);
        
        this.target = target;
        this.xOffset = x;
        this.yOffset = y;
    }

    /**
     * Act - do whatever the TroopHealthBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Animate changes in the health bar's values
        if (hpVal < updtVal)
        {
            updtOverflow = true;
            updtVal -= (int) ((double) maxHpVal / baseHealthValue + 0.5);
        }
        else if (hpVal > updtVal)
        {
            updtOverflow = false;
            updtVal += (int) ((double) maxHpVal / baseHealthValue + 0.5);
        }
        
        // Edge case handling (if the difference between the update value and current value is very small)
        if ((updtOverflow && hpVal > updtVal) || (!updtOverflow && hpVal < updtVal))
        {
            updtVal = hpVal;
        }
        
        this.setLocation(target.getX() + xOffset, target.getY() + yOffset);//follow troop it's attached to
    }
    /**
     * Updates the TroopHealthBar's current value with a new value. Also updates the appearance of the TroopHealthBar accordingly.
     * 
     * @param newVal New value to update the TroopHealthBar with.
     */
    public void update(int newVal)
    {
        if (newVal > maxHpVal) {newVal = maxHpVal;} // protect values (cannot exceed max)
        if (newVal < 0) {return;} // protect values (cannot have negative values)
        hpVal = newVal; // update value

        // Additional negative value protection
        if (hpVal < 0) {hpVal = 0;}

        // Text display for rectangular bar
        textImg = new GreenfootImage(hpVal + " / " + maxHpVal, 20, txtColor, Color.WHITE);
        textImg.scale(width / 4, height / 2);
        
        // Drawing onto canvas
        img.clear();
        img.drawImage(textImg, 0, 0);
        img.setColor(borderColor);
        img.fillRect(0, 0, width, height);
        
        // If statements for updating down or updating up values
        if(hpVal < updtVal)
        {
             int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
             int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
             int emptyWidth = (width - 2 * borderWidth) - updatingWidth;
             img.setColor(updateDownColor);
             img.fillRect(borderWidth, borderWidth, updatingWidth, height - 2 * borderWidth);
            
             img.setColor(filledColor);
             img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
            
             img.setColor(emptyColor);
             img.fillRect(borderWidth + updatingWidth, borderWidth, emptyWidth, height - 2 * borderWidth);
        }
        else
        {
            int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
            int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
            int emptyWidth = (width - 2 * borderWidth) - filledWidth; // width of the "empty" section of the bar
            img.setColor(updateUpColor);
            img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth, updatingWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - 2 * borderWidth);
        }
        setImage(img);
    }
    
    /**
     * Updates the TroopHealthBar's current value and current maximum value with new values. Also updates the appearance of the TroopHealthBar accordingly.
     * 
     * @param newVal New value to update the TroopHealthBar with.
     * @param newMax New maximum value to update the TroopHealthBar with.
     */
    public void update(int newVal, int newMax)
    {
       if (newVal < 0 || newMax < 0) {return;} // Both parameters must not be negative
       maxHpVal = newMax; // Set max to the specified max to be updated to
       if (newVal > maxHpVal) {newVal = maxHpVal;} // Cannot exceed the max value
       hpVal = newVal; // Update current value 

       // Drawing onto the canvas
       textImg = new GreenfootImage(hpVal + " / " + maxHpVal, 20, txtColor, Color.WHITE);
       textImg.scale(width / 4, height / 2);
       img.clear();
       img.drawImage(textImg, 0, 0);
       img.setColor(borderColor);
       img.fillRect(0, 0, width, height);

       // If statements for updating down or updating up values
       if(newVal < updtVal)
       {
           int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
           int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
           int emptyWidth = (width - 2 * borderWidth) - updatingWidth;
           img.setColor(updateDownColor);
           img.fillRect(borderWidth, borderWidth, updatingWidth, height - 2 * borderWidth);
            
           img.setColor(filledColor);
           img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
            
           img.setColor(emptyColor);
           img.fillRect(borderWidth + updatingWidth, borderWidth, emptyWidth, height - 2 * borderWidth);
       }
       else
       {
           int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
           int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
           int emptyWidth = (width - 2 * borderWidth) - filledWidth; // width of the "empty" section of the bar
           img.setColor(updateUpColor);
           img.fillRect(borderWidth, borderWidth, filledWidth, height - 2 * borderWidth);
            
           img.setColor(filledColor);
           img.fillRect(borderWidth, borderWidth, updatingWidth, height - 2 * borderWidth);
            
           img.setColor(emptyColor);
           img.fillRect(borderWidth + filledWidth, borderWidth, emptyWidth, height - 2 * borderWidth);
       }
       setImage(img);
    }

    /**
     * Gets the maximum hp value for this stat bar.
     * 
     * @return int Maximum HP value (maxHpVal).
     */
    public int getMaxHpValue()
    {
        return maxHpVal;
    }
}