package Items;

import Core.AudioBank;
import Core.Player;
import Core.TurnManager;
import Enemies.Enemy;
import Enemies.EnemyManager;
import GUI.GUI;
import GUI.GUIStatPopup;
import Items.Armour.*;
import Items.Potions.HealthPotion;
import Items.Weapons.Dagger;
import Items.Weapons.Longsword;
import Items.Weapons.Sword;
import Items.Weapons.Weapon;
import TileSystem.Tile;
import TileSystem.TileMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ollie on 27/04/14.
 */
public class ItemManager {

    public static ArrayList<Item> levelItems = new ArrayList<Item>();
    public static ArrayList<Item> inventoryItems = new ArrayList<Item>();
    public static Item playerWeapon = new Dagger(0, 0, 4, 1, true);
    public static Item playerArmour = new Shirt(0, 0, 1, 1, true);
    public static Item playerJewel1 = null;
    public static Item playerJewel2 = null;
    public static final int maxInventory = 16;

    private static Random r = new Random(System.nanoTime());

    public static void reset(){
        playerWeapon = new Dagger(0, 0, 4, 1, true);
        playerArmour = new Shirt(0, 0, 1, 1, true);
        playerJewel1 = null;
        playerJewel2 = null;
        inventoryItems.clear();
        levelItems.clear();
    }

    public static boolean useItem(Item item, int x, int y, EnemyManager em, Player player, TurnManager tm){
        if(item == playerWeapon){
            Enemy e = em.getEnemy(x, y);
            if(e != null){
                if(playerWeapon != null){
                    float dodge = r.nextInt(e.stats.Evade+7);
                    if(dodge < 8){
                        int damage = r.nextInt(Math.abs(playerWeapon.stats.Attack - e.stats.Defense/2)+1)+playerWeapon.stats.Attack/2;
                        e.stats.HP -= damage;
                        GUI.addComponent(new GUIStatPopup(e.posX, e.posY-1,"-"+damage, Color.orange), 1.5f);
                        AudioBank.playEffect(AudioBank.Hit1);
                    }else{
                        GUI.addComponent(new GUIStatPopup(e.posX, e.posY-1,"Dodge", Color.orange), 1.5f);
                    }
                }else{
                    int damage = r.nextInt(1)+1;
                    float dodge = r.nextInt(e.stats.Evade+7);
                    if(dodge < 4){
                        e.stats.HP -= damage;
                        GUI.addComponent(new GUIStatPopup(e.posX, e.posY-1,"-"+1, Color.orange), 1.5f);
                        AudioBank.playEffect(AudioBank.Hit1);
                    }else{
                        GUI.addComponent(new GUIStatPopup(e.posX, e.posY-1,"Dodge", Color.orange), 1.5f);
                    }
                }
                em.getEnemy(x, y).state = Enemy.AIState.ATTACKING;
                if(e.stats.HP <= 0){
                    GUI.addComponent(new GUIStatPopup(player.posX, player.posY-1,"+"+em.getEnemy(x, y).exp+" XP", Color.green), 1.5f);
                    player.exp += em.getEnemy(x, y).exp;
                    em.removeEnemy(e);
                }
                if(playerWeapon != null){
                    tm.addEnemyTurns(playerWeapon.stats.Speed);
                }else{
                    tm.addEnemyTurns(1);
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public static void PickupItem(Item item){
        if(item.isStackable){
            for(Item i : inventoryItems){
                if(i.id == item.id){
                    i.quantity++;
                    levelItems.remove(item);
                    AudioBank.playEffect(AudioBank.Pickup1);
                    return;
                }
            }
            if(inventoryItems.size() < maxInventory){
                inventoryItems.add(item);
                levelItems.remove(item);
                AudioBank.playEffect(AudioBank.Pickup1);
            }
        }else if(inventoryItems.size() < maxInventory){
            inventoryItems.add(item);
            levelItems.remove(item);
            AudioBank.playEffect(AudioBank.Pickup1);
        }else if(inventoryItems.size() >= maxInventory){
            GUI.addComponent(new GUIStatPopup(0, 0, "", Color.white), 1);
            return;
        }
    }

    public static boolean addEquippedToInventory(Item i){
        if(inventoryItems.size() < maxInventory){
            i.isEquipped = false;
            inventoryItems.add(i);
            AudioBank.playEffect(AudioBank.Pickup1);
            return true;
        }
        return false;
    }

    public static void Equip(Item i){
        inventoryItems.remove(i);
        switch(i.type){
            case Item.WeaponType:
                if(playerWeapon != null){
                    inventoryItems.add(playerWeapon);
                    playerWeapon.isEquipped = false;
                    playerWeapon = i;
                    playerWeapon.isEquipped = true;
                }else{
                    playerWeapon = i;
                    playerWeapon.isEquipped = true;
                }
                break;
            case Item.ArmourType:
                if(playerArmour != null){
                    inventoryItems.add(playerArmour);
                    playerArmour = i;
                    playerArmour.isEquipped = true;
                }else{
                    playerArmour = i;
                    playerArmour.isEquipped = true;
                }
                break;
            case Item.JewelryType:
                //TODO
                break;
        }
        AudioBank.playEffect(AudioBank.Pickup1);
    }

    public static void DropItem(Item item, int x, int y){
        item.isEquipped = false;
        inventoryItems.remove(item);
        item.x = x;
        item.y = y;
        levelItems.add(item);
    }

    public static void DropEnemyItem(Item item, int x, int y){
        item.x = x;
        item.y = y;
        levelItems.add(item);
    }

    public static Item getItem(int x, int y){
        for(Item i : levelItems){
            if(i.x == x && i.y == y)
                return i;
        }
        return null;
    }

    public static Item getInvItem(int i){
        if(i < inventoryItems.size())
            return inventoryItems.get(i);
        return null;
    }

    public static void renderItems(Graphics g, float xOffset, float yOffset, float scale){
        for(Item i : levelItems){
            i.renderLevelItem(g, xOffset, yOffset, scale);
        }
    }

    public static boolean InventoryIsFull(){
        if(inventoryItems.size() == maxInventory)
            return true;
        return false;
    }

    public static void ScatterItems(int level, int numItems, TileMap map){
        Random r = new Random(System.nanoTime());
        switch(level){
            case 1:
                for(int i = 0; i < numItems; i++){
                    while(true){
                        int x = r.nextInt(40);
                        int y = r.nextInt(40);
                        if(!map.getTileSolid(x, y) && map.getTileType(x, y) != Tile.TileType.EMPTY && map.getTileType(x, y) != Tile.TileType.STONE_DOOR){
                            float f = r.nextFloat();
                            if(f < 0.3f)
                                DropEnemyItem(new Dagger(0, 0, 4, 1, false), x, y);
                            else if(f >= 0.3f && f < 0.5f)
                                DropEnemyItem(new Sword(0, 0, 5, 1, false), x, y);
                            else if(f >= 0.5f && f < 0.6f)
                                DropEnemyItem(new Longsword(0, 0, 9, 2, false), x, y);
                            else if(f >= 0.6f && f < 0.85f)
                                DropEnemyItem(new HealthPotion(50, 0), x, y);
                            else if(f >= 0.85f && f < 0.9f)
                                DropEnemyItem(new LeatherArmour(0, 0, 3, 1, false), x, y);
                            else if(f >= 0.9f && f < 0.95f)
                                DropEnemyItem(new SteelArmour(0, 0, 5, 1, false), x, y);
                            else if(f >= 0.95f && f < 1f)
                                DropEnemyItem(new ScaleArmour(0, 0, 8, 1, false), x, y);
                            break;
                        }
                    }
                }
                break;
        }
    }

}
