package Enemies;

import Core.CellSystem;
import Core.Player;
import Core.TileMap;
import Core.TurnManager;
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
            if(map.getTile(x, y) == TileMap.TileType.FLOOR_STONE){
                if(x != player.posX && y != player.posY && !CellSystem.cells[x][y].enemy){
                    enemies.add(new Slime(x, y, difficulty));
                    CellSystem.cells[x][y].enemy = true;
                    return;
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
        CellSystem.cells[e.posX][e.posY].enemy = false;
        enemies.remove(e);
    }

}
