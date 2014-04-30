package TileSystem;

import Items.Item;

import java.util.ArrayList;

/**
 * Created by Ollie on 30/04/14.
 */
public class Tile {

    public enum TileType{
        EMPTY,
        DIRT,
        STONE,
        WOOD,
        DOOR_STONE,
        DOOR_STONE_OPEN,
        STAIRS_UP,
        STAIRS_DOWN,
        FLOOR_STONE,
        FLOOR_WATER,
        FLOOR_WOOD,
        FLOOR_DIRT
    }

    public TileType type;
    public ArrayList<Item> items;
    public boolean hasEnemy = false;

    public Tile(TileType type){
        this.type = type;
        items = new ArrayList<Item>();
    }

}
