package Fish;

import entity.Entity;
import main.GamePanel;

public class Fish_Remora extends Entity {
    public Fish_Remora(GamePanel gp){
        super(gp);
        fishRarity = "Common";
        fishName = "Remora";
        fishPrice = 5;
        fishStar = 2;
        fishImage = setup("/Fish/Remora_2");
        fishFrame = setup("Fishingframe/Common");
    }
}
