package Core;

import Enemies.EnemyManager;
import GUI.GUI;
import Items.Item;
import Items.ItemManager;
import PathFinding.GridPos;
import PathFinding.Path;
import PathFinding.PathFinder;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Random;

/**
 * Created by Ollie on 26/04/14.
 */
public class Play extends BasicGameState {

    private TileMap map;
    private float positionX, positionY, zoom;
    private Player player;
    private Path playerPath;
    private double moveLastTime = 0;
    private EnemyManager enemyManager;
    private TurnManager turnManager;
    private int difficulty = 1;

    boolean reloaded = false;
    boolean movementPhase = false;

    @Override
    public int getID() {
        return 11;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        difficulty = 1;
        zoom = 2;
        map = DungeonGenerator.CreateDungeon(40, 40, DungeonGenerator.DungeonType.PRISON, 1);
        player = new Player();
        player.setPos(20, 17);
        moveLastTime = System.nanoTime();
        PathFinder.setMap(map);
        enemyManager = new EnemyManager(13, 1, map);
        enemyManager.addAllEnemies(map, player);
        turnManager = new TurnManager();
        ItemManager.levelItems.clear();
        FogManager.init(40, 40);
        reloaded = true;
        movementPhase = false;
        positionX = -player.posX*32+384;
        positionY = -player.posY*32+288;
    }

    private void nextLevel(){
        difficulty++;
        map = DungeonGenerator.CreateDungeon(40, 40, DungeonGenerator.DungeonType.PRISON, difficulty);
        player.setPos(20, 17);
        moveLastTime = System.nanoTime();
        enemyManager = new EnemyManager(13, difficulty, map);
        enemyManager.addAllEnemies(map, player);
        PathFinder.setMap(map);
        turnManager = new TurnManager();
        FogManager.init(40, 40);
        playerPath = new Path();
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render(graphics, positionX, positionY, zoom);
        ItemManager.renderItems(graphics, positionX, positionY, zoom);
        player.render(graphics, positionX, positionY, zoom);
        enemyManager.render(graphics, positionX, positionY, zoom);
        FogManager.render(graphics, positionX, positionY, zoom);
        GUI.render(graphics, player, positionX, positionY, zoom, difficulty);
        if(ItemManager.playerWeapon != null)
            ItemManager.playerWeapon.image.draw(740, 542, 48, 48);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        FogManager.update(player.posX, player.posY, 4);
        GUI.update(player, stateBasedGame, gameContainer, turnManager);

        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            stateBasedGame.enterState(0);
            stateBasedGame.getState(0).init(gameContainer, stateBasedGame);
        }

        if(GUI.state != GUI.GUIState.DEAD){
            updatePlayer(input, stateBasedGame, gameContainer);
            enemyManager.update(turnManager, player);
        }

        if(reloaded){
            GUI.state = GUI.GUIState.IN_GAME;
            reloaded = false;
        }
    }

    private void updatePlayer(Input input, StateBasedGame sbg, GameContainer gc) throws SlickException{
        if(player.hp <= 0){
            player.hp = 0;
            GUI.state = GUI.GUIState.DEAD;
        }

        if(player.exp >= player.targetExp){
            player.targetExp *= 2;
            //player.levelUp();
        }

        if(GUI.state == GUI.GUIState.IN_GAME){
            if(!movementPhase){
                if(input.isMousePressed(0)){
                    int x, y;
                    x = (int)(player.posX + input.getMouseX()/32 - 11.5f);
                    y = (int)(player.posY + input.getMouseY()/32 - 8.375f);
                    TileMap.TileType i = map.getTile(x, y);
                    if(i != TileMap.TileType.STONE && i != null && i != TileMap.TileType.EMPTY && input.getMouseY() < 542 && input.getMouseY() > 52 && FogManager.fog[x][y] != 2){
                        playerPath = PathFinder.findPath(player.posX, player.posY, x, y, false);
                        movementPhase = true;
                    }
                    if(Math.abs(x - player.posX) <= 1 && Math.abs(y - player.posY) <= 1){
                        movementPhase = ItemManager.useItem(ItemManager.playerWeapon, x, y, enemyManager, player, turnManager);
                    }
                }

                Item i = ItemManager.getItem(player.posX, player.posY);
                if(i != null){
                    GUI.isOverItem = true;
                }else{
                    GUI.isOverItem = false;
                }

                if(input.isKeyPressed(Input.KEY_SPACE)){
                    if(player.hp < player.maxHealth){
                        Random r = new Random(System.nanoTime());
                        if(r.nextFloat() < 0.04f){
                            player.addHealth(1);
                        }
                    }
                    turnManager.addEnemyTurns(1);
                    movementPhase = false;
                }
            }else{
                Item i = ItemManager.getItem(player.posX, player.posY);
                if(i != null){
                    GUI.isOverItem = true;
                }else{
                    GUI.isOverItem = false;
                }
                if(System.nanoTime() - moveLastTime >= 100000000){
                    GridPos n = playerPath.getNextNode();
                    if(n != null){
                        if(!CellSystem.cells[n.x][n.y].enemy){
                            playerPath.removeLastNode();
                            player.posX = n.x;
                            player.posY = n.y;
                            moveLastTime = System.nanoTime();
                            turnManager.addEnemyTurns(1);
                            if(player.hp < player.maxHealth){
                                Random r = new Random(System.nanoTime());
                                if(r.nextFloat() < 0.07f){
                                    player.addHealth(1);
                                }
                            }
                        }else{
                            playerPath = new Path();
                            movementPhase = false;
                        }
                    }else{
                        if(map.getTile(player.posX, player.posY) == TileMap.TileType.STAIRS_DOWN){
                            nextLevel();
                        }
                        movementPhase = false;
                    }
                    positionX = -player.posX*32+384;
                    positionY = -player.posY*32+288;
                }
            }
        }
    }
}
