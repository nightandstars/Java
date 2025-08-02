package dnd.game.db;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLBoard {
    private String url = "jdbc:mysql://localhost:3306/dnd?user=root&password=MorganE1740*";
    Connection connection;
    /**
     * Connects to the database
     */
    public MySQLBoard(){
        try{
            connection = DriverManager.getConnection(this.url);
        } catch (SQLException e) {
            System.out.println("Could not connect to Database");
            e.printStackTrace();
        }
    }

    public int createBoard(List<String> jsonCells){
        int boardId = 0;
        try{
            connection.setAutoCommit(false);
            String sql = "INSERT INTO Board VALUES ()";
            PreparedStatement fill = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            fill.executeUpdate();
            ResultSet result = fill.getGeneratedKeys();
            if(result.next()){
                boardId = result.getInt(1);
            }
            fill.close();

            String sql2 = "INSERT INTO Cell (board_id, cell_position, cell_json) VALUES (?, ?, ?)";
            PreparedStatement fill2 = connection.prepareStatement(sql2);
            for(int i = 0; i < jsonCells.size(); i++){
                String cellJson = jsonCells.get(i);
                fill2.setInt(1, boardId);
                fill2.setInt(2, i + 1);
                fill2.setString(3, cellJson);
                fill2.addBatch();
            }
            fill2.executeBatch();
            fill2.close();
            connection.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return boardId;
    }

    public List<String> getBoardById(int boardId) {
        List<String> jsonCells = new ArrayList<>();
        try {
            String sql = "SELECT cell_json FROM Cell WHERE board_id = ? ORDER BY  cell_position";
            PreparedStatement fill = connection.prepareStatement(sql);
            fill.setInt(1, boardId);
            ResultSet result = fill.executeQuery();
            while(result.next()){
                String cellJson = result.getString("cell_json");
                jsonCells.add(cellJson);
            }
            result.close();
            fill.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonCells;
    }

    public Cell getCellType(String json){
        Gson gson = new GsonBuilder().registerTypeAdapterFactory(
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
                .create();
        if(json.contains("\"enemy\"")){
            return gson.fromJson(json, EnemyCell.class);
        } else if (json.contains("\"loot\"")){
            return gson.fromJson(json, LootCell.class);
        }else{
            return gson.fromJson(json, EmptyCell.class);
        }
    }
}
