package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Coelacanths extends Entity {
    public Fish_Coelacanths(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        name = "Coelacanths";
        price = 5;
        fishStar = 2;
        down1 = setup("/Fish/Coelacanths_2");
        count = 0;
        fishFinalImage = setup("/Item/Qm");
//        fishFrame = setup("Fishingframe/Common");
    }
}
