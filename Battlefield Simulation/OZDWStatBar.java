import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.lang.Math;
/**
 * OZDWStatBar is a Greenfoot actor that displays a pair of stat bars with their individual features.
 * The first stat bar is a rectangular bar that is intended to be used as an HP/Shield bar, but
 * it can be used for other applicable uses. The other is a circular bar that displays a 
 * percentage value in the middle as well as some ticks to provide a visual representation
 * of the percentage. It is intended to be an ability charge bar, but can also be used for
 * other purposes. The two bars have their own separate values.
 * <p>
 * The purpose of this class is to provide a well integrated statistical bar that can help
 * provide visuals for their abilities and any type of numerical bar that is required. This
 * can provide a programmer easy access to a stats bar that is typically used in a RPG game.
 * <p>
 * This class contains accessor (getter) and setter (update) methods.
 * 
 * 
 * @author Daniel Wei & Owen Zhu
 * @version March 11, 2020
 */
public class OZDWStatBar extends Actor
{
    // Constants (used for ratios)
    private static final int baseChargeValue = 100;
    private static final int baseHealthValue = 100;
    private static final int offset = 20;
    
    // Array to keep track of the animation for a fully charged ability bar
    GreenfootImage[] fullChargeFrames = {new GreenfootImage("fCharge (0).gif"), new GreenfootImage("fCharge (1).gif"), new GreenfootImage("fCharge (2).gif"), new GreenfootImage("fCharge (3).gif"), new GreenfootImage("fCharge (4).gif"),
                                new GreenfootImage("fCharge (5).gif"), new GreenfootImage("fCharge (6).gif"), new GreenfootImage("fCharge (7).gif"), new GreenfootImage("fCharge (8).gif"), 
                                new GreenfootImage("fCharge (9).gif"), new GreenfootImage("fCharge (10).gif"), new GreenfootImage("fCharge (11).gif"), new GreenfootImage("fCharge (12).gif"), 
                                new GreenfootImage("fCharge (13).gif"), new GreenfootImage("fCharge (14).gif"), new GreenfootImage("fCharge (15).gif"), new GreenfootImage("fCharge (16).gif"),
                                new GreenfootImage("fCharge (17).gif"), new GreenfootImage("fCharge (18).gif"), new GreenfootImage("fCharge (19).gif"), new GreenfootImage("fCharge (20).gif"), 
                                new GreenfootImage("fCharge (21).gif"), new GreenfootImage("fCharge (22).gif"), new GreenfootImage("fCharge (23).gif"), new GreenfootImage("fCharge (24).gif"),
                                new GreenfootImage("fCharge (25).gif"), new GreenfootImage("fCharge (26).gif"), new GreenfootImage("fCharge (27).gif"), new GreenfootImage("fCharge (28).gif"),
                                new GreenfootImage("fCharge (29).gif"), new GreenfootImage("fCharge (30).gif"), new GreenfootImage("fCharge (31).gif"), new GreenfootImage("fCharge (32).gif"),
                                new GreenfootImage("fCharge (33).gif"), new GreenfootImage("fCharge (34).gif"), new GreenfootImage("fCharge (35).gif"), new GreenfootImage("fCharge (36).gif"),
                                new GreenfootImage("fCharge (37).gif"), new GreenfootImage("fCharge (38).gif"), new GreenfootImage("fCharge (39).gif"), new GreenfootImage("fCharge (40).gif"),
                                new GreenfootImage("fCharge (41).gif"), new GreenfootImage("fCharge (42).gif"), new GreenfootImage("fCharge (43).gif"), new GreenfootImage("fCharge (44).gif"),
                                new GreenfootImage("fCharge (45).gif"), new GreenfootImage("fCharge (46).gif"), new GreenfootImage("fCharge (47).gif"), new GreenfootImage("fCharge (48).gif"),
                                new GreenfootImage("fCharge (49).gif"), new GreenfootImage("fCharge (50).gif"), new GreenfootImage("fCharge (51).gif"), new GreenfootImage("fCharge (52).gif"), 
                                new GreenfootImage("fCharge (53).gif"), new GreenfootImage("fCharge (54).gif"), new GreenfootImage("fCharge (55).gif"), new GreenfootImage("fCharge (56).gif"),
                                new GreenfootImage("fCharge (57).gif"), new GreenfootImage("fCharge (58).gif"), new GreenfootImage("fCharge (59).gif"), new GreenfootImage("fCharge (60).gif"),
                                new GreenfootImage("fCharge (61).gif"), new GreenfootImage("fCharge (62).gif"), new GreenfootImage("fCharge (63).gif"), new GreenfootImage("fCharge (64).gif"),
                                new GreenfootImage("fCharge (65).gif"), new GreenfootImage("fCharge (66).gif"), new GreenfootImage("fCharge (67).gif"), new GreenfootImage("fCharge (68).gif"),
                                new GreenfootImage("fCharge (69).gif"), new GreenfootImage("fCharge (70).gif"), new GreenfootImage("fCharge (71).gif"), new GreenfootImage("fCharge (72).gif"),
                                new GreenfootImage("fCharge (73).gif"), new GreenfootImage("fCharge (74).gif"), new GreenfootImage("fCharge (75).gif"), new GreenfootImage("fCharge (76).gif"),
                                new GreenfootImage("fCharge (77).gif"), new GreenfootImage("fCharge (78).gif"), new GreenfootImage("fCharge (79).gif"), new GreenfootImage("fCharge (80).gif"),
                                new GreenfootImage("fCharge (81).gif"), new GreenfootImage("fCharge (82).gif"), new GreenfootImage("fCharge (83).gif"), new GreenfootImage("fCharge (84).gif"),
                                new GreenfootImage("fCharge (85).gif"), new GreenfootImage("fCharge (86).gif"), new GreenfootImage("fCharge (87).gif"), new GreenfootImage("fCharge (88).gif"),
                                new GreenfootImage("fCharge (89).gif"), new GreenfootImage("fCharge (90).gif")};
                                
    // Array to keep track of the chargeBar states
    private GreenfootImage[] chargeBarFrames = {new GreenfootImage("ultBar0.png"), new GreenfootImage("ultBar1.png"), new GreenfootImage("ultBar2.png"), new GreenfootImage("ultBar3.png"), 
                                             new GreenfootImage("ultBar4.png"), new GreenfootImage("ultBar5.png"), new GreenfootImage("ultBar6.png"), new GreenfootImage("ultBar7.png"),
                                             new GreenfootImage("ultBar8.png"), new GreenfootImage("ultBar9.png"), new GreenfootImage("ultBar10.png"), new GreenfootImage("ultBar11.png"), 
                                             new GreenfootImage("ultBar12.png"), new GreenfootImage("ultBar13.png"), new GreenfootImage("ultBar14.png"), new GreenfootImage("ultBar15.png"),  
                                             new GreenfootImage("ultBar16.png"), new GreenfootImage("ultBar17.png"), new GreenfootImage("ultBar18.png"), new GreenfootImage("ultBar19.png"), 
                                             new GreenfootImage("ultBar20.png"), new GreenfootImage("ultBar21.png"), new GreenfootImage("ultBar22.png"), new GreenfootImage("ultBar23.png"),
                                             new GreenfootImage("ultBar24.png"), new GreenfootImage("ultBar25.png"), new GreenfootImage("ultBar26.png"), new GreenfootImage("ultBar27.png"), 
                                             new GreenfootImage("ultBar28.png"), new GreenfootImage("ultBar29.png"), new GreenfootImage("ultBar30.png"), new GreenfootImage("ultBar31.png"),
                                             new GreenfootImage("ultBar32.png"), new GreenfootImage("ultBar33.png"), new GreenfootImage("ultBar34.png"), new GreenfootImage("ultBar35.png"), 
                                             new GreenfootImage("ultBar36.png")};

    // Track if the ult fully charged sound has been played
    private boolean playedUltSound;
    
    // Track if the low HP warning noise is currently playing
    private boolean playingLowHPSound;
    
    // Track if sound is ALLOWED to be played (enabled by default)
    private boolean soundEnabled = true;
    
    private boolean updtOverflow = false;
    
    // Track values
    private int hpVal;
    private int maxHpVal;
    private int chargeVal;
    private int maxChargeVal;
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
    private int diameter;
    private int borderWidth;
    
    // Index for chargeBar array images
    private int index = 0;
    
    // Index for ability animation
    private int fullChargeIndex = 0;
    
    // Animation delay
    private int animDelay = 0;

    // Sounds
    private GreenfootSound ultCharged = new GreenfootSound("UltimateCharged.mp3");
    private GreenfootSound lowHPSound = new GreenfootSound("LowHealthNoise.mp3");
    
    // Canvas
    private GreenfootImage img;
    private GreenfootImage textImg;
    
    /**
     * Creates a generic OZDWStatBar with default dimensions and values.
     */
    public OZDWStatBar()
    {
        diameter = 120;
        width = 400;
        height = 60;
        hpVal = 100;
        borderWidth = 10;
        maxHpVal = 100;
        chargeVal = 0;
        maxChargeVal = 100;
        
        img = new GreenfootImage(width + diameter + offset * (int) ((double) (width / 200) + 0.5), diameter);
        playedUltSound = false;

        filledColor = Color.GREEN;
        emptyColor = Color.BLACK;
        borderColor = Color.BLACK;
        updateDownColor = Color.RED;
        updateUpColor = new Color(200, 255, 200);
        txtColor = Color.BLACK;
        
        img.setColor(Color.BLACK);
        img.fillRect(0, diameter / 4, width, height);
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, diameter / 4 + borderWidth, width - 2 * borderWidth, height - 2 * borderWidth);
        
        chargeBarFrames[0].scale(diameter, diameter);
        img.drawImage(chargeBarFrames[0], width + offset * (int) ((double) (width / 200) + 0.5), 0);
        
        setImage(img);
    }
    
    /**
     * Creates an OZDWStatBar of the dimensions specified and with the values specified. All values must not be negative.
     * 
     * @param width Width of the rectangular bar portion of the stat bar.
     * @param height Height of the rectangular bar portion of the stat bar (diameter of the circular bar will be twice this height).
     * @param borderWidth Width of the border surrounding the rectangular bar portion of the stat bar.
     * @param hpVal Initial/starting value for the rectangular bar.
     * @param maxHpVal Maximum possible value for the rectangular bar.
     * @param chargeVal Initial/starting value for the circular bar.
     * @param maxChargeVal Maximum possible value for the circular bar.
     */
    public OZDWStatBar(int width, int height, int borderWidth, int hpVal, int maxHpVal, int chargeVal, int maxChargeVal)
    {
        this.maxHpVal = maxHpVal;
        this.hpVal = hpVal;
        this.chargeVal = chargeVal;
        this.maxChargeVal = maxChargeVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        this.diameter = height * 2;
        img = new GreenfootImage(width + diameter + offset * (int) ((double) (width / 200) + 0.5), diameter);

        playedUltSound = false;
        
        double increment = (double) maxChargeVal / 37;
        
        index = (int) (chargeVal / increment);
        if (index == 37) {index = 36;}
        chargeBarFrames[index].scale(diameter, diameter);
        img.drawImage(chargeBarFrames[index], width + offset * (int) ((double) (width / 200) + 0.5), 0);

        playingLowHPSound = false;
        filledColor = Color.GREEN;
        emptyColor = Color.BLACK;
        borderColor = Color.BLACK;
        updateDownColor = Color.RED;
        updateUpColor = new Color(200, 255, 200);
        txtColor = Color.BLACK;
        
        img.setColor(borderColor);
        img.fillRect(0, diameter / 4, width, height);
        
        int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal;
        int emptyWidth = (width - 2 * borderWidth) - filledWidth;
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
        
        img.setColor(emptyColor);
        img.fillRect(borderWidth + filledWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);

        setImage(img);
    }
    
    /**
     * Creates an OZDWStatBar with the dimensions and values specified. All values must not be negative.
     * Colors are also specified but only apply to the rectangular bar portion.
     * 
     * @param width Width of the rectangular bar portion of the stat bar.
     * @param height Height of the rectangular bar portion of the stat bar (diameter of the circular bar will be twice this height).
     * @param borderWidth Width of the border surrounding the rectangular bar portion of the stat bar.
     * @param hpVal Initial/starting value for the rectangular bar.
     * @param maxHpVal Maximum possible value for the rectangular bar.
     * @param chargeVal Initial/starting value for the circular bar.
     * @param maxChargeVal Maximum possible value for the circular bar.
     * @param filledColor Colour for the filled up portion of the rectangular bar.
     * @param emptyColor Colour for the empty portion of the rectangular bar.
     * @param borderColor Colour for the border surrounding the rectangular bar.
     * @param updateDownColor Colour for the value update animation for decreases in the rectangular bar's value.
     * @param updateUpColor Colour for the value update animation for increases in the rectangular bar's value
     * @param txtColor Colour for the rectangular bar's text display.
     */
    public OZDWStatBar(int width, int height, int borderWidth, int hpVal, int maxHpVal, int chargeVal, int maxChargeVal, Color filledColor, Color emptyColor, Color borderColor, Color updateDownColor, Color updateUpColor, Color txtColor)
    {
        this.maxHpVal = maxHpVal;
        this.hpVal = hpVal;
        this.chargeVal = chargeVal;
        this.maxChargeVal = maxChargeVal;
        this.width = width;
        this.borderWidth = borderWidth;
        this.height = height;
        this.diameter = height * 2;
        img = new GreenfootImage(width + diameter + offset * (int) ((double) (width / 200) + 0.5), diameter);

        playedUltSound = false;
        
        double increment = (double) maxChargeVal / 37;
        
        index = (int) (chargeVal / increment);
        if (index > 36) {index = 36;}
        chargeBarFrames[index].scale(diameter, diameter);
        img.drawImage(chargeBarFrames[index], width + offset * (int) ((double) (width / 200) + 0.5), 0);
        
        playingLowHPSound = false;
        this.filledColor = filledColor;
        this.emptyColor = emptyColor;
        this.borderColor = borderColor;
        this.updateDownColor = updateDownColor;
        this.updateUpColor = updateUpColor;
        this.txtColor = txtColor;
        
        img.setColor(borderColor);
        img.fillRect(0, diameter / 4, width, height);
        
        int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal;
        int emptyWidth = (width - 2 * borderWidth) - filledWidth;
        
        img.setColor(filledColor);
        img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
        
        img.setColor(emptyColor);
        img.fillRect(borderWidth + filledWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);

        setImage(img);

    }
    
    /**
     * Act - do whatever the OZDWStatBar wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // keep
        //soundCheck();
        
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
    }
    
    /**
     * Updates the OZDWStatBar's current value with a new value. Also updates the appearance of the OZDWStatBar accordingly.
     * 
     * @param updatingHp True if the HP (rectangular) bar's values are being updated, false if the ability (circular) bar's values are being updated.
     * @param newVal New value to update the OZDWStatBar with.
     */
    public void update(boolean updatingHp, int newVal)
    {
        if (updatingHp) // if updating the hp value
        {
            if (newVal > maxHpVal) {newVal = maxHpVal;} // protect values (cannot exceed max)
            if (newVal < 0) {return;} // protect values (cannot have negative values)
            hpVal = newVal; // update value
        }
        else // if updating the charge value
        {
            if (newVal > maxChargeVal) {newVal = maxChargeVal;} // protect values (cannot exceed max)
            if (newVal < 0) {return;} // protect values (cannot have negative values)
            chargeVal = newVal; // update value
        }
        
        // Additional negative value protection
        if (hpVal < 0) {hpVal = 0;}
        if (chargeVal < 0) {chargeVal = 0;}
        
        // Text display for rectangular bar
        textImg = new GreenfootImage(hpVal + " / " + maxHpVal, 20, txtColor, Color.WHITE);
        textImg.scale(width / 4, height / 2);
        
        // Drawing onto canvas
        img.clear();
        img.drawImage(textImg, 0, 0);
        img.setColor(borderColor);
        img.fillRect(0, diameter / 4, width, height);
        
        double increment = (double) maxChargeVal / 36; // 36 ticks in the bar to fill, each tick is worth "increment" amount of charge
        index = (int) (chargeVal / increment); // Get the corresponding index for the bar's image
        if (index == 37) {index = 0;} // Prevent index out of bounds exception
        chargeBarFrames[index].scale(diameter, diameter); 
        img.drawImage(chargeBarFrames[index], width + offset * (int) ((double) (width / 200) + 0.5), 0); // draw the image
        
        // Animation driver code
        if (chargeVal == maxChargeVal)
        {
            fullChargeIndex += animDelay / 5;
            if (animDelay == 5) {animDelay = 0;}
            else {animDelay++;}
            if (fullChargeIndex > 90) {fullChargeIndex = 0;}
        }
        else
        {
            fullChargeIndex = 0;
        }
        fullChargeFrames[fullChargeIndex].scale(diameter, diameter);
        img.drawImage(fullChargeFrames[fullChargeIndex], width + offset * (int) ((double) (width / 200) + 0.5), 0);

        // If statements for updating down or updating up values
        if(hpVal < updtVal)
        {
             int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
             int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
             int emptyWidth = (width - 2 * borderWidth) - updatingWidth;
             img.setColor(updateDownColor);
             img.fillRect(borderWidth, borderWidth + diameter / 4, updatingWidth, height - 2 * borderWidth);
            
             img.setColor(filledColor);
             img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
            
             img.setColor(emptyColor);
             img.fillRect(borderWidth + updatingWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);
        }
        else
        {
            int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
            int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
            int emptyWidth = (width - 2 * borderWidth) - filledWidth; // width of the "empty" section of the bar
            img.setColor(updateUpColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, updatingWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + filledWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);
        }
        setImage(img);
    }
    
    /**
     * Updates the OZDWStatBar's current value and current maximum value with new values. Also updates the appearance of the OZDWStatBar accordingly.
     * 
     * @param updatingHp True if the HP (rectangular) bar's values are being updated, false if the ability (circular) bar's values are being updated.
     * @param newVal New value to update the OZDWStatBar with.
     * @param newMax New maximum value to update the OZDWStatBar with.
     */
    public void update(boolean updatingHp, int newVal, int newMax)
    {
        if (updatingHp) 
        {
           if (newVal < 0 || newMax < 0) {return;} // Both parameters must not be negative
            maxHpVal = newMax; // Set max to the specified max to be updated to
            if (newVal > maxHpVal) {newVal = maxHpVal;} // Cannot exceed the max value
            hpVal = newVal; // Update current value 
        }
        else
        {
            if (newVal < 0 || newMax < 0) {return;}
            maxChargeVal = newMax;
            if (newVal > maxChargeVal) {newVal = maxChargeVal;}
            chargeVal = newVal;
        }
        
        // More negative value protection
        if (hpVal < 0) {hpVal = 0;}
        if (chargeVal < 0) {chargeVal = 0;}
        
        // Drawing onto the canvas
        textImg = new GreenfootImage(hpVal + " / " + maxHpVal, 20, txtColor, Color.WHITE);
        textImg.scale(width / 4, height / 2);
        img.clear();
        img.drawImage(textImg, 0, 0);
        img.setColor(borderColor);
        img.fillRect(0, diameter / 4, width, height);
        
        // Draw correct frame for ability bar
        double increment = (double) maxChargeVal / 36; // 36 ticks in the bar to fill, each tick is worth "increment" amount of charge
        index = (int) (chargeVal / increment); // Get the corresponding index for the bar's image
        if (index == 37) {index = 0;} // Prevent index out of bounds exception
        chargeBarFrames[index].scale(diameter, diameter); 
        img.drawImage(chargeBarFrames[index], width + offset * (int) ((double) (width / 200) + 0.5), 0); // draw the image
        
        // Animation driver code
        if (chargeVal == maxChargeVal)
        {
            fullChargeIndex += animDelay / 5;
            if (animDelay == 5) {animDelay = 0;}
            else {animDelay++;}
            if (fullChargeIndex > 90) {fullChargeIndex = 0;}
        }
        else
        {
            fullChargeIndex = 0;
        }
        fullChargeFrames[fullChargeIndex].scale(diameter, diameter);
        img.drawImage(fullChargeFrames[fullChargeIndex], width + offset * (int) ((double) (width / 200) + 0.5), 0);
        
        // If statements for updating down or updating up values
        if(newVal < updtVal)
        {
            int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
            int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
            int emptyWidth = (width - 2 * borderWidth) - updatingWidth;
            img.setColor(updateDownColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, updatingWidth, height - 2 * borderWidth);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + updatingWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);
        }
        else
        {
            int filledWidth = (width - 2 * borderWidth) * hpVal / maxHpVal; // width of the "filled in" section of the bar
            int updatingWidth = (width - 2 * borderWidth) * updtVal / maxHpVal; // width of the section to be updated
            int emptyWidth = (width - 2 * borderWidth) - filledWidth; // width of the "empty" section of the bar
            img.setColor(updateUpColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, filledWidth, height - 2 * borderWidth);
            
            img.setColor(filledColor);
            img.fillRect(borderWidth, borderWidth + diameter / 4, updatingWidth, height - 2 * borderWidth);
            
            img.setColor(emptyColor);
            img.fillRect(borderWidth + filledWidth, borderWidth + diameter / 4, emptyWidth, height - 2 * borderWidth);
        }
        setImage(img);
    }
    
    /**
     * Checks to see if any sounds currently are playing
     * or need to be playing/played.
     */
    private void soundCheck()
    {
        // Attempt to play the fully charged sound if the current charge value is at the maximum (full charge)
        if (chargeVal == maxChargeVal)
        {
            if (playedUltSound) {return;} // Don't play the sound again if it has played already
            ultCharged.play();
            playedUltSound = true;
        }
        else if (chargeVal < maxChargeVal) // Reset boolean once the current value drops below the maximum
        {
            playedUltSound = false;
        }

        if (hpVal * 10 <= maxHpVal * 3) // low HP warning noise plays when the current HP value is <=30% of the maximum value
        {
            if (!playingLowHPSound) // Begin the loop if the sound was not already playing
            {
                lowHPSound.playLoop();
                playingLowHPSound = true;
            }
        }
        else // stop the sound once the current value is >30% of the maximum value
        {
            lowHPSound.stop();
            playingLowHPSound = false;
        }
    }
    
    /**
     * Gets the maximum charge value for this stat bar.
     * 
     * @return int Maximum charge value (maxChargeVal)
     */
    public int getMaxChargeValue()
    {
        return maxChargeVal;
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