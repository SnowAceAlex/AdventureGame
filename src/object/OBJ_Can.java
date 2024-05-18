package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Can extends Entity {
    public OBJ_Can(GamePanel gp){
        super(gp);
        name = "Can";
        price = 0;
        fishStar = 0;
        down1 = setup("/Item/Can");
        count = 0;
        fishFinalImage = setup("/Item/Qm");
    }
}
