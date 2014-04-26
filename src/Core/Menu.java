package Core;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Created by Ollie on 26/04/14.
 */
public class Menu extends BasicGameState {

    private String Play = "Create New World";
    private String Options = "Options";
    private String About = "About";
    private String Exit = "Quit";

    private enum menuState{
        MAIN,
        OPTIONS,
        ABOUT
    }

    private menuState state = menuState.MAIN;

    public Menu(){

    }

    @Override
    public int getID() {
        return 0;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void render(GameContainer gameContainer, StateBasedGame stateBasedGame, Graphics graphics) throws SlickException {
        switch (state){
            case MAIN:
                graphics.drawString(Play, 10, 10);
                graphics.drawString(Options, 10, 40);
                graphics.drawString(About, 10, 70);
                graphics.drawString(Exit, 10, 100);
                break;
        }
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
