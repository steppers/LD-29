package TileSystem;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;

/**
 * Created by Ollie on 26/04/14.
 */
public class TileMap {
    public int width, height, resolution;

    private Tile[][] tiles;

    private Image textures;

    public TileMap(int width, int height, int resolution){
        this.resolution = resolution;
        this.width = width;
        this.height = height;
        tiles = new Tile[width][height];
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
                switch(tiles[x][y].type){
                    case EMPTY:
                        break;
                    case STONE_WALL:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, 0, 0, resolution, resolution);
                        break;
                    case DIRT_WALL:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution, 0, resolution+resolution, resolution);
                        break;
                    case WOOD_WALL:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*2, 0, resolution+resolution*2, resolution);
                        break;
                    case STONE_DOOR:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*3, 0, resolution+resolution*3, resolution);
                        break;
                    case STONE_DOOR_OPEN:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*4, 0, resolution+resolution*4, resolution);
                        break;
                    case STONE_FLOOR:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*5, 0, resolution+resolution*5, resolution);
                        break;
                    case WATER_FLOOR:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*6, 0, resolution+resolution*6, resolution);
                        break;
                    case WOOD_FLOOR:
                        g.drawImage(textures, (x*resolution*scale)+xOffset, (y*resolution*scale)+yOffset, (x*resolution*scale)+xOffset+resolution*scale, (y*resolution*scale)+yOffset+resolution*scale, resolution*7, 0, resolution+resolution*7, resolution);
                        break;
                    case DIRT_FLOOR:
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

    public void setTiles(Tile[][] tiles){
        this.tiles = tiles;
    }

    public boolean isEmpty(Rectangle r){
        for(int x = (int)r.getMinX(); x < r.getMaxX(); x++){
            for(int y = (int)r.getMinY(); x < r.getMaxY(); y++){
                if(tiles[x][y].type != Tile.TileType.EMPTY)
                    return false;
            }
        }
        return true;
    }

    public Tile getTile(int x, int y){
        if(x>=0 && x < width && y >= 0 && y < height)
            return tiles[x][y];
        else
            return null;
    }

    public Tile.TileType getTileType(int x, int y){
        if(x>=0 && x < width && y >= 0 && y < height)
            return tiles[x][y].type;
        else
            return null;
    }

    public boolean getTileSolid(int x, int y){
        return tiles[x][y].isSolid;
    }

    public boolean getTileEnemy(int x, int y){
        return tiles[x][y].hasEnemy;
    }

    public void swapHasEnemy(int x, int y){
        tiles[x][y].hasEnemy = !tiles[x][y].hasEnemy;
    }
}
