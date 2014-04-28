package Items;

import Core.Stats;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Ollie on 28/04/14.
 */
public class Potion extends Item{

    public Stats stats;

    public Potion(int HP, int maxHP, int Attack, int Defense, int Speed, int Evade){
        super(0, 0);
        isStackable = true;
        stats = new Stats(HP, maxHP, Attack, Defense, Speed, Evade);
    }

}
