package Core.Tiles;

import Core.Perlin.PerlinNoise;
import org.newdawn.slick.geom.Vector2f;

import java.util.Random;

/**
 * Created by Ollie on 26/04/14.
 */
public class WorldGenerator {

    private static Random r;
    private static Vector2f size;

    public static void init(int chunksX, int chunksY){
        r = new Random();
        PerlinNoise.setSeed(r.nextLong());
        size = new Vector2f(chunksX, chunksY);
    }

    public static TileWorld generateWorld(){
        TileWorld world = new TileWorld(size);
        for(int x = 0; x < size.x; x++){
            for(int y = 0; y < size.y; y++){

                Tile[][] t = new Tile[64][64];
                for(int cx = 0; cx < 64; cx++){
                    for(int cy = 0; cy < 64; cy++){
                        float p = PerlinNoise.PerlinNoise_2D((x*size.x)+ cx, (y*size.y)+ cy);
                        if(p <0.5f){
                            t[cx][cy] = new Tile(1);
                        }else{
                            t[cx][cy] = new Tile(0);
                        }
                    }
                }
                world.setChunk(new Chunk(t), x, y);
            }
        }
        return world;
    }

}
