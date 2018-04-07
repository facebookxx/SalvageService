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

import tanat.beans.Fishermen;
import tanat.database.DBUtils;
import tanat.database.MySQLConnUtils;



@WebServlet(urlPatterns = { "/fishermenlist" })
public class FishermenList extends HttpServlet {
	
    private static final long serialVersionUID = 1L;
    
    public FishermenList() {
        super();
    }
 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	Connection conn = null;
        String errorString = null;
        List<Fishermen> listfishermen = null;
        
		conn = MySQLConnUtils.getDBConnection();
     
        try {
        	listfishermen = DBUtils.queryFishermen(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        } 

        request.setAttribute("errorString", errorString);
        request.setAttribute("listfishermen", listfishermen);
        
        // проверял, в консоль данные идут
        for (int i=0; i< listfishermen.size(); i++) {
        	System.out.println(listfishermen.get(i).getName());
        	System.out.println(listfishermen.get(i).getSurname());
        	System.out.println(listfishermen.get(i).getNumSave());
        	System.out.println(listfishermen.get(i).getLocation());
        	System.out.println("\n");
        }
         
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/fishermenlist.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
