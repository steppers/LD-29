package Items;

import Core.*;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 26/04/14.
 */
public class Item extends Core.Object {

    public boolean isHeld = false;
    public boolean isEquipped = false;

    public enum ItemType{
        WEAPON,
        ARMOUR,
        POTION,
        JEWELRY
    }

    public Item(int x, int y, Image image) {
        super(x, y, true, image);
    }
}
