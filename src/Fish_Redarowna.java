package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Redarowna extends Entity {
    public Fish_Redarowna(GamePanel gp){
        super(gp);
        fishRarity = "Uncommon";
        fishName = "Redarowna";
        fishPrice = 5;
        fishStar = 2;
        fishImage = setup("/Fish/Redarowna_2");
        fishFrame = setup("Fishingframe/Uncommon");
    }
}
