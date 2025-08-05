package dnd.game.enemy;

public class Enemy {
    private String name;
    private int attack;
    private int health;
    private String type;

    public Enemy(String name, int attack, int health, String type){
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.type = type;
    }
    public String getName(){
        return name;
    };

    public int getAttack(){
        return attack;
    };

    public int getHealth(){
        return health;
    };

    public void setHealth(int health){
        this.health = health;
    };

    public String getDescription(){
        return "Oh no, an enemy!";
    };

    public String getType() {
        return type;
    }

}
