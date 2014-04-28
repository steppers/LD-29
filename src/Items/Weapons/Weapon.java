package Items.Weapons;

import Core.Stats;
import Items.Item;

/**
 * Created by Ollie on 27/04/14.
 */
public class Weapon extends Item {

    public Weapon(int HP, int maxHP, int Attack, int Speed, boolean isEquipped){
        super(0, 0);
        this.isEquipped = isEquipped;
        type = WeaponType;
        isStackable = false;
        isEquipable = true;
        stats = new Stats(HP, maxHP, Attack, 0, Speed, 0);
    }
}
