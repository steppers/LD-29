package Items.Potions;

import Core.Stats;
import Items.Item;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Ollie on 28/04/14.
 */
public class Potion extends Item {

    public Stats stats;

    public Potion(int HP, int maxHP){
        super(0, 0);
        isStackable = true;
        stats = new Stats(HP, 0, 0, 0, 0, 0);
    }

}
