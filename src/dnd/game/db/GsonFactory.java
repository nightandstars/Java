package dnd.game.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSerializer;
import dnd.game.board.cell.*;
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
import dnd.game.npc.Arcanist;
import dnd.game.npc.Blacksmith;
import dnd.game.npc.Merchant;
import dnd.game.npc.Npc;

public class GsonFactory {
    private static Gson gson = new GsonBuilder().registerTypeAdapterFactory(
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
                            .registerSubtype(NpcCell.class, "npc")
            )
            .registerTypeAdapterFactory(
                    RuntimeTypeAdapterFactory
                            .of(Npc.class, "type")
                            .registerSubtype(Merchant.class, "Merchant")
                            .registerSubtype(Blacksmith.class, "Blacksmith")
                            .registerSubtype(Arcanist.class, "Arcanist")
            )
            .registerTypeAdapter(ThreadLocal.class, (JsonSerializer<ThreadLocal<?>>) (src, typeOfSrc, context) -> null)
            .create();

    public static Gson getGson() {
        return gson;
    }
}

