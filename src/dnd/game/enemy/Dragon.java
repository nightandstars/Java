package dnd.game.enemy;

public class Dragon extends Enemy {
    public Dragon(){
        super("Dragon", 4, 15, "Dragon", 15);
    }

    @Override
    public String getDescription() {
        return "Woosh Woosh, a Dragon appeared over you, take cover!";
    }
}
