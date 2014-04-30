package TileSystem;

import Items.Item;

import java.util.ArrayList;

/**
 * Created by Ollie on 30/04/14.
 */
public class Tile {

    public enum TileType{
        EMPTY,
        DIRT_WALL,
        STONE_WALL,
        WOOD_WALL,
        STONE_DOOR,
        STONE_DOOR_OPEN,
        STAIRS_UP,
        STAIRS_DOWN,
        STONE_FLOOR,
        WATER_FLOOR,
        WOOD_FLOOR,
        DIRT_FLOOR
    }

    public TileType type;
    public ArrayList<Item> items;
    public boolean hasEnemy = false;
    public boolean isSolid = false;

    public Tile(TileType type, boolean isSolid){
        this.isSolid = isSolid;
        this.type = type;
        items = new ArrayList<Item>();
    }

}
