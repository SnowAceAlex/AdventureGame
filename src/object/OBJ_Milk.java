package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Milk extends Entity {
    public OBJ_Milk(GamePanel gp){
        super(gp);
        caught = true;
        name = "Milk";
        price = 1;
        fishStar = 1;
        count = 0;
        down1 = setup("/Item/Milk");
        fishRarity = "";

    }
}
