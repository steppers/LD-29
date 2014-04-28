package Items;

import Core.*;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 26/04/14.
 */
public class Item extends Core.Object {

    public static final int Sword = 0;
    public static final int LongSword = 1;
    public static final int Dagger = 2;
    public static final int HealthPotion = 3;
    public static final int LeatherArmour = 4;
    public static final int IronArmour = 5;
    public static final int ScaleArmour = 6;
    public static final int Shirt = 7;

    public static final int WeaponType = 0;
    public static final int ArmourType = 1;
    public static final int JewelryType = 2;

    public boolean isHeld = false;
    public boolean isEquipable = false;
    public boolean isEquipped = false;
    public boolean isStackable = true;
    public int quantity = 1;
    public int id;
    public int type;
    public String name;
    public Stats stats;

    public Item(int x, int y) {
        super(x, y, true);
    }
}
