package entity;

import main.GamePanel;
import main.KeyHandler;
import object.OBJ_BOOTS;
import object.OBJ_Key;
import object.OBJ_Shield_Wood;
import object.OBJ_Sword_Normal;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;
import java.util.ArrayList;

public class Player extends Entity{


    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public boolean lightUpdated = false;
    public int rod = 2;


    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);


        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize / 2);
        screenY = gp.screenHeight/2 - (gp.tileSize / 2);

        solidArea = new Rectangle(8,16,32,32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValue();
        getPlayerImaged();
        setItems();
    }

    public void setDefaultValue(){
        worldX = gp.tileSize * 15;
        worldY = gp.tileSize * 10;
        speed = 4;
        direction = "down";

        //Player Status
        level = 1;
        strength = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 500;
        currentWeapon = new OBJ_Sword_Normal(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = 23;
        defense = 29;
        maxLife = 6;
        life = maxLife;
    }

    public void setItems(){
        inventory.add(currentShield);
        inventory.add(currentWeapon);
        inventory.add(new OBJ_Key(gp));
        inventory.add(new OBJ_BOOTS(gp));
    }

    public void getPlayerImaged(){
            up1 = setup("/player/boy_up_1");
            up2 = setup("/player/boy_up_2");
            down1 = setup("/player/boy_down_1");
            down2 = setup("/player/boy_down_2");
            left1 = setup("/player/boy_left_1");
            left2 = setup("/player/boy_left_2");
            right1 = setup("/player/boy_right_1");
            right2 = setup("/player/boy_right_2");
    }

    public void update(){
        //Check Direction Via Key Pressed
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPress == true || keyH.rightPress == true || keyH.enterPressed == true){
            if(keyH.upPressed == true){
                direction = "up";
            }
            else if(keyH.downPressed == true){
                direction = "down";
            }
            else if(keyH.leftPress == true){
                direction = "left";
            }
            else if(keyH.rightPress == true){
                direction = "right";
            }

            //Collision Tile Checker
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //Collision Object Checker
            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            //Collision NPC Checker
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            //Collision Monster Checker
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            //If Collision False Player Can Move
            if(collisionOn == false && keyH.enterPressed == false){
                switch (direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            gp.keyH.enterPressed = false;

            spriteCounter++;
            if(spriteCounter > 10){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        //invincible delay time for npc health outside key if statement
        if(invincible == true){
            invincibleCounter++;
            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void pickUpObject(int i){
        if(i != 999){

            String text;

            if(canObtainItem(gp.obj[i]) == true){
                gp.playSE(1);
                text = "Got a " + gp.obj[i].name + "!";
            }else {
                text = "You cannot carry any more";
            }
            gp.ui.addMessage(text);
            gp.obj[i] = null;
        }
    }
    public void interactNPC(int i){
        if(i != 999){
            if(gp.keyH.enterPressed){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
        gp.keyH.enterPressed = false;
    }

    public void contactMonster(int i){
        if(i != 999){
            if(invincible == false){
                life -= 1;
                invincible = true;
            }

        }
    }

    public int seachItemInInventory(String itemName){
        int itemIndex = 999;

        for(int i = 0; i < inventory.size(); i++){
            if(inventory.get(i).name.equals(itemName)){
                itemIndex = i;
                break;
            }
        }
        return itemIndex;
    }

    public boolean canObtainItem(Entity item){
        boolean canContain = false;

        //Check if stackable
        if(item.stackable == true){
            int index = seachItemInInventory(item.name);

            if(index != 999){
                inventory.get(index).amount++;
                canContain = true;
            }else{
                //New item so need to track vacancy
                if(inventory.size() != maxInventorySize){
                    inventory.add(item);
                    canContain = true;
                }
            }
        }else{
            //Not stackable so check vacancy
            if(inventory.size() != maxInventorySize){
                inventory.add(item);
                canContain = true;
            }
        }
        return canContain;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch (direction){
            case "up":
                if(spriteNum == 1){
                    image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                    image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                    image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                    image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        g2.drawImage(image, screenX, screenY,null);

        //Reset Alphaa
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }
}
