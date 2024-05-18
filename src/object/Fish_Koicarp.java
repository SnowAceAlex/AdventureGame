package object;

import entity.Entity;
import main.GamePanel;

public class Fish_Koicarp extends Entity {
    public Fish_Koicarp(GamePanel gp){
        super(gp);
        fishRarity = "Legendary";
        name = "Koi Carp";
        price = 13;
        fishStar = 2;
        down1 = setup("/Fish/Koicarp_2");
        count = 0;
        fishFinalImage = setup("/Item/Qm");
//        fishFrame = setup("Fishingframe/Legendary");
    }
}
