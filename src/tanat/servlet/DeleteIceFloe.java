package tanat.servlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tanat.beans.IceFloe;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;

public class DeleteIceFloe {
	
	public static void deleteIceFloe (String nameIce, int numPas) throws SQLException {
    	Connection conn = null;
		try {
			conn = MySQLConnUtils.getMySQLConnection();
		} catch (ClassNotFoundException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Автоматически созданный блок catch
			e1.printStackTrace();
		}
		
        String sql = "SELECT `id`, `number_of_fishermen` FROM `ice_floe` WHERE name_ice_floe = '" + nameIce + "';";
        
        PreparedStatement pstm = conn.prepareStatement(sql);
        ResultSet rs = pstm.executeQuery();
        IceFloe iceFloe = null;
      
        int id = 0;
        int numFish = 0;
        while (rs.next()) {
        	id = rs.getInt("id");
        	numFish = rs.getInt("number_of_fishermen");
        }

        int resultNumFish = numFish - numPas;
        
        System.out.println("---------------------");
        System.out.println(id);
        System.out.println(numFish + "-" + numPas + "=" + resultNumFish);
        System.out.println("---------------------");
        
        if (resultNumFish <= 0) {
        	DBUtils.deleteIceFloe(conn, id);
        } else {
        	sql = "UPDATE `ice_floe` SET `number_of_fishermen`= " + resultNumFish + " WHERE id = " + id +";";    
        	pstm.execute(sql);
        }
	}
}
