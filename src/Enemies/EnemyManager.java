package Enemies;

import Core.*;
import PathFinding.PathFinder;
import TileSystem.Tile;
import TileSystem.TileMap;
import org.newdawn.slick.Graphics;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Ollie on 27/04/14.
 */
public class EnemyManager {

    private TileMap map;
    private int maxEnemies;
    private float difficulty;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public EnemyManager(int maxEnemies, float difficulty, TileMap map){
        this.difficulty = difficulty;
        this.maxEnemies = maxEnemies;
        this.map = map;
    }

    public void update(TurnManager turnManager, Player player){
        if(turnManager.hasTurnsLeft()){
            for(Enemy e : enemies){
                e.update(map, player);
            }

            if(enemies.size() < maxEnemies){
                double r = Math.random();
                if(r < 0.1){
                    addNewEnemy(map, player);
                }
            }
            turnManager.useTurn();
        }
    }

    private void addNewEnemy(TileMap map, Player player) {
        Random r = new Random(System.nanoTime());
        while(true){
            int x = r.nextInt(map.width);
            int y = r.nextInt(map.height);
            if(map.getTileType(x, y) == Tile.TileType.STONE_FLOOR){
                if(PathFinder.findPath(x, y, player.posX, player.posY, false).length() > 9){
                    if(x != player.posX && y != player.posY && !map.getTileEnemy(x, y)){
                        enemies.add(new Slime(x, y, difficulty));
                        map.swapHasEnemy(x, y);
                        return;
                    }
                }
            }
        }
    }

    public void addAllEnemies(TileMap map, Player player){
        while(enemies.size() < maxEnemies){
            addNewEnemy(map, player);
        }
    }

    public void render(Graphics g, float xOffset, float yOffset, float scale){
        for(Enemy e : enemies){
            if(FogManager.fog[e.posX][e.posY] == 4)
                e.render(g, xOffset, yOffset, scale);
        }
    }

    public Enemy getEnemy(int x, int y){
        for(Enemy e : enemies){
            if (e.posX == x && e.posY == y)
                return e;
        }
        return null;
    }

    public void removeEnemy(Enemy e){
        e.dropItems();
        map.swapHasEnemy(e.posX, e.posY);
        enemies.remove(e);
    }

}
