package dnd.game.board.cell;

public class EmptyCell extends Cell {
    @Override
    public void getDescription() {
        System.out.println("You found...nothing");
    }
}
