package Items;

import Core.*;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 26/04/14.
 */
public class Item extends Core.Object {

    public boolean isHeld = false;
    public boolean isEquipped = false;
    public boolean isStackable = true;
    public int quantity = 1;

    public Item(int x, int y) {
        super(x, y, true);
    }
}
