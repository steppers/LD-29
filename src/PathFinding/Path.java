package PathFinding;

import java.util.ArrayList;

/**
 * Created by Ollie on 27/04/14.
 */
public class Path {
    private ArrayList<GridPos> path = new ArrayList<GridPos>();

    public void addNode(int x, int y){
        path.add(new GridPos(x, y));
    }

    public GridPos getNextNode(){
        if(path.size() <= 0)
            return null;
        GridPos p = path.get(path.size()-1);
        path.remove(p);
        return p;
    }
}
