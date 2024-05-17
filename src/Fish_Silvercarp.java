package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Silvercarp extends Entity {
    public Fish_Silvercarp(GamePanel gp){
        super(gp);
        fishRarity = "Rare";
        fishName = "Silvercarp";
        fishPrice = 2;
        fishStar = 1;
        fishImage = setup("/Fish/Silvercarp_1");
        fishFrame = setup("Fishingframe/Rare");
    }
}
