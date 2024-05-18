package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Herring extends Entity {
    public Fish_Herring(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        name = "Herring";
        price = 1;
        fishStar = 1;
        down1 = setup("/Fish/Herring_1");
        count = 0;
        fishFinalImage = setup("/Item/Qm");
//        fishFrame = setup("Fishingframe/Common");
    }


}
