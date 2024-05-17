package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Herring extends Entity {
    public Fish_Herring(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Herring";
        price = 1;
        fishStar = 1;
        down1 = setup("/Fish/Herring_1");
//        fishFrame = setup("Fishingframe/Common");
    }


}
