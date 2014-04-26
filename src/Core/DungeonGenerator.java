package Core;

/**
 * Created by Ollie on 26/04/14.
 */
public class DungeonGenerator {

    public enum DungeonType{
        PRISON,
        DWARVEN,
        UNDEAD,
    }

    public static TileMap CreateDungeon(int width, int height, DungeonType type, int difficulty){
        TileMap map = new TileMap(width, height, Config.textureResolution);
        TileMap.TileType[][] tiles = new TileMap.TileType[20][20];
        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                tiles[x][y] = TileMap.TileType.EMPTY;
            }
        }

        switch(type){
            case PRISON:
                generatePrison(tiles);
                break;
        }

        map.setTiles(tiles);
        return map;
    }
}
