package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;

public class KeyHandler implements KeyListener {
    GamePanel gp;
    public boolean upPressed, downPressed, leftPress, rightPress, enterPressed;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e){

        int code = e.getKeyCode();

        //TileState
        if(gp.gameState == gp.tileState){
            titleState(code);
        }

        //PlayState
        else if(gp.gameState == gp.playState){
            playState(code);
        }
        //PauseState
         else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }
        //DialogueState
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        //CharacterState
        else if (gp.gameState == gp.characterState) {
            characterState(code);
        }
        //OptionsState
        else if (gp.gameState == gp.optionsState) {
            optionsState(code);
        }
        //TradeState
        else if (gp.gameState == gp.tradeState) {
            tradeState(code);
        }
        //Fishingstate
        else if (gp.gameState == gp.fishingState){
            fishingState(code);
        }
        //After fishing state
        else if (gp.gameState == gp.afterFishingState){
            afterFishingState(code);
        }

        else if(gp.gameState == gp.inventoryState){
            inventoryManagementState(code);
        }

    }

    public void titleState(int code){
        if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0 ){
                gp.ui.commandNum = 4;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 4){
                gp.ui.commandNum = 0;
            }
        }

        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.playMusic(0);
            }
            if(gp.ui.commandNum == 1){
                //Add Later for Continue
            }
            if(gp.ui.commandNum == 2){
                //Add Later for Load Game
            }
            if(gp.ui.commandNum == 3){
                //Add Later for Settings
            }
            if(gp.ui.commandNum == 4){
                System.exit(0);
            }

        }
    }
    public void playState(int code){
        if(code == KeyEvent.VK_UP){
            upPressed = true;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPress = true;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = true;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPress = true;
        }
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }
        if(code == KeyEvent.VK_E){
            enterPressed = true;
        }
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.optionsState;
        }
        if(code == KeyEvent.VK_SPACE){
            gp.gameState = gp.fishingState;
        }
        if(code == KeyEvent.VK_B){
            gp.gameState = gp.inventoryState;
        }

    }
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }
    public void dialogueState(int code){
        if(code == KeyEvent.VK_E){
            gp.gameState = gp.playState;
        }
    }
    public void characterState(int code){
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        playerInventory(code);
    }
    public void optionsState(int code){
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }
        if(code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            gp.playSE(5);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            gp.playSE(5);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;

            }
        }
        if(code == KeyEvent.VK_LEFT){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSE(5);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale > 0){
                    gp.se.volumeScale--;
                    gp.playSE(5);
                }
            }
        }
        if(code == KeyEvent.VK_RIGHT){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSE(5);
                }
                if(gp.ui.commandNum == 2 && gp.se.volumeScale < 5){
                    gp.se.volumeScale++;
                    gp.playSE(5);
                }
            }
        }

    }
    public void tradeState(int code){
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
                gp.playSE(5);
            }
            if(code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
                gp.playSE(5);
            }
        }
        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
        if(gp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }
    public void playerInventory(int code){
        if(code == KeyEvent.VK_UP){
            if(gp.ui.playerSlotRow != 0){
                gp.ui.playerSlotRow--;
                gp.playSE(5);
            }
        }
        if(code == KeyEvent.VK_DOWN){
            if(gp.ui.playerSlotRow != 3){
                gp.ui.playerSlotRow++;
                gp.playSE(5);
            }
        }
        if(code == KeyEvent.VK_LEFT){
            if(gp.ui.playerSlotCol != 0){
                gp.ui.playerSlotCol--;
                gp.playSE(5);
            }
        }
        if(code == KeyEvent.VK_RIGHT){
            if(gp.ui.playerSlotCol != 4){
                gp.ui.playerSlotCol++;
                gp.playSE(5);
            }
        }
    }
    public void npcInventory(int code){
        if(code == KeyEvent.VK_UP){
            if(gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
                gp.playSE(5);
            }
        }
        if(code == KeyEvent.VK_DOWN){
            if(gp.ui.npcSlotRow != 3){
                gp.ui.npcSlotRow++;
                gp.playSE(5);
            }
        }
        if(code == KeyEvent.VK_LEFT){
            if(gp.ui.npcSlotCol != 0){
                gp.ui.npcSlotCol--;
                gp.playSE(5);
            }
        }
        if(code == KeyEvent.VK_RIGHT){
            if(gp.ui.npcSlotCol != 4){
                gp.ui.npcSlotCol++;
                gp.playSE(5);
            }
        }
    }

    public void fishingState(int code) {
        if (code == KeyEvent.VK_SPACE) {
            gp.ui.completion += 10;
            if (gp.ui.completion >= 110) {
                gp.ui.completion = 0;
                gp.iManage.Fishing(gp.player.rod);
                gp.gameState = gp.afterFishingState;
            }
        }
    }

    public void afterFishingState(int code) {
        if (code == KeyEvent.VK_ENTER) {
            gp.gameState = gp.playState;
        }
    }
    public void inventoryManagementState(int key) {
        if (key == KeyEvent.VK_D) {
            if (gp.ui.inventorySlotCol != 5) {
                gp.ui.inventorySlotCol++;
            }
        }
        if (key == KeyEvent.VK_A) {
            if (gp.ui.inventorySlotCol != 0) {
                gp.ui.inventorySlotCol--;
            }
        }
        if (key == KeyEvent.VK_W) {
            if (gp.ui.inventorySlotRow != 0) {
                gp.ui.inventorySlotRow --;
            }
        }
        if (key == KeyEvent.VK_S) {
            if (gp.ui.inventorySlotRow  != 3) {
                gp.ui.inventorySlotRow ++;
            }

        } else if (key == KeyEvent.VK_B) {
            gp.gameState = gp.playState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_UP){
            upPressed = false;
        }
        if(code == KeyEvent.VK_LEFT){
            leftPress = false;
        }
        if(code == KeyEvent.VK_DOWN){
            downPressed = false;
        }
        if(code == KeyEvent.VK_RIGHT){
            rightPress = false;
        }
    }
}
