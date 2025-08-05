package dnd.game.enemy;

public class Goblin extends Enemy {
    public Goblin(){
        super("Goblin", 1, 6, "Goblin", 10);
    }

    @Override
    public String getDescription() {
        return "Oh no! An ugly Goblin appeared";
    }
}
