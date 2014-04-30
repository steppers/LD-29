package Enemies;

import Core.ImageBank;
import Core.Stats;
import Items.*;
import Items.Armour.LeatherArmour;
import Items.Armour.ScaleArmour;
import Items.Armour.SteelArmour;
import Items.Potions.HealthPotion;
import Items.Weapons.Dagger;
import Items.Weapons.Longsword;
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

        exp = (int)(3*difficulty/2);
        Random r = new Random(System.nanoTime());
        int i = r.nextInt(3);
        if(i == 0){
            float f = r.nextFloat();
            if(f < 0.3f)
                    items = new Item[]{new Dagger(0, 0, 4, 1, false)};
            else if(f >= 0.3f && f < 0.5f)
                    items = new Item[]{new Sword(0, 0, 5, 1, false)};
            else if(f >= 0.5f && f < 0.6f)
                    items = new Item[]{new Longsword(0, 0, 6, 2, false)};
            else if(f >= 0.6f && f < 0.85f)
                    items = new Item[]{new HealthPotion(50, 0)};
            else if(f >= 0.85f && f < 0.9f)
                    items = new Item[]{new LeatherArmour(0, 0, 3, 1, false)};
            else if(f >= 0.9f && f < 0.95f)
                    items = new Item[]{new SteelArmour(0, 0, 5, 1, false)};
            else if(f >= 0.95f && f < 1f)
                    items = new Item[]{new ScaleArmour(0, 0, 8, 1, false)};
        }
        stats = new Stats((int)(20*difficulty/3), (int)(20*difficulty/3), (int)(2+difficulty/3), 0, (int)(1*difficulty/3), 1);
        moveSpeed = 1;
    }
}
