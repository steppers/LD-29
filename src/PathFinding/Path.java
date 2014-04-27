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
        return path.get(path.size()-1);
    }

    public void removeLastNode(){
        path.remove(path.size()-1);
    }

    public int length(){
        return path.size();
    }
}
