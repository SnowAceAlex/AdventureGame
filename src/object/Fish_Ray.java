package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Ray extends Entity {
    public Fish_Ray(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Ray";
        price = 11;
        fishStar = 3;
        down1 = setup("/Fish/Ray_3");
//        fishFrame = setup("Fishingframe/Common");
    }
}
