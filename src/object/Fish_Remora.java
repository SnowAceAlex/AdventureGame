package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Remora extends Entity {
    public Fish_Remora(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        name = "Remora";
        price = 5;
        fishStar = 2;
        down1 = setup("/Fish/Remora_2");
        count = 0;
        fishFinalImage = setup("/Item/Qm");
//        fishFrame = setup("Fishingframe/Common");
    }
}
