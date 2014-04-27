package GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Ollie on 27/04/14.
 */
public class GUIComponent {

    public int x, y;
    public Color color;
    public float duration;
    public float startTime;

    public GUIComponent(int x, int y, Color c){
        this.x = x; this.y = y;
        color = c;
    }

    public void render(Graphics g, float xOffset, float yOffset, float scale){

    }
}
