package Core;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Ollie on 26/04/14.
 */
public class Play extends BasicGameState {

    private TileMap map;
    private float positionX, positionY, zoom;

    @Override
    public int getID() {
        return 11;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        zoom = 2;
        map = new TileMap(20, 20, 16);
        TileMap.TileType[][] tiles = new TileMap.TileType[20][20];
        for(int x = 0; x < 20; x++){
            for(int y = 0; y < 20; y++){
                tiles[x][y] = TileMap.TileType.EMPTY;
            }
        }
        map.setTiles(tiles);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        map.render(graphics, positionX, positionY, zoom);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        if(input.isKeyPressed(Input.KEY_ESCAPE))
            stateBasedGame.enterState(0);

        if(input.isKeyDown(Input.KEY_W))
            positionY += 3.5f * i/10;
        if(input.isKeyDown(Input.KEY_S))
            positionY -= 3.5f * i/10;
        if(input.isKeyDown(Input.KEY_A))
            positionX += 3.5f * i/10;
        if(input.isKeyDown(Input.KEY_D))
            positionX -= 3.5f * i/10;

        zoom += Mouse.getDWheel()*i/1000;
    }
}
