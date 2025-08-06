package dnd.game.db;

import dnd.game.Menu;
import dnd.game.character.Character;
import dnd.game.character.Warrior;
import dnd.game.character.Wizard;

import java.sql.*;

/**
 * Represents and allows interaction with a Characters table in MySQL DB
 */
public class MySQLHero {
    private String url = "jdbc:mysql://localhost:3306/dnd?user=root&password=MorganE1740*";
    private Statement query = null;
    private ResultSet result = null;
    private Connection connection;
    private Menu menu = null;

    /**
     * Connects to the database
     */
    public MySQLHero(Menu menu){
        try{
            connection = DriverManager.getConnection(this.url);
            this.menu = menu;
        } catch (SQLException e) {
            System.out.println("Could not connect to Database");
            e.printStackTrace();
        }
    }

    /**
     * Shows all characters saved in the DB
     */
    public void getCharacters(){
        try{
            query = connection.createStatement();
            result = query.executeQuery("SELECT* FROM Characters");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String type = result.getString("type");
                int health = result.getInt("health");
                int attack = result.getInt("attack");
                int offensiveLootId = result.getInt("offensive_loot_id");
                int defensiveLootId = result.getInt("defensive_loot_id");

                Menu.showMessage("ID: " + id
                        + ", Name: " + name
                        + ", Type: " + type
                        + ", Health: " + health
                        + ", Attack: " + attack
                        + ", Offensive Loot ID: " + offensiveLootId
                        + ", Defensive Loot ID: " + defensiveLootId);
            }
            result.close();
            query.close();
            connection.close();

        }catch (SQLException ex){
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }

    /**
     * Saves a new character inside the DB
     * @param character that has been created and all its stats
     * @param name of the character chosen by the player
     */
   public void createHero(Character character, String name){
        try{
            String sql = "INSERT INTO Characters (name, type, health, attack, armor_class) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement fill = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            fill.setString(1, name);
            fill.setString(2, character.getType());
            fill.setInt(3, character.getHealth());
            fill.setInt(4, character.getAttack());
            fill.setInt(4, character.getArmorClass());
            fill.executeUpdate();

            ResultSet result = fill.getGeneratedKeys();
            if(result.next()){
                int id = result.getInt(1);
                character.setId(id);
            }
            fill.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }

    /**
     * Changes the name of the character saved in the DB
     * @param character to be changed
     * @param name new name to be assigned
     */
   public void editHero(Character character, String name){
        try{
            String sql = "UPDATE Characters SET name = ? WHERE id = ? ";
            PreparedStatement fill = connection.prepareStatement(sql);
            fill.setString(1, name);
            fill.setInt(2, character.getId());
            fill.executeUpdate();
            fill.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
   }

    /**
     * At the end of a game, saves the character's current status to the DB
     * @param character being played
     */
   public void updateHero(Character character){
       try{
           String sql = "UPDATE Characters SET attack = ?, health = ? WHERE id = ?";
           PreparedStatement fill = connection.prepareStatement(sql);
           fill.setInt(1, character.getAttack());
           fill.setInt(2, character.getHealth());
           fill.setInt(3, character.getId());
           fill.executeUpdate();
           fill.close();
       }catch(SQLException e){
           e.printStackTrace();
       }
   }

    /**
     * Loads a character present in the DB and all its corresponding stats
     * @param characterId character to be loaded
     */
   public void loadCharacter(int characterId){
        try{
            String sql = "SELECT * FROM Characters WHERE id = ?";
            PreparedStatement fill = connection.prepareStatement(sql);
            fill.setInt(1, characterId);
            result = fill.executeQuery();
            if(result.next()){
                String name = result.getString("name");
                String type = result.getString("type");
                int health = result.getInt("health");
                int attack = result.getInt("attack");
//                int offensiveLootId = result.getInt("offensive_loot_id");
//                int defensiveLootId = result.getInt("defensive_loot_id");
                Character character = switch (type) {
                    case "Warrior" -> new Warrior(name, attack, health, characterId);
                    case "Wizard" -> new Wizard(name, attack, health, characterId);
                    default -> null;
                };
                menu.setChosenCharacter(character);
            }
            result.close();
            fill.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
   }

}
