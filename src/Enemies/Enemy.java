package Enemies;

import Core.*;
import GUI.GUI;
import GUI.GUIStatPopup;
import Items.Item;
import Items.ItemManager;
import PathFinding.GridPos;
import PathFinding.Path;
import PathFinding.PathFinder;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import java.util.Random;

/**
 * Created by Ollie on 27/04/14.
 */
public class Enemy {

    Random r;

    public enum AIState{
        IDLE,
        FOLLOWING,
        ATTACKING,
        SLEEPING
    }

    public AIState state;
    public int posX, posY;
    private Image image;
    private Animation anim;
    public float difficulty = 1;
    public Stats stats;
    public Item[] items;

    public int lastX, lastY;
    public Path currentPath;

    public int exp;
    public int moveSpeed;

    public Enemy(int posX, int posY, float difficulty){
        this.posX = posX;
        this.posY = posY;
        r = new Random(System.nanoTime());
        state = AIState.IDLE;
        items = new Item[0];
    }

    public void setImage(Image i){
        this.image = i;
        i.setFilter(Image.FILTER_NEAREST);
        anim = new Animation(new Image[]{i.getSubImage(0, 0, 16, 16),
                i.getSubImage(16, 0, 16, 16),
                i.getSubImage(32, 0, 16, 16),
                i.getSubImage(48, 0, 16, 16),
                i.getSubImage(64, 0, 16, 16)}, 100);
    }

    public void dropItems(){
        for(int i = 0; i < items.length; i++){
            ItemManager.DropEnemyItem(items[i], posX, posY);
        }
        items = new Item[0];
    }

    public void update(TileMap map, Player player){
        switch(state){
            case IDLE:
                updateIdle(map, player);
                break;
            case FOLLOWING:
                updateFollowing(map, player);
                break;
            case ATTACKING:
                updateAttacking(map, player);
                break;
            case SLEEPING:
                updateSleeping(map, player);
                break;
        }
    }

    private void updateIdle(TileMap map, Player player) {
        int decision = 0;
        boolean done = false;
        while(!done){
            decision = r.nextInt(8);
            switch(decision){
                case 0:
                    if(map.getTile(posX, posY-1) != TileMap.TileType.STONE && !CellSystem.cells[posX][posY-1].enemy){
                        if(posX == player.posX && posY-1 == player.posY){
                            break;
                        }else{
                            CellSystem.cells[posX][posY].enemy = false;
                            CellSystem.cells[posX][posY-1].enemy = true;
                            lastY = posY;
                            lastX = posX;
                            posY--;
                            done = true;
                        }
                    }
                    break;
                case 1:
                    if(map.getTile(posX, posY+1) != TileMap.TileType.STONE && !CellSystem.cells[posX][posY+1].enemy){
                        if(posX == player.posX && posY+1 == player.posY){
                            break;
                        }else{
                            CellSystem.cells[posX][posY].enemy = false;
                            CellSystem.cells[posX][posY+1].enemy = true;
                            lastY = posY;
                            lastX = posX;
                            posY++;
                            done = true;
                        }
                    }
                    break;
                case 2:
                    if(map.getTile(posX+1, posY) != TileMap.TileType.STONE && !CellSystem.cells[posX+1][posY].enemy){
                        if(posX == player.posX+1 && posY == player.posY){
                            break;
                        }else{
                            CellSystem.cells[posX][posY].enemy = false;
                            CellSystem.cells[posX+1][posY].enemy = true;
                            lastY = posY;
                            lastX = posX;
                            posX++;
                            done = true;
                        }
                    }
                    break;
                case 3:
                    if(map.getTile(posX-1, posY) != TileMap.TileType.STONE && !CellSystem.cells[posX-1][posY].enemy){
                        if(posX-1 == player.posX && posY == player.posY){
                            break;
                        }else{
                            CellSystem.cells[posX][posY].enemy = false;
                            CellSystem.cells[posX-1][posY].enemy = true;
                            lastY = posY;
                            lastX = posX;
                            posX--;
                            done = true;
                        }
                    }
                    break;
                default:
                    done = true;
                    break;
            }
        }
        currentPath = PathFinder.findPath(posX, posY, player.posX, player.posY, false);
        if(currentPath.length() < 5)
            state = AIState.FOLLOWING;
    }

    private void updateFollowing(TileMap map, Player player) {
        currentPath = PathFinder.findPath(posX, posY, player.posX, player.posY, false);
        if(currentPath.length() > 7)
            state = AIState.IDLE;
        if(currentPath.length() < 2)
            state = AIState.ATTACKING;
        GridPos n = currentPath.getNextNode();
        if(n != null){
            if(!CellSystem.cells[n.x][n.y].enemy){
                if(n.x != player.posX || n.y != player.posY){
                    currentPath.removeLastNode();
                    CellSystem.cells[posX][posY].enemy = false;
                    CellSystem.cells[n.x][n.y].enemy = true;
                    posX = n.x;
                    posY = n.y;
                }
            }
        }
    }
    private void updateAttacking(TileMap map, Player player) {
        if(Math.abs(posX - player.posX) > 1 || Math.abs(posY - player.posY) > 1){
            currentPath = PathFinder.findPath(posX, posY, player.posX, player.posY, false);
            state = AIState.FOLLOWING;
        }else{
            float dodge = r.nextInt(ItemManager.playerArmour.stats.Evade+5);
            if(dodge < 8){
                int damage = r.nextInt(Math.abs(stats.Attack - ItemManager.playerArmour.stats.Defense/2)+1)+stats.Attack/2;
                if(damage < 0)
                    damage = 0;
                player.hp -= damage;
                GUI.addComponent(new GUIStatPopup(player.posX, player.posY - 1, "-" + damage, Color.red), 1.5f);
                AudioBank.playEffect(AudioBank.Hit2);
            }else{
                GUI.addComponent(new GUIStatPopup(player.posX, player.posY - 1,"Dodge", Color.orange), 1.5f);
            }
        }
    }
    private void updateSleeping(TileMap map, Player player) {

    }
    public void render(Graphics g, float xOffset, float yOffset, float scale){
        anim.draw((posX*16*scale)+xOffset, (posY*16*scale)+yOffset, 16*scale, 16*scale);
    }

}
