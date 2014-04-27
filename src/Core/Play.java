package Core;

import Enemies.EnemyManager;
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
    private EnemyManager enemyManager;
    private TurnManager turnManager;

    boolean reloaded = false;

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
        enemyManager = new EnemyManager(8, 1, map);
        enemyManager.addAllEnemies(map, player);
        PathFinder.setMap(map);
        turnManager = new TurnManager();
        reloaded = true;
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render(graphics, positionX, positionY, zoom);
        player.render(graphics, positionX, positionY, zoom);
        enemyManager.render(graphics, positionX, positionY, zoom);
        GUI.render(graphics, player);
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
        if(input.isKeyPressed(Input.KEY_W)&&(map.getTile(player.posX, player.posY-1) != TileMap.TileType.STONE)){
            player.posY--;
            turnManager.addEnemyTurns(1);
        }
        if(input.isKeyPressed(Input.KEY_S)&&(map.getTile(player.posX, player.posY+1) != TileMap.TileType.STONE)){
            player.posY++;
            turnManager.addEnemyTurns(1);
        }
        if(input.isKeyPressed(Input.KEY_A)&&(map.getTile(player.posX-1, player.posY) != TileMap.TileType.STONE)){
            player.posX--;
            turnManager.addEnemyTurns(1);
        }
        if(input.isKeyPressed(Input.KEY_D)&&(map.getTile(player.posX+1, player.posY) != TileMap.TileType.STONE)){
            player.posX++;
            turnManager.addEnemyTurns(1);
        }

        positionX = -player.posX*32+384;
        positionY = -player.posY*32+288;
    }
}
