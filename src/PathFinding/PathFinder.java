package PathFinding;

import TileSystem.TileMap;

/**
 * Created by Ollie on 27/04/14.
 */
public class PathFinder {

    public static final byte onOpenList = 1;
    public static final byte onClosedList = 2;

    public static final byte walkable = 0;
    public static final byte unwalkable = 1;

    public static final byte found = 0;
    public static final byte nonexistent = 1;

    public static TileMap map;
    public static byte[][] tmp;

    public static void setMap(TileMap map){
        PathFinder.map = map;
        tmp = new byte[map.width][map.height];
        for(int x = 0; x < tmp.length; x++){
            for(int y = 0; y < tmp[0].length; y++){
                if(map.getTileSolid(x, y)){
                    tmp[x][y] = unwalkable;
                }else{
                    tmp[x][y] = walkable;
                }
            }
        }
    }

    public static Path findPath(int sx, int sy, int dx, int dy, boolean calcEnemies){

        if(calcEnemies){
            for(int x = 0; x < tmp.length; x++){
                for(int y = 0; y < tmp[0].length; y++){
                    if(map.getTileEnemy(x, y))
                        tmp[x][y] = 1;
                }
            }
        }else{
            for(int x = 0; x < tmp.length; x++){
                for(int y = 0; y < tmp[0].length; y++){
                    if(map.getTileEnemy(x, y))
                        tmp[x][y] = 0;
                }
            }
        }

        int parentXval = 0, parentYval = 0,
        a = 0, b = 0, m = 0, u = 0, v = 0, temp = 0, corner = 0,numberOfOpenListItems = 0,
        tempGcost = 0, path = 0,
        tempx, pathX, pathY, cellPosition,
        newOpenListItemID = 0;

        int[] openList = new int[map.width*map.height+2];
        byte[][] whichList = new byte[map.width+1][map.height+1];
        int[] openX= new int[map.width*map.height+2];
        int[] openY= new int[map.width*map.height+2];
        int[][] parentX= new int[map.width+1][map.height+1];
        int[][] parentY= new int[map.width+1][map.height+1];
        int[] Fcost= new int[map.width*map.height+2];
        int[][] Gcost= new int[map.width+1][map.height+1];
        int[] Hcost= new int[map.width*map.height+2];

        for(int x = 0; x < map.width; x++){
            for(int y = 0; y < map.height; y++){
                whichList[x][y] = 0;
            }
        }
        Gcost[sx][sy] = 0;

        numberOfOpenListItems = 1;
        openList[1] = 1;
        openX[1] = sx; openY[1] = sy;

        do{
            if(numberOfOpenListItems != 0){
                parentXval = openX[openList[1]];
                parentYval = openY[openList[1]];
                whichList[parentXval][parentYval] = onClosedList;

                numberOfOpenListItems--;
                openList[1] = openList[numberOfOpenListItems+1];
                v = 1;

                while(true){
                    u = v;
                    if(2*u+1 <= numberOfOpenListItems){
                        if(Fcost[openList[u]] >= Fcost[openList[2*u]])
                            v = 2*u;
                        if (Fcost[openList[v]] >= Fcost[openList[2*u+1]])
                            v = 2*u+1;
                    }else{
                        if (2*u <= numberOfOpenListItems)
                        {
                            if (Fcost[openList[u]] >= Fcost[openList[2*u]])
                                v = 2*u;
                        }
                    }
                    if (u != v){
                        temp = openList[u];
                        openList[u] = openList[v];
                        openList[v] = temp;
                    }else{
                        break;
                    }
                }

                for(b = parentYval-1; b <= parentYval+1; b++){
                    for(a = parentXval-1; a <= parentXval+1; a++){
                        if(a != -1 && b != -1 && a != map.width && b != map.height){
                            if(whichList[a][b] != onClosedList){
                                if(tmp[a][b] != unwalkable){
                                    if(whichList[a][b] != onOpenList){
                                        newOpenListItemID = newOpenListItemID + 1;
                                        m = numberOfOpenListItems+1;
                                        openList[m] = newOpenListItemID;
                                        openX[newOpenListItemID] = a;
                                        openY[newOpenListItemID] = b;

                                        Gcost[a][b] = Gcost[parentXval][parentYval] + 1;
                                        Hcost[openList[m]] = Math.abs(a - dx)+Math.abs(b - dy);
                                        Fcost[openList[m]] = Gcost[a][b] + Hcost[openList[m]];
                                        parentX[a][b] = parentXval; parentY[a][b] = parentYval;

                                        while (m != 1)
                                        {
                                            if (Fcost[openList[m]] <= Fcost[openList[m/2]])
                                            {
                                                temp = openList[m/2];
                                                openList[m/2] = openList[m];
                                                openList[m] = temp;
                                                m = m/2;
                                            }
                                            else
                                                break;
                                        }
                                        numberOfOpenListItems = numberOfOpenListItems+1;
                                        whichList[a][b] = onOpenList;
                                    }else{
                                        tempGcost = Gcost[parentXval][parentYval] + 1;

                                        if(tempGcost < Gcost[a][b]){
                                            parentX[a][b] = parentXval;
                                            parentY[a][b] = parentYval;
                                            Gcost[a][b] = tempGcost;

                                            for(int x = 1; x <= numberOfOpenListItems; x++){
                                                if(openX[openList[x]] == a && openY[openList[x]] == b){
                                                    Fcost[openList[x]] = Gcost[a][b] + Hcost[openList[x]];

                                                    m = x;
                                                    while(m != 1){
                                                        if(Fcost[openList[m]] < Fcost[openList[m/2]]){
                                                            temp = openList[m/2];
                                                            openList[m/2] = openList[m];
                                                            openList[m] = temp;
                                                            m = m/2;
                                                        }else
                                                            break;
                                                    }
                                                    break;
                                                }
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }else{
                path = nonexistent;
                break;
            }

            if(whichList[dx][dy] == onOpenList){
                path = found;
                break;
            }
        }while(true);

        Path p = new Path();
        if(path == found){
            pathX = dx; pathY = dy;

            do{
                p.addNode(pathX, pathY);
                tempx = parentX[pathX][pathY];
                pathY = parentY[pathX][pathY];
                pathX = tempx;
            }while(pathX != sx || pathY != sy);
        }
        return p;
    }
}
