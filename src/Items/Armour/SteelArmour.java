package Items.Armour;

import Core.ImageBank;
import Items.Item;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 28/04/14.
 */
public class SteelArmour extends Armour{

    public SteelArmour(int HP, int maxHP, int Defense, int Evade, boolean isEquipped) {
        super(HP, maxHP, Defense, Evade, isEquipped);
        name = "Steel Armour";
        id = Item.IronArmour;
        image = ImageBank.itemsTiles.getSubImage(80, 0, 16, 16);
        image.setFilter(Image.FILTER_NEAREST);
    }
}
