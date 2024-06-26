package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {
    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Normal Sword";
        down1 = setup("/objects/sword_normal");
        attackValue = 1;
        description = "[" + name + "]\nAn Old Sword.";
        price = 300;
    }
}
