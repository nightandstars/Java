package dnd.game.enemy;

public class Enemy {
    private String name;
    private int attack;
    private int health;
    private String type;
    private int armorClass;

    public Enemy(String name, int attack, int health, String type, int armorClass){
        this.name = name;
        this.attack = attack;
        this.health = health;
        this.type = type;
        this.armorClass = armorClass;
    }
    public String getName(){
        return name;
    };

    public void setArmorClass(int armorClass) {
        this.armorClass = armorClass;
    }

    public int getArmorClass() {
        return armorClass;
    }

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
