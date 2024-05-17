package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Herring extends Entity {
    public Fish_Herring(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Herring";
        fishPrice = 1;
        fishStar = 1;
        fishImage = setup("/Fish/Herring_1");
        fishFrame = setup("Fishingframe/Common");
    }


}
