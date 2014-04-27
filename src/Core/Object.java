package Core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 26/04/14.
 */
public class Object {

    public int x, y;
    public boolean isCollectable = false;
    public Image image;

    public Object(int x, int y, boolean isCollectable){
        this.x = x;
        this.y = y;
        this.isCollectable = isCollectable;
    }

    public void renderLevelItem(Graphics g, float xOffset, float yOffset, float scale){
        image.draw((x*16*scale)+xOffset, (y*16*scale)+yOffset, 16*scale, 16*scale);
    }

    public void renderEquipped(Graphics g){

    }
}
