package Enemies;

import Core.ImageBank;
import Core.Stats;
import Items.*;
import Items.Potions.HealthPotion;
import Items.Weapons.Dagger;
import Items.Weapons.Sword;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * Created by Ollie on 27/04/14.
 */
public class Slime extends Enemy {

    public Slime(int posX, int posY, float difficulty) {
        super(posX, posY, difficulty);
        setImage(ImageBank.slime);

        exp = 3;
        Random r = new Random(System.nanoTime());
        int i = r.nextInt(5);
        //if(i == 0){
            i = r.nextInt(7);
            switch(i){
                case Item.Dagger:
                    items = new Item[]{new Dagger(0, 0, 6, 1, false)};
                    break;
                case Item.Sword:
                    items = new Item[]{new Sword(0, 0, 6, 1, false)};
                    break;
                case Item.HealthPotion:
                    items = new Item[]{new HealthPotion(20, 0)};
                    break;
                case Item.LeatherArmour:
                    items = new Item[]{new Sword(0, 0, 6, 1, false)};
                    break;
            }
        //}
        stats = new Stats((int)(10*difficulty), (int)(10*difficulty), (int)(1+difficulty), 0, (int)(1*difficulty), (int)(3+difficulty));
        moveSpeed = 1;
    }
}
