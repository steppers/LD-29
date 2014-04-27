package Items;

import Core.Stats;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 27/04/14.
 */
public class Weapon extends Item{

    public Stats stats;

    public Weapon(int HP, int maxHP, int Attack, int Defense, int Speed, int Evade){
        super(0, 0);
        isStackable = false;
        stats = new Stats(HP, maxHP, Attack, Defense, Speed, Evade);
    }
}
