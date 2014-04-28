package Items.Weapons;

import Items.Item;
import Items.Weapons.Weapon;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Ollie on 27/04/14.
 */
public class Sword extends Weapon {

    public Sword(int HP, int maxHP, int Attack, int Defense, int Speed, int Evade) {
        super(HP, maxHP, Attack, Defense, Speed, Evade);
        id = Item.Sword;
        try{
            image = new Image("res/tex/ItemsTiles.png").getSubImage(0, 0, 16, 16);
            image.setFilter(Image.FILTER_NEAREST);
        }catch(SlickException e){
            System.err.println("Error: Cannot load ItemTiles.png");
        }
    }
}
