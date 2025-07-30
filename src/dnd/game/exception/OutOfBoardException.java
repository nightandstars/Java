package dnd.game.exception;

public class OutOfBoardException extends RuntimeException {
    public OutOfBoardException() {
        super("Character cannot go over cell 64!");
    }
}
