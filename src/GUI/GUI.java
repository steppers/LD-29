package GUI;

import Core.ItemManager;
import Core.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import java.util.ArrayList;

/**
 * Created by Ollie on 26/04/14.
 */
public class GUI {

    private static Image gui;
    private static Image menu;

    private static Rectangle info = new Rectangle(682, 542, 48, 48);
    private static Rectangle pickUp = new Rectangle(376, 542, 48, 48);

    private static ArrayList<GUIComponent> components = new ArrayList<GUIComponent>();

    public enum GUIState{
        MAIN_MENU,
        IN_GAME,
        INVENTORY,
        INFO,
        DEAD
    }

    public static boolean isOverItem = false;
    public static GUIState state = GUIState.IN_GAME;
    public static Color GUIBacking = new Color(70, 70, 70);

    public static void init(){
        try{
            menu = new Image("res/tex/Menu.png");
            menu.setFilter(Image.FILTER_NEAREST);
            gui = new Image("res/tex/GUI.png");
            gui.setFilter(Image.FILTER_NEAREST);
        }catch(SlickException e){
            System.err.println("Error: Cannot load GUI files.");
            e.printStackTrace();
        }
    }

    public static void addComponent(GUIComponent c, float duration){
        c.startTime = System.nanoTime();
        c.duration = duration;
        components.add(c);
    }

    public static void update(Input input, Player player){
        switch(state){
            case IN_GAME:
                for(int i = 0; i < components.size(); i++){
                    if(System.nanoTime() - components.get(i).startTime >= 1000000000*components.get(i).duration){
                        components.remove(i);
                        i--;
                    }
                }

                if(info.contains(input.getMouseX(), input.getMouseY()) && input.isMousePressed(0)){
                    state = GUIState.INFO;
                }
                if(pickUp.contains(input.getMouseX(), input.getMouseY()) && input.isMousePressed(0) && isOverItem){
                    ItemManager.PickupItem(ItemManager.getItem(player.posX, player.posY));
                }
                break;
        }
    }

    public static void render(Graphics g, Player p, float xOffset, float yOffset, float scale){
        for(GUIComponent c : components){
            c.render(g, xOffset, yOffset, scale);
        }
        g.setColor(GUIBacking);
        switch(state){
            case IN_GAME:
                renderInGame(g, p);
                break;
            case INFO:
                renderInfo(g, p);
                break;
            case MAIN_MENU:
                renderMenu(g);
        }
    }

    private static void renderMenu(Graphics g) {
        menu.draw(0, 0, 800, 600, 0, 0, 64, 48);
    }

    private static void renderInGame(Graphics g, Player p){
        //bl
        g.fillRoundRect(-12, 530, 140, 82, 12);
        gui.draw(68, 542, 116, 590, 0, 0, 16, 16);
        gui.draw(10, 542, 58, 590, 16, 0, 32, 16);
        //br
        g.fillRoundRect(672, 530, 140, 82, 12);
        gui.draw(682, 542, 730, 590, 64, 0, 80, 16);
        gui.draw(740, 542, 788, 590, 96, 0, 112, 16);

        if(isOverItem){
            gui.draw(376, 542, 424, 590, 112, 0, 128, 16);
        }

        //tr
        g.fillRoundRect(485, -12, 332, 64, 12);
        g.setColor(Color.lightGray);
        g.fillRect(520, 9, 270, 10);
        g.fillRect(520, 29, 270, 10);

        g.setColor(Color.red);
        g.drawString("HP", 497, 5);
        float width = 270*(p.hp/p.maxHealth);
        g.fillRect(520, 9, width, 10);

        g.setColor(Color.yellow);
        g.drawString("EX", 497, 25);
        width = 270*(p.exp/p.targetExp);
        g.fillRect(520, 29, width, 10);
    }

    private static void renderInfo(Graphics g, Player p){
        //bl
        g.fillRoundRect(-12, 530, 140, 82, 12);
        gui.draw(68, 542, 116, 590, 0, 0, 16, 16);
        gui.draw(10, 542, 58, 590, 16, 0, 32, 16);
        //br
        g.fillRoundRect(672, 530, 140, 82, 12);
        gui.draw(682, 542, 730, 590, 64, 0, 80, 16);
        gui.draw(740, 542, 788, 590, 80, 0, 96, 16);
        //info box
        g.fillRoundRect(200, 100, 400, 400, 12);

        //tr
        g.fillRoundRect(485, -12, 332, 64, 12);
        g.setColor(Color.lightGray);
        g.fillRect(520, 9, 270, 10);
        g.fillRect(520, 29, 270, 10);

        g.setColor(Color.red);
        g.drawString("HP", 497, 5);
        float width = 270*(p.hp/p.maxHealth);
        g.fillRect(520, 9, width, 10);

        g.setColor(Color.yellow);
        g.drawString("EX", 497, 25);
        width = 270*(p.exp/p.targetExp);
        g.fillRect(520, 29, width, 10);
    }

}
