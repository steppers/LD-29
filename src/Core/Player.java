package Core;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import java.util.ArrayList;

/**
 * Created by Ollie on 26/04/14.
 */
public class Player {

    public int posX, posY;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Image image;

    public String playerClass = "Warrior";

    public float hp = 20;
    public float maxHealth = 20;
    public float exp = 0;
    public float targetExp = 10;

    public Player(){
        try{
            image = new Image("res/tex/Player.png");
            image.setFilter(Image.FILTER_NEAREST);
        }catch(SlickException e){
            System.err.println("Error: Cannot load Player.png.");
            e.printStackTrace();
        }
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public boolean hasItemRoom(){
        if(items.size() < 18)
            return true;
        else
            return false;
    }

    public void moveTo(int x, int y){

    }

    public void setPos(int x, int y){
        posX = x;
        posY = y;
    }

    public void render(Graphics g, float xOffset, float yOffset, float scale){
        g.drawImage(image, (posX*16*scale)+xOffset, (posY*16*scale)+yOffset, (posX*16*scale)+xOffset+16*scale, (posY*16*scale)+yOffset+16*scale, 0, 0, 16, 16);
    }

}
