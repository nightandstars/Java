package dnd.game.character;
import java.util.Random;

public class Character {
    private String name;
    private int health;
    private int attack;
    private String equipment;
    private String type;

    public int getHealth(){
        return health;
    }

    public int getAttack(){
        return attack;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public String toString(){
        return name + " is a " + type + " with " + health + " health and an attack of " + attack;
    }
}
