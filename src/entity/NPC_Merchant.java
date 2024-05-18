package entity;

import main.GamePanel;
import object.*;

import java.awt.*;

public class NPC_Merchant extends Entity{
    public NPC_Merchant(GamePanel gp){
        super(gp);

        direction = "down";
        speed = 0;

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 32;
        solidArea.height = 32;

        getImaged();
        setDialogue();
        setItem();
    }

    public void getImaged(){
        up1 = setup("/npc/merchant_down_1");
        up2 = setup("/npc/merchant_down_2");
        down1 = setup("/npc/merchant_down_2");
        down2 = setup("/npc/merchant_down_2");
        left1 = setup("/npc/merchant_down_2");
        left2 = setup("/npc/merchant_down_2");
        right1 = setup("/npc/merchant_down_1");
        right2 = setup("/npc/merchant_down_2");
    }

    public void setDialogue(){
        dialogues[0] = "I have some good stuff. Do you want to trade huh!";
    }

    public void setItem(){
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_BOOTS(gp));
        inventory.add(new OBJ_BOOTS(gp));
        inventory.add(new OBJ_BOOTS(gp));
    }

    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
