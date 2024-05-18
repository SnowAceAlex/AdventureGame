package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Sunfish extends Entity {
    public Fish_Sunfish(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        name = "Sun Fish";
        price = 11;
        fishStar = 3;
        down1 = setup("/Fish/Sunfish_3");
        count = 0;
        fishFinalImage = setup("/Item/Qm");
//        fishFrame = setup("Fishingframe/Common");
    }
}
