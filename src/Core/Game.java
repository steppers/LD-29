package Core;

import org.lwjgl.opengl.Display;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 *
 * @author Ollie
 */
public class Game extends StateBasedGame
{

    static int height = 480;
    static int width = 640;
    static boolean fullscreen = false;
    static boolean showFPS = true;
    static final String title = "LD-29";
    static int fpsLimit = 60;
    
    static final int menu = 0;
    static final int options = 1;
    static final int about = 2;

    static final int levelLoad = 10;
    static final int play = 11;
    
    public Game(){
        super(title);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {
        this.addState(new Menu());
        this.enterState(menu);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Game());
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(showFPS);
        app.setTargetFrameRate(fpsLimit);
        app.setShowFPS(showFPS);
        app.start();
    }
}
