package dnd.game.db;

import dnd.game.character.Character;

import java.sql.*;

public class MySQL {
    private String url = "jdbc:mysql://localhost:3306/dnd?user=root&password=MorganE1740*";
    Statement query = null;
    ResultSet result = null;
    Connection connection;
    /**
     * Connects to the database
     */
    public MySQL(){
        try{
            connection = DriverManager.getConnection(this.url);
            System.out.println("Connected to Database");
        } catch (SQLException e) {
            System.out.println("Could not connect to Database");
            e.printStackTrace();
        }
    }

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

                System.out.println("ID: " + id
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
    public void getLoot(){
        try{
            query = connection.createStatement();
            result = query.executeQuery("SELECT* FROM Loot");
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String type = result.getString("type");
                int effect = result.getInt("effect");

                System.out.println("ID: " + id
                        + ", Name: " + name
                        + ", Type: " + type
                        + ", Effect: " + effect);
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

   public void createHero(Character character, String name){
        String type = character.getType();
        int health = character.getHealth();
        int attack = character.getAttack();
        try{
            String sql = "INSERT INTO Characters (name, type, health, attack) VALUES (?, ?, ?, ?)";
            PreparedStatement fill = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            fill.setString(1, name);
            fill.setString(2, type);
            fill.setInt(3, health);
            fill.setInt(4, attack);
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

   public void editHero(Character character, String name){
        try{
            String sql = "UPDATE Characters SET name = ? WHERE id = ? ";
            PreparedStatement fill = connection.prepareStatement(sql);
            fill.setString(1, name);
            fill.setInt(2, character.getId());
            fill.executeUpdate();
            fill.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
   }

   public void changeHealth(Character character){
       try{
           String sql = "UPDATE Characters SET health = ? WHERE id = ? ";
           PreparedStatement fill = connection.prepareStatement(sql);
           fill.setInt(1, character.getHealth());
           fill.setInt(2, character.getId());
           fill.executeUpdate();
           fill.close();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

}
