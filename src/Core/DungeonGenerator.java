package Core;

import org.newdawn.slick.geom.Rectangle;

import java.util.Random;

/**
 * Created by Ollie on 26/04/14.
 */
public class DungeonGenerator {

    private static Random rand;

    private static TileMap map;
    private static TileMap.TileType[][] tiles;

    private static TileMap.TileType floor;
    private static TileMap.TileType wall;
    private static TileMap.TileType door;
    private static TileMap.TileType openDoor;

    public enum DungeonType{
        PRISON,
        DWARVEN,
        UNDEAD,
    }

    public static TileMap CreateDungeon(int width, int height, DungeonType type, int difficulty){
        rand = new Random(System.nanoTime());
        map = new TileMap(width, height, Config.textureResolution);
        CellSystem.setSize(width, height);
        CellSystem.init();
        tiles = new TileMap.TileType[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                tiles[x][y] = TileMap.TileType.EMPTY;
            }
        }

        genDungeon();

        map.setTiles(tiles);
        return map;
    }

    private static void genDungeon() {
        genRoom(20, 20, 6, 5, 0, false);
        int r;
        for(int i = 0; i < 400; i++){
            r = rand.nextInt(100);
            if(r>=0 && r<=35){//Corridors
                int[] pos = findWall();
                genCorridor(pos[0], pos[1], rand.nextInt(6)+5, pos[2]);
            }
            if(r>35 && r<=100){//Rooms
                int[] pos = findWall();
                genRoom(pos[0], pos[1], rand.nextInt(6)+2, rand.nextInt(6)+2, pos[2], true);
            }
        }
    }

    private static void genCorridor(int posX, int posY, int len, int dir){
        int minx, maxx, miny, maxy;
        switch(dir){
            //north
            case 0:
                minx = posX-1;
                maxx = posX+1;
                miny = posY-len-1;
                maxy = posY-1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    tiles[posX][posY] = TileMap.TileType.DOOR_STONE;
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x==minx || x == maxx || y == miny){
                                tiles[x][y] = TileMap.TileType.STONE;
                            }else{
                                tiles[x][y] = TileMap.TileType.FLOOR_STONE;
                            }
                        }
                    }
                }
                break;
            //south
            case 1:
                minx = posX-1;
                maxx = posX+1;
                miny = posY+1;
                maxy = posY+len+1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    tiles[posX][posY] = TileMap.TileType.DOOR_STONE;
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x==minx || x == maxx || y == maxy){
                                tiles[x][y] = TileMap.TileType.STONE;
                            }else{
                                tiles[x][y] = TileMap.TileType.FLOOR_STONE;
                            }
                        }
                    }
                }
                break;
            //east
            case 2:
                minx = posX+1;
                maxx = posX+len+1;
                miny = posY-1;
                maxy = posY+1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    tiles[posX][posY] = TileMap.TileType.DOOR_STONE;
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x == maxx || y == maxy || y == miny){
                                tiles[x][y] = TileMap.TileType.STONE;
                            }else{
                                tiles[x][y] = TileMap.TileType.FLOOR_STONE;
                            }
                        }
                    }
                }
                break;
            //west
            case 3:
                minx = posX-len-1;
                maxx = posX-1;
                miny = posY-1;
                maxy = posY+1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    tiles[posX][posY] = TileMap.TileType.DOOR_STONE;
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x == minx || y == maxy || y == miny){
                                tiles[x][y] = TileMap.TileType.STONE;
                            }else{
                                tiles[x][y] = TileMap.TileType.FLOOR_STONE;
                            }
                        }
                    }
                }
                break;
        }
    }

    private static void genRoom(int posX, int posY, int width, int height, int dir, boolean door){
        int minx, maxx, miny, maxy;
        switch(dir){
            //north
            case 0:
                minx = posX-(width/2)-1;
                maxx = minx+width+1;
                miny = posY-height-1;
                maxy = posY-1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x==minx || x == maxx || y == miny || y == maxy){
                                tiles[x][y] = TileMap.TileType.STONE;
                            }else{
                                tiles[x][y] = TileMap.TileType.FLOOR_STONE;
                            }
                        }
                    }
                    if(door){
                        tiles[posX][posY] = TileMap.TileType.DOOR_STONE;
                        tiles[posX][posY-1] = TileMap.TileType.FLOOR_STONE;
                    }
                }
                break;
            //south
            case 1:
                minx = posX-(width/2)-1;
                maxx = minx+width+1;
                miny = posY+1;
                maxy = posY+height+1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x==minx || x == maxx || y == miny || y == maxy){
                                tiles[x][y] = TileMap.TileType.STONE;
                            }else{
                                tiles[x][y] = TileMap.TileType.FLOOR_STONE;
                            }
                        }
                    }
                    if(door){
                        tiles[posX][posY] = TileMap.TileType.DOOR_STONE;
                        tiles[posX][posY+1] = TileMap.TileType.FLOOR_STONE;
                    }
                }
                break;
            //east
            case 2:
                minx = posX+1;
                maxx = posX+width+1;
                miny = posY-(height/2)-1;
                maxy = miny+height+1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x==minx || x == maxx || y == miny || y == maxy){
                                tiles[x][y] = TileMap.TileType.STONE;
                            }else{
                                tiles[x][y] = TileMap.TileType.FLOOR_STONE;
                            }
                        }
                    }
                    if(door){
                        tiles[posX][posY] = TileMap.TileType.DOOR_STONE;
                        tiles[posX+1][posY] = TileMap.TileType.FLOOR_STONE;
                    }
                }
                break;
            //west
            case 3:
                minx = posX-width-1;
                maxx = posX-1;
                miny = posY-(height/2)-1;
                maxy = miny+height+1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x==minx || x == maxx || y == miny || y == maxy){
                                tiles[x][y] = TileMap.TileType.STONE;
                            }else{
                                tiles[x][y] = TileMap.TileType.FLOOR_STONE;
                            }
                        }
                    }
                    if(door){
                        tiles[posX][posY] = TileMap.TileType.DOOR_STONE;
                        tiles[posX-1][posY] = TileMap.TileType.FLOOR_STONE;
                    }
                }
                break;
        }
    }

    private static boolean isEmpty(int x, int y, int x2, int y2){
        for(int posX = x; posX < x2+1; posX++){
            for(int posY = y; posY < y2+1; posY++){
                if(posX<0 || posX>=tiles.length || posY<0 || posY>=tiles[0].length){
                    return false;
                }else if(tiles[posX][posY] != TileMap.TileType.EMPTY){
                    return false;
                }
            }
        }
        return true;
    }

    private static int[] findWall(){
        int pos[] = new int[3];
        while(true){
            pos[0] = rand.nextInt(tiles.length-1)+1;
            pos[1] = rand.nextInt(tiles[0].length-1)+1;
            if(tiles[pos[0]][pos[1]] == TileMap.TileType.STONE){
                if(pos[0] < tiles.length-1 && pos[0] > 0 && pos[1] < tiles[0].length-1 && pos[1] > 0){
                    if(tiles[pos[0]-1][pos[1]] == TileMap.TileType.STONE && tiles[pos[0]+1][pos[1]] == TileMap.TileType.STONE){
                        if(tiles[pos[0]][pos[1]+1] == TileMap.TileType.EMPTY && tiles[pos[0]][pos[1]-1] == TileMap.TileType.FLOOR_STONE){
                            pos[2] = 1;
                            return pos;
                        }
                        if(tiles[pos[0]][pos[1]-1] == TileMap.TileType.EMPTY && tiles[pos[0]][pos[1]+1] == TileMap.TileType.FLOOR_STONE){
                            pos[2] = 0;
                            return pos;
                        }
                    }
                    if(tiles[pos[0]][pos[1]-1] == TileMap.TileType.STONE && tiles[pos[0]][pos[1]+1] == TileMap.TileType.STONE){
                        if(tiles[pos[0]+1][pos[1]] == TileMap.TileType.EMPTY && tiles[pos[0]-1][pos[1]] == TileMap.TileType.FLOOR_STONE){
                            pos[2] = 2;
                            return pos;
                        }
                        if(tiles[pos[0]-1][pos[1]] == TileMap.TileType.EMPTY && tiles[pos[0]+1][pos[1]] == TileMap.TileType.FLOOR_STONE){
                            pos[2] = 3;
                            return pos;
                        }
                    }
                }
            }
        }
    }
}
