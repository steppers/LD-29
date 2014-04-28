package Items.Weapons;

import Core.ImageBank;
import Items.Item;

/**
 * Created by Ollie on 27/04/14.
 */
public class Sword extends Weapon {

    public Sword(int HP, int maxHP, int Attack, int Speed, boolean isEquipped) {
        super(HP, maxHP, Attack, Speed, isEquipped);
        name = "Sword";
        id = Item.Sword;
        image = ImageBank.itemsTiles.getSubImage(0, 0, 16, 16);
    }
}
