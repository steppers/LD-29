package Enemies;

import Core.CellSystem;
import Core.Player;
import Core.Stats;
import Core.TileMap;
import PathFinding.GridPos;
import PathFinding.Path;
import PathFinding.PathFinder;
import org.newdawn.slick.Animation;
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
    private float difficulty = 1;
    public Stats stats;

    public int lastX, lastY;
    private Path currentPath;

    public int moveSpeed;

    public Enemy(int posX, int posY, float difficulty){
        this.posX = posX;
        this.posY = posY;
        r = new Random(System.nanoTime());
        state = AIState.FOLLOWING;
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
    }

    private void updateFollowing(TileMap map, Player player) {
        currentPath = PathFinder.findPath(posX, posY, player.posX, player.posY);
        GridPos n = currentPath.getNextNode();
        if(n != null){
            if(n.x != player.posX || n.y != player.posY){
                CellSystem.cells[posX][posY].enemy = false;
                CellSystem.cells[n.x][n.y].enemy = true;
                posX = n.x;
                posY = n.y;
            }
        }
    }
    private void updateAttacking(TileMap map, Player player) {

    }
    private void updateSleeping(TileMap map, Player player) {

    }
    public void render(Graphics g, float xOffset, float yOffset, float scale){
        anim.draw((posX*16*scale)+xOffset, (posY*16*scale)+yOffset, 16*scale, 16*scale);
    }

}
