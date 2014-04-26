package Core;

import org.newdawn.slick.Image;

/**
 * Created by Ollie on 26/04/14.
 */
public class Object {

    public int x, y;
    public boolean isCollectable = false;
    public Image image;

    public Object(int x, int y, boolean isCollectable, Image image){
        this.x = x;
        this.y = y;
        this.isCollectable = isCollectable;
        this.image = image;
    }
}
