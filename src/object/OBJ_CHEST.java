package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_CHEST extends Entity {
    public OBJ_CHEST(GamePanel gp){
        super(gp);

        name = "Chest";
        down1 = setup("/objects/chest");
        collision = true;
    }
}
