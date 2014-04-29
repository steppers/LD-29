package Items.Armour;

import Core.ImageBank;
import Items.Item;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 28/04/14.
 */
public class Shirt extends Armour {

    public Shirt(int HP, int maxHP, int Defense, int Evade, boolean isEquipped) {
        super(HP, maxHP, Defense, Evade, isEquipped);
        name = "Cloth Shirt";
        id = Item.Shirt;
        image = ImageBank.itemsTiles.getSubImage(112, 0, 16, 16);
        image.setFilter(Image.FILTER_NEAREST);
    }
}
