package Items.Weapons;

import Core.ImageBank;
import Items.Item;

/**
 * Created by Ollie on 28/04/14.
 */
public class Longsword extends Weapon {

    public Longsword(int HP, int maxHP, int Attack, int Speed, boolean isEquipped) {
        super(HP, maxHP, Attack, Speed, isEquipped);
        name = "Longsword";
        id = Item.LongSword;
        image = ImageBank.itemsTiles.getSubImage(16, 0, 16, 16);
    }

}
