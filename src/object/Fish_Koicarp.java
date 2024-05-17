package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Koicarp extends Entity {
    public Fish_Koicarp(GamePanel gp){
        super(gp);
        fishRarity = "Legendary";
        fishName = "Koi Carp";
        price = 13;
        fishStar = 2;
        down1 = setup("/Fish/Koicarp_2");
//        fishFrame = setup("Fishingframe/Legendary");
    }
}
