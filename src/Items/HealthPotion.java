package Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Ollie on 28/04/14.
 */
public class HealthPotion extends Potion {
    public HealthPotion(int HP, int maxHP, int Attack, int Defense, int Speed, int Evade) {
        super(HP, maxHP, Attack, Defense, Speed, Evade);
        try{
            image = new Image("res/tex/ItemsTiles.png").getSubImage(48, 0, 16, 16);
            image.setImageColor(0.7f, 0, 0);
        }catch(SlickException e){
            System.err.println("Error: Cannot load ItemTiles.png");
        }
    }
}
