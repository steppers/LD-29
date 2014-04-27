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

    private int maxEnemies;
    private float difficulty;
    private ArrayList<Enemy> enemies = new ArrayList<Enemy>();

    public EnemyManager(int maxEnemies, float difficulty){
        this.difficulty = difficulty;
        this.maxEnemies = maxEnemies;
    }

    public void update(TileMap map, TurnManager turnManager, Player player){
        if(turnManager.hasTurnsLeft()){
            for(Enemy e : enemies){
                e.update(player);
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
                    break;
                }
            }
        }
    }

    public void render(Graphics g, float xOffset, float yOffset, float scale){
        for(Enemy e : enemies){
            e.render(g, xOffset, yOffset, scale);
        }
    }

}
