package Core;

import PathFinding.Path;
import PathFinding.PathFinder;
import TileSystem.Tile;
import TileSystem.TileMap;
import TileSystem.Tiles.*;

import java.util.Random;

/**
 * Created by Ollie on 26/04/14.
 */
public class DungeonGenerator {

    private static Random rand;

    private static TileMap map;
    private static Tile[][] tiles;

    private static Tile.TileType floor;
    private static Tile.TileType wall;
    private static Tile.TileType door;
    private static Tile.TileType openDoor;
    private static Tile.TileType StairsUp;
    private static Tile.TileType StairsDown;

    public enum DungeonType{
        PRISON,
        DWARVEN,
        UNDEAD,
    }

    public static TileMap CreateDungeon(int width, int height, DungeonType type, int difficulty){
        rand = new Random(System.nanoTime());
        map = new TileMap(width, height, Config.textureResolution);
        tiles = new Tile[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                tiles[x][y] = new Tile(Tile.TileType.EMPTY, false);
            }
        }

        genDungeon();

        map.setTiles(tiles);
        PathFinder.setMap(map);
        int x, y;
        while(true){
            x = rand.nextInt(tiles.length);
            y = rand.nextInt(tiles[0].length);
            if(tiles[x][y].type == Tile.TileType.STONE_FLOOR){
                Path p = PathFinder.findPath(20, 17, x, y, false);
                if(p.length() > 20){
                    tiles[x][y] = new StairsDown();
                    break;
                }
            }
        }
        map.setTiles(tiles);
        return map;
    }

    private static void genDungeon() {
        genRoom(20, 20, 6, 5, 0, false);
        int r;
        for(int i = 0; i < 200; i++){
            r = rand.nextInt(100);
            if(r>=0 && r<=40){//Corridors
                int[] pos = findWall();
                genCorridor(pos[0], pos[1], rand.nextInt(5)+3, pos[2]);
            }
            if(r>40 && r<=100){//Rooms
                int[] pos = findWall();
                genRoom(pos[0], pos[1], rand.nextInt(4)+3, rand.nextInt(4)+3, pos[2], true);
            }
        }

        for(int x = 0; x < tiles.length; x++){
            for(int y = 0; y < tiles[0].length; y++){
                if(!tiles[x][y].isSolid && tiles[x][y].type != Tile.TileType.EMPTY){
                    int c = 0;
                    int d = 0;
                    for(int i = 0; i < 4; i++){
                        if(!tiles[x-1][y].isSolid){
                            c++; d = 4;
                        }
                        if(!tiles[x+1][y].isSolid){
                            c++; d = 3;
                        }
                        if(!tiles[x][y-1].isSolid){
                            c++; d = 1;
                        }
                        if(!tiles[x][y+1].isSolid){
                            c++; d = 2;
                        }
                        if(c == 1){
                            linkCorridor(x, y, d);
                        }
                    }
                }
            }
        }

        tiles[19][17] = new StairsUp();
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
                    if(!tiles[posX][posY+2].isSolid)
                        tiles[posX][posY] = new StoneDoor();
                    else
                        tiles[posX][posY] = new StoneFloor();
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x==minx || x == maxx || y == miny){
                                tiles[x][y] = new StoneWall();
                            }else{
                                tiles[x][y] = new StoneFloor();
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
                    if(!tiles[posX][posY-2].isSolid)
                        tiles[posX][posY] = new StoneDoor();
                    else
                        tiles[posX][posY] = new StoneFloor();
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x==minx || x == maxx || y == maxy){
                                tiles[x][y] = new StoneWall();
                            }else{
                                tiles[x][y] = new StoneFloor();
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
                    if(!tiles[posX-2][posY].isSolid)
                        tiles[posX][posY] = new StoneDoor();
                    else
                        tiles[posX][posY] = new StoneFloor();
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x == maxx || y == maxy || y == miny){
                                tiles[x][y] = new StoneWall();
                            }else{
                                tiles[x][y] = new StoneFloor();
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
                    if(!tiles[posX+2][posY].isSolid)
                        tiles[posX][posY] = new StoneDoor();
                    else
                        tiles[posX][posY] = new StoneFloor();
                    for(int x = minx; x < maxx+1; x++){
                        for(int y = miny; y < maxy+1; y++){
                            if(x == minx || y == maxy || y == miny){
                                tiles[x][y] = new StoneWall();
                            }else{
                                tiles[x][y] = new StoneFloor();
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
                minx = posX-(width/2);
                maxx = minx+width;
                miny = posY-height;
                maxy = posY-1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    for(int x = minx-1; x < maxx+2; x++){
                        for(int y = miny-1; y < maxy+2; y++){
                            if(x==minx-1 || x == maxx+1 || y == miny-1 || y == maxy+1){
                                tiles[x][y] = new StoneWall();
                            }else{
                                tiles[x][y] = new StoneFloor();
                            }
                        }
                    }
                    if(door){
                        tiles[posX][posY] = new StoneDoor();
                    }
                }
                break;
            //south
            case 1:
                minx = posX-(width/2);
                maxx = minx+width-1;
                miny = posY+1;
                maxy = posY+height;
                if(isEmpty(minx, miny, maxx, maxy)){
                    for(int x = minx-1; x < maxx+2; x++){
                        for(int y = miny-1; y < maxy+2; y++){
                            if(x==minx-1 || x == maxx+1 || y == miny-1 || y == maxy+1){
                                tiles[x][y] = new StoneWall();
                            }else{
                                tiles[x][y] = new StoneFloor();
                            }
                        }
                    }
                    if(door){
                        tiles[posX][posY] = new StoneDoor();
                    }
                }
                break;
            //east
            case 2:
                minx = posX+1;
                maxx = posX+width;
                miny = posY-(height/2);
                maxy = miny+height;
                if(isEmpty(minx, miny, maxx, maxy)){
                    for(int x = minx-1; x < maxx+2; x++){
                        for(int y = miny-1; y < maxy+2; y++){
                            if(x==minx-1 || x == maxx+1 || y == miny-1 || y == maxy+1){
                                tiles[x][y] = new StoneWall();
                            }else{
                                tiles[x][y] = new StoneFloor();
                            }
                        }
                    }
                    if(door){
                        tiles[posX][posY] = new StoneDoor();
                    }
                }
                break;
            //west
            case 3:
                minx = posX-width;
                maxx = posX-1;
                miny = posY-(height/2);
                maxy = miny+height-1;
                if(isEmpty(minx, miny, maxx, maxy)){
                    for(int x = minx-1; x < maxx+2; x++){
                        for(int y = miny-1; y < maxy+2; y++){
                            if(x==minx-1 || x == maxx+1 || y == miny-1 || y == maxy+1){
                                tiles[x][y] = new StoneWall();
                            }else{
                                tiles[x][y] = new StoneFloor();
                            }
                        }
                    }
                    if(door){
                        tiles[posX][posY] = new StoneDoor();
                        tiles[posX-1][posY] = new StoneFloor();
                    }
                }
                break;
        }
    }

    private static void linkCorridor(int posX, int posY, int dir){
        for(int i = 0; i < 4; i++){
            switch(i){
                case 1:
                    if(dir != 1)
                        for(int o = 1; o < 6; o++){
                            if(posY-o == 0)
                                break;
                            if(!tiles[posX][posY-o].isSolid && tiles[posX][posY-o].type != Tile.TileType.EMPTY){
                                for(int a = 1; a < o; a++){
                                    tiles[posX][posY-a] = new StoneFloor();
                                    tiles[posX-1][posY-a] = new StoneWall();
                                    tiles[posX+1][posY-a] = new StoneWall();
                                }
                                tiles[posX][posY-o+1] = new StoneDoor();
                                return;
                            }
                        }
                    break;
                case 2:
                    if(dir != 2)
                        for(int o = 1; o < 6; o++){
                            if(posY+o == tiles[0].length)
                                break;
                            if(!tiles[posX][posY+o].isSolid && tiles[posX][posY+o].type != Tile.TileType.EMPTY){
                                for(int a = 1; a < o; a++){
                                    tiles[posX][posY+a] = new StoneFloor();
                                    tiles[posX-1][posY+a] = new StoneWall();
                                    tiles[posX+1][posY+a] = new StoneWall();
                                }
                                tiles[posX][posY+o-1] = new StoneDoor();
                                return;
                            }
                        }
                    break;
                case 3:
                    if(dir != 3)
                        for(int o = 1; o < 6; o++){
                            if(posX+o == tiles.length)
                                break;
                            if(!tiles[posX+o][posY].isSolid && tiles[posX+o][posY].type != Tile.TileType.EMPTY){
                                for(int a = 1; a < o; a++){
                                    tiles[posX+a][posY] = new StoneFloor();
                                    tiles[posX+a][posY-1] = new StoneWall();
                                    tiles[posX+a][posY+1] = new StoneWall();
                                }
                                tiles[posX+o-1][posY] = new StoneDoor();
                                return;
                            }
                        }
                    break;
                case 4:
                    if(dir != 4)
                        for(int o = 1; o < 6; o++){
                            if(posX-o == tiles.length)
                                break;
                            if(!tiles[posX-o][posY].isSolid && tiles[posX-o][posY].type != Tile.TileType.EMPTY){
                                for(int a = 1; a < o; a++){
                                    tiles[posX-a][posY] = new StoneFloor();
                                    tiles[posX-a][posY-1] = new StoneWall();
                                    tiles[posX-a][posY+1] = new StoneWall();
                                }
                                tiles[posX-o+1][posY] = new StoneDoor();
                                return;
                            }
                        }
                    break;
            }
        }
    }

    private static boolean isEmpty(int x, int y, int x2, int y2){
        if(x-1<0 || x2+1>=tiles.length || y-1<0 || y2+1>=tiles[0].length)
            return false;

        for(int posX = x; posX < x2+1; posX++){
            for(int posY = y; posY < y2+1; posY++){
                if(tiles[posX][posY].isSolid){
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
            if(tiles[pos[0]][pos[1]].type == Tile.TileType.STONE_WALL){
                if(pos[0] < tiles.length-1 && pos[0] > 0 && pos[1] < tiles[0].length-1 && pos[1] > 0){
                    if(tiles[pos[0]-1][pos[1]].type == Tile.TileType.STONE_WALL && tiles[pos[0]+1][pos[1]].type == Tile.TileType.STONE_WALL){
                        if(tiles[pos[0]][pos[1]+1].type == Tile.TileType.EMPTY && tiles[pos[0]][pos[1]-1].type == Tile.TileType.STONE_FLOOR){
                            pos[2] = 1;
                            return pos;
                        }
                        if(tiles[pos[0]][pos[1]-1].type == Tile.TileType.EMPTY && tiles[pos[0]][pos[1]+1].type == Tile.TileType.STONE_FLOOR){
                            pos[2] = 0;
                            return pos;
                        }
                    }
                    if(tiles[pos[0]][pos[1]-1].type == Tile.TileType.STONE_WALL && tiles[pos[0]][pos[1]+1].type == Tile.TileType.STONE_WALL){
                        if(tiles[pos[0]+1][pos[1]].type == Tile.TileType.EMPTY && tiles[pos[0]-1][pos[1]].type == Tile.TileType.STONE_FLOOR){
                            pos[2] = 2;
                            return pos;
                        }
                        if(tiles[pos[0]-1][pos[1]].type == Tile.TileType.EMPTY && tiles[pos[0]+1][pos[1]].type == Tile.TileType.STONE_FLOOR){
                            pos[2] = 3;
                            return pos;
                        }
                    }
                }
            }
        }
    }

    private boolean isInRoom(int x, int y){
        return false;
    }
}
