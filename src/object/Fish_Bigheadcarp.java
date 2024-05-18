package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Bigheadcarp extends Entity {
    public Fish_Bigheadcarp(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Big Head Carp";
        price = 1;
        fishStar = 1;
        down1 = setup("/Fish/Bigheadcarp_1");
//        fishFrame = setup("/Fishingframe/Common");
    }


}
