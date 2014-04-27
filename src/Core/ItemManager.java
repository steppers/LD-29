package Core;

import Enemies.Enemy;
import Enemies.EnemyManager;
import Items.Item;
import Items.Sword;
import Items.Weapon;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;

/**
 * Created by Ollie on 27/04/14.
 */
public class ItemManager {

    public static ArrayList<Item> levelItems = new ArrayList<Item>();
    public static ArrayList<Item> inventoryItems = new ArrayList<Item>();
    public static Weapon playerWeapon = new Sword(0, 0, 5, 0, 1, 0);
    public static final int maxInventory = 18;

    public static boolean useItem(Item item, int x, int y, EnemyManager em){
        if(item == playerWeapon){
            Enemy e = em.getEnemy(x, y);
            if(e != null){
                e.stats.HP -= playerWeapon.stats.Attack;
                e.state = 
                if(e.stats.HP <= 0)
                    em.removeEnemy(e);
                return false;
            }
            return true;
        }
        return false;
    }

    public static void PickupItem(Item item){
        if(inventoryItems.contains(item) && item.isStackable){
            int i = inventoryItems.indexOf(item);
            inventoryItems.get(i).quantity++;
            levelItems.remove(item);
        }else if(inventoryItems.contains(item) && inventoryItems.size() < maxInventory){
            inventoryItems.add(item);
            levelItems.remove(item);
        }else if(inventoryItems.size() >= maxInventory){
            return;
        }
    }

    public static void DropItem(Item item, int x, int y){
        inventoryItems.remove(item);
        item.x = x;
        item.y = y;
        levelItems.add(item);
    }

    public static void renderItems(Graphics g, float xOffset, float yOffset, float scale){
        playerWeapon.image.draw(740, 542, 48, 48);
        for(Item i : levelItems){
            i.renderLevelItem(g, xOffset, yOffset, scale);
        }
    }

}
