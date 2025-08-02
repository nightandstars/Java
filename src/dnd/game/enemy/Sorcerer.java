package dnd.game.enemy;

public class Sorcerer extends Enemy {
    public Sorcerer(){
        super("Sorcerer", 2,9, "Sorcerer");
    }

    @Override
    public String getDescription() {
        return "Bipity Bopity, here's the Sorcerer";
    }
}
