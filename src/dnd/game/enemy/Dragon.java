package dnd.game.enemy;

public class Dragon extends Enemy {
    public Dragon(){
        super("Dragon", 4, 15, "Dragon");
    }

    @Override
    public String getDescription() {
        return "Woosh Woosh, a Dragon appeared over you, take cover!";
    }
}
