package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Bottle extends Entity {
    public Fish_Bottle(GamePanel gp){
        super(gp);
        fishName = "Bottle";
        price = 0;
        fishStar = 0;
        down1 = setup("/Item/Bottle");
    }
}
