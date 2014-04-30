package Core;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

/**
 * Created by Ollie on 28/04/14.
 */
public class FogManager {

    public static byte[][] fog;

    private static final byte hidden = 2;
    private static final byte seen = 3;
    private static final byte visible = 4;
    private static final Color semi = new Color(0, 0, 0, 0.7f);

    public static void init(int width, int height){
        fog = new byte[width][height];
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                fog[x][y] = hidden;
            }
        }
    }

    public static void update(int x, int y, float range){
        float rangeM1 = range - 1;

        for(int fogx = 0; fogx < fog.length; fogx++){
            for(int fogy = 0; fogy < fog[0].length; fogy++){
                float l = new Vector2f(fogx - x, fogy - y).length();
                if(l > range && fog[fogx][fogy] == hidden){
                    continue;
                }
                if(l <= rangeM1 && fog[fogx][fogy] == hidden){
                    fog[fogx][fogy] = visible;
                }else if(l <= rangeM1 && fog[fogx][fogy] == seen){
                    fog[fogx][fogy] = visible;
                }
                if(l > range && fog[fogx][fogy] == visible){
                    fog[fogx][fogy] = seen;
                }
                if(l == range){
                    fog[fogx][fogy] = seen;
                }
            }
        }
    }

    public static void render(Graphics g, float xOffset, float yOffset, float scale){
        for(int fogx = 0; fogx < fog.length; fogx++){
            for(int fogy = 0; fogy < fog[0].length; fogy++){
                switch (fog[fogx][fogy]){
                    case hidden:
                        g.setColor(Color.black);
                        g.fillRect((fogx*16*scale)+xOffset, (fogy*16*scale)+yOffset, 16*scale, 16*scale);
                        break;
                    case seen:
                        g.setColor(semi);
                        g.fillRect((fogx*16*scale)+xOffset, (fogy*16*scale)+yOffset, 16*scale, 16*scale);
                        break;
                }
            }
        }
    }
}
