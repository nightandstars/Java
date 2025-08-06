package dnd.game.board.cell;

/**
 * Represents an empty cell on the board
 */
public class EmptyCell extends Cell {
    private String type = "empty";

    @Override
    public void getDescription() {
        System.out.println("You found...nothing");
    }
}
