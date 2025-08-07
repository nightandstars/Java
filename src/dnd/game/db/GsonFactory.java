package dnd.game.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dnd.game.board.cell.Cell;
import dnd.game.board.cell.EmptyCell;
import dnd.game.board.cell.EnemyCell;
import dnd.game.board.cell.LootCell;
import dnd.game.enemy.*;
import dnd.game.loot.Loot;
import dnd.game.loot.potion.LargePotion;
import dnd.game.loot.potion.SmallPotion;
import dnd.game.loot.spell.Fireball;
import dnd.game.loot.spell.Invisibility;
import dnd.game.loot.spell.Lightning;
import dnd.game.loot.weapon.Bow;
import dnd.game.loot.weapon.Mace;
import dnd.game.loot.weapon.Sword;

public class GsonFactory {
    private static final Gson gson = new GsonBuilder().registerTypeAdapterFactory(
                    RuntimeTypeAdapterFactory
                            .of(Loot.class, "type")
                            .registerSubtype(LargePotion.class, "LargePotion")
                            .registerSubtype(SmallPotion.class, "SmallPotion")
                            .registerSubtype(Fireball.class, "Fireball")
                            .registerSubtype(Lightning.class, "Lightning")
                            .registerSubtype(Mace.class, "Mace")
                            .registerSubtype(Sword.class, "Sword")
                            .registerSubtype(Bow.class, "Bow")
                            .registerSubtype(Invisibility.class, "Invisibility")
            )
            .registerTypeAdapterFactory(
                    RuntimeTypeAdapterFactory
                            .of(Enemy.class, "type")
                            .registerSubtype(Goblin.class, "Goblin")
                            .registerSubtype(Sorcerer.class, "Sorcerer")
                            .registerSubtype(Dragon.class, "Dragon")
                            .registerSubtype(Orc.class, "Orc")
                            .registerSubtype(EvilSpirit.class, "EvilSpirit")
            )
            .registerTypeAdapterFactory(
                    RuntimeTypeAdapterFactory.of(Cell.class, "type")
                            .registerSubtype(EmptyCell.class, "empty")
                            .registerSubtype(EnemyCell.class, "enemy")
                            .registerSubtype(LootCell.class, "loot")
            )
            .create();

    public static Gson getGson() {
        return gson;
    }
}

