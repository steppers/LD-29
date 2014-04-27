package GUI;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

/**
 * Created by Ollie on 27/04/14.
 */
public class GUIStatPopup extends GUIComponent{

    private String text;
    private int hlength;

    public GUIStatPopup(int x, int y, String text, Color color){
        super(x, y, color);
        this.text = text;
        hlength = text.length()/2;
    }

    @Override
    public void render(Graphics g, float xOffset, float yOffset, float scale){
        g.setColor(color);
        g.drawString(text, (x*16*scale)+xOffset+16-(hlength*8), (y*16*scale)+yOffset+5);
    }

}
