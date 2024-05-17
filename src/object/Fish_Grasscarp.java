package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Grasscarp extends Entity {
    public Fish_Grasscarp(GamePanel gp){
        super(gp);
        fishRarity = "Uncommon";
        fishName = "Grasscarp";
        price = 2;
        fishStar = 1;
        down1 = setup("/Fish/Grasscarp_1");
//        fishFrame = setup("Fishingframe/Uncommon");
    }
}
