package Items.Weapons;

import Core.Stats;
import Items.Item;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 27/04/14.
 */
public class Weapon extends Item {

    public Stats stats;

    public Weapon(int HP, int maxHP, int Attack, int Speed){
        super(0, 0);
        isStackable = false;
        stats = new Stats(HP, maxHP, Attack, 0, Speed, 0);
    }
}
