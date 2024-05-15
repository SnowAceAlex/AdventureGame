package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_COIN_BROZEN extends Entity {
    public OBJ_COIN_BROZEN(GamePanel gp) {
        super(gp);

        name = "Wood Shield";
        down1 = setup("/objects/coin_bronze");
    }
}
