package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Redarowna extends Entity {
    public Fish_Redarowna(GamePanel gp){
        super(gp);
        fishRarity = "Uncommon";
        fishName = "Redarowna";
        name = "Redarowna";
        price = 5;
        fishStar = 2;
        down1 = setup("/Fish/Redarowna_2");
//        fishFrame = setup("Fishingframe/Uncommon");
    }
}
