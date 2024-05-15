package main;

import entity.NPC_Merchant;
import entity.NPC_OldMan;
import monster.MON_GreenSlime;
import object.*;

public class AssetSetter {

    GamePanel gp;
    public AssetSetter(GamePanel gp){
        this.gp = gp;
    }

    public void setObject(){
        int i = 0;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize*23;
        gp.obj[i].worldY = gp.tileSize*13;
        i++;
        gp.obj[i] = new OBJ_Key(gp);
        gp.obj[i].worldX = gp.tileSize*19;
        gp.obj[i].worldY = gp.tileSize*10;
        i++;
        gp.obj[i] = new OBJ_Lantern(gp);
        gp.obj[i].worldX = gp.tileSize*12;
        gp.obj[i].worldY = gp.tileSize*9;

    }
    public void setNPC(){

        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*14;
        gp.npc[0].worldY = gp.tileSize*10;

        gp.npc[1] = new NPC_Merchant(gp);
        gp.npc[1].worldX = gp.tileSize*16;
        gp.npc[1].worldY = gp.tileSize*8;
    }

    public void setMonster(){
     gp.monster[0] = new MON_GreenSlime(gp);
     gp.monster[0].worldX = gp.tileSize*18;
     gp.monster[0].worldY = gp.tileSize*12;

    }
}
