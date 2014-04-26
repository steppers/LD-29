package Core;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Ollie on 26/04/14.
 */
public class Menu extends BasicGameState {

    private String Play = "Play";
    private Rectangle playRect = new Rectangle(10, 40, 80, 15);
    private String Options = "Options";
    private Rectangle optionsRect = new Rectangle(10, 70, 80, 15);
    private String About = "About";
    private Rectangle aboutRect = new Rectangle(10, 100, 80, 15);
    private String Exit = "Quit";
    private Rectangle quitRect = new Rectangle(10, 130, 80, 15);

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
            case OPTIONS:
                graphics.drawString("Currently no options available :(", 10, 40);
                graphics.drawString("<- Back", 10, 130);
                break;
            case ABOUT:
                graphics.drawString("Created by Steppers for the Ludumdare-29 48-hour competition.", 10, 40);
                graphics.drawString("<- Back", 10, 130);
                break;
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        Input input = gameContainer.getInput();
        Vector2f mousePos = new Vector2f(input.getMouseX(), input.getMouseY());

        switch (state){
            case MAIN:
                if(input.isMousePressed(0)){
                    if(playRect.contains(mousePos.x, mousePos.y))
                        stateBasedGame.enterState(11);
                    if(optionsRect.contains(mousePos.x, mousePos.y))
                        state = menuState.OPTIONS;
                    if(aboutRect.contains(mousePos.x, mousePos.y))
                        state = menuState.ABOUT;
                    if(quitRect.contains(mousePos.x, mousePos.y))
                        gameContainer.exit();
                }
                break;
            case OPTIONS:
                if(input.isMousePressed(0)){
                    if(quitRect.contains(mousePos.x, mousePos.y))
                        state = menuState.MAIN;
                }
                break;
            case ABOUT:
                if(input.isMousePressed(0)){
                    if(quitRect.contains(mousePos.x, mousePos.y))
                        state = menuState.MAIN;
                }
                break;
        }

        scrollTopPos += (1 * i) /20.5f;
        scrollPos += (1 * i) /50.5f;
        if(scrollPos > 640)
            scrollPos -= 640;
        if(scrollTopPos > 640)
            scrollTopPos -= 640;
    }
}
