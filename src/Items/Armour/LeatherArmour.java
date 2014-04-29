package Items.Armour;

import Core.ImageBank;
import Items.Item;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 28/04/14.
 */
public class LeatherArmour extends Armour {
    public LeatherArmour(int HP, int maxHP, int Defense, int Evade, boolean isEquipped) {
        super(HP, maxHP, Defense, Evade, isEquipped);
        name = "Leather Armour";
        id = Item.LeatherArmour;
        image = ImageBank.itemsTiles.getSubImage(64, 0, 16, 16);
        image.setFilter(Image.FILTER_NEAREST);
    }
}
