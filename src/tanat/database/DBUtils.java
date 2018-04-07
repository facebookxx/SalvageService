package tanat.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tanat.beans.Fishermen;
import tanat.beans.IceFloe;
import tanat.beans.See;
import tanat.beans.Ship;
 
public class DBUtils {
 
	//выборка всех льдин
    public static List<IceFloe> queryIceFloe(Connection conn) throws SQLException {
        String sql = "SELECT * "
        		+ "FROM ice_floe;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<IceFloe> list = new ArrayList<IceFloe>();
        
//	    Statement statement = conn.createStatement();
//	    ResultSet rs = statement.executeQuery(sql);
      
        while (rs.next()) {
        	int id = rs.getInt("id");
            String name = rs.getString("name_ice_floe");
            int size = rs.getInt("size");
            int numberOfFishermen = rs.getInt("number_of_fishermen");
            
            IceFloe iceFloe = new IceFloe(id, name, size, numberOfFishermen);
            iceFloe.setIdIceFloe(id);
            iceFloe.setSize(size);
            iceFloe.setName(name);
            iceFloe.setNumberOfFishermen(numberOfFishermen);
            list.add(iceFloe);
        }
        return list;
    }
 
    //выборка льдин для топа
    public static List<IceFloe> findIceFloe(Connection conn) throws SQLException {
    	String sql = "SELECT  `name_ice_floe` ,  `size` ,  `number_of_fishermen` "
    			+ "FROM ice_floe ORDER BY  `number_of_fishermen` DESC LIMIT 10;";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<IceFloe> list = new ArrayList<IceFloe>();
        
        int top = 1;
        while (rs.next()) {        	
            String name = rs.getString("name_ice_floe");
            int size = rs.getInt("size");
            int numberOfFishermen = rs.getInt("number_of_fishermen");
            IceFloe iceFloe = new IceFloe(top, name, size, numberOfFishermen);
/*            iceFloe.setIdIceFloe(top);
            iceFloe.setName(name);
            iceFloe.setSize(size);
            iceFloe.setNumberOfFishermen(numberOfFishermen);*/
            list.add(iceFloe);
            top++;
        }
        return list;
    }
    
    //выбор одной льдины
    public static IceFloe findOneIceFloe(Connection conn, int id) throws SQLException {
        String sql = "SELECT `name_ice_floe`, `size`, `number_of_fishermen` FROM `ice_floe` WHERE id = " + id + ";";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        IceFloe iceFloe = null;
      
        while (rs.next()) {
            String name = rs.getString("name_ice_floe");
            int size = rs.getInt("size");
            int numFish = rs.getInt("number_of_fishermen");
            
            iceFloe = new IceFloe(id, name, size, numFish);
            iceFloe.setIdIceFloe(id);
            iceFloe.setName(name);
            iceFloe.setSize(size);
            iceFloe.setNumberOfFishermen(numFish);
        }

        return iceFloe;
    }
    
    //обновление информации про льдины
    public static void updateIceFloe(Connection conn, IceFloe iceFloe) throws SQLException {
    	Statement stm = conn.createStatement();
    	
    	int id = iceFloe.getId();
        String name = iceFloe.getName();
        int size = iceFloe.getSize();
        int numFishermen = iceFloe.getNumberOfFishermen();
    	
        String sql = "UPDATE `ice_floe` SET `name_ice_floe`= '" 
        + name + "',`size`= " + size + ",`number_of_fishermen`= " + numFishermen + " WHERE id = " + id +";";    
        stm.execute(sql);
    }
 
    //добавление льдин
    public static void insertIceFloe(Connection conn, IceFloe iceFloe) throws SQLException {
 
        Statement pstm = conn.createStatement();
        
        String name = iceFloe.getName();
        int size = iceFloe.getSize();
        int num = iceFloe.getNumberOfFishermen();
 
        String sql = "INSERT INTO `ice_floe`(`name_ice_floe`, `size`, `number_of_fishermen`) VALUES ('"
        		+ name + "', " + size + ", " + num + ");";
        
        pstm.execute(sql);
    }
 
    //удаление льдины
    public static void deleteIceFloe(Connection conn, int id) throws SQLException {
    	Statement stm = conn.createStatement();
        String sql = "Delete From ice_floe where id = '" + id + "';";   
        stm.execute(sql);
    }
    
    //выборка кораблей
    public static List<Ship> queryShip(Connection conn) throws SQLException {
        String sql = "SELECT * FROM ships ORDER BY `id`;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Ship> list = new ArrayList<Ship>();
      
        while (rs.next()) {
        	int id = rs.getInt("id");
            String name = rs.getString("name");
            int numPas = rs.getInt("num_passenger");
            boolean location = rs.getBoolean("location");
            
            Ship ship = new Ship(id, name, numPas, location);
            ship.setId(id);
            ship.setName(name);
            ship.setNumPas(numPas);
            ship.setLocation(location);
            list.add(ship);
        }
        return list;
    }  
    
    //выбор одного корабля
    public static Ship findShip(Connection conn, int id) throws SQLException {
        String sql = "SELECT `name`, `num_passenger`, `location` FROM `ships` WHERE id = " + id +";";
 
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        Ship ship = null;
      
        while (rs.next()) {
            String name = rs.getString("name");
            int numPas = rs.getInt("num_passenger");
            boolean location = rs.getBoolean("location");
            
            ship = new Ship(id, name, numPas, location);
            ship.setId(id);
            ship.setName(name);
            ship.setNumPas(numPas);
            ship.setLocation(location);
        }
        return ship;
    }
       
    //добавление коробля
	public static void insertShip(Connection conn, Ship ship) throws SQLException {
        Statement pstm = conn.createStatement();
        
        int id = ship.getId();
        String name = ship.getName();
        int numPas = ship.getNumPas();
        boolean location = false;
        
        String sql = "INSERT INTO `ships`(`id`, `name`, `num_passenger`, `location`) VALUES (" 
        + id + ", '" + name + "', " + numPas + ", " + location + ");";
        
        pstm.execute(sql);
		
	}

    //изменение инфы корабль
    public static void updateShip(Connection conn, Ship ship) throws SQLException {

    	Statement stm = conn.createStatement();
    	
    	int id = ship.getId();
        String temp = ship.getLocation();
        boolean location;
        if (temp.equals("В море")) {
        	location = true;
        } else {
        	location = false;
        }
    	
        String sql = "UPDATE `ships` SET `location`= " + location + " WHERE (id = " + id + ");";    
        stm.execute(sql);
    }
	
    //удаление корабля
    public static void deleteShip(Connection conn, int id) throws SQLException {
        
    	Statement stm = conn.createStatement();
        String sql = "Delete From ships where id = '" + id + "';";   
        stm.execute(sql);
    }
	
    //выборка рыбаков
    public static List<Fishermen> queryFishermen(Connection conn) throws SQLException {
        String sql = "SELECT * "
        		+ "FROM fishermen;";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        List<Fishermen> list = new ArrayList<Fishermen>();
      
        while (rs.next()) {
        	String name = rs.getString("name");
            String surname = rs.getString("surname");
            int numSave = rs.getInt("num_save");
            boolean location = rs.getBoolean("location");
            
            Fishermen fishermen = new Fishermen(name, surname, numSave, location);
            fishermen.setName(name);
            fishermen.setSurname(surname);
            fishermen.setNumPas(numSave);
            fishermen.setLocation(location);
            list.add(fishermen);
        }
        return list;
    } 

    //добавление рыбаков
    public static void insertFishermen(Connection conn, Fishermen fishermen) throws SQLException {
    	 
    	        Statement pstm = conn.createStatement();
    	        
    	        String name = fishermen.getName();
    	        String surname = fishermen.getSurname();
    	        int numSave = fishermen.getNumSave();
    	        boolean location = false;
    	        
    	        String sql = "INSERT INTO `fishermen`(`id_fish`, `name`, `surname`, `num_save`, `location`) VALUES (0, '" 
    	        + name + "', '" + surname + "', " + numSave + ", " + location + ");";
    	        
    	        pstm.execute(sql);
    	    }
    
    //изменение инфы про рыбака
    public static void updateFishermen(Connection conn, Fishermen fishermen, boolean location) throws SQLException {

    	Statement stm = conn.createStatement();
    	
        String name = fishermen.getName();
        int numSave = fishermen.getNumSave();
    	
        String sql = "UPDATE `fishermen` SET `num_save`= " + numSave + ",`location`= " 
        		+ location +" WHERE (name = '" + name + "');";    
        stm.execute(sql);
    }
    
    //удаление рыбака
    public static void deleteFishermen(Connection conn, String name) throws SQLException {
        
    	Statement stm = conn.createStatement();
        String sql = "Delete From fishermen where name = '" + name + "';";   
        stm.execute(sql);
    }
    
    //выборка по морю
    public static List<See> querySee(Connection conn) throws SQLException {
        String sqlSelectIseFloe = "SELECT COUNT(*) AS num FROM ice_floe;";
        String sqlSelectShip = "SELECT COUNT(*) AS num FROM ships WHERE location = 1;";
        String sqlSelectFishermen = "SELECT COUNT(*) AS num FROM fishermen WHERE location = 1;";
        String sqlSelectFishIce = "SELECT SUM(number_of_fishermen) AS sum FROM ice_floe;";
        
        List<See> listSee = new ArrayList<See>();
        Statement stm = conn.createStatement();
        ResultSet rs = null;
        
        int numIseFloe = 0;
        int numShip = 0;
        int numFishermen = 0;
        int numFishIce = 0;
        
        String temp;
        
        rs = stm.executeQuery(sqlSelectIseFloe);
        if (rs.next()) {
            temp = rs.getString("num"); // "num" - псевдоним из запроса
            numIseFloe = Integer.parseInt(temp);
        }
        
        rs = stm.executeQuery(sqlSelectShip);
        if (rs.next()) {
            temp = rs.getString("num"); // "num" - псевдоним из запроса
            numShip = Integer.parseInt(temp);
        }
        
        rs = stm.executeQuery(sqlSelectFishermen);
        if (rs.next()) {
            temp = rs.getString("num"); // "num" - псевдоним из запроса
            numFishermen = Integer.parseInt(temp);
        }
        
        rs = stm.executeQuery(sqlSelectFishIce);
        if (rs.next()) {
            temp = rs.getString("sum"); // "sum" - псевдоним из запроса
            numFishIce = Integer.parseInt(temp);
        }
        
          
        See see = new See(numIseFloe, numShip, numFishermen, numFishIce);
        see.setNumIceFloe(numIseFloe);
        see.setNumShip(numShip);
        see.setNumFishermen(numFishermen);
        see.setNumFishIce(numFishIce);
        listSee.add(see);
        
        stm.close();
        return listSee;
    } 

    //добавление статистики
    public static void insertSeeStatic(Connection conn, See see) throws SQLException {
    	 
    	        Statement pstm = conn.createStatement();
    	        
    	        String date = see.getDate();
    	        int numIceFloe = see.getNumIceFloe();
    	        int numShip = see.getNumShip();
    	        int numFishIce = see.getNumFishIce();
    	        int numFishermen = see.getNumFishermen();
    	        
    	        String sql = "INSERT INTO `sea`(`date`, `num_ice_floes`, `num_ships`, `num_fish_ice`, `num_fishermen`) "
    	        		+ "VALUES ('" + date + "', " + numIceFloe + ", " + numShip + ", " + numFishIce + ", " + numFishermen + ")";
    	        
    	        pstm.execute(sql);
    	        
    	        
    	    }
}