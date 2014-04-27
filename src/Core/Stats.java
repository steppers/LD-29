package Core;

/**
 * Created by Ollie on 27/04/14.
 */
public class Stats {

    public int HP;
    public int maxHP;

    public int Attack;
    public int Defense;
    public int Speed;
    public int Evade;

    public Stats(int HP, int maxHP, int Attack, int Defense, int Speed, int Evade){
        this.HP = HP;
        this.maxHP = maxHP;
        this.Attack = Attack;
        this.Defense = Defense;
        this.Speed = Speed;
        this.Evade = Evade;
    }
}
