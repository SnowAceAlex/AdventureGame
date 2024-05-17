package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Swordfish extends Entity {
    public Fish_Swordfish(GamePanel gp){
        super(gp);
        fishRarity = "Uncommon";
        fishName = "Sword Fish";
        fishPrice = 12;
        fishStar = 3;
        fishImage = setup("/Fish/Swordfish_3");
        fishFrame = setup("Fishingframe/Uncommon");
    }
}
