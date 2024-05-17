package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Bottle extends Entity {
    public Fish_Bottle(GamePanel gp){
        super(gp);
        fishName = "Bottle";
        fishPrice = 0;
        fishStar = 0;
        fishImage = setup("/Item/Bottle");
    }
}
