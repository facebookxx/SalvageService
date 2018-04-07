package tanat.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tanat.beans.IceFloe;
import tanat.beans.See;
import tanat.beans.Ship;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;



@WebServlet(urlPatterns = { "/see" })
public class SeeList extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    public SeeList() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
	Connection conn = null;
        String errorString = null;
        List<See> listsee = null;
        
		conn = MySQLConnUtils.getDBConnection();
     
        try {
        	listsee = DBUtils.querySee(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 

        request.setAttribute("errorString", errorString);
        request.setAttribute("listsee", listsee);
                 
        // Forward к /views/productListView.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/see.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      
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

        int numIceFloe = Integer.parseInt(request.getParameter("numIceFloe"));
        int numShip = Integer.parseInt(request.getParameter("numShip"));
        int numFishIce = Integer.parseInt(request.getParameter("numFishIce"));
        int numFishermen = Integer.parseInt(request.getParameter("numFishermen"));
        
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow  = new SimpleDateFormat("HH:mm:ss a E dd.MM.yyyy");
        String date = String.valueOf(formatForDateNow.format(dateNow));
        
        See see = new See(date, numIceFloe, numShip, numFishermen, numFishIce);
        
        try {
            DBUtils.insertSeeStatic(conn, see);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect(request.getContextPath() + "/see");
    }
}
