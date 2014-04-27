package Enemies;

import Core.Player;
import Core.Stats;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

/**
 * Created by Ollie on 27/04/14.
 */
public class Enemy {

    public int posX, posY;
    private Image image;
    private float difficulty = 1;
    public Stats stats;

    public int moveSpeed;

    public Enemy(int posX, int posY, float difficulty){
        this.posX = posX;
        this.posY = posY;
    }

    public void setImage(Image i){
        this.image = i;
    }

    public void update(Player player){

    }

    public void render(Graphics g, float xOffset, float yOffset, float scale){
        g.drawImage(image, (posX*16*scale)+xOffset, (posY*16*scale)+yOffset, (posX*16*scale)+xOffset+16*scale, (posY*16*scale)+yOffset+16*scale, 0, 0, 16, 16);
    }

}
