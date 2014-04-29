package Core;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Ollie on 28/04/14.
 */
public class ImageBank {

    public static Image itemsTiles;
    public static Image textures;
    public static Image menu;
    public static Image gui;
    public static Image gameOver;
    public static Image slime;

    public static void init(){
        try {
            itemsTiles = new Image("res/tex/ItemsTiles.png");
            itemsTiles.setFilter(Image.FILTER_NEAREST);
            textures = new Image("res/tex/tiles.png");
            textures.setFilter(Image.FILTER_NEAREST);
            menu = new Image("res/tex/Menu.png");
            menu.setFilter(Image.FILTER_NEAREST);
            gui = new Image("res/tex/GUI.png");
            gui.setFilter(Image.FILTER_NEAREST);
            gameOver = new Image("res/tex/GameOver.png");
            gameOver.setFilter(Image.FILTER_NEAREST);
            slime = new Image("res/tex/Slime.png");
            slime.setFilter(Image.FILTER_NEAREST);
        } catch (SlickException e) {
            System.err.println("Error: Cannot load Textures.");
            e.printStackTrace();
        }
    }

}
