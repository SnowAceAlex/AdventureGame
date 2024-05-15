package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_BOOTS extends Entity {
    public OBJ_BOOTS(GamePanel gp){
        super(gp);

        name = "Boots";
        down1 = setup("/objects/boots");
        description = "[" + name + "]\nHelp you to speed up!.";
        price = 80;
        stackable = true;
    }
}
