package Core.Tiles;

/**
 * Created by Ollie on 26/04/14.
 */
public class Chunk {

    public Tile[][] tiles;

    public Chunk(Tile[][] in){
        tiles = in;
    }

    public void setTile(Tile in, int x, int y){
        tiles[x][y] = in;
    }

    public void setTiles(Tile[][] in){
        tiles = in;
    }

    public Tile getTile(int x, int y){
        return tiles[x][y];
    }

}
