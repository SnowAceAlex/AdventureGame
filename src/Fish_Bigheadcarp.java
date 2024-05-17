package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Bigheadcarp extends Entity {
    public Fish_Bigheadcarp(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Big Head Carp";
        fishPrice = 1;
        fishStar = 1;
        fishImage = setup("/Fish/Bigheadcarp_1");
        fishFrame = setup("/Fishingframe/Common");
    }


}
