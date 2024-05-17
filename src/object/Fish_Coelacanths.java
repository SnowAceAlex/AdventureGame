package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Coelacanths extends Entity {
    public Fish_Coelacanths(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Coelacanths";
        price = 5;
        fishStar = 2;
        down1 = setup("/Fish/Coelacanths_2");
//        fishFrame = setup("Fishingframe/Common");
    }
}
