package dnd.game.board.cell;

import dnd.game.character.Character;

public abstract class Cell{

    public abstract void getDescription();

    public void interact(Character character, int playerPosition ){};
}
