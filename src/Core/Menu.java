package Core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Ollie on 26/04/14.
 */
public class Menu extends BasicGameState {

    private String Play = "Play";
    private String Options = "Options";
    private String About = "About";
    private String Exit = "Quit";

    private Image Background;
    private Image BackgroundTop;
    private float scrollPos = 0;
    private float scrollTopPos = 0;

    private enum menuState{
        MAIN,
        OPTIONS,
        ABOUT
    }

    private menuState state = menuState.MAIN;

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        Background = new Image("res/tex/Background.png");
        BackgroundTop = new Image("res/tex/BackgroundTop.png");
    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        Background.draw(0 - scrollPos, 0, 1);
        Background.draw(640 - scrollPos, 0, 1);
        BackgroundTop.draw(0 - scrollTopPos, 0, 1);
        BackgroundTop.draw(640 - scrollTopPos, 0, 1);
        switch (state){
            case MAIN:
                graphics.drawString(Play, 10, 40);
                graphics.drawString(Options, 10, 70);
                graphics.drawString(About, 10, 100);
                graphics.drawString(Exit, 10, 130);
                break;
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        scrollTopPos += (1 * i) /20.5f;
        scrollPos += (1 * i) /50.5f;
        if(scrollPos > 640)
            scrollPos -= 640;
        if(scrollTopPos > 640)
            scrollTopPos -= 640;
    }
}
