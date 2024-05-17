package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Grasscarp extends Entity {
    public Fish_Grasscarp(GamePanel gp){
        super(gp);
        fishRarity = "Uncommon";
        fishName = "Grasscarp";
        fishPrice = 2;
        fishStar = 1;
        fishImage = setup("/Fish/Grasscarp_1");
        fishFrame = setup("Fishingframe/Uncommon");
    }
}
