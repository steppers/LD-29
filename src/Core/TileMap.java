package Core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Ollie on 26/04/14.
 */
public class TileMap {
    public int width, height, resolution;

    private TileType[][] tiles;

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
            textures.setFilter(Image.FILTER_NEAREST);
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
                    case STONE:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, 0, 0, resolution, resolution);
                        break;
                    case DIRT:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution, 0, resolution+resolution, resolution);
                        break;
                    case WOOD:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*2, 0, resolution+resolution*2, resolution);
                        break;
                    case DOOR_STONE:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*3, 0, resolution+resolution*3, resolution);
                        break;
                    case DOOR_STONE_OPEN:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*4, 0, resolution+resolution*4, resolution);
                        break;
                    case FLOOR_STONE:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*5, 0, resolution+resolution*5, resolution);
                        break;
                    case FLOOR_WATER:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*6, 0, resolution+resolution*6, resolution);
                        break;
                    case FLOOR_WOOD:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*7, 0, resolution+resolution*7, resolution);
                        break;
                    case FLOOR_DIRT:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, 0, resolution, resolution, resolution+resolution);
                        break;
                    case STAIRS_UP:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution, resolution, resolution*2, resolution+resolution);
                        break;
                    case STAIRS_DOWN:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*2, resolution, resolution*3, resolution+resolution);
                        break;
                }
            }
        }
    }

    public void setTiles(TileType[][] tiles){
        this.tiles = tiles;
    }

    public boolean isEmpty(Rectangle r){
        for(int x = (int)r.getMinX(); x < r.getMaxX(); x++){
            for(int y = (int)r.getMinY(); x < r.getMaxY(); y++){
                if(tiles[x][y] != TileType.EMPTY)
                    return false;
            }
        }
        return true;
    }

    public TileType getTile(int x, int y){
        if(x>=0 && x < width && y >= 0 && y < height)
            return tiles[x][y];
        else
            return null;
    }
}
