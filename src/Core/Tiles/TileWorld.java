package Core.Tiles;

import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Ollie on 26/04/14.
 */
public class TileWorld {

    private Chunk[][] chunks;

    public TileWorld(Vector2f size){
        chunks = new Chunk[(int)size.getX()][(int)size.getY()];
    }

    public void setChunk(Chunk in, int x, int y){
        chunks[x][y] = in;
    }

}
