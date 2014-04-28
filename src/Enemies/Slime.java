package Enemies;

import Core.Stats;
import Items.*;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.Random;

/**
 * Created by Ollie on 27/04/14.
 */
public class Slime extends Enemy {

    public Slime(int posX, int posY, float difficulty) {
        super(posX, posY, difficulty);
        try{
            setImage(new Image("res/tex/Slime.png"));
        }catch(SlickException e){
            System.err.println("Error: Slime.png not found");
            e.printStackTrace();
        }

        exp = 3;
        Random r = new Random(System.nanoTime());
        int i = r.nextInt(5);
        //if(i == 0){
            i = r.nextInt(7);
            switch(i){
                case Item.Dagger:
                    items = new Item[]{new Dagger(0, 0, 6, 0, 1, 0)};
                    break;
                case Item.Sword:
                    items = new Item[]{new Sword(0, 0, 6, 0, 1, 0)};
                    break;
                case Item.LongSword:
                    items = new Item[]{new Longsword(0, 0, 6, 0, 1, 0)};
                    break;
                case Item.IronArmour:
                    items = new Item[]{new Sword(0, 0, 6, 0, 1, 0)};
                    break;
                case Item.Potion:
                    items = new Item[]{new HealthPotion(20, 0, 0, 0, 1, 0)};
                    break;
                case Item.LeatherArmour:
                    items = new Item[]{new Sword(0, 0, 6, 0, 1, 0)};
                    break;
                case Item.ScaleArmour:
                    items = new Item[]{new Sword(0, 0, 6, 0, 1, 0)};
                    break;
            }
        //}
        stats = new Stats((int)(10*difficulty), (int)(10*difficulty), (int)(3+difficulty), 0, (int)(1*difficulty), (int)(3+difficulty));
        moveSpeed = 1;
    }
}
