package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Clownfish extends Entity {
    public Fish_Clownfish(GamePanel gp){
        super(gp);
        fishRarity = "Legendary";
        fishName = "Clown Fish";
        price = 7;
        fishStar = 1;
        down1 = setup("/Fish/Clownfish_1");
//        fishFrame = setup("Fishingframe/Legendary");
    }
}
