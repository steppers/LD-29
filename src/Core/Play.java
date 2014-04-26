package Core;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.ArrayList;

/**
 * Created by Ollie on 26/04/14.
 */
public class Play extends BasicGameState {

    private Image Background;
    private Image BackgroundTop;
    private float scrollPos = 0;
    private float scrollTopPos = 0;
    private float scrollGroundPos = 0;

    private ArrayList<Rectangle> blocks = new ArrayList<Rectangle>();

    private Color block, outline;

    @Override
    public int getID() {
        return 11;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Background = new Image("res/tex/Background.png");
        BackgroundTop = new Image("res/tex/BackgroundTop.png");
        blocks.add(new Rectangle(0, 400, 640, 80));
        block = new Color(0.5f, 0, 0);
        outline = new Color(0.7f, 0.4f, 0.4f);
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Background.draw(0 - scrollPos, 0, 1);
        Background.draw(640 - scrollPos, 0, 1);

        for(Rectangle r : blocks){
            graphics.setColor(block);
            graphics.fillRect(r.getX() - scrollGroundPos, r.getY(), r.getWidth(), r.getHeight());
            graphics.setColor(outline);
            graphics.setLineWidth(6);
            graphics.drawRect(r.getX() - scrollGroundPos, r.getY(), r.getWidth(), r.getHeight());
        }

        BackgroundTop.draw(0 - scrollTopPos, 0, 1);
        BackgroundTop.draw(640 - scrollTopPos, 0, 1);

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();

        if(input.isKeyPressed(Input.KEY_ESCAPE))
            stateBasedGame.enterState(0);

        scrollTopPos += (1 * i) /10.5f;
        scrollPos += (1 * i) /20.5f;
        scrollGroundPos += (1 * i) /15.5f;
        if(scrollPos > 640)
            scrollPos -= 640;
        if(scrollTopPos > 640)
            scrollTopPos -= 640;
    }
}
