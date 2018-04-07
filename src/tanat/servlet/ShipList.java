package tanat.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
 
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import tanat.beans.Ship;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;



@WebServlet(urlPatterns = { "/shiplist" })
public class ShipList extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    public ShipList() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
   //     Connection conn = MyUtils.getStoredConnection(request);
	Connection conn = null;
        String errorString = null;
        List<Ship> listship = null;
        
		conn = MySQLConnUtils.getDBConnection();
     
        try {
        	listship = DBUtils.queryShip(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 
        // Сохранить информацию в request attribute перед тем как forward к views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("listship", listship);
        
        // проверял, в консоль данные идут
        for (int i=0; i< listship.size(); i++) {
        	System.out.println(listship.get(i).getId());
        	System.out.println(listship.get(i).getName());
        	System.out.println(listship.get(i).getNumPas());
        	System.out.println(listship.get(i).getLocation());
        	System.out.println("\n");
        }
         
        // Forward к /views/productListView.jsp
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/ship.jsp");
        // и так пробывал
//      RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/views/iceFloeListView.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
