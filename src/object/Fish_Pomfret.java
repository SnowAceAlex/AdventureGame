package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Pomfret extends Entity {
    public Fish_Pomfret(GamePanel gp){
        super(gp);
        fishRarity = "Rare";
        fishName = "Pomfret";
        price = 7;
        fishStar = 2;
        down1 = setup("/Fish/Pomfret_2");
//        fishFrame = setup("Fishingframe/Rare");
    }
}
