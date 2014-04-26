package Core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Ollie on 26/04/14.
 */
public class TileMap {
    private int width, height, resolution;

    private TileType[][] tiles;

    public enum TileType{
        EMPTY,
        DIRT,
        STONE,
        WOOD,
        DOOR,
        FLOOR_STONE,
        FLOOR_WATER,
        FLOOR_WOOD,
        FLOOR_DIRT
    }

    private Image textures;

    public TileMap(int width, int height, int resolution){
        this.resolution = resolution;
        this.width = width;
        this.height = height;
        tiles = new TileType[width][height];
        InitTextureAtlas();
    }

    private void InitTextureAtlas() {
        try{
            textures = new Image("res/tex/tiles.png");
        }catch(SlickException e){
            System.err.println("Error: Cannot load tiles.png");
            e.printStackTrace();
        }
    }

    public void render(Graphics g, float xOffset, float yOffset, float scale){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                switch(tiles[x][y]){
                    case EMPTY:
                        break;
                    case DIRT:
                        textures.draw();
                        break;
                }
            }
        }
    }

    public void setTiles(TileType[][] tiles){
        this.tiles = tiles;
    }
}
