package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Lantern extends Entity {

    public OBJ_Lantern(GamePanel gp){
        super(gp);

        type = type_light;
        name = "Lantern";
        down1 = setup("/objects/lantern");
        description = "[Lantern]\nIlluminates your surroundings.";
        price = 20;
        lightRadius = 250;
    }
}
