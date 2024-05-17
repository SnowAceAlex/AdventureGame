package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Shark extends Entity {
    public Fish_Shark(GamePanel gp){
        super(gp);
        fishRarity = "Legendary";
        fishName = "Shark";
        fishPrice = 19;
        fishStar = 3;
        fishImage = setup("/Fish/Shark_3");
        fishFrame = setup("Fishingframe/Legendary");
    }
}
