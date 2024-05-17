package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Ray extends Entity {
    public Fish_Ray(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Ray";
        fishPrice = 11;
        fishStar = 3;
        fishImage = setup("/Fish/Ray_3");
        fishFrame = setup("Fishingframe/Common");
    }
}
