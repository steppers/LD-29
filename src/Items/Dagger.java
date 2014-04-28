package Items;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Ollie on 28/04/14.
 */
public class Dagger extends Weapon {

    public Dagger(int HP, int maxHP, int Attack, int Defense, int Speed, int Evade) {
        super(HP, maxHP, Attack, Defense, Speed, Evade);
        try{
            image = new Image("res/tex/ItemsTiles.png").getSubImage(32, 0, 16, 16);
        }catch(SlickException e){
            System.err.println("Error: Cannot load ItemTiles.png");
        }
    }

}
