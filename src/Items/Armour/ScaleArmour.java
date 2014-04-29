package Items.Armour;

import Core.ImageBank;
import Items.Item;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 28/04/14.
 */
public class ScaleArmour extends Armour {
    public ScaleArmour(int HP, int maxHP, int Defense, int Evade, boolean isEquipped) {
        super(HP, maxHP, Defense, Evade, isEquipped);
        name = "Scale Armour";
        id = Item.ScaleArmour;
        image = ImageBank.itemsTiles.getSubImage(96, 0, 16, 16);
        image.setFilter(Image.FILTER_NEAREST);
    }
}
