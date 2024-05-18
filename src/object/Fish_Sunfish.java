package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Sunfish extends Entity {
    public Fish_Sunfish(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Sun Fish";
        price = 11;
        fishStar = 3;
        down1 = setup("/Fish/Sunfish_3");
//        fishFrame = setup("Fishingframe/Common");
    }
}
