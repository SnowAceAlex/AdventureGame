package main;

import entity.Entity;
import object.OBJ_COIN_BROZEN;
import object.OBJ_HEART;
import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class UI {

    GamePanel gp;
    Graphics2D g2;
    Font maruMonica;
    BufferedImage heart_full, heart_half, heart_blank, coin;
    public boolean messageOn = false;
//    public String message = "";
//    int messageCounter = 0;
    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int playerSlotCol = 0;
    public int playerSlotRow = 0;
    public int npcSlotCol = 0;
    public int npcSlotRow = 0;
    int subState = 0;
    public Entity npc;

    public UI(GamePanel gp){
        this.gp = gp;
        try {
            InputStream is = getClass().getResourceAsStream("/font/x12y16pxMaruMonica.ttf");
            maruMonica = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Create HUD Object
        Entity heart = new OBJ_HEART(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        Entity bronzeCoin = new OBJ_COIN_BROZEN(gp);
        coin = bronzeCoin.down1;
    }

    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }
    public void draw(Graphics2D g2){

        this.g2 = g2;

        g2.setFont(maruMonica);
        g2.setColor(Color.WHITE);

        //UI State
        if(gp.gameState == gp.tileState){
            drawTileScreen();
        }
        //Play State
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            drawMessage();
        }
        //Pause State
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
            drawPlayerLife();
        }
        //Dialogue State
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
            drawPlayerLife();
        }
        //Character State
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory(gp.player,true);
        }

        //Options State
        if(gp.gameState == gp.optionsState){
            drawOptionsScreen();
        }

        //Trade State
        if(gp.gameState == gp.tradeState){
            drawTradeScreen();
        }
    }

    public void drawTileScreen(){

        g2.setColor(new Color(159, 213, 163));
        g2.fillRect(0,0,gp.screenWidth,gp.screenHeight);

        //Tile Name
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,100F));
        String text = "Thang Ngu Phieu Luu Ky";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/4;

        //Shadow
        g2.setColor(Color.black);
        g2.drawString(text, x+5, y+5);
        //Text Color
        g2.setColor(Color.white);
        g2.drawString(text,x,y);

        //Image
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2,null);

        //Menu
        g2.setFont(g2.getFont().deriveFont(Font.BOLD,48F));

        text = "NEW GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize*3;
        g2.drawString(text,x,y);
        if(commandNum == 0){
            g2.drawString(">",x - gp.tileSize + 30,y);
        }

        text = "CONTINUE";
        x = getXforCenteredText(text);
        y += gp.tileSize-10;
        g2.drawString(text,x,y);
        if(commandNum == 1){
            g2.drawString(">",x - gp.tileSize + 30,y);
        }

        text = "LOAD GAME";
        x = getXforCenteredText(text);
        y += gp.tileSize-10;
        g2.drawString(text,x,y);
        if(commandNum == 2){
            g2.drawString(">",x - gp.tileSize + 30,y);
        }

        text = "SETTINGS";
        x = getXforCenteredText(text);
        y += gp.tileSize-10;
        g2.drawString(text,x,y);
        if(commandNum == 3){
            g2.drawString(">",x - gp.tileSize + 30,y);
        }

        text = "QUIT TO DESKTOP";
        x = getXforCenteredText(text);
        y += gp.tileSize-10;
        g2.drawString(text,x,y);
        if(commandNum == 4){
            g2.drawString(">",x - gp.tileSize + 30,y);
        }
    }

    public void drawPlayerLife(){

        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        //Draw Blank Heart
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        //Reset
        x = gp.tileSize/2;
        y = gp.tileSize/2;
        i = 0;

        //Draw Current Life
        while (i < gp.player.life){
            g2.drawImage(heart_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gp.tileSize;
        }
    }

    public void drawMessage(){
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,80F));
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight/2;

        g2.drawString(text,x,y);
    }

    public void drawDialogueScreen(){

        //Window
        int x = gp.tileSize * 2;
        int y = gp.tileSize * 2;
        int width = gp.screenWidth - (gp.tileSize*6);
        int height = gp.tileSize * 4;
        drawSubWindow(x, y , width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 32F));
        x += gp.tileSize;
        y += gp.tileSize;

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawOptionsScreen(){

        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(40F));

        //Sub Window
        int frameX = gp.tileSize*6;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*8;
        int frameHeight = gp.tileSize*10;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch (subState){
            case 0: options_top(frameX, frameY); break;
            case 1: options_fullScreenNotification(frameX, frameY); break;
            case 2: options_control(frameX,frameY); break;
            case 3: options_endGameCofirmation(frameX,frameY); break;
        }

        gp.keyH.enterPressed = false;
    }
    public void options_top(int frameX, int frameY){
        int textX;
        int textY;

        //Title
        String text = "Options";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        //FullScreen
        textX = frameX + gp.tileSize;
        textY += gp.tileSize*2;
        g2.drawString("Fullscreen", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                if(gp.fullScreenOn == false){
                    gp.fullScreenOn = true;
                } else if (gp.fullScreenOn = true){
                    gp.fullScreenOn = false;
                }
                subState = 1;
            }
        }

        //Music
        textY += gp.tileSize;
        g2.drawString("Music", textX, textY);
        if(commandNum == 1){
            g2.drawString(">", textX - 25, textY);
        }

        //SE
        textY += gp.tileSize;
        g2.drawString("SE", textX, textY);
        if(commandNum == 2){
            g2.drawString(">", textX - 25, textY);
        }

        //Control
        textY += gp.tileSize;
        g2.drawString("Control", textX, textY);
        if(commandNum == 3){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 2;
                commandNum = 0;
            }
        }


        //Quit to main menu
        textY += gp.tileSize;
        g2.drawString("Quit to main menu", textX, textY);
        if(commandNum == 4){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 3;
                commandNum = 0;
            }
        }

        //Back
        textY += gp.tileSize*2;
        g2.drawString("Back", textX, textY);
        if(commandNum == 5){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 0;
                gp.gameState = gp.playState;
            }
        }

        //FullScreen check box
        textX = frameX + (int) (gp.tileSize*4.5);
        textY = frameY + gp.tileSize*2 + 38;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX,textY,24,24);
        if(gp.fullScreenOn == true){
            g2.fillRect(textX, textY, 24,24);
        }

        //Music Volume
        textY += gp.tileSize;
        g2.drawRect(textX,textY,180,24); //180/5 = 36
        int volumeWidth = 36 * gp.music.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);

        //SE Volume
        textY += gp.tileSize;
        g2.drawRect(textX,textY,180,24);
        volumeWidth = 36 * gp.se.volumeScale;
        g2.fillRect(textX,textY,volumeWidth,24);
    }
    public void options_fullScreenNotification(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "The change will be take \neffect after restarting \nthe game.";

        for(String line: currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY += 40;
        }

        //Back
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
            }
        }
    }
    public void options_control(int frameX, int frameY){
        int textX;
        int textY;

        //Title
        String text = "Control";
        textX = getXforCenteredText(text);
        textY = frameY + gp.tileSize;
        g2.drawString(text, textX, textY);

        textX = frameX + gp.tileSize;
        textY += gp.tileSize;
        g2.drawString("Move",textX,textY); textY += gp.tileSize;
        g2.drawString("Confirm/Attack",textX,textY); textY += gp.tileSize;
        g2.drawString("Shoot/Cast",textX,textY); textY += gp.tileSize;
        g2.drawString("Character Screen",textX,textY); textY += gp.tileSize;
        g2.drawString("Pause",textX,textY); textY += gp.tileSize;
        g2.drawString("Options",textX,textY); textY += gp.tileSize;

        textX = frameX + gp.tileSize*6;
        textY = frameY + gp.tileSize*2;
        g2.drawString("WASD",textX,textY); textY += gp.tileSize;
        g2.drawString("E",textX,textY); textY += gp.tileSize;
        g2.drawString("F",textX,textY); textY += gp.tileSize;
        g2.drawString("C",textX,textY); textY += gp.tileSize;
        g2.drawString("P",textX,textY); textY += gp.tileSize;
        g2.drawString("ESC",textX,textY); textY += gp.tileSize;


        //Back
        textX = frameX + gp.tileSize;
        textY = frameY + gp.tileSize*9;
        g2.drawString("Back", textX, textY);
        if(commandNum == 0){
            g2.drawString(">", textX - 25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 3;
            }
        }
    }
    public void options_endGameCofirmation(int frameX, int frameY){
        int textX = frameX + gp.tileSize;
        int textY = frameY + gp.tileSize*3;

        currentDialogue = "Quit the game and \nreturn to the main menu?";

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line,textX,textY);
            textY += 40;
        }

        //Yes
        String text = "Yes";
        textX = getXforCenteredText(text);
        textY += gp.tileSize*3;
        g2.drawString(text,textX,textY);
        if(commandNum == 0){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                gp.gameState = gp.tileState;
            }
        }

        //No
        text = "No";
        textX = getXforCenteredText(text);
        textY += gp.tileSize;
        g2.drawString(text,textX,textY);
        if(commandNum == 1){
            g2.drawString(">", textX-25, textY);
            if(gp.keyH.enterPressed == true){
                subState = 0;
                commandNum = 4;
            }
        }

    }
    public void drawTradeScreen(){

        switch (subState){
            case 0: trade_select(); break;
            case 1: trade_buy(); break;
            case 2: trade_sell(); break;
        }
        gp.keyH.enterPressed = false;
    }
    public void trade_select(){
        drawDialogueScreen();

        //Draw
        int x = gp.tileSize*15;
        int y = gp.tileSize*4;
        int width = gp.tileSize * 3;
        int height = (int) (gp.tileSize * 3.5);
        drawSubWindow(x,y,width,height);

        //DrawText
        x += gp.tileSize;
        y += gp.tileSize;
        g2.drawString("Buy",x,y);
        if(commandNum == 0){
            g2.drawString(">", x-24,y);
            if(gp.keyH.enterPressed == true){
                subState = 1;
            }
        }


        y += gp.tileSize;
        g2.drawString("Sell",x,y);
        if(commandNum == 1){
            g2.drawString(">", x-24,y);
            if(gp.keyH.enterPressed == true){
                subState = 2;
            }
        }


        y += gp.tileSize;
        g2.drawString("Leave",x,y);
        if(commandNum == 2){
            g2.drawString(">", x-24,y);
            if(gp.keyH.enterPressed == true){
                commandNum = 0;
                gp.gameState = gp.dialogueState;
                currentDialogue = "Come again, kekekeke!";
            }
        }

    }
    public void trade_buy(){
        //Player Inventory
        drawInventory(gp.player, false);
        //NPC Inventory
        drawInventory(npc, true);
        //Hint Window
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);
        //Player Coin Window
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your coin: " + gp.player.coin, x+24, y+60);
        //Price Window
        int itemIndex = getItemIndexOnSlot(npcSlotCol,npcSlotRow);
        if(itemIndex < npc.inventory.size()){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,38F));

            x = (int) (gp.tileSize*5.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+10, 40, 40, null);

            int price = npc.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize*8);
            g2.drawString(text, x-28, y+45);

            //Buy an item
            if(gp.keyH.enterPressed == true){
                if(npc.inventory.get(itemIndex).price > gp.player.coin){
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You need more coin to buy that!";
                    drawDialogueScreen();
                }else{
                    if(gp.player.canObtainItem(npc.inventory.get(itemIndex)) == true){
                        gp.player.coin -= npc.inventory.get(itemIndex).price;
                    }
                    else{
                        subState = 0;
                        gp.gameState = gp.dialogueState;
                        currentDialogue = "You cannot carry any more!";

                    }
                }
            }

        }
    }
    public void trade_sell(){
        //Player Inventory
        drawInventory(gp.player, true);

        //Hint Window
        int x = gp.tileSize*2;
        int y = gp.tileSize*9;
        int width = gp.tileSize*6;
        int height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("[ESC] Back", x+24, y+60);
        //Player Coin Window
        x = gp.tileSize*12;
        y = gp.tileSize*9;
        width = gp.tileSize*6;
        height = gp.tileSize*2;
        drawSubWindow(x,y,width,height);
        g2.drawString("Your coin: " + gp.player.coin, x+24, y+60);
        //Price Window
        int itemIndex = getItemIndexOnSlot(playerSlotCol,playerSlotRow);
        if(itemIndex < gp.player.inventory.size()){
            g2.setFont(g2.getFont().deriveFont(Font.BOLD,38F));

            x = (int) (gp.tileSize*15.5);
            y = (int) (gp.tileSize*5.5);
            width = (int) (gp.tileSize*2.5);
            height = gp.tileSize;
            drawSubWindow(x,y,width,height);
            g2.drawImage(coin, x+10, y+10, 40, 40, null);

            int price = gp.player.inventory.get(itemIndex).price;
            String text = "" + price;
            x = getXforAlignToRightText(text, gp.tileSize*18);
            g2.drawString(text, x-28, y+45);

            //Sell an item
            if(gp.keyH.enterPressed == true){

                if(gp.player.inventory.get(itemIndex) == gp.player.currentWeapon || gp.player.inventory.get(itemIndex) == gp.player.currentShield){
                    commandNum = 0;
                    subState = 0;
                    gp.gameState = gp.dialogueState;
                    currentDialogue = "You cannot sell an equipped item!";
                }else {
                    if(gp.player.inventory.get(itemIndex).amount > 1){
                        gp.player.inventory.get(itemIndex).amount--;
                    }else{
                        gp.player.inventory.remove(itemIndex);
                    }
                    gp.player.coin += price;
                }

            }

        }
    }


    public void drawSubWindow(int x, int y, int width, int height){

        Color c = new Color(0,0,0,240);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255,255,255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25 );
    }

    public int getXforCenteredText(String text){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = gp.screenWidth/2 - length/2;
        return x;
    }
    public int getXforAlignToRightText(String text, int tailX){
        int length = (int)g2.getFontMetrics().getStringBounds(text,g2).getWidth();
        int x = tailX - length;
        return x;
    }

    public void drawCharacterScreen(){
        //Create Frame
        final int frameX = gp.tileSize;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*4;
        final int frameHeight = gp.tileSize*9;
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //Text
        g2.setColor(Color.WHITE);
        g2.setFont(g2.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        //Names
        g2.drawString("Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Life", textX, textY);
        textY += lineHeight;
        g2.drawString("Strength", textX, textY);
        textY += lineHeight;
        g2.drawString("Dexterity", textX, textY);
        textY += lineHeight;
        g2.drawString("Attack", textX, textY);
        textY += lineHeight;
        g2.drawString("Defense", textX, textY);
        textY += lineHeight;
        g2.drawString("EXP", textX, textY);
        textY += lineHeight;
        g2.drawString("Next Level", textX, textY);
        textY += lineHeight;
        g2.drawString("Coin", textX, textY);
        textY += lineHeight + 20;
        g2.drawString("Weapon", textX, textY);
        textY += lineHeight  + 40;
        g2.drawString("Shield", textX, textY);
        textY += lineHeight;

        //Values
        int tailX = (frameX + frameWidth) - 30;
        //Reset textY
        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strength);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentWeapon.down1, tailX - gp.tileSize, textY - 15, null);
        textY+= gp.tileSize;

        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY - 15, null );

    }
    public void drawInventory(Entity entity, boolean cursor){

        int frameX = 0;
        int frameY = 0;
        int frameWidth = 0;
        int frameHeight = 0;
        int slotCol = 0;
        int slotRow = 0;

        if(entity == gp.player){
            frameX = gp.tileSize*12;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = playerSlotCol;
            slotRow = playerSlotRow;
        }else {
            frameX = gp.tileSize*2;
            frameY = gp.tileSize;
            frameWidth = gp.tileSize*6;
            frameHeight = gp.tileSize*5;
            slotCol = npcSlotCol;
            slotRow = npcSlotRow;
        }

        //Frame
        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        //Slot
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY= slotYstart;
        int slotSize = gp.tileSize+3;

        //Draw Player's Items
        for(int i = 0; i < entity.inventory.size(); i++){

            //Equip Cursor
            if(entity.inventory.get(i) == entity.currentWeapon || entity.inventory.get(i) == entity.currentShield){
                g2.setColor(new Color(240,190,90));
                g2.fillRoundRect(slotX,slotY,gp.tileSize,gp.tileSize,10,10);
            }

            g2.drawImage(entity.inventory.get(i).down1, slotX, slotY, null);

            //Display amount
            if(entity == gp.player && entity.inventory.get(i).amount > 1){

                g2.setFont(g2.getFont().deriveFont(32f));
                int amountX;
                int amountY;

                String s = "" + entity.inventory.get(i).amount;
                amountX = getXforAlignToRightText(s, slotX + 44);
                amountY = slotY + gp.tileSize;

                //Shadow
                g2.setColor(new Color(60,60,60));
                g2.drawString(s, amountX+10, amountY);
                //Number
                g2.setColor(Color.WHITE);
                g2.drawString(s, amountX+7, amountY-3);
            }

            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }

        //Cursor
        if(cursor == true){
            int cursorX = slotXstart + (slotSize * slotCol);
            int cursorY = slotYstart + (slotSize * slotRow);
            int cursorWidth = gp.tileSize;
            int cursorHeight = gp.tileSize;

            //Draw Cursor
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(3));
            g2.drawRoundRect(cursorX,cursorY,cursorWidth,cursorHeight,10,10);

            //Description Frame
            int dFrameX = frameX;
            int dFrameY = frameY + frameHeight;
            int dFrameWidth  = frameWidth;
            int dFrameHeight  = gp.tileSize*3;


            //Description Text
            int textX = dFrameX + 20;
            int textY = dFrameY + gp.tileSize;
            g2.setFont(g2.getFont().deriveFont(28F));

            int itemIndex = getItemIndexOnSlot(slotCol,slotRow);

            if(itemIndex < entity.inventory.size()){

                drawSubWindow(dFrameX,dFrameY,dFrameWidth,dFrameHeight);

                for(String line: entity.inventory.get(itemIndex).description.split("\n")){
                    g2.drawString(line, textX, textY);
                    textY += 32;
                }
            }
        }
    }
    public int getItemIndexOnSlot(int slotCol, int slotRow){
        int itemIndex = slotCol + (slotRow*5);
        return itemIndex;
    }
}
