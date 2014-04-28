package Core;

import GUI.GUI;
import Items.Item;
import org.newdawn.slick.*;

import GUI.GUIStatPopup;
import java.util.ArrayList;

/**
 * Created by Ollie on 26/04/14.
 */
public class Player {

    public int posX, posY;
    private ArrayList<Item> items = new ArrayList<Item>();
    private Image image;
    private Animation downanim;

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
        downanim = new Animation(new Image[]{image.getSubImage(0, 0, 16, 16),
                image.getSubImage(16, 0, 16, 16),
                image.getSubImage(32, 0, 16, 16),
                image.getSubImage(48, 0, 16, 16)}, 250);
    }

    public void addHealth(int health){
        if(hp < maxHealth){
            hp += health;
            GUI.addComponent(new GUIStatPopup(posX, posY - 1, "+"+health+" HP", Color.green), 1.5f);
            if(hp > maxHealth)
                hp = maxHealth;
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
        downanim.draw((posX*16*scale)+xOffset, (posY*16*scale)+yOffset, 16*scale, 16*scale);
    }

}
