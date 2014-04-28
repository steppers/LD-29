package GUI;

import Core.AudioBank;
import Core.ImageBank;
import Core.TurnManager;
import Items.Item;
import Items.ItemManager;
import Core.Player;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.state.StateBasedGame;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;

/**
 * Created by Ollie on 26/04/14.
 */
public class GUI {

    private static Image gui;
    private static Image menu;
    private static Image gameOver;

    private static Rectangle info = new Rectangle(682, 542, 48, 48);
    private static Rectangle pickUp = new Rectangle(376, 542, 48, 48);
    private static Rectangle inv = new Rectangle(68,542,48,48);

    private static Rectangle invW = new Rectangle(193, 40, 96, 96);
    private static Rectangle invA = new Rectangle(299, 40, 96, 96);
    private static Rectangle invJ = new Rectangle(405,40,96,96);
    private static Rectangle invJ2 = new Rectangle(511,40,96,96);
    private static Rectangle inv1 = new Rectangle(193,146,96,96);
    private static Rectangle inv2 = new Rectangle(299,146,96,96);
    private static Rectangle inv3 = new Rectangle(405,146,96,96);
    private static Rectangle inv4 = new Rectangle(511,146,96,96);
    private static Rectangle inv5 = new Rectangle(193,252,96,96);
    private static Rectangle inv6 = new Rectangle(299,252,96,96);
    private static Rectangle inv7 = new Rectangle(405,252,96,96);
    private static Rectangle inv8 = new Rectangle(511,252,96,96);
    private static Rectangle inv9 = new Rectangle(193,358,96,96);
    private static Rectangle inv10 = new Rectangle(299,358,96,96);
    private static Rectangle inv11 = new Rectangle(405,358,96,96);
    private static Rectangle inv12 = new Rectangle(511,358,96,96);
    private static Rectangle inv13 = new Rectangle(193,464,96,96);
    private static Rectangle inv14 = new Rectangle(299,464,96,96);
    private static Rectangle inv15 = new Rectangle(405,464,96,96);
    private static Rectangle inv16 = new Rectangle(511,464,96,96);

    private static Rectangle useItem = new Rectangle(672, 185, 118, 70);
    private static Rectangle dropItem = new Rectangle(672, 265, 118, 70);
    private static Rectangle throwItem = new Rectangle(672, 345, 118, 70);
    private static int xSel, ySel;

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
    public static Item selected = null;

    public static void init(){
        menu = ImageBank.menu;
        gui = ImageBank.gui;
        gameOver = ImageBank.gameOver;
    }

    public static void addComponent(GUIComponent c, float duration){
        c.startTime = System.nanoTime();
        c.duration = duration;
        components.add(c);
    }

    public static void update(Player player, StateBasedGame sbg, GameContainer gc, TurnManager tm) throws SlickException{
        Input input = gc.getInput();
        switch(state){
            case IN_GAME:
                for(int i = 0; i < components.size(); i++){
                    if(System.nanoTime() - components.get(i).startTime >= 1000000000*components.get(i).duration){
                        components.remove(i);
                        i--;
                    }
                }

                if(GUI.info.contains(input.getMouseX(), input.getMouseY()) && input.isMousePressed(0)){
                    state = GUIState.INFO;
                    AudioBank.playEffect(AudioBank.Select2);
                }
                if(GUI.pickUp.contains(input.getMouseX(), input.getMouseY()) && input.isMousePressed(0) && isOverItem){
                    ItemManager.PickupItem(ItemManager.getItem(player.posX, player.posY));
                    tm.addEnemyTurns(1);
                }
                if(GUI.inv.contains(input.getMouseX(), input.getMouseY()) && input.isMousePressed(0)){
                    state = GUIState.INVENTORY;
                    AudioBank.playEffect(AudioBank.Select2);
                }
                break;
            case DEAD:
                if(input.isKeyPressed(Input.KEY_SPACE)){
                    state = GUIState.MAIN_MENU;
                    sbg.enterState(0);
                    sbg.getState(0).init(gc, sbg);
                }
                break;
            case INVENTORY:
                if(input.isMousePressed(0)){
                    if(GUI.invW.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.playerWeapon;
                        AudioBank.playEffect(AudioBank.Select2);
                        xSel = 0; ySel = 0;
                    }
                    if(GUI.invA.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.playerArmour;
                        AudioBank.playEffect(AudioBank.Select2);
                        xSel = 1; ySel = 0;
                    }
                    if(GUI.invJ.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.playerJewel1;
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 2; ySel = 0;
                    }
                    if(GUI.invJ2.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.playerJewel2;
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 3; ySel = 0;
                    }
                    if(GUI.inv1.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(0);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 0; ySel = 1;
                    }
                    if(GUI.inv2.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(1);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 1; ySel = 1;
                    }
                    if(GUI.inv3.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(2);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 2; ySel = 1;
                    }
                    if(GUI.inv4.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(3);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 3; ySel = 1;
                    }
                    if(GUI.inv5.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(4);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 0; ySel = 2;
                    }
                    if(GUI.inv6.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(5);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 1; ySel = 2;
                    }
                    if(GUI.inv7.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(6);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 2; ySel = 2;
                    }
                    if(GUI.inv8.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(7);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 3; ySel = 2;
                    }
                    if(GUI.inv9.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(8);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 0; ySel = 3;
                    }
                    if(GUI.inv10.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(9);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 1; ySel = 3;
                    }
                    if(GUI.inv11.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(10);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 2; ySel = 3;
                    }
                    if(GUI.inv12.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(12);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 3; ySel = 3;
                    }
                    if(GUI.inv13.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(13);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 0; ySel = 4;
                    }
                    if(GUI.inv14.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(14);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 1; ySel = 4;
                    }
                    if(GUI.inv15.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(15);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 2; ySel = 4;
                    }
                    if(GUI.inv16.contains(input.getMouseX(), input.getMouseY())){
                        selected = ItemManager.getInvItem(16);
                        if(selected != null)
                            AudioBank.playEffect(AudioBank.Select2);
                        xSel = 3; ySel = 4;
                    }
                    if(GUI.inv.contains(input.getMouseX(), input.getMouseY())){
                        state = GUIState.IN_GAME;
                        selected = null;
                        AudioBank.playEffect(AudioBank.Select2);
                    }
                    if(GUI.useItem.contains(input.getMouseX(), input.getMouseY()) && selected != null){
                        if(selected.isEquipable){
                            if(selected.isEquipped){
                                if(ItemManager.addEquippedToInventory(selected)){
                                    makeSelectedNull();
                                }
                            }else{
                                ItemManager.Equip(selected);
                                selected = null;
                            }
                        }else if(selected.id == Item.HealthPotion){
                            player.addHealth(selected.stats.HP);
                            AudioBank.playEffect(AudioBank.Pickup1);
                            if(--selected.quantity == 0){
                                ItemManager.inventoryItems.remove(selected);
                            }
                            makeSelectedNull();
                        }
                    }
                    if(GUI.dropItem.contains(input.getMouseX(), input.getMouseY()) && selected != null){
                        if(selected == ItemManager.playerWeapon)
                            return;
                        if(selected == ItemManager.playerArmour)
                            return;
                        if(selected == ItemManager.playerJewel1)
                            return;
                        if(selected == ItemManager.playerJewel2)
                            return;
                        ItemManager.DropItem(selected, player.posX, player.posY);
                        makeSelectedNull();
                    }
                }
                break;
            case INFO:
                if(GUI.info.contains(input.getMouseX(), input.getMouseY()) && input.isMousePressed(0)){
                    state = GUIState.IN_GAME;
                    AudioBank.playEffect(AudioBank.Select2);
                }
                break;
        }
    }

    public static void render(Graphics g, Player p, float xOffset, float yOffset, float scale, int level){
        for(GUIComponent c : components){
            c.render(g, xOffset, yOffset, scale);
        }
        g.setColor(GUIBacking);
        switch(state){
            case IN_GAME:
                renderInGame(g, p, level);
                break;
            case INFO:
                renderInGame(g, p, level);
                renderInfo(g, p);
                break;
            case MAIN_MENU:
                renderMenu(g);
                break;
            case DEAD:
                renderInGame(g, p, level);
                renderDead(g);
                break;
            case INVENTORY:
                renderInGame(g, p, level);
                renderInventory(g);
                break;
        }
    }

    private static void renderInventory(Graphics g) {
        g.setColor(GUIBacking);
        g.setLineWidth(2);
        g.fillRoundRect(181, 28, 438, 544, 12);
        g.setColor(Color.gray);
        g.drawRoundRect(181, 28, 438, 544, 12);

        g.setColor(Color.lightGray);
        for(int x = 0; x < 4; x++){
            for(int y = 0; y < 5; y++){
                g.fillRect(193 + (x*96) + (x*10), 40 + (y*96) + (y*10), 96, 96);
            }
        }

        if(selected != null){
            drawSelectedData(g);
            drawActionMenu(g);
        }

        if(ItemManager.playerWeapon != null){
            ItemManager.playerWeapon.image.draw(193, 40, 96, 96);
        }
        if(ItemManager.playerArmour != null){
            ItemManager.playerArmour.image.draw(299, 40, 96, 96);
        }

        int x = 0, y = 1;
        for(Item i : ItemManager.inventoryItems){
            i.image.draw(193 + (x*96) + (x*10), 40 + (y*96) + (y*10), 96, 96);
            if(i.quantity > 1){
                g.setColor(Color.black);
                g.drawString(Integer.toString(i.quantity), 193 + (x*96) + (x*10) + 5, 40 + (y*96) + (y*10) + 5);
            }
            x++;
            if(x == 4){
                y++;
                x = 0;
            }
        }
    }

    private static void drawActionMenu(Graphics g) {
        g.setColor(GUIBacking);
        g.fillRoundRect(662, 175, 150, 250, 12);
        g.setColor(Color.gray);
        g.drawRoundRect(662, 175, 150, 250, 12);
        g.setColor(Color.white);

        g.setColor(Color.lightGray);
        g.fillRoundRect(672, 185, 118, 70, 5);
        g.setColor(Color.white);
        if(selected.isEquipable && !selected.isEquipped){
            g.drawString("EQUIP", 690, 209);
        }else if(selected.isEquipable && selected.isEquipped && !ItemManager.InventoryIsFull()){
            g.drawString("UNEQUIP", 690, 209);
        }else{
            g.drawString("USE", 690, 209);
        }

        g.setColor(Color.lightGray);
        g.fillRoundRect(672, 265, 118, 70, 5);
        g.setColor(Color.white);
        g.drawString("DROP", 690, 292);

        g.setColor(Color.lightGray);
        g.fillRoundRect(672, 345, 118, 70, 5);
        g.setColor(Color.white);
        g.drawString("THROW", 690, 372);
    }

    private static void drawSelectedData(Graphics g) {
        g.setColor(GUIBacking);
        g.fillRoundRect(-12, 175, 150, 250, 12);
        g.setColor(Color.gray);
        g.drawRoundRect(-12, 175, 150, 250, 12);
        g.setColor(Color.green);
        g.drawRect(193 + (xSel * 96) + (xSel * 10), 40 + (ySel * 96) + (ySel * 10), 96, 96);
        g.setColor(Color.white);

        g.drawString(selected.name, 5, 180);
        g.drawString("ATK:  " + selected.stats.Attack, 5, 200);
        g.drawString("DEF:  " + selected.stats.Defense, 5, 220);
        g.drawString("+HP:  " + selected.stats.HP, 5, 240);
        if(selected.stats.Speed == 1){
            g.drawString("SPD:  FAST", 5, 260);
        }else{
            g.drawString("SPD:  SLOW", 5, 260);
        }
        g.drawString("EVA:  " + selected.stats.Evade, 5, 280);
    }

    private static void renderMenu(Graphics g) {
        menu.draw(0, 0, 800, 600, 0, 0, 64, 48);
    }

    private static void renderInGame(Graphics g, Player p, int level){
        g.setLineWidth(2);
        //bl
        g.fillRoundRect(-12, 530, 140, 82, 12);
        g.setColor(Color.gray);
        g.drawRoundRect(-12, 530, 140, 82, 12);
        g.setColor(GUIBacking);
        gui.draw(68, 542, 116, 590, 0, 0, 16, 16);
        gui.draw(10, 542, 58, 590, 16, 0, 32, 16);
        //br
        g.fillRoundRect(672, 530, 140, 82, 12);
        g.setColor(Color.gray);
        g.drawRoundRect(672, 530, 140, 82, 12);
        g.setColor(GUIBacking);
        gui.draw(682, 542, 730, 590, 64, 0, 80, 16);
        gui.draw(740, 542, 788, 590, 96, 0, 112, 16);

        if(isOverItem){
            gui.draw(376, 542, 424, 590, 112, 0, 128, 16);
        }

        //tr
        g.fillRoundRect(370, -12, 442, 64, 12);
        g.setColor(Color.gray);
        g.drawRoundRect(370, -12, 442, 64, 12);
        g.setColor(Color.lightGray);
        g.fillRect(520, 9, 270, 10);
        g.fillRect(520, 29, 270, 10);
        //Stairs/ level indicator
        gui.draw(385, 9, 415, 39, 0, 16, 16, 32);
        g.setColor(Color.orange);
        g.drawString("Level "+level, 420, 16);

        g.setColor(Color.red);
        g.drawString("HP", 497, 5);
        float width = 270*(p.hp/p.maxHealth);
        g.fillRect(520, 9, width, 10);

        g.setColor(Color.yellow);
        g.drawString("XP", 497, 25);
        width = 270*(p.exp/p.targetExp);
        g.fillRect(520, 29, width, 10);
    }

    private static void renderInfo(Graphics g, Player p){
        g.setColor(GUIBacking);
        //info box
        g.fillRoundRect(200, 100, 400, 400, 12);
    }

    public static void renderDead(Graphics g){
        gameOver.draw(203, 150, 400, 300);
        g.setColor(Color.green);
    }

    private static void makeSelectedNull(){
        if(selected.equals(ItemManager.playerWeapon))
            ItemManager.playerWeapon = null;
        else if(selected.equals(ItemManager.playerArmour))
            ItemManager.playerArmour = null;
        else if(selected.equals(ItemManager.playerJewel1))
            ItemManager.playerJewel1 = null;
        else if(selected.equals(ItemManager.playerJewel2))
            ItemManager.playerJewel2 = null;
        selected = null;
    }

}
