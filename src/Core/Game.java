package Core;

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
    static final String title = "Slick2D Template";
    static int fpsLimit = 60;
    
    static final int menu = 0;
    
    public Game(String title){
        super(title);
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException
    {

    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SlickException
    {
        AppGameContainer app = new AppGameContainer(new Game(title));
        app.setDisplayMode(width, height, fullscreen);
        app.setSmoothDeltas(showFPS);
        app.setTargetFrameRate(fpsLimit);
        app.setShowFPS(showFPS);
        app.start();
    }
}
