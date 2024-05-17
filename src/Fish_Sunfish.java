package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Sunfish extends Entity {
    public Fish_Sunfish(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Sun Fish";
        fishPrice = 11;
        fishStar = 3;
        fishImage = setup("/Fish/Sunfish_3");
        fishFrame = setup("Fishingframe/Common");
    }
}
