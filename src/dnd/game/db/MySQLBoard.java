package dnd.game.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import dnd.game.board.cell.Cell;
import dnd.game.board.cell.EmptyCell;
import dnd.game.board.cell.EnemyCell;
import dnd.game.board.cell.LootCell;
import dnd.game.enemy.Dragon;
import dnd.game.enemy.Enemy;
import dnd.game.enemy.Goblin;
import dnd.game.enemy.Sorcerer;
import dnd.game.loot.Loot;
import dnd.game.loot.potion.LargePotion;
import dnd.game.loot.potion.SmallPotion;
import dnd.game.loot.spell.Fireball;
import dnd.game.loot.spell.Lightning;
import dnd.game.loot.weapon.Mace;
import dnd.game.loot.weapon.Sword;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBoard {
    private String url = "jdbc:mysql://localhost:3306/dnd?user=root&password=MorganE1740*";
    Connection connection;
    Gson gsonBuilder = new GsonBuilder().registerTypeAdapterFactory(
                    RuntimeTypeAdapterFactory
                            .of(Loot.class, "type")
                            .registerSubtype(LargePotion.class, "LargePotion")
                            .registerSubtype(SmallPotion.class, "SmallPotion")
                            .registerSubtype(Fireball.class, "FireballSpell")
                            .registerSubtype(Lightning.class, "LightningSpell")
                            .registerSubtype(Mace.class, "MaceWeapon")
                            .registerSubtype(Sword.class, "SwordWeapon")
            )
            .registerTypeAdapterFactory(
                    RuntimeTypeAdapterFactory
                            .of(Enemy.class, "type")
                            .registerSubtype(Goblin.class, "Goblin")
                            .registerSubtype(Sorcerer.class, "Sorcerer")
                            .registerSubtype(Dragon.class, "Dragon")
            )
            .registerTypeAdapterFactory(
                    RuntimeTypeAdapterFactory.of(Cell.class, "type")
                            .registerSubtype(EmptyCell.class, "empty")
                            .registerSubtype(EnemyCell.class, "enemy")
                            .registerSubtype(LootCell.class, "loot")
            )
            .create();

    /**
     * Connects to the database
     */
    public MySQLBoard() {
        try {
            connection = DriverManager.getConnection(this.url);
        } catch (SQLException e) {
            System.out.println("Could not connect to Database");
            e.printStackTrace();
        }
    }

    public Gson getGsonBuilder() {
        return this.gsonBuilder;
    }

    public int createBoard(List<Cell> jsonCells) {
        int boardId = 0;
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO Board (board_json) VALUES (?)";
            PreparedStatement fill = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            String boardJson = gsonBuilder.toJson(jsonCells);
            fill.setString(1, boardJson);
            fill.executeUpdate();
            ResultSet result = fill.getGeneratedKeys();
            if (result.next()) {
                boardId = result.getInt(1);
            }
            result.close();
            fill.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ignored) {
            }
        }
        return boardId;
    }

    public List<Cell> loadBoard(int boardId) {
        try {
            String sql = "SELECT board_json FROM Board WHERE id = ?";
            PreparedStatement fill = connection.prepareStatement(sql);
            fill.setInt(1, boardId);
            ResultSet result = fill.executeQuery();
            if (result.next()) {
                String json = result.getString("board_json");
                Type type = new TypeToken<ArrayList<Cell>>() {
                }.getType();
                return gsonBuilder.fromJson(json, type);
            }
            result.close();
            fill.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void updateBoard(int boardId, List<Cell> cells){
        try{
            String json = gsonBuilder.toJson(cells);
            PreparedStatement fill = connection.prepareStatement("UPDATE Board SET board_json = ? WHERE id = ?");
            fill.setString(1, json);
            fill.setInt(2, boardId);
            fill.executeUpdate();
            fill.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public String getBoardById(int boardId) {
        String boardJson = null;
        try {
            String sql = "SELECT board_json FROM Board WHERE id = ?";
            PreparedStatement fill = connection.prepareStatement(sql);
            fill.setInt(1, boardId);
            ResultSet result = fill.executeQuery();
            if (result.next()) {
                boardJson = result.getString("board_json");
            }
            result.close();
            fill.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardJson;
    }


}
