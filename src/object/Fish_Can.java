package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Can extends Entity {
    public Fish_Can(GamePanel gp){
        super(gp);
        fishName = "can";
        price = 0;
        fishStar = 0;
        down1 = setup("/Item/Can");
    }
}
