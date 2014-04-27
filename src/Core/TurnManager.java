package Core;

/**
 * Created by Ollie on 27/04/14.
 */
public class TurnManager {

    private int enemyTurnsLeft = 0;

    public void addEnemyTurns(int turns){
        enemyTurnsLeft += turns;
    }

    public boolean hasTurnsLeft(){
        if(enemyTurnsLeft>0)
            return true;
        else
            return false;
    }

    public void useTurn(){
        enemyTurnsLeft--;
    }

}
