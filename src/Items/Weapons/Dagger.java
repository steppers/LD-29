package Items.Weapons;

import Core.ImageBank;
import Items.Item;

/**
 * Created by Ollie on 28/04/14.
 */
public class Dagger extends Weapon {

    public Dagger(int HP, int maxHP, int Attack, int Speed, boolean isEquipped) {
        super(HP, maxHP, Attack, Speed, isEquipped);
        name = "Dagger";
        id = Item.Dagger;
        image = ImageBank.itemsTiles.getSubImage(32, 0, 16, 16);
    }

}
