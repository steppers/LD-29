package Enemies;

import Core.Stats;
import Items.Item;
import Items.Sword;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

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

        items = new Item[]{new Sword(0, 0, 2, 0, 1, 0)};
        stats = new Stats((int)(10*difficulty), (int)(10*difficulty), (int)(3+difficulty), 0, (int)(1*difficulty), (int)(3+difficulty));
        moveSpeed = 1;
    }
}
