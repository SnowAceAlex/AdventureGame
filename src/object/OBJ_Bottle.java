package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Bottle extends Entity {
    public OBJ_Bottle(GamePanel gp){
        super(gp);
        name = "Bottle";
        price = 0;
        fishStar = 0;
        down1 = setup("/Item/Bottle");
        count = 0;
        fishFinalImage = setup("/Item/Qm");
    }
}
