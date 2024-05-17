package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Pomfret extends Entity {
    public Fish_Pomfret(GamePanel gp){
        super(gp);
        fishRarity = "Rare";
        fishName = "Pomfret";
        fishPrice = 7;
        fishStar = 2;
        fishImage = setup("/Fish/Pomfret_2");
        fishFrame = setup("Fishingframe/Rare");
    }
}
