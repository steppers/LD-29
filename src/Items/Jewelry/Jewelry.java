package Items.Jewelry;

import Core.Stats;
import Items.Item;

/**
 * Created by Ollie on 28/04/14.
 */
public class Jewelry extends Item {

    public Jewelry(int HP, int maxHP, int Attack, int Defense, int Speed, int Evade, boolean isEquipped){
        super(0, 0);
        this.isEquipped = isEquipped;
        type = JewelryType;
        isEquipable = true;
        isStackable = false;
        stats = new Stats(HP, maxHP, Attack, Defense, Speed, Evade);
    }

}
