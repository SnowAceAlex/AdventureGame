package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Grass extends Entity{
        public OBJ_Grass(GamePanel gp){
            super(gp);
            caught = true;
            name = "Grass";
            price = 0;
            count = 0;
            down1 = setup("/Item/Grass");
            fishRarity = "";
        }


}
