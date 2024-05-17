package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Hammerhead extends Entity {
    public Fish_Hammerhead(GamePanel gp){
        super(gp);
        fishRarity = "Rare";
        fishName = "Big Head Carp";
        fishPrice = 13;
        fishStar = 3;
        fishImage = setup("/Fish/Hammerhead_3");
        fishFrame = setup("Fishingframe/Rare");
    }
}
