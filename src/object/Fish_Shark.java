package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Shark extends Entity {
    public Fish_Shark(GamePanel gp){
        super(gp);
        fishRarity = "Legendary";
        fishName = "Shark";
        price = 19;
        fishStar = 3;
        down1 = setup("/Fish/Shark_3");
    }
}
