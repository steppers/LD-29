package Core;

import Enemies.Enemy;
import Enemies.EnemyManager;
import PathFinding.GridPos;
import PathFinding.Path;
import PathFinding.PathFinder;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

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

    boolean reloaded = false;
    boolean movementPhase = false;

    @Override
    public int getID() {
        return 11;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        zoom = 2;
        map = DungeonGenerator.CreateDungeon(40, 40, DungeonGenerator.DungeonType.PRISON, 1);
        player = new Player();
        player.setPos(20, 17);
        moveLastTime = System.nanoTime();
        enemyManager = new EnemyManager(8, 1, map);
        enemyManager.addAllEnemies(map, player);
        PathFinder.setMap(map);
        turnManager = new TurnManager();
        reloaded = true;
        movementPhase = false;
        positionX = -player.posX*32+384;
        positionY = -player.posY*32+288;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render(graphics, positionX, positionY, zoom);
        player.render(graphics, positionX, positionY, zoom);
        enemyManager.render(graphics, positionX, positionY, zoom);
        GUI.render(graphics, player);
        ItemManager.renderItems(graphics, positionX, positionY, zoom);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        if(input.isKeyPressed(Input.KEY_ESCAPE)){
            stateBasedGame.enterState(0);
            stateBasedGame.getState(0).init(gameContainer, stateBasedGame);
        }

        updatePlayer(input);
        enemyManager.update(turnManager, player);

        if(reloaded){
            GUI.state = GUI.GUIState.IN_GAME;
            reloaded = false;
        }

        GUI.update(input);
    }

    private void updatePlayer(Input input) {
        if(!movementPhase){
            if(input.isMousePressed(0)){
                int x, y;
                x = (int)(player.posX + input.getMouseX()/32 - 11.5f);
                y = (int)(player.posY + input.getMouseY()/32 - 8.375f);
                TileMap.TileType i = map.getTile(x, y);
                if(i != TileMap.TileType.STONE && i != null && i != TileMap.TileType.EMPTY && input.getMouseY() < 542 && input.getMouseY() > 52){
                    playerPath = PathFinder.findPath(player.posX, player.posY, x, y, false);
                    movementPhase = true;
                }
                if(Math.abs(x - player.posX) <= 1 && Math.abs(y - player.posY) <= 1){
                    movementPhase = ItemManager.useItem(ItemManager.playerWeapon, x, y, enemyManager);
                }
            }

            if(input.isKeyPressed(Input.KEY_SPACE)){
                turnManager.addEnemyTurns(1);
                movementPhase = true;
            }
        }else{
            if(System.nanoTime() - moveLastTime >= 100000000){
                GridPos n = playerPath.getNextNode();
                if(n != null){
                    if(!CellSystem.cells[n.x][n.y].enemy){
                        playerPath.removeLastNode();
                        player.posX = n.x;
                        player.posY = n.y;
                        moveLastTime = System.nanoTime();
                        turnManager.addEnemyTurns(1);
                    }else{
                        playerPath = new Path();
                        movementPhase = false;
                    }
                }else{
                    movementPhase = false;
                }
                positionX = -player.posX*32+384;
                positionY = -player.posY*32+288;
            }
        }
    }
}
