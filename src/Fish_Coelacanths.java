package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Coelacanths extends Entity {
    public Fish_Coelacanths(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Coelacanths";
        fishPrice = 5;
        fishStar = 2;
        fishImage = setup("/Fish/Coelacanths_2");
        fishFrame = setup("Fishingframe/Common");
    }
}
