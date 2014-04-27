package Core;

/**
 * Created by Ollie on 27/04/14.
 */
public class CellSystem {

    public static Cell[][] cells;

    public static void setSize(int width, int height){
        cells = new Cell[width][height];
    }

    public static void init(){
        for(int x = 0; x < cells.length; x++){
            for(int y = 0; y < cells[0].length; y++){
                cells[x][y] = new Cell();
            }
        }
    }
}

